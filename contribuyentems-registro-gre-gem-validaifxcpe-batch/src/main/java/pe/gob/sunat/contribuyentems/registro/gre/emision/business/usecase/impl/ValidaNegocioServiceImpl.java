package pe.gob.sunat.contribuyentems.registro.gre.emision.business.usecase.impl;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import pe.gob.sunat.contribuyentems.registro.gre.basebdgre.comprobante.domain.*;
import pe.gob.sunat.contribuyentems.registro.gre.basebdgre.util.ConexionException;
import pe.gob.sunat.contribuyentems.registro.gre.basebdgre.util.EnumSeg;
import pe.gob.sunat.contribuyentems.registro.gre.basebdgre.util.GreConstantes;
import pe.gob.sunat.contribuyentems.registro.gre.basebdgre.util.ProcesoException;
import pe.gob.sunat.contribuyentems.registro.gre.emision.business.usecase.ValidaNegocioService;
import pe.gob.sunat.contribuyentems.registro.gre.emision.domain.dao.T130detamuComprobanteFisico;
import pe.gob.sunat.contribuyentems.registro.gre.emision.domain.dao.T4241cabcpeComprobanteElectronico;
import pe.gob.sunat.contribuyentems.registro.gre.emision.domain.dao.T9432conscpcontinComprobanteContingencia;
import pe.gob.sunat.contribuyentems.registro.gre.emision.infrastructure.dataprovider.broker.kafka.producer.KafkaProducerProcess;
import pe.gob.sunat.contribuyentems.registro.gre.emision.infrastructure.entrypoint.vo.MensajeEnvioAceptadoRechazadoVO;
import pe.gob.sunat.contribuyentems.registro.gre.emision.infrastructure.entrypoint.vo.ObservacionNegocioVO;
import pe.gob.sunat.contribuyentems.registro.gre.emision.shared.util.ConstantesUtils;
import pe.gob.sunat.contribuyentems.registro.gre.emision.shared.util.ValidationException;
import pe.gob.sunat.contribuyentems.registro.gre.emision.shared.util.ValidatorParams;
import pe.gob.sunat.contribuyentems.registro.gre.shared.utils.utils.ValidacionUtil;
import pe.gob.sunat.tecnologiams.arquitectura.framework.microservices.util.UtilLog;

public class ValidaNegocioServiceImpl implements ValidaNegocioService {

	@Inject
	private UtilLog utilLog;

	@Inject
	private ValidatorParams validatorParams;

	@Inject
	private KafkaProducerProcess kafkaProducerProcess;

	@Inject
	private T4241cabcpeComprobanteElectronico t4241cabcpeComprobanteElectronico;

	@Inject
	private T130detamuComprobanteFisico t130detamuComprobanteFisico;
	
	@Inject
	private T9432conscpcontinComprobanteContingencia t9432conscpcontinComprobanteContingencia;

	@Override
	public synchronized void validaNegocio(String mensajeRecibido) 
			throws Exception, ValidationException {

		validatorParams = new ValidatorParams();
		utilLog.imprimirLogKibana(ConstantesUtils.LEVEL_INFO, "INI - Validaciones de Negocio");
		utilLog.imprimirLogKibana(ConstantesUtils.LEVEL_DEBUG, "Json Entrada : " + mensajeRecibido);
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		MensajeEnvioAceptadoRechazadoVO mensaje = new MensajeEnvioAceptadoRechazadoVO();

		try {
			mensaje = mapper.readValue(mensajeRecibido, MensajeEnvioAceptadoRechazadoVO.class);
			if (ConstantesUtils.COD_TIPO_CPE_REMITENTE.equals(mensaje.getComprobanteGre().getCodCpe())) {
				utilLog.imprimirLogKibana(ConstantesUtils.LEVEL_DEBUG, "INI - Validaciones Remitente");
				validacionesNegocioRemitente(mensaje.getComprobanteGre());
				utilLog.imprimirLogKibana(ConstantesUtils.LEVEL_DEBUG, "FIN - Validaciones Remitente");

			} else if (ConstantesUtils.COD_TIPO_CPE_TRANSPORTISTA.equals(mensaje.getComprobanteGre().getCodCpe())) {
				utilLog.imprimirLogKibana(ConstantesUtils.LEVEL_DEBUG, "INI - Validaciones de Transportista");
				validacionesNegocioTransportista(mensaje.getComprobanteGre());
				utilLog.imprimirLogKibana(ConstantesUtils.LEVEL_DEBUG, "FIN - Validaciones de Transportista");
			}
			List<ObservacionNegocioVO> obs = mensaje.getObservacionesNegocio();
			if(!ValidacionUtil.esEmpty(validatorParams.getlstObsNegocio())) {
				obs.addAll(validatorParams.getlstObsNegocio());
			}
			mensaje.setObservacionesNegocio(obs);
			mensaje.setListaSeguimiento(actualizarSeguimiento(mensaje.getListaSeguimiento(),true));
			String mensajeValidaInicial = mapper.writeValueAsString(mensaje);
			Seguimiento seg = obtenerTopicoaProducirMsg(mensaje.getListaSeguimiento());
			if(seg!=null) {
				String validacion = EnumSeg.byMs(seg.getMs()).getKey();
				kafkaProducerProcess.enviarMensajeKafkaEnvioValidaciones(mensajeValidaInicial, validacion);
				utilLog.imprimirLogKibana(ConstantesUtils.LEVEL_INFO, "FIN - Validaciones de Negocio - Mensaje " + validacion);
			} else {
				kafkaProducerProcess.enviarMensajeKafkaEnvioAceptado(mensajeValidaInicial);
				utilLog.imprimirLogKibana(ConstantesUtils.LEVEL_INFO, "FIN - Validaciones de Negocio - Mensaje Aceptado");
			}
		} catch (ValidationException v) {
			mensaje.setIndValidacion(ConstantesUtils.IND_VALIDACION_ERROR);
			mensaje.setProcedencia(ConstantesUtils.MS_PROCEDENCIA_VALIDAIFXCPE);
			mensaje.setError(validatorParams.getErrorMsg());
			mensaje.setListaSeguimiento(actualizarSeguimiento(mensaje.getListaSeguimiento(),false));
			String mensajeValidaInicial = mapper.writeValueAsString(mensaje);
			kafkaProducerProcess.enviarMensajeKafkaEnvioRechazado(mensajeValidaInicial);
			utilLog.imprimirLogKibana(ConstantesUtils.LEVEL_INFO, "FIN - Validaciones de Negocio - Mensaje Rechazado");
		} catch (Exception e) {
			StringWriter printStackTrace = new StringWriter();
			e.printStackTrace(new PrintWriter(printStackTrace));
			utilLog.imprimirLog(ConstantesUtils.LEVEL_ERROR, "Error Validaciones de Negocio : " + printStackTrace.toString(),
					Thread.currentThread().getStackTrace());
			
			validatorParams.validarMensaje0200(false,false);
			mensaje.setIndValidacion(ConstantesUtils.IND_VALIDACION_ERROR);
			mensaje.setProcedencia(ConstantesUtils.MS_PROCEDENCIA_VALIDAIFXCPE);
			mensaje.setError(validatorParams.getErrorMsg());
			mensaje.setListaSeguimiento(actualizarSeguimiento(mensaje.getListaSeguimiento(),false));
			String mensajeValidaInicial = mapper.writeValueAsString(mensaje);
			kafkaProducerProcess.enviarMensajeKafkaEnvioRechazado(mensajeValidaInicial);
			utilLog.imprimirLogKibana(ConstantesUtils.LEVEL_INFO, "FIN - Validaciones de Negocio - Mensaje Rechazado");
			
			throw e;
		}

	}

	private void validacionesNegocioRemitente(Comprobante comprobante) 
			throws ValidationException, Exception{
		try {
			// PAS20231U210700176 si el comprobante es de contingencia
			Boolean isContingencia = ValidacionUtil.esNumerico(comprobante.getNumSerie())? Boolean.TRUE:Boolean.FALSE;

			// Inicio PAS20231U210700176 Validaciones para contingencia
			if (isContingencia) {
				int conscpcontin = t9432conscpcontinComprobanteContingencia.buscarComprobanteContingencia(comprobante.getNumRuc(),
						comprobante.getCodCpe(), Integer.parseInt(comprobante.getNumSerie()) + "",
						comprobante.getNumCpe() + "");
				utilLog.imprimirLogKibana(ConstantesUtils.LEVEL_DEBUG, "Validar 4204");
				validatorParams.validarComprobanteContingenciaAutorizado4204(conscpcontin);
				
				int detmau = t130detamuComprobanteFisico.buscarRangoNumCpe(comprobante.getNumRuc(),
						comprobante.getCodCpe(), Integer.parseInt(comprobante.getNumSerie()) + "",
						comprobante.getNumCpe());
				utilLog.imprimirLogKibana(ConstantesUtils.LEVEL_DEBUG, "Validar 3207");
				validatorParams.validarComprobanteFisico3207(detmau);
			}			
			// Fin 
			if (comprobante.getDocRelacionado() != null) {
				int cont = 0;
				for (DocRelacionado docRelacionado : comprobante.getDocRelacionado()) {
					if (Arrays.asList(ConstantesUtils.COD_COMPROBANTE_ELECTRONICO).contains(docRelacionado.getCodTipoDocumento())) {
						if (!isComprobanteFisico(docRelacionado.getNumSerie())) {
							Integer contador = 0;
							contador = t4241cabcpeComprobanteElectronico.buscarComprobantePagoElectronico(docRelacionado.getNumRuc(),
											docRelacionado.getCodTipoDocumento(), docRelacionado.getNumSerie(),
											Integer.parseInt(docRelacionado.getNumDocumento()));
							if (contador != 0) {
								comprobante.getDocRelacionado().get(cont).setIndEncSunDocRelacionado("1");
							}
							utilLog.imprimirLogKibana(ConstantesUtils.LEVEL_DEBUG, "Validar 4382");
							validatorParams.validarComprobanteElectronico4382(contador);

						}
					}
					if (Arrays.asList(ConstantesUtils.COD_COMPROBANTE_FISICO).contains(docRelacionado.getCodTipoDocumento())) {
						if (isComprobanteFisico(docRelacionado.getNumSerie())) {
							Integer contador = 0;
							contador = t130detamuComprobanteFisico.buscarRangoNumCpe(docRelacionado.getNumRuc(),
											docRelacionado.getCodTipoDocumento(), Integer.parseInt(docRelacionado.getNumSerie()) + "",
											Integer.parseInt(docRelacionado.getNumDocumento()));
							if (contador != 0) {
								comprobante.getDocRelacionado().get(cont).setIndEncSunDocRelacionado("1");
							}
							utilLog.imprimirLogKibana(ConstantesUtils.LEVEL_DEBUG, "Validar 3207");
							validatorParams.validarComprobanteFisicoRelacionado3207(contador, isContingencia, docRelacionado.getNumSerie(), docRelacionado.getNumDocumento());
						}
					}
					cont++;
				}
			}
		} catch (ValidationException v) {
			throw v;
		} catch (Exception e) {
			throw e;
		}
	}

	private void validacionesNegocioTransportista(Comprobante comprobante) 
			throws ValidationException, Exception {
		try {

			// PAS20231U210700176 si el comprobante es de contingencia
			Boolean isContingencia = ValidacionUtil.esNumerico(comprobante.getNumSerie())? Boolean.TRUE:Boolean.FALSE;

			// Inicio PAS20231U210700176 Validaciones para contingencia
			if (isContingencia) {
				int conscpcontin = t9432conscpcontinComprobanteContingencia.buscarComprobanteContingencia(comprobante.getNumRuc(),
						comprobante.getCodCpe(), Integer.parseInt(comprobante.getNumSerie()) + "",
						comprobante.getNumCpe() + "");
				utilLog.imprimirLogKibana(ConstantesUtils.LEVEL_DEBUG, "Validar 4204");
				validatorParams.validarComprobanteContingenciaAutorizado4204(conscpcontin);
				
				int detmau = t130detamuComprobanteFisico.buscarRangoNumCpe(comprobante.getNumRuc(),
						comprobante.getCodCpe(), Integer.parseInt(comprobante.getNumSerie()) + "",
						comprobante.getNumCpe());
				utilLog.imprimirLogKibana(ConstantesUtils.LEVEL_DEBUG, "Validar 3207");
				validatorParams.validarComprobanteFisico3207(detmau);
			}			
			// Fin 
			if (comprobante.getDocRelacionado() != null) {
				int cont = 0;
				for (DocRelacionado docRelacionado : comprobante.getDocRelacionado()) {
					if (Arrays.asList(ConstantesUtils.COD_COMPROBANTE_ELECTRONICO).contains(docRelacionado.getCodTipoDocumento())) {
						if (!isComprobanteFisico(docRelacionado.getNumSerie())) {
							Integer contador = 0;
							contador = t4241cabcpeComprobanteElectronico.buscarComprobantePagoElectronico(docRelacionado.getNumRuc(),
											docRelacionado.getCodTipoDocumento(), docRelacionado.getNumSerie(),
											Integer.parseInt(docRelacionado.getNumDocumento()));
							if (contador != 0) {
								comprobante.getDocRelacionado().get(cont).setIndEncSunDocRelacionado("1");
							}
							utilLog.imprimirLogKibana(ConstantesUtils.LEVEL_DEBUG, "Validar 4382");
							validatorParams.validarComprobanteElectronico4382(contador);

						}
					}
					if (Arrays.asList(ConstantesUtils.COD_COMPROBANTE_FISICO).contains(docRelacionado.getCodTipoDocumento())) {
						if (isComprobanteFisico(docRelacionado.getNumSerie())) {
							Integer contador = 0;
							contador = t130detamuComprobanteFisico.buscarRangoNumCpe(docRelacionado.getNumRuc(),
											docRelacionado.getCodTipoDocumento(), Integer.parseInt(docRelacionado.getNumSerie()) + "",
											Integer.parseInt(docRelacionado.getNumDocumento()));
							if (contador != 0) {
								comprobante.getDocRelacionado().get(cont).setIndEncSunDocRelacionado("1");
							}
							utilLog.imprimirLogKibana(ConstantesUtils.LEVEL_DEBUG, "Validar 3207");
							validatorParams.validarComprobanteFisicoRelacionado3207(contador, isContingencia, docRelacionado.getNumSerie(), docRelacionado.getNumDocumento());
						}
					}
					cont++;
				}
			}

		} catch (ValidationException v) {
			throw v;
		} catch (Exception e) {
			throw e;
		}
	}

	public List<Seguimiento> actualizarSeguimiento(List<Seguimiento> listaSeguimiento, boolean resultado) {
		int cont=0;
		for(Seguimiento seg:listaSeguimiento) {
			if(seg.getMs().equals(EnumSeg.VALIFXCPE.getMs())) {
				listaSeguimiento.get(cont).setEstado(GreConstantes.SEG_ESTADO_PROCESADO);
				listaSeguimiento.get(cont).setResultado(resultado?GreConstantes.SEG_RESULTADO_OK:GreConstantes.SEG_RESULTADO_ERROR);
				break;
			}
			cont++;
		}
		return listaSeguimiento;
	}
	
	public Seguimiento obtenerTopicoaProducirMsg(List<Seguimiento> listaSeguimiento) {
		List<Seguimiento> listaSeguimientoAct = new ArrayList<>();
		listaSeguimientoAct = listaSeguimiento.stream()
		.filter(seg -> GreConstantes.SEG_ESTADO_PENDIENTE.equals(seg.getEstado()))
		.collect(Collectors.toList());
		if(!ValidacionUtil.esEmpty(listaSeguimientoAct)) {
			return listaSeguimientoAct.get(0);
		}
		return null;
	}

	private boolean isComprobanteFisico(String numSerie) {
		try {
			Integer.parseInt(numSerie);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}

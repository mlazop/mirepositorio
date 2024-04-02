package pe.gob.sunat.contribuyentems.registro.gre.emision.shared.util;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import pe.gob.sunat.contribuyentems.registro.gre.basebdFuentesExternas.licenciaconducir.domain.LicenciaConducir;
import pe.gob.sunat.contribuyentems.registro.gre.basebdgre.comprobante.domain.Comprobante;
import pe.gob.sunat.contribuyentems.registro.gre.basebdgre.documentosrelacionados.domain.DocumentosRelacionados;
import pe.gob.sunat.contribuyentems.registro.gre.basebdgre.parametria.domain.ListaParametros;
import pe.gob.sunat.contribuyentems.registro.gre.basebdgre.parametria.domain.Parametria;
import pe.gob.sunat.contribuyentems.registro.gre.emision.infrastructure.entrypoint.vo.ErrorMessageVO;
import pe.gob.sunat.contribuyentems.registro.gre.emision.infrastructure.entrypoint.vo.ObservacionNegocioVO;
import pe.gob.sunat.contribuyentems.registro.gre.shared.utils.utils.ValidacionUtil;

public class ValidatorParams {

	public boolean error = false;
	public ErrorMessageVO errorMsg;
	boolean obs = false;
	public List<ObservacionNegocioVO> lstObsNegocio = new ArrayList<ObservacionNegocioVO>();

	public boolean existErrors() {
		return error;
	}

	public void addErrors(ErrorMessageVO errorMsg) {
		error = true;
		this.errorMsg = errorMsg;
	}

	public ErrorMessageVO getErrorMsg() {
		return errorMsg;
	}

	public boolean existObs() {
		return obs;
	}

	public void addObs(ObservacionNegocioVO obsNegocio) {
		obs = true;
		lstObsNegocio.add(obsNegocio);
	}

	public List<ObservacionNegocioVO> getlstObsNegocio() {
		return lstObsNegocio;
	}

	public void validarFecEmision2108(boolean fecha) throws ValidationException {

		if (!fecha) {
			addErrors(new ErrorMessageVO(EnumErrores.ERROR_2108.getCodigo(),
					String.format(EnumErrores.ERROR_2108.getMensaje())));
			throw new ValidationException();
		}
	}

	public void validarCodEstado2010(String codEstado) throws ValidationException {

		if (!ConstantesUtils.COD_ESTADO_ACTIVO.equals(codEstado)) {
			addErrors(new ErrorMessageVO(EnumErrores.ERROR_2010.getCodigo(),
					String.format(EnumErrores.ERROR_2010.getMensaje())));
			throw new ValidationException();
		}
	}

	public void validarIndCondicion2011(String indCondicion) throws ValidationException {

		if (!ConstantesUtils.IND_CONDICION_ACTIVO.equals(indCondicion)) {
			addErrors(new ErrorMessageVO(EnumErrores.ERROR_2011.getCodigo(),
					String.format(EnumErrores.ERROR_2011.getMensaje())));
			throw new ValidationException();
		}
	}

	public void validarCodMotivoBajaRelacionado2536(String codMotivoBaja) throws ValidationException {

		if (!Arrays.asList(ConstantesUtils.COD_MOTIVO_BAJA).contains(codMotivoBaja)) {
			addErrors(new ErrorMessageVO(EnumErrores.ERROR_2536.getCodigo(),
					String.format(EnumErrores.ERROR_2536.getMensaje())));
			throw new ValidationException();
		}
	}

	public void validarCodMotivoBajaRelacionado2536(boolean existe) throws ValidationException {

		if (!existe) {
			addErrors(new ErrorMessageVO(EnumErrores.ERROR_2536.getCodigo(),
					String.format(EnumErrores.ERROR_2536.getMensaje())));
			throw new ValidationException();
		}

	}

	public void validarComprobanteDocRelacionado3433(Comprobante comprobante) throws ValidationException {

		if (comprobante == null) {
			addErrors(new ErrorMessageVO(EnumErrores.ERROR_3433.getCodigo(),
					String.format(EnumErrores.ERROR_3433.getMensaje())));
			throw new ValidationException();
		}

	}
	
	public void validarComprobanteDocRelacionado3434() throws ValidationException {

			addErrors(new ErrorMessageVO(EnumErrores.ERROR_3434.getCodigo(),
					String.format(EnumErrores.ERROR_3434.getMensaje())));
			throw new ValidationException();
	}

	public void validarCodEstadoDestinatario4373(String codEstado) {

		if (!ConstantesUtils.COD_ESTADO_ACTIVO.equals(codEstado)) {
			addObs(new ObservacionNegocioVO(EnumErrores.ERROR_4373.getCodigo(),
					String.format(EnumErrores.ERROR_4373.getMensaje())));
		}
	}

	public void validarCodDomHabidoDestinatario4374(String indCondicion) {
		// JMV P_SNATI286-4886
		if (!ConstantesUtils.IND_CONDICION_ACTIVO.equals(indCondicion)) {
			addObs(new ObservacionNegocioVO(EnumErrores.ERROR_4374.getCodigo(),
					String.format(EnumErrores.ERROR_4374.getMensaje())));
		}
	}

	public void validarCodEstadoProveedor4051(String codEstado) {

		if (!ConstantesUtils.COD_ESTADO_ACTIVO.equals(codEstado)) {
			addObs(new ObservacionNegocioVO(EnumErrores.ERROR_4051.getCodigo(),
					String.format(EnumErrores.ERROR_4051.getMensaje())));
		}
	}

	public void validarCodDomHabidoProveedor4052(String indCondicion) {

		if (!ConstantesUtils.IND_CONDICION_ACTIVO.equals(indCondicion)) {
			addObs(new ObservacionNegocioVO(EnumErrores.ERROR_4052.getCodigo(),
					String.format(EnumErrores.ERROR_4052.getMensaje())));
		}
	}

	public void validarCodEstadoComprador4379(String codEstado) {

		if (!ConstantesUtils.COD_ESTADO_ACTIVO.equals(codEstado)) {
			addObs(new ObservacionNegocioVO(EnumErrores.ERROR_4379.getCodigo(),
					String.format(EnumErrores.ERROR_4379.getMensaje())));
		}
	}

	public void validarCodDomHabidoComprador4380(String indCondicion) {

		if (!ConstantesUtils.IND_CONDICION_ACTIVO.equals(indCondicion)) {
			addObs(new ObservacionNegocioVO(EnumErrores.ERROR_4380.getCodigo(),
					String.format(EnumErrores.ERROR_4380.getMensaje())));
		}
	}

	public void validarCodEstadoTransportista3349(String codEstado) throws ValidationException {

		if (!ConstantesUtils.COD_ESTADO_ACTIVO.equals(codEstado)) {
			addErrors(new ErrorMessageVO(EnumErrores.ERROR_3349.getCodigo(),
					String.format(EnumErrores.ERROR_3349.getMensaje())));
			throw new ValidationException();
		}
	}

	public void validarCodDomHabidoTransportista3350(String indCondicion) throws ValidationException {

		if (!ConstantesUtils.IND_CONDICION_ACTIVO.equals(indCondicion)) {
			addErrors(new ErrorMessageVO(EnumErrores.ERROR_3350.getCodigo(),
					String.format(EnumErrores.ERROR_3350.getMensaje())));
			throw new ValidationException();
		}
	}

	public void validarNumRegistroMtc4393(Integer numero) {

		if (numero == 0) {
			addObs(new ObservacionNegocioVO(EnumErrores.ERROR_4393.getCodigo(),
					String.format(EnumErrores.ERROR_4393.getMensaje())));
		}
		
//		if (transporteMercaVehiculo == null || transporteMercaVehiculo.getCodPartida() == null
//		    || !(numRegistroMtc.equals(transporteMercaVehiculo.getCodPartida()))) {
//			addObs(new ObservacionNegocioVO(EnumErrores.ERROR_4393.getCodigo(),
//					String.format(EnumErrores.ERROR_4393.getMensaje())));
//		}
	}

	public void validarNumPlaca4398(boolean numPlaca, String numPlacaVehiculo) {
		if (!numPlaca) {
			addObs(new ObservacionNegocioVO(EnumErrores.ERROR_4398.getCodigo(),
					String.format(EnumErrores.ERROR_4398.getMensaje())+", placa: " + numPlacaVehiculo));
		}

	}

	public void validarRegistroMtc4400(boolean numPlaca, String numTucChv) {
		if (!numPlaca) {
			addObs(new ObservacionNegocioVO(EnumErrores.ERROR_4400.getCodigo(),
					String.format(EnumErrores.ERROR_4400.getMensaje(), numTucChv)));
		}

	}

	public void validarNumLicencia4412(LicenciaConducir licenciaConducir, String numLicencia) {
		if (licenciaConducir == null) {
			addObs(new ObservacionNegocioVO(EnumErrores.ERROR_4412.getCodigo(),
					String.format(EnumErrores.ERROR_4412.getMensaje(),numLicencia)));
		}

	}
	
	public void validarCodigoEstablecimientoPartida3366(Boolean existe) throws ValidationException {

		if (!existe) {
			addErrors(new ErrorMessageVO(EnumErrores.ERROR_3366.getCodigo(),
					String.format(EnumErrores.ERROR_3366.getMensaje())));
			throw new ValidationException();
		}

	}
	
	public void validarCodigoEstablecimientoUbigeoPartida3367(Boolean existe) throws ValidationException {

		if (!existe) {
			addErrors(new ErrorMessageVO(EnumErrores.ERROR_3367.getCodigo(),
					String.format(EnumErrores.ERROR_3367.getMensaje())));
			throw new ValidationException();
		}

	}
	
	public void validarCodigoEstablecimientoLlegada3370(Boolean existe) throws ValidationException {

		if (!existe) {
			addErrors(new ErrorMessageVO(EnumErrores.ERROR_3370.getCodigo(),
					String.format(EnumErrores.ERROR_3370.getMensaje())));
			throw new ValidationException();
		}

	}
	
	public void validarCodigoEstablecimientoUbigeoLlegada3371(Boolean existe) throws ValidationException {

		if (!existe) {
			addErrors(new ErrorMessageVO(EnumErrores.ERROR_3371.getCodigo(),
					String.format(EnumErrores.ERROR_3371.getMensaje())));
			throw new ValidationException();
		}

	}

	public void validarSubPartida3378(Integer numero,String codSubPartida) throws ValidationException {
		if (numero == 0) {
			addErrors(new ErrorMessageVO(EnumErrores.ERROR_3378.getCodigo(),
					String.format(EnumErrores.ERROR_3378.getMensaje().replace("%", codSubPartida) )));
			throw new ValidationException();
		}

	}

	public void validarCodEstadoRemitente4420(String codEstado) {

		if (!ConstantesUtils.COD_ESTADO_ACTIVO.equals(codEstado)) {
			addObs(new ObservacionNegocioVO(EnumErrores.ERROR_4420.getCodigo(),
					String.format(EnumErrores.ERROR_4420.getMensaje())));
		}
	}

	public void validarCodDomHabidoRemitente4421(String indCondicion) {

		if (!ConstantesUtils.IND_CONDICION_ACTIVO.equals(indCondicion)) {
			addObs(new ObservacionNegocioVO(EnumErrores.ERROR_4421.getCodigo(),
					String.format(EnumErrores.ERROR_4421.getMensaje())));
		}
	}
	//jmv Pagador Flete
	
	public void validarComprobanteElectronico4382(Integer numero) {

		if (numero == 0) {
			addObs(new ObservacionNegocioVO(EnumErrores.ERROR_4382.getCodigo(),
					String.format(EnumErrores.ERROR_4382.getMensaje())));
		}
	}
	
	public void validarComprobanteFisico3207(Integer numero) throws ValidationException {

		if (numero == 0) {
			addErrors(new ErrorMessageVO(EnumErrores.ERROR_3207.getCodigo(),
				String.format(EnumErrores.ERROR_3207.getMensaje())));
			throw new ValidationException();
		}

	}

	public void validarComprobanteContingenciaAutorizado4204(Integer numero) throws ValidationException {

		if (numero == 0) {
			addObs(new ObservacionNegocioVO(EnumErrores.ERROR_4204.getCodigo(),
				String.format(EnumErrores.ERROR_4204.getMensaje())));
		}

	}
	
	public void validarComprobanteFisicoRelacionado3207(Integer cantidad, Boolean contingencia, String serie, String numero) throws ValidationException {

		if (cantidad == 0) {
			if(!contingencia) {
				addErrors(new ErrorMessageVO(EnumErrores.ERROR_3207_RELACIONADO.getCodigo(),
						String.format(EnumErrores.ERROR_3207_RELACIONADO.getMensaje(), serie, numero)));
				throw new ValidationException();
			}
			else {
				addObs(new ObservacionNegocioVO(EnumErrores.ERROR_3207_RELACIONADO.getCodigo(),
					String.format(EnumErrores.ERROR_3207_RELACIONADO.getMensaje(), serie, numero)));
			}
		}

	}

	public void validarConstanciaDeposito4432(Integer numero) {

		if (numero > 0) {
			addObs(new ObservacionNegocioVO(EnumErrores.ERROR_4432.getCodigo(),
					String.format(EnumErrores.ERROR_4432.getMensaje())));
		}
	}

	public void validarExisteDamDs3341(DocumentosRelacionados documentosRelacionados) throws ValidationException {

		if (ValidacionUtil.esEmpty(documentosRelacionados)) {
			addErrors(new ErrorMessageVO(EnumErrores.ERROR_3341.getCodigo(),
					String.format(EnumErrores.ERROR_3341.getMensaje())));
			throw new ValidationException();
		}

	}
	//------------------------------------------------------------------------------------------------------------------
	
	public void validarRucDepTemp3424(String numRuc, String rucDepositoTemporal) throws ValidationException {

		if (!numRuc.equals(rucDepositoTemporal)) {
			addErrors(new ErrorMessageVO(EnumErrores.ERROR_3424.getCodigo(),
					String.format(EnumErrores.ERROR_3424.getMensaje())));
			throw new ValidationException();
		}

	}
	
	public void validarRucDepTemp3415(String numRuc, String rucDepositoTemporal) throws ValidationException {

		if (!numRuc.equals(rucDepositoTemporal)) {
			addErrors(new ErrorMessageVO(EnumErrores.ERROR_3415.getCodigo(),
					String.format(EnumErrores.ERROR_3415.getMensaje())));
			throw new ValidationException();
		}

	}

	public void validarRucDuenoConsig3394(int i, String rucDuenoConsignatarioIngreso,
			DocumentosRelacionados documentosRelacionados, String codMotivoTraslado, String codMotConstante) throws ValidationException {

		if (i > 0) {
			if (rucDuenoConsignatarioIngreso!=null && documentosRelacionados.getConsignatario().getNumDocIdentidad()!=null &&
					!rucDuenoConsignatarioIngreso.equals(documentosRelacionados.getConsignatario().getNumDocIdentidad()) && 
					codMotivoTraslado.equals(codMotConstante)) {
				addErrors(new ErrorMessageVO(EnumErrores.ERROR_3394.getCodigo(),
						String.format(EnumErrores.ERROR_3394.getMensaje())));
				throw new ValidationException(); 
			}
		}

	}

	public void validarConstanciaDeposito3342(Integer numero) throws ValidationException {

		if (numero > 0) {
			addErrors(new ErrorMessageVO(EnumErrores.ERROR_3342.getCodigo(),
					String.format(EnumErrores.ERROR_3342.getMensaje())));
			throw new ValidationException();
		}

	}
	
	public void validarCantidadBienAduana3432(String numSerie, List<String> listaNumSeries, String codMotivoTraslado) throws ValidationException {

		if (!listaNumSeries.contains(numSerie) && (codMotivoTraslado.equals(ConstantesUtils.COD_MOTTRASLADO_IMPOR) 
				|| codMotivoTraslado.equals(ConstantesUtils.COD_MOTTRASLADO_EXPOR)))
				/*|| (ValidacionUtil.esEmpty(listaNumSeries) && !ValidacionUtil.esEmpty(numSerie))*/ {
			addErrors(new ErrorMessageVO(EnumErrores.ERROR_3432.getCodigo(),
					String.format(EnumErrores.ERROR_3432.getMensaje(),numSerie)));
			throw new ValidationException();
		}
	}

	public void validarConstanciaDeposito4404(Integer numero) {

		if (numero == 0) {
			addObs(new ObservacionNegocioVO(EnumErrores.ERROR_4404.getCodigo(),
					String.format(EnumErrores.ERROR_4404.getMensaje())));
		}
	}
	
	public static String obtenerFechaFormateada(Date fecha, String formato) {
    SimpleDateFormat sdf = new SimpleDateFormat(formato);
    return sdf.format(fecha);
	}

	public void validarNumDocAduanaIngreso4435(String rucDuenoConsignatarioIngreso, String numDocIdentidad) {

		if (!rucDuenoConsignatarioIngreso.equals(numDocIdentidad)) {
			addObs(new ObservacionNegocioVO(EnumErrores.ERROR_4435.getCodigo(),
					String.format(EnumErrores.ERROR_4435.getMensaje())));
		}
	}
	
	public void validarTrasladoDescOtros4190(List<Parametria> listaParametria, String desMotivoTrasladoOtros, String numParametro, String codParametro) {
		Optional<ListaParametros> lista = listaParametria.stream().filter(x -> x.getNumParametro().equals(numParametro))
				.findFirst().get().getListaParametros().stream().filter(y -> y.getCodParametro().equals(codParametro))
				.findFirst();
		String[] descripcion =(lista.isPresent()? lista.get().getDesParametro().split("\\|"): null );

		for(int x = 2; x < descripcion.length; x++)
		{
			String[] texto = descripcion[x].split(":");
			if(texto[0].toUpperCase().equals(desMotivoTrasladoOtros.toUpperCase())) 
			{
				addObs(new ObservacionNegocioVO(EnumErrores.ERROR_4190.getCodigo(),
						String.format(EnumErrores.ERROR_4190.getMensaje())));
				break;
			}
		}
	}
	
	public void validarCantidadBienAduana4436(BigDecimal cantidadXML, BigDecimal cantidadAduana, String numSerie) {

		if (cantidadXML.compareTo(cantidadAduana)  != 0) {
			addObs(new ObservacionNegocioVO(EnumErrores.ERROR_4436.getCodigo(),
					String.format(EnumErrores.ERROR_4436.getMensaje(), numSerie)));
		}
	}
	
	public void validarParseoXML(boolean valid, boolean prop) throws ValidationException {

		if (!valid) {
			addErrors(new ErrorMessageVO(EnumErrores.ERROR_0306.getCodigo(),
					String.format(EnumErrores.ERROR_0306.getMensaje())));
			if(prop) throw new ValidationException();
		}
	}
	
	public void validarMensaje0200(boolean valid, boolean prop) throws ValidationException {

		if (!valid) {
			addErrors(new ErrorMessageVO(EnumErrores.ERROR_0200.getCodigo(),
					String.format(EnumErrores.ERROR_0200.getMensaje())));
			if(prop) throw new ValidationException();
		}
	}

}

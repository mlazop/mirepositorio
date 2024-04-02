package pe.gob.sunat.contribuyentems.registro.gre.emision.infrastructure.entrypoint.vo;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import pe.gob.sunat.contribuyentems.registro.gre.basebdgre.comprobante.domain.Comprobante;
import pe.gob.sunat.contribuyentems.registro.gre.basebdgre.comprobante.domain.Seguimiento;
import pe.gob.sunat.contribuyentems.registro.gre.basebdgre.util.FormatoFecha;

public class MensajeEnvioAceptadoRechazadoVO {

	private String numTicket;
	private ComprobanteVO comprobante;
	private String procedencia;
	private ArchivoVO archivo;
	private List<ObservacionVO> observacionesDp;
	private List<ObservacionNegocioVO> observacionesNegocio;
	private ErrorMessageVO error;
	private String indValidacion;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = FormatoFecha.yyyyMMddTHHmmssS, timezone = FormatoFecha.ZONA_HORARIA)
	private Date fecRecepcion;
	private Comprobante comprobanteGre;
	private String codUsuario;

	private List<Seguimiento> listaSeguimiento;
	
	public String getNumTicket() {
		return numTicket;
	}
	public void setNumTicket(String numTicket) {
		this.numTicket = numTicket;
	}
	public ComprobanteVO getComprobante() {
		return comprobante;
	}
	public void setComprobante(ComprobanteVO comprobante) {
		this.comprobante = comprobante;
	}
	public String getProcedencia() {
		return procedencia;
	}
	public void setProcedencia(String procedencia) {
		this.procedencia = procedencia;
	}
	public ArchivoVO getArchivo() {
		return archivo;
	}
	public void setArchivo(ArchivoVO archivo) {
		this.archivo = archivo;
	}
	public List<ObservacionVO> getObservacionesDp() {
		return observacionesDp;
	}
	public void setObservacionesDp(List<ObservacionVO> observacionesDp) {
		this.observacionesDp = observacionesDp;
	}
	public List<ObservacionNegocioVO> getObservacionesNegocio() {
		return observacionesNegocio;
	}
	public void setObservacionesNegocio(List<ObservacionNegocioVO> observacionesNegocio) {
		this.observacionesNegocio = observacionesNegocio;
	}
	
	public ErrorMessageVO getError() {
		return error;
	}
	public void setError(ErrorMessageVO error) {
		this.error = error;
	}
	public String getIndValidacion() {
		return indValidacion;
	}
	public void setIndValidacion(String indValidacion) {
		this.indValidacion = indValidacion;
	}
	public Date getFecRecepcion() {
		return fecRecepcion;
	}
	public void setFecRecepcion(Date fecRecepcion) {
		this.fecRecepcion = fecRecepcion;
	}
	public Comprobante getComprobanteGre() {
		return comprobanteGre;
	}
	public void setComprobanteGre(Comprobante comprobanteGre) {
		this.comprobanteGre = comprobanteGre;
	}
  public String getCodUsuario() {
    return codUsuario;
  }
  public void setCodUsuario(String codUsuario) {
    this.codUsuario = codUsuario;
  }

	public List<Seguimiento> getListaSeguimiento() {
		return listaSeguimiento;
	}

	public void setListaSeguimiento(List<Seguimiento> listaSeguimiento) {
		this.listaSeguimiento = listaSeguimiento;
	}
}

package pe.gob.sunat.contribuyentems.registro.gre.emision.infrastructure.entrypoint.vo;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import pe.gob.sunat.contribuyentems.registro.gre.basebdgre.util.FormatoFecha;

public class ComprobanteVO  implements Serializable {

	private static final long serialVersionUID = -1L;
	
	private String numRuc;
	private String codCpe;
	private String numSerie;
	private Integer numCpe;
	private String numCpeOri;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = FormatoFecha.yyyyMMddTHHmmss, timezone = FormatoFecha.ZONA_HORARIA)
	private Date fecEmision;
	private String codTipDocReceptor;
	private String numTipDocReceptor;
	private String desQr;
	
	public String getNumRuc() {
		return numRuc;
	}
	public void setNumRuc(String numRuc) {
		this.numRuc = numRuc;
	}
	public String getCodCpe() {
		return codCpe;
	}
	public void setCodCpe(String codCpe) {
		this.codCpe = codCpe;
	}
	public String getNumSerie() {
		return numSerie;
	}
	public void setNumSerie(String numSerie) {
		this.numSerie = numSerie;
	}
	public Integer getNumCpe() {
		return numCpe;
	}
	public void setNumCpe(Integer numCpe) {
		this.numCpe = numCpe;
	}
	public String getNumCpeOri() {
    return numCpeOri;
  }
  public void setNumCpeOri(String numCpeOri) {
    this.numCpeOri = numCpeOri;
  }
  public Date getFecEmision() {
		return fecEmision;
	}
	public void setFecEmision(Date fecEmision) {
		this.fecEmision = fecEmision;
	}
	public String getCodTipDocReceptor() {
		return codTipDocReceptor;
	}
	public void setCodTipDocReceptor(String codTipDocReceptor) {
		this.codTipDocReceptor = codTipDocReceptor;
	}
	public String getNumTipDocReceptor() {
		return numTipDocReceptor;
	}
	public void setNumTipDocReceptor(String numTipDocReceptor) {
		this.numTipDocReceptor = numTipDocReceptor;
	}
  public String getDesQr() {
    return desQr;
  }
  public void setDesQr(String desQr) {
    this.desQr = desQr;
  }
	
	
	
}

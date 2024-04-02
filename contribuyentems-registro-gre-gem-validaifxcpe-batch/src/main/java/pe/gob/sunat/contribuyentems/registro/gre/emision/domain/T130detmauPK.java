package pe.gob.sunat.contribuyentems.registro.gre.emision.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class T130detmauPK implements Serializable{

private static final long serialVersionUID = -4582067725772956170L;
	
	@Column(name="t130_numruc")
	private String numRuc;
	
	@Column(name="t130_serie")
	private String serie;
	
	@Column(name="t130_tipdoc")
	private String tipdoc;
	
	public T130detmauPK() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getNumRuc() {
		return numRuc;
	}

	public void setNumRuc(String numRuc) {
		this.numRuc = numRuc;
	}

	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	public String getTipdoc() {
		return tipdoc;
	}

	public void setTipdoc(String tipdoc) {
		this.tipdoc = tipdoc;
	}
}

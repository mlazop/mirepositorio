package pe.gob.sunat.contribuyentems.registro.gre.emision.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class T9432conscpcontinPK implements Serializable {
	
	private static final long serialVersionUID = -4582067725772956170L;
	
	@Column(name="num_fv816")
	private Integer numFV816;

	@Column(name="num_ruc")
	private String numRuc;
	
	@Column(name="num_establecimiento")
	private Integer numEstablecimiento;
	
	@Column(name="cod_cp")
	private String codCP;
	
	@Column(name="num_serie_cp")
	private String numSerieCp;
	
	public T9432conscpcontinPK() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Integer getNumFV816() {
		return numFV816;
	}

	public void setNumFV816(Integer numFV816) {
		this.numFV816 = numFV816;
	}

	public String getNumRuc() {
		return numRuc;
	}

	public void setNumRuc(String numRuc) {
		this.numRuc = numRuc;
	}

	public Integer getNumEstablecimiento() {
		return numEstablecimiento;
	}

	public void setNumEstablecimiento(Integer numEstablecimiento) {
		this.numEstablecimiento = numEstablecimiento;
	}

	public String getCodCP() {
		return codCP;
	}

	public void setCodCP(String codCP) {
		this.codCP = codCP;
	}

	public String getNumSerieCp() {
		return numSerieCp;
	}

	public void setNumSerieCp(String numSerieCp) {
		this.numSerieCp = numSerieCp;
	}
}

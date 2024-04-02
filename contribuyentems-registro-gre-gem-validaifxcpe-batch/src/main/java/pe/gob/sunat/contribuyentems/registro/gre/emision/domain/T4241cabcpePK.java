package pe.gob.sunat.contribuyentems.registro.gre.emision.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class T4241cabcpePK implements Serializable {

	private static final long serialVersionUID = -4582067725772956170L;
	
	@Column(name="num_ruc")
	private String numRuc;
	
	@Column(name="cod_cpe")
	private String codCpe;
	
	@Column(name="num_serie_cpe")
	private String numSerieCpe;
	
	@Column(name="num_cpe")
	private Integer numCpe;

	
	public T4241cabcpePK() {
		super();
		// TODO Auto-generated constructor stub
	}

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

	public String getNumSerieCpe() {
		return numSerieCpe;
	}

	public void setNumSerieCpe(String numSerieCpe) {
		this.numSerieCpe = numSerieCpe;
	}

	public Integer getNumCpe() {
		return numCpe;
	}

	public void setNumCpe(Integer numCpe) {
		this.numCpe = numCpe;
	}
	
	
}

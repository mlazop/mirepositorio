package pe.gob.sunat.contribuyentems.registro.gre.emision.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.bson.codecs.pojo.annotations.BsonId;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

@Entity
@Table(name = "t9432conscpcontin")
public class T9432conscpcontin implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@BsonId
	@JsonSerialize(using = ToStringSerializer.class)
	
	@EmbeddedId
	private T9432conscpcontinPK t9432conscpcontinPK;
	
	@Column(name="num_inicial_cp")
	private Integer numInicialCP;
	
	@Column(name="num_final_cp")
	private Integer numFinalCP;
	
	@Column(name="cnt_cp_uso")
	private Integer cntCPUso;
	
	@Column(name="cnt_cp_baja_ope")
	private Integer cntCPBajaOpe;
	
	@Column(name="cnt_cp_baja_of")
	private Integer cntCPBajaOf;
	
	@Column(name="cnt_cp_baja_dif")
	private Integer cntCPBajaDif;
	
	@Column(name="cod_estado")
	private String codEstado;
	
	@Column(name="fec_aut")
	private Date fecAut;
	
	@Column(name="cod_usuregis")
	private String codUsuRegis;
	
	@Column(name="fec_regis")
	private Date fecRegis;
	
	@Column(name="ind_padron")
	private String indPadron;
	
	public T9432conscpcontin() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public T9432conscpcontinPK getT9432conscpcontinPK() {
		return t9432conscpcontinPK;
	}

	public void setT9432conscpcontinPK(T9432conscpcontinPK t9432conscpcontinPK) {
		this.t9432conscpcontinPK = t9432conscpcontinPK;
	}

	public Integer getNumInicialCP() {
		return numInicialCP;
	}

	public void setNumInicialCP(Integer numInicialCP) {
		this.numInicialCP = numInicialCP;
	}

	public Integer getNumFinalCP() {
		return numFinalCP;
	}

	public void setNumFinalCP(Integer numFinalCP) {
		this.numFinalCP = numFinalCP;
	}

	public Integer getCntCPUso() {
		return cntCPUso;
	}

	public void setCntCPUso(Integer cntCPUso) {
		this.cntCPUso = cntCPUso;
	}

	public Integer getCntCPBajaOpe() {
		return cntCPBajaOpe;
	}

	public void setCntCPBajaOpe(Integer cntCPBajaOpe) {
		this.cntCPBajaOpe = cntCPBajaOpe;
	}

	public Integer getCntCPBajaOf() {
		return cntCPBajaOf;
	}

	public void setCntCPBajaOf(Integer cntCPBajaOf) {
		this.cntCPBajaOf = cntCPBajaOf;
	}

	public Integer getCntCPBajaDif() {
		return cntCPBajaDif;
	}

	public void setCntCPBajaDif(Integer cntCPBajaDif) {
		this.cntCPBajaDif = cntCPBajaDif;
	}

	public String getCodEstado() {
		return codEstado;
	}

	public void setCodEstado(String codEstado) {
		this.codEstado = codEstado;
	}

	public Date getFecAut() {
		return fecAut;
	}

	public void setFecAut(Date fecAut) {
		this.fecAut = fecAut;
	}

	public String getCodUsuRegis() {
		return codUsuRegis;
	}

	public void setCodUsuRegis(String codUsuRegis) {
		this.codUsuRegis = codUsuRegis;
	}

	public Date getFecRegis() {
		return fecRegis;
	}

	public void setFecRegis(Date fecRegis) {
		this.fecRegis = fecRegis;
	}

	public String getIndPadron() {
		return indPadron;
	}

	public void setIndPadron(String indPadron) {
		this.indPadron = indPadron;
	}
	
}

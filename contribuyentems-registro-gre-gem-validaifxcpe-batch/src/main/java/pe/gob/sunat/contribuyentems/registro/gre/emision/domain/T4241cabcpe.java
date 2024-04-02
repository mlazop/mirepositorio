package pe.gob.sunat.contribuyentems.registro.gre.emision.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.bson.codecs.pojo.annotations.BsonId;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "t4241cabcpe")
public class T4241cabcpe implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@BsonId
	@JsonSerialize(using = ToStringSerializer.class)
	
	@EmbeddedId
	private T4241cabcpePK t4241cabcpePK;
	
	@Column(name="cod_tipo_cpe")
	private String codTipoCpe;
	
	@Column(name="cod_docide_recep")
	private String codDocideRecep;
	
	@Column(name="num_docide_recep")
	private String numDocideRecep;
	
	@Column(name="des_nombre_recep")
	private String desNombreRecep;
	
	@Column(name="cod_moneda")
	private String codMoneda;
	
	@Column(name="des_observacion")
	private String desObservacion;
	
	@Column(name="ind_estado")
	private String indEstado;
	
	@Column(name="ind_rechazo")
	private String indRechazo;
	
	@Column(name="mto_importe_total")
	private BigDecimal mtoImporteTotal;
	
	@Column(name="num_id_xml")
	private Integer numIdXml;
	
	@Column(name="fec_emision")
	private Date fecEmision;
	
	@Column(name="cod_est_anexo")
	private Integer codEstAnexo;
	
	@Column(name="ind_procedencia")
	private String indProcedencia;
	
	@Column(name="mto_total_igv")
	private BigDecimal mtoTotalIgv;
	
	@Column(name="mto_total_venta")
	private BigDecimal mtoTotalVenta;
	
	@Column(name="num_per_regven")
	private String numPerRegven;
	
	@Column(name="num_per_regcom")
	private String numPerRegcom;
	
	@Column(name="num_cuo_regven")
	private Integer numCuoRegven;
	
	@Column(name="cod_usumodif")
	private String codUsumodif;
	
	@Column(name="fec_modif")
	private Date fecModif;

	
	public T4241cabcpe() {
		super();
		// TODO Auto-generated constructor stub
	}

	public T4241cabcpePK getT4241cabcpePK() {
		return t4241cabcpePK;
	}

	public void setT4241cabcpePK(T4241cabcpePK t4241cabcpePK) {
		this.t4241cabcpePK = t4241cabcpePK;
	}

	public String getCodTipoCpe() {
		return codTipoCpe;
	}

	public void setCodTipoCpe(String codTipoCpe) {
		this.codTipoCpe = codTipoCpe;
	}

	public String getCodDocideRecep() {
		return codDocideRecep;
	}

	public void setCodDocideRecep(String codDocideRecep) {
		this.codDocideRecep = codDocideRecep;
	}

	public String getNumDocideRecep() {
		return numDocideRecep;
	}

	public void setNumDocideRecep(String numDocideRecep) {
		this.numDocideRecep = numDocideRecep;
	}

	public String getDesNombreRecep() {
		return desNombreRecep;
	}

	public void setDesNombreRecep(String desNombreRecep) {
		this.desNombreRecep = desNombreRecep;
	}

	public String getCodMoneda() {
		return codMoneda;
	}

	public void setCodMoneda(String codMoneda) {
		this.codMoneda = codMoneda;
	}

	public String getDesObservacion() {
		return desObservacion;
	}

	public void setDesObservacion(String desObservacion) {
		this.desObservacion = desObservacion;
	}

	public String getIndEstado() {
		return indEstado;
	}

	public void setIndEstado(String indEstado) {
		this.indEstado = indEstado;
	}

	public String getIndRechazo() {
		return indRechazo;
	}

	public void setIndRechazo(String indRechazo) {
		this.indRechazo = indRechazo;
	}

	public BigDecimal getMtoImporteTotal() {
		return mtoImporteTotal;
	}

	public void setMtoImporteTotal(BigDecimal mtoImporteTotal) {
		this.mtoImporteTotal = mtoImporteTotal;
	}

	public Integer getNumIdXml() {
		return numIdXml;
	}

	public void setNumIdXml(Integer numIdXml) {
		this.numIdXml = numIdXml;
	}

	public Date getFecEmision() {
		return fecEmision;
	}

	public void setFecEmision(Date fecEmision) {
		this.fecEmision = fecEmision;
	}

	public Integer getCodEstAnexo() {
		return codEstAnexo;
	}

	public void setCodEstAnexo(Integer codEstAnexo) {
		this.codEstAnexo = codEstAnexo;
	}

	public String getIndProcedencia() {
		return indProcedencia;
	}

	public void setIndProcedencia(String indProcedencia) {
		this.indProcedencia = indProcedencia;
	}

	public BigDecimal getMtoTotalIgv() {
		return mtoTotalIgv;
	}

	public void setMtoTotalIgv(BigDecimal mtoTotalIgv) {
		this.mtoTotalIgv = mtoTotalIgv;
	}

	public BigDecimal getMtoTotalVenta() {
		return mtoTotalVenta;
	}

	public void setMtoTotalVenta(BigDecimal mtoTotalVenta) {
		this.mtoTotalVenta = mtoTotalVenta;
	}

	public String getNumPerRegven() {
		return numPerRegven;
	}

	public void setNumPerRegven(String numPerRegven) {
		this.numPerRegven = numPerRegven;
	}

	public String getNumPerRegcom() {
		return numPerRegcom;
	}

	public void setNumPerRegcom(String numPerRegcom) {
		this.numPerRegcom = numPerRegcom;
	}

	public Integer getNumCuoRegven() {
		return numCuoRegven;
	}

	public void setNumCuoRegven(Integer numCuoRegven) {
		this.numCuoRegven = numCuoRegven;
	}

	public String getCodUsumodif() {
		return codUsumodif;
	}

	public void setCodUsumodif(String codUsumodif) {
		this.codUsumodif = codUsumodif;
	}

	public Date getFecModif() {
		return fecModif;
	}

	public void setFecModif(Date fecModif) {
		this.fecModif = fecModif;
	}
	
	
}

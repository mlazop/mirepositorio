package pe.gob.sunat.contribuyentems.registro.gre.emision.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.bson.codecs.pojo.annotations.BsonId;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "t130detmau")
public class T130detmau implements Serializable {

	private static final long serialVersionUID = -4582067725772956170L;
	
	@BsonId
	@JsonSerialize(using = ToStringSerializer.class)
	
	@EmbeddedId
	private T130detmauPK t130detmauPK;
	
	@Column(name="t130_desde")
	private String desde;
	
	@Column(name="t130_hasta")
	private String hasta;
	
	@Column(name="t130_userna")
	private String userna;
	
	@Column(name="t130_fecact")
	private String fecact;

	
	public T130detmau() {
		super();
	}

	public T130detmauPK getT130detmauPK() {
		return t130detmauPK;
	}

	public void setT130detmauPK(T130detmauPK t130detmauPK) {
		this.t130detmauPK = t130detmauPK;
	}

	public String getDesde() {
		return desde;
	}

	public void setDesde(String desde) {
		this.desde = desde;
	}

	public String getHasta() {
		return hasta;
	}

	public void setHasta(String hasta) {
		this.hasta = hasta;
	}

	public String getUserna() {
		return userna;
	}

	public void setUserna(String userna) {
		this.userna = userna;
	}

	public String getFecact() {
		return fecact;
	}

	public void setFecact(String fecact) {
		this.fecact = fecact;
	}
	
	
	
}

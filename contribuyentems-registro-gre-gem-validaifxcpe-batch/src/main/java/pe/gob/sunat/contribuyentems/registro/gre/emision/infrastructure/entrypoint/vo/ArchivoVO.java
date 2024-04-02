package pe.gob.sunat.contribuyentems.registro.gre.emision.infrastructure.entrypoint.vo;

import java.io.Serializable;

public class ArchivoVO  implements Serializable {

	private static final long serialVersionUID = -1L;
	
	private String nomArchivo;
	private String arcGreZip;
	
	public String getNomArchivo() {
		return nomArchivo;
	}
	public void setNomArchivo(String nomArchivo) {
		this.nomArchivo = nomArchivo;
	}
	public String getArcGreZip() {
		return arcGreZip;
	}
	public void setArcGreZip(String arcGreZip) {
		this.arcGreZip = arcGreZip;
	}
	
	
}

package pe.gob.sunat.contribuyentems.registro.gre.emision.infrastructure.entrypoint.vo;

public class ObservacionNegocioVO {

	private String codObs;
	private String desObs;
	
	public ObservacionNegocioVO() {
		super();
	}
	public ObservacionNegocioVO(String codObs, String desObs) {
		this.codObs = codObs;
		this.desObs = desObs;
	}
	public String getCodObs() {
		return codObs;
	}
	public void setCodObs(String codObs) {
		this.codObs = codObs;
	}
	public String getDesObs() {
		return desObs;
	}
	public void setDesObs(String desObs) {
		this.desObs = desObs;
	}
	
	
}

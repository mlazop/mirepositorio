package pe.gob.sunat.contribuyentems.registro.gre.emision.infrastructure.entrypoint.vo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

public class ErrorMessageVO {

	private static final long serialVersionUID = 1L;
	
	private String numError;
	private String desError;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private List<ErrorMessageVO> errors;
	
	
	
	
	
	public ErrorMessageVO(String numError, String desError) {
		this.numError = numError;
		this.desError = desError;
	}

	public String getNumError() {
		return numError;
	}

	public void setNumError(String numError) {
		this.numError = numError;
	}

	public String getDesError() {
		return desError;
	}

	public void setDesError(String desError) {
		this.desError = desError;
	}

	public List<ErrorMessageVO> getErrors() {
		return errors;
	}
	public void setErrors(List<ErrorMessageVO> errors) {
		this.errors = errors;
	}

	@Override
	public String toString() {
		return "ErrorMessage [numError=" + numError + " desError" + desError + "]";
	}
	
}

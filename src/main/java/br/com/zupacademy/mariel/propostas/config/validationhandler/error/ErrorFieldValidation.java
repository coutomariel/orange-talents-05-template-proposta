package br.com.zupacademy.mariel.propostas.config.validationhandler.error;

public class ErrorFieldValidation extends ErrorValidation {

	private String field;

	public ErrorFieldValidation(String field, String message) {
		super(field, message);
		this.field = field;
	}

	public String getField() {
		return field;
	}

}

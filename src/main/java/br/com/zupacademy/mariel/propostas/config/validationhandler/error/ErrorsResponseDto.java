package br.com.zupacademy.mariel.propostas.config.validationhandler.error;

import java.util.ArrayList;
import java.util.List;

public class ErrorsResponseDto {

	private List<ErrorValidation> errors = new ArrayList<ErrorValidation>();

	public List<ErrorValidation> getErrors() {
		return errors;
	}
	
	public void addError(ErrorValidation error) {
		this.errors.add(error);
	}

}

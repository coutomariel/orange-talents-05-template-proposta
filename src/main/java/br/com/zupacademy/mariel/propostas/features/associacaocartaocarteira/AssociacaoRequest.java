package br.com.zupacademy.mariel.propostas.features.associacaocartaocarteira;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class AssociacaoRequest {

	@Email
	@NotBlank
	private String email;
	
	public String getEmail() {
		return email;
	}

}

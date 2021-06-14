package br.com.zupacademy.mariel.propostas.cadastrobiometrias;

import javax.validation.constraints.NotBlank;

import br.com.zupacademy.mariel.propostas.entities.Biometria;
import br.com.zupacademy.mariel.propostas.novaspropostas.entities.Cartao;
import br.com.zupacademy.mariel.propostas.validators.IsBase64;

public class BiometriaRequest {

	@NotBlank
	@IsBase64
	private String biometria;

	public String getBiometria() {
		return biometria;
	}

	public Biometria toModel(Cartao cartao) {
		return new Biometria(cartao, biometria);
	}
	
}

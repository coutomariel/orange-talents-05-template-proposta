package br.com.zupacademy.mariel.propostas.features.cadastrobiometrias;

import javax.validation.constraints.NotBlank;

import br.com.zupacademy.mariel.propostas.config.commomvalidators.IsBase64;
import br.com.zupacademy.mariel.propostas.domain.entities.Biometria;
import br.com.zupacademy.mariel.propostas.domain.entities.Cartao;

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

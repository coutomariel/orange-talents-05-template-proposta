package br.com.zupacademy.mariel.propostas.clientsfeign.comunicaassociacaocartaocarteira;

import br.com.zupacademy.mariel.propostas.domain.entities.TipoCarteira;

public class AssociacaoClientRequest {

	private String email;
	private TipoCarteira carteira;
	
	public AssociacaoClientRequest(String email, TipoCarteira carteira) {
		this.email = email;
		this.carteira = carteira;
	}

	public String getEmail() {
		return email;
	}

	public TipoCarteira getCarteira() {
		return carteira;
	}

}

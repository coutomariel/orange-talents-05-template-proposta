package br.com.zupacademy.mariel.propostas.novaspropostas.controller.response;

import br.com.zupacademy.mariel.propostas.novaspropostas.entities.Proposta;

public class PropostaResponse {

	private Long id;
	private String nome;
	private String estado;
	
	
	public PropostaResponse(Proposta proposta) {
		this.id = proposta.getId();
		this.nome = proposta.getNome();
		this.estado = proposta.getStatus().toString();
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getEstado() {
		return estado.equals("ELEGIVEL") ? "Aprovada" : "Rejeitada";
	}
	
}

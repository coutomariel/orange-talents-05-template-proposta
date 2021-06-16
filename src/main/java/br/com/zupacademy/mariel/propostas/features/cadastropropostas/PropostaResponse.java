package br.com.zupacademy.mariel.propostas.features.cadastropropostas;

import br.com.zupacademy.mariel.propostas.domain.entities.Proposta;

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

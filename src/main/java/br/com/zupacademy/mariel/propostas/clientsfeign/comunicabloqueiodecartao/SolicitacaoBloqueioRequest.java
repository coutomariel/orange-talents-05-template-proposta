package br.com.zupacademy.mariel.propostas.clientsfeign.comunicabloqueiodecartao;

public class SolicitacaoBloqueioRequest {

	private String sistemaResponsavel;

	public SolicitacaoBloqueioRequest(String sistemaResponsavel) {
		this.sistemaResponsavel = sistemaResponsavel;
	}

	public String getSistemaResponsavel() {
		return sistemaResponsavel;
	}

}

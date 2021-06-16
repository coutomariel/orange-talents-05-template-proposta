package br.com.zupacademy.mariel.propostas.clientsfeign.consultadocumento;

import javax.validation.constraints.NotEmpty;

public class ConsultaDocumentoClientRequest {

	@NotEmpty
	private String nome;
	
	@NotEmpty
	private String documento;

	@NotEmpty
	private String idProposta;

	public ConsultaDocumentoClientRequest(String nome, String documento, String idProposta) {
		this.nome = nome;
		this.documento = documento;
		this.idProposta = idProposta;
	}

	public String getNome() {
		return nome;
	}

	public String getDocumento() {
		return documento;
	}

	public String getIdProposta() {
		return idProposta;
	}

}

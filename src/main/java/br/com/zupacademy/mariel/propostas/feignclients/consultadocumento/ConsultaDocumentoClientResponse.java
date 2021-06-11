package br.com.zupacademy.mariel.propostas.feignclients.consultadocumento;

public class ConsultaDocumentoClientResponse {

	private String nome;
	private String documento;
	private String idProposta;
	private String resultadoSolicitacao;

	public ConsultaDocumentoClientResponse(String nome, String documento, String idProposta,
			String resultadoSolicitacao) {
		this.nome = nome;
		this.documento = documento;
		this.idProposta = idProposta;
		this.resultadoSolicitacao = resultadoSolicitacao;
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

	public String getResultadoSolicitacao() {
		return resultadoSolicitacao;
	}

}

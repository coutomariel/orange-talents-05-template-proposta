package br.com.zupacademy.mariel.propostas.features.cadastroavisoviagem;

import java.time.LocalDate;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;

import br.com.zupacademy.mariel.propostas.domain.entities.AvisoViagem;
import br.com.zupacademy.mariel.propostas.domain.entities.Cartao;

public class AvisoViagemRequest {

	@NotBlank
	private String destino;

	@Future
	private LocalDate dataTermino;

	public String getDestino() {
		return destino;
	}

	public LocalDate getDataTermino() {
		return dataTermino;
	}

	public AvisoViagem toModel(Cartao cartao, String remoteIp, String userAgent) {
		return new AvisoViagem(destino, dataTermino, remoteIp, userAgent, cartao);
	}

}

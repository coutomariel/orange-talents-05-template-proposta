package br.com.zupacademy.mariel.propostas.clientsfeign.comunicaavisodeviagem;

import java.time.LocalDate;

public class AvisoViagemClientRequest {

	private String destino;
	private LocalDate validoAte;

	public AvisoViagemClientRequest(String destino, LocalDate validoAte) {
		this.destino = destino;
		this.validoAte = validoAte;
	}

	public String getDestino() {
		return destino;
	}

	public LocalDate getValidoAte() {
		return validoAte;
	}

}

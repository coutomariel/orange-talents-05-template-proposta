package br.com.zupacademy.mariel.propostas.domain.entities;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Bloqueio {

	@Id
	private String id;
	private LocalDateTime bloqueadoEm;

	@ManyToOne
	private Cartao cartao;
	public Bloqueio(String id, LocalDateTime bloqueadoEm) {
		this.id = id;
		this.bloqueadoEm = bloqueadoEm;
	}

	public String getId() {
		return id;
	}

	public LocalDateTime getBloqueadoEm() {
		return bloqueadoEm;
	}

}

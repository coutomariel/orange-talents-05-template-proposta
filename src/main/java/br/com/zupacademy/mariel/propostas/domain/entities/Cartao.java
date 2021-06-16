package br.com.zupacademy.mariel.propostas.domain.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Cartao {

	@Id
	private String id;

	/**
	 * 
	 * @deprecated para uso exclusivo do hibernate
	 */
	@Deprecated
	public Cartao() {
	}

	public Cartao(String id, List<Bloqueio> bloqueios) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

}

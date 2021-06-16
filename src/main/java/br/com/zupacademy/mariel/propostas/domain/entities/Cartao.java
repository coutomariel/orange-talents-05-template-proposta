package br.com.zupacademy.mariel.propostas.domain.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Cartao {

	@Id
	private String id;
	
	@NotNull
	private Boolean bloqueado =  false;

	/**
	 * 
	 * @deprecated para uso exclusivo do hibernate
	 */
	@Deprecated
	public Cartao() {
	}

	public String getId() {
		return id;
	}

	public boolean isBloqueado() {
		return this.bloqueado == true;
	}

	public void bloquear() {
		this.bloqueado = true;
	}

}

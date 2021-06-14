package br.com.zupacademy.mariel.propostas.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zupacademy.mariel.propostas.novaspropostas.entities.Cartao;

@Entity
public class Biometria {
	
	public Long getId() {
		return id;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@ManyToOne
	private Cartao cartao;
	
	@NotBlank
	private String biometria;

	/**
	 * @deprecated para uso exclusivo do hibernate
	 */
	@Deprecated
	public Biometria() {
	}

	public Biometria(@NotNull Cartao cartao, @NotBlank String biometria) {
		this.cartao = cartao;
		this.biometria = biometria;
	}

}

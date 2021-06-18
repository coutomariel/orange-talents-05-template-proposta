package br.com.zupacademy.mariel.propostas.domain.entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;


@Entity
public class Carteira {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Cartao cartao;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private TipoCarteira tipoCarteira;

	/**
	 * @deprecated construtor para uso exclusivo do hibernate
	 */
	@Deprecated
	public Carteira() {
	}

	public Carteira(Cartao cartao, @NotNull TipoCarteira tipoCarteira) {
		this.cartao = cartao;
		this.tipoCarteira = tipoCarteira;
	}

	public Long getId() {
		return id;
	}
	
}

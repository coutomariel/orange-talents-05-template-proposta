package br.com.zupacademy.mariel.propostas.domain.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class AvisoViagem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String destino;

	@NotNull
	private LocalDate dataCriacao;

	@Future
	@NotNull
	private LocalDate dataTermino;

	@NotNull
	private String remoteIp;

	@NotNull
	private String userAgent;

	@ManyToOne
	private Cartao cartao;

	public AvisoViagem(@NotBlank String destino, @Future @NotNull LocalDate dataTermino, @NotNull String remoteIp,
			@NotNull String userAgent, Cartao cartao) {
		this.destino = destino;
		this.dataCriacao = LocalDate.now();
		this.dataTermino = dataTermino;
		this.remoteIp = remoteIp;
		this.userAgent = userAgent;
		this.cartao = cartao;
	}

}

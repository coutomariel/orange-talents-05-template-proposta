package br.com.zupacademy.mariel.propostas.domain.entities;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Bloqueio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String remoteIp;
	
	@NotBlank
	private String userAgent;

	@NotNull
	private LocalDateTime bloqueadoEm;

	@ManyToOne
	private Cartao cartao;
	
	
	/**
	 * @deprecated construtor para uso exclusivo do hibernate
	 */
	@Deprecated
	public Bloqueio() {
	}

	public Bloqueio(Cartao cartao, String remoteIp, String userAgent) {
		this.cartao = cartao;
		this.remoteIp = remoteIp;
		this.userAgent = userAgent;
		this.bloqueadoEm = LocalDateTime.now();
	}
	
	public Long getId() {
		return id;
	}
	
	public String getCartaoId() {
		return cartao.getId();
	}
	

}

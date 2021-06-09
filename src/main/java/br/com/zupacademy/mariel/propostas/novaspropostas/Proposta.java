package br.com.zupacademy.mariel.propostas.novaspropostas;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Entity
public class Proposta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Email
	private String email;

	@NotBlank
	private String nome;

	@NotBlank
	private String documento;

	@NotBlank
	private String endereco;

	@NotNull
	@PositiveOrZero
	private BigDecimal salario;

	@Enumerated(EnumType.STRING)
	private StatusProposta status;

	/**
	 * @deprecated construtor para uso exclusivo do hibernate
	 */
	@Deprecated
	public Proposta() {
	}

	public Proposta(@NotBlank @Email String email, @NotBlank String nome, @NotBlank String documento,
			@NotBlank String endereco, @NotNull @PositiveOrZero BigDecimal salario, StatusProposta status) {
		this.email = email;
		this.nome = nome;
		this.documento = documento;
		this.endereco = endereco;
		this.salario = salario;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getDocumento() {
		return documento;
	}

	public void setStatus(StatusProposta status) {
		this.status = status;
	}

}

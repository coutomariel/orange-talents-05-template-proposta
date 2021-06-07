package br.com.zupacademy.mariel.propostas.novaspropostas;

import java.math.BigDecimal;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;
import org.hibernate.validator.group.GroupSequenceProvider;

import br.com.zupacademy.mariel.propostas.novaspropostas.config.validation.groups.DocumentGroupSequenceProvider;
import br.com.zupacademy.mariel.propostas.novaspropostas.config.validation.groups.PessoaFisica;
import br.com.zupacademy.mariel.propostas.novaspropostas.config.validation.groups.PessoaJuridica;

@GroupSequenceProvider(value = DocumentGroupSequenceProvider.class)
public class PropostaRequest {

	@Email
	@NotBlank
	private String email;
	
	@NotBlank
	private String nome;

	@CPF(groups = PessoaFisica.class)
	@CNPJ(groups = PessoaJuridica.class)
	@NotBlank
	private String documento;

	@NotBlank
	private String endereco;

	@NotNull
	@PositiveOrZero
	private BigDecimal salario;

	
	public PropostaRequest(@Email @NotBlank String email, @NotBlank String nome, @CNPJ @CPF @NotBlank String documento,
			@NotBlank String endereco, @NotNull @PositiveOrZero BigDecimal salario) {
		this.email = email;
		this.nome = nome;
		this.documento = documento;
		this.endereco = endereco;
		this.salario = salario;
	}

	public String getEmail() {
		return email;
	}

	public String getNome() {
		return nome;
	}

	public String getDocumento() {
		return documento;
	}

	public String getEndereco() {
		return endereco;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public Proposta toModel() {
		return new Proposta(email, nome, documento, endereco, salario);
	}
	
	public String getTipo() {
		return documento.length() == 11 ? "PF" : "PJ";
	}

	
}

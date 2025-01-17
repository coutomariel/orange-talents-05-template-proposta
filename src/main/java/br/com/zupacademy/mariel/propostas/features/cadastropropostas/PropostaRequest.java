package br.com.zupacademy.mariel.propostas.features.cadastropropostas;

import java.math.BigDecimal;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;
import org.hibernate.validator.group.GroupSequenceProvider;
import org.springframework.security.crypto.encrypt.TextEncryptor;

import br.com.zupacademy.mariel.propostas.domain.entities.Proposta;
import br.com.zupacademy.mariel.propostas.features.cadastropropostas.validacaodocumento.DocumentGroupSequenceProvider;
import br.com.zupacademy.mariel.propostas.features.cadastropropostas.validacaodocumento.PessoaFisica;
import br.com.zupacademy.mariel.propostas.features.cadastropropostas.validacaodocumento.PessoaJuridica;

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

	public Proposta toModel(TextEncryptor encryptor) {
		return new Proposta(email, nome, encryptor.encrypt(documento), endereco, salario, null);
	}

	public String getTipo() {
		return documento.length() == 11 ? "PF" : "PJ";
	}

}

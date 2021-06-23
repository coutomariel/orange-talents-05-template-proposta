package br.com.zupacademy.mariel.propostas.utils.encryptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;

@Configuration
public class EncriptadorStrings {

	@Bean
	public TextEncryptor passwordEncoder() {
		TextEncryptor encryptor = Encryptors.queryableText("document", "646f63756d656e74");
		return encryptor;
	}

}

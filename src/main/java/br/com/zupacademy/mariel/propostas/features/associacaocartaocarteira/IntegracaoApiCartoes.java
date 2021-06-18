package br.com.zupacademy.mariel.propostas.features.associacaocartaocarteira;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import br.com.zupacademy.mariel.propostas.clientsfeign.comunicaassociacaocartaocarteira.AssociacaoClientRequest;
import br.com.zupacademy.mariel.propostas.clientsfeign.comunicaassociacaocartaocarteira.ComunicaAssociacaoCartaoCarteiraFeignClient;
import br.com.zupacademy.mariel.propostas.domain.entities.TipoCarteira;
import feign.FeignException;

@Configuration
public class IntegracaoApiCartoes {

	@Autowired
	private ComunicaAssociacaoCartaoCarteiraFeignClient apiCartoes;

	private static final Logger LOGGER = Logger.getLogger(IntegracaoApiCartoes.class.getName());

	public Boolean comunicaAssociacaoCartaCarteira(String cartaoId, String email, TipoCarteira carteira) {

		Boolean associacaoEfetuada = false;

		try {
			String response = apiCartoes.comunicaAvisoViagem(cartaoId, new AssociacaoClientRequest(email, carteira)).getResultado();
			associacaoEfetuada = response.equals("ASSOCIADA");
		} catch (FeignException e) {
			LOGGER.log(Level.WARNING, "problema com associação de carteira");
		}

		return associacaoEfetuada;
	}

}

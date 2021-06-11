package br.com.zupacademy.mariel.propostas.feignclients.consultacartoes.scheduling;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.zupacademy.mariel.propostas.feignclients.consultacartoes.ConsultaEmissaoCartoesFeignClient;
import br.com.zupacademy.mariel.propostas.novaspropostas.entities.Cartao;
import br.com.zupacademy.mariel.propostas.novaspropostas.entities.Proposta;
import br.com.zupacademy.mariel.propostas.novaspropostas.entities.StatusProposta;
import br.com.zupacademy.mariel.propostas.novaspropostas.repositories.CartaoRepository;
import br.com.zupacademy.mariel.propostas.novaspropostas.repositories.PropostasRepository;

@Component
public class BuscaPorCartoesEmitidos {

	@Autowired
	private PropostasRepository propostasRepository;

	@Autowired
	private CartaoRepository cartaoRepository;

	@Autowired
	private ConsultaEmissaoCartoesFeignClient emissorCartoesFeignClient;

	Logger logger = org.slf4j.LoggerFactory.getLogger(BuscaPorCartoesEmitidos.class);

	@Scheduled(fixedDelayString = "${periodicidade.executa-busca-por-cartoes-emitidos}")
	public void BuscaCartoesNaApi() {
		List<Proposta> propostas = propostasRepository.findByStatusAndCartaoIsNull(StatusProposta.ELEGIVEL);
		propostas.forEach(proposta -> associaCartao(proposta));
	}

	private void associaCartao(Proposta proposta) {
		try {
			Cartao cartao = emissorCartoesFeignClient.consultaCartaoParaProposta(proposta.getId().toString());
			proposta.setCartao(cartaoRepository.save(cartao));
			propostasRepository.save(proposta);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
}

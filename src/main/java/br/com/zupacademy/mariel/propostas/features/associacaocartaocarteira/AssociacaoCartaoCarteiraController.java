package br.com.zupacademy.mariel.propostas.features.associacaocartaocarteira;

import java.net.URI;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupacademy.mariel.propostas.clientsfeign.comunicaassociacaocartaocarteira.AssociacaoClientRequest;
import br.com.zupacademy.mariel.propostas.clientsfeign.comunicaassociacaocartaocarteira.ComunicaAssociacaoCartaoCarteiraFeignClient;
import br.com.zupacademy.mariel.propostas.domain.entities.Cartao;
import br.com.zupacademy.mariel.propostas.domain.entities.Carteira;
import br.com.zupacademy.mariel.propostas.domain.entities.TipoCarteira;
import br.com.zupacademy.mariel.propostas.domain.repositories.CartaoRepository;
import br.com.zupacademy.mariel.propostas.domain.repositories.CarteirasRepository;
import feign.FeignException;

@RestController
@RequestMapping("/cartoes")
public class AssociacaoCartaoCarteiraController {

	@Autowired
	private CartaoRepository cartaoRepository;

	@Autowired
	private CarteirasRepository carteirasRepository;

	@Autowired
	private ComunicaAssociacaoCartaoCarteiraFeignClient apiCartoes;

	@Transactional
	@PostMapping("/{cartaoId}/carteiras/paypal")
	public ResponseEntity<?> associarCarteiraPaypal(@PathVariable String cartaoId,
			@RequestBody AssociacaoRequest associacaoReq, UriComponentsBuilder uriBuilder) {

		if (!cartaoRepository.existsById(cartaoId)) {
			return ResponseEntity.notFound().build();
		}

		if (carteirasRepository.existsByTipoCarteiraAndCartaoId(TipoCarteira.PAYPAL, cartaoId)) {
			return ResponseEntity.unprocessableEntity().build();
		}

		String clientResponse = comunicaAssociacaoCartaCarteira(cartaoId,
				new AssociacaoClientRequest(associacaoReq.getEmail(), TipoCarteira.PAYPAL));
		if (!clientResponse.equals("ASSOCIADA")) {
			return ResponseEntity.unprocessableEntity().build();
		}

		Cartao cartao = cartaoRepository.findById(cartaoId).get();
		Carteira novaCarteira = carteirasRepository.save(new Carteira(cartao, TipoCarteira.PAYPAL));

		URI uri = uriBuilder.path("/{cartaoId}/carteiras/{id}").buildAndExpand(cartaoId, novaCarteira.getId()).toUri();
		return ResponseEntity.created(uri).body("Carteira associada com sucesso!");

	}

	private String comunicaAssociacaoCartaCarteira(String cartaoId, AssociacaoClientRequest associacaoClientRequest) {

		try {
			return apiCartoes.comunicaAvisoViagem(cartaoId, associacaoClientRequest).getResultado();
		} catch (FeignException e) {
			System.out.println("Falhou");
			return "FALHA";
		}
	}

}

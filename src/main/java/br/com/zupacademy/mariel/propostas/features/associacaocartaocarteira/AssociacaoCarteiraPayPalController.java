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

import br.com.zupacademy.mariel.propostas.domain.entities.Cartao;
import br.com.zupacademy.mariel.propostas.domain.entities.Carteira;
import br.com.zupacademy.mariel.propostas.domain.entities.TipoCarteira;
import br.com.zupacademy.mariel.propostas.domain.repositories.CartaoRepository;
import br.com.zupacademy.mariel.propostas.domain.repositories.CarteirasRepository;

@RestController
@RequestMapping("/cartoes")
public class AssociacaoCarteiraPayPalController {

	@Autowired
	private CartaoRepository cartaoRepository;

	@Autowired
	private CarteirasRepository carteirasRepository;

	@Autowired
	private IntegracaoApiCartoes apiCartoes;

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

		boolean associacaoComunicadaAoSistemaLegado = apiCartoes.comunicaAssociacaoCartaCarteira(cartaoId, associacaoReq.getEmail(), TipoCarteira.PAYPAL);
		if (!associacaoComunicadaAoSistemaLegado) {
			return ResponseEntity.unprocessableEntity().build();
		}
		
		Cartao cartao = cartaoRepository.findById(cartaoId).get();
		Carteira novaCarteira = carteirasRepository.save(new Carteira(cartao, TipoCarteira.PAYPAL));

		URI uri = uriBuilder.path("/{cartaoId}/carteiras/{id}").buildAndExpand(cartao.getId(), novaCarteira.getId()).toUri();
		return ResponseEntity.created(uri).body("Carteira associada com sucesso!");

	}


}

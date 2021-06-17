package br.com.zupacademy.mariel.propostas.features.cadastroavisoviagem;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.mariel.propostas.clientsfeign.comunicaavisodeviagem.AvisoViagemClientRequest;
import br.com.zupacademy.mariel.propostas.clientsfeign.comunicaavisodeviagem.AvisoViagemClientResponse;
import br.com.zupacademy.mariel.propostas.clientsfeign.comunicaavisodeviagem.ComunicaAvisoDeViagenFeignClient;
import br.com.zupacademy.mariel.propostas.domain.entities.Cartao;
import br.com.zupacademy.mariel.propostas.domain.repositories.AvisosViagemRepository;
import br.com.zupacademy.mariel.propostas.domain.repositories.CartaoRepository;
import feign.FeignException;

@RestController
@RequestMapping("/cartoes")
public class CadastroAvisoDeViagemController {

	@Autowired
	private CartaoRepository cartaoRespository;

	@Autowired
	private AvisosViagemRepository viagensRepository;

	@Autowired
	private ComunicaAvisoDeViagenFeignClient avisosViagemClient;

	@Transactional
	@PostMapping("/{cartaoId}/viagens")
	public ResponseEntity<?> criaAvisoViagem(HttpServletRequest req, Authentication auth,
			@PathVariable String cartaoId, @RequestBody @Valid AvisoViagemRequest viagemRequest) {

		if(!cartaoRespository.existsById(cartaoId)) {
			return ResponseEntity.notFound().build();
		}

		String retornoSistemaLegado = informaAvisoDeViagemParaSistemaLegado(cartaoId, viagemRequest);
		if (retornoSistemaLegado.equals("CRIADO")) {
			Cartao cartao = cartaoRespository.findById(cartaoId).get();
			WebAuthenticationDetails details = (WebAuthenticationDetails) auth.getDetails();
			String remoteIp = details.getRemoteAddress();
			String userAgent = req.getHeader("User-agent");

			viagensRepository.save(viagemRequest.toModel(cartao, remoteIp, userAgent));
		}

		return ResponseEntity.ok().build();
	}

	private String informaAvisoDeViagemParaSistemaLegado(String cartaoId, AvisoViagemRequest aviso) {
		try {
			AvisoViagemClientResponse response = avisosViagemClient.comunicaAvisoViagem(cartaoId,
					new AvisoViagemClientRequest(aviso.getDestino(), aviso.getDataTermino()));

			return response.getResultado();
		} catch (FeignException e) {
			return "FALHA";
		}
	}

}

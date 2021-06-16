package br.com.zupacademy.mariel.propostas.features.cadastrobloqueios;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.mariel.propostas.clientsfeign.comunicabloqueiodecartao.ComunicaBloqueioDeCartaoFeignClient;
import br.com.zupacademy.mariel.propostas.clientsfeign.comunicabloqueiodecartao.SolicitacaoBloqueioRequest;
import br.com.zupacademy.mariel.propostas.clientsfeign.comunicabloqueiodecartao.SolicitacaoBloqueioResponse;
import br.com.zupacademy.mariel.propostas.domain.entities.Bloqueio;
import br.com.zupacademy.mariel.propostas.domain.entities.Cartao;
import br.com.zupacademy.mariel.propostas.domain.repositories.BloqueiosRepository;
import br.com.zupacademy.mariel.propostas.domain.repositories.CartaoRepository;
import feign.FeignException.FeignClientException;

@RestController
@RequestMapping("/bloqueios")
public class CadastroBloqueioController {

	@Autowired
	private CartaoRepository cartaoRepository;
	@Autowired
	private BloqueiosRepository bloqueiosRepository;
	@Autowired
	private ComunicaBloqueioDeCartaoFeignClient clientBloqueioDeCartao;

	@Transactional
	@PostMapping("/{cartaoId}")
	public ResponseEntity<?> criaBloqueio(HttpServletRequest request, Authentication authentication,
			@PathVariable String cartaoId) {
		Optional<Cartao> buscaCartao = cartaoRepository.findById(cartaoId);
		if (buscaCartao.isEmpty()) {
			return ResponseEntity.notFound().build();
		}


		Cartao cartao = buscaCartao.get();
		if (cartao.isBloqueado()) {
			return ResponseEntity.unprocessableEntity().body("Cartão já se encontra bloqueado!");
		} 
		
		String retornoSistemaLegado = informaBloqueioCartaoParaSistemaLegado(cartaoId);
		if(retornoSistemaLegado.equals("BLOQUEADO")) {
			System.out.println("Bloqueou");
			
			cartao.bloquear();

			WebAuthenticationDetails details = (WebAuthenticationDetails) authentication.getDetails();
			String remoteIp = details.getRemoteAddress();
			String userAgent = request.getHeader("User-agent");
			
			bloqueiosRepository.save(new Bloqueio(buscaCartao.get(), remoteIp, userAgent));
		}


		return ResponseEntity.ok().build();
	}

	private String informaBloqueioCartaoParaSistemaLegado(String cartaoId) {
		try {
			SolicitacaoBloqueioResponse response = clientBloqueioDeCartao.bloqueiaCartao(cartaoId,
					new SolicitacaoBloqueioRequest("propostas-api"));
			System.out.println("Entrou no try");
			return response.getResultado();
		} catch (FeignClientException e) {
			System.out.println("Entrou no catch");
			System.out.println("Exception: " + e.getMessage());
			return "ERROR";
		}
	}

}

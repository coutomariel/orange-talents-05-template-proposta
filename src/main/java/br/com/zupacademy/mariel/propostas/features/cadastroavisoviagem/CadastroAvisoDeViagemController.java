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

import br.com.zupacademy.mariel.propostas.domain.entities.Cartao;
import br.com.zupacademy.mariel.propostas.domain.repositories.AvisosViagemRepository;
import br.com.zupacademy.mariel.propostas.domain.repositories.CartaoRepository;

@RestController
@RequestMapping("/cartoes")
public class CadastroAvisoDeViagemController {
	
	@Autowired
	private CartaoRepository cartaoRespository;
	
	@Autowired
	private AvisosViagemRepository viagensRepository;
	
	
	@Transactional
	@PostMapping("/{cartaoId}/viagens")
	public ResponseEntity<?> criaAvisoViagem(HttpServletRequest req, Authentication auth, 
			@PathVariable String cartaoId, @RequestBody @Valid AvisoViagemRequest viagemRequest) {
		
		if(!cartaoRespository.existsById(cartaoId)) {
			return ResponseEntity.notFound().build();
		}

		Cartao cartao = cartaoRespository.findById(cartaoId).get();
		WebAuthenticationDetails details = (WebAuthenticationDetails) auth.getDetails();
		String remoteIp = details.getRemoteAddress();
		String userAgent = req.getHeader("User-agent");
		
		viagensRepository.save(viagemRequest.toModel(cartao, remoteIp, userAgent));
		
		return ResponseEntity.ok().build();
	}

}

package br.com.zupacademy.mariel.propostas.cadastrobiometrias;

import java.net.URI;
import java.util.Base64;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupacademy.mariel.propostas.entities.Biometria;
import br.com.zupacademy.mariel.propostas.novaspropostas.entities.Cartao;
import br.com.zupacademy.mariel.propostas.novaspropostas.repositories.CartaoRepository;
import br.com.zupacademy.mariel.propostas.repositories.BiometriaRepository;

@RestController
@RequestMapping("/biometrias")
public class CadastroBiometriaController {
	
	@Autowired
	private CartaoRepository cartaoRepository;
	
	@Autowired
	private BiometriaRepository biometriaRepository;
	
	
	@GetMapping("/{cartaoId}")
	public ResponseEntity<?> criaBiometria(@PathVariable String cartaoId, @RequestBody @Valid BiometriaRequest request, UriComponentsBuilder uriBuilder) {
		
		Optional<Cartao> cartao = cartaoRepository.findById(cartaoId);
		if(cartao.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		String isBase64 = Base64.getDecoder().decode(request.getBiometria()).toString();
		System.out.println("Is Base64? " + isBase64);
		
		Biometria novaBiometria = biometriaRepository.save(request.toModel(cartao.get()));
		
		URI uri = uriBuilder.path("/biometrias/{id}").buildAndExpand(novaBiometria.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

}

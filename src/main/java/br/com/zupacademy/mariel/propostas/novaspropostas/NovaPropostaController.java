package br.com.zupacademy.mariel.propostas.novaspropostas;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/propostas")
public class NovaPropostaController {

	private PropostasRepository propostasRepository;

	@Autowired
	public NovaPropostaController(PropostasRepository propostasRepository) {
		this.propostasRepository = propostasRepository;
	}

	@PostMapping
	public ResponseEntity<?> criaProposta(@RequestBody @Valid PropostaRequest request, UriComponentsBuilder uriBuilder) {
		Proposta novaProposta = propostasRepository.save(request.toModel());
		URI uri = uriBuilder.path("/propostas/{id}").buildAndExpand(novaProposta.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
}

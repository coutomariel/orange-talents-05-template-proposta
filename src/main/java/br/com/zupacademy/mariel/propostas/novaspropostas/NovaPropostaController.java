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

import br.com.zupacademy.mariel.propostas.novaspropostas.config.validation.error.ErrorFieldValidation;
import br.com.zupacademy.mariel.propostas.novaspropostas.config.validation.error.ErrorsResponseDto;

@RestController
@RequestMapping("/propostas")
public class NovaPropostaController {

	private PropostasRepository propostasRepository;

	@Autowired
	public NovaPropostaController(PropostasRepository propostasRepository) {
		this.propostasRepository = propostasRepository;
	}

	@PostMapping
	public ResponseEntity<?> criaProposta(@RequestBody @Valid PropostaRequest request,
			UriComponentsBuilder uriBuilder) {

		if (jaExistePropostaParaEsteDocumento(request.getDocumento())) {
			ErrorsResponseDto response = new ErrorsResponseDto();
			response.addError(new ErrorFieldValidation("documento", "Já existe proposta para este documento"));
			return ResponseEntity.unprocessableEntity().body(response);
		}
		Proposta novaProposta = propostasRepository.save(request.toModel());
		URI uri = uriBuilder.path("/propostas/{id}").buildAndExpand(novaProposta.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	private boolean jaExistePropostaParaEsteDocumento(String documento) {
		return propostasRepository.findByDocumento(documento).isPresent();
	}

}

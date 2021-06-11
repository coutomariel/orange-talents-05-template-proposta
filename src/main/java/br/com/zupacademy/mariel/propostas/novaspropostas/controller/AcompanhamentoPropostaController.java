package br.com.zupacademy.mariel.propostas.novaspropostas.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.mariel.propostas.novaspropostas.controller.response.PropostaResponse;
import br.com.zupacademy.mariel.propostas.novaspropostas.entities.Proposta;
import br.com.zupacademy.mariel.propostas.novaspropostas.repositories.PropostasRepository;

@RestController
@RequestMapping("/propostas")
public class AcompanhamentoPropostaController {
	
	@Autowired
	private PropostasRepository propostasRepository;
	
	
	@GetMapping("/{id}")
	public ResponseEntity<?> consultaProposta(@PathVariable Long id) {
		
		Optional<Proposta> proposta = propostasRepository.findById(id);
		if(proposta.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(new PropostaResponse(proposta.get()));
	}

}

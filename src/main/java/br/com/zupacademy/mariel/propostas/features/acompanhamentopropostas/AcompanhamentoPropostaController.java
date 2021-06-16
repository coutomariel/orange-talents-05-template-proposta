package br.com.zupacademy.mariel.propostas.features.acompanhamentopropostas;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.mariel.propostas.domain.entities.Proposta;
import br.com.zupacademy.mariel.propostas.domain.repositories.PropostasRepository;
import br.com.zupacademy.mariel.propostas.features.cadastropropostas.PropostaResponse;

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

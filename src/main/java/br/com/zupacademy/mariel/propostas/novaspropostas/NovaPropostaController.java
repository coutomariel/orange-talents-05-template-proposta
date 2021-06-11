package br.com.zupacademy.mariel.propostas.novaspropostas;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupacademy.mariel.propostas.feignclients.consultadocumento.ConsultaDocumentoClientRequest;
import br.com.zupacademy.mariel.propostas.feignclients.consultadocumento.ConsultaDocumentoFeignClient;
import br.com.zupacademy.mariel.propostas.novaspropostas.entities.Proposta;
import br.com.zupacademy.mariel.propostas.novaspropostas.entities.StatusProposta;
import br.com.zupacademy.mariel.propostas.novaspropostas.repositories.PropostasRepository;
import feign.FeignException;

@RestController
@RequestMapping("/propostas")
public class NovaPropostaController {

	@Autowired
	private PropostasRepository propostasRepository;

	@Autowired
	private ConsultaDocumentoFeignClient consultaDocumentoFeignClient;

	@PostMapping
	@Transactional
	public ResponseEntity<?> criaProposta(@RequestBody @Valid PropostaRequest req, UriComponentsBuilder uriBuilder) {

		Optional<Proposta> propostaComDocumentoInformado = propostasRepository.findByDocumento(req.getDocumento());
		if (propostaComDocumentoInformado.isPresent()) {
			Map<String, String> response = new HashMap<String, String>();
			response.put("Documento", "Já existe proposta para este documento");
			return ResponseEntity.unprocessableEntity().body(response);
		}

		Proposta novaProposta = propostasRepository.save(req.toModel());
		StatusProposta statusConsulta = consultaStatusCliente(novaProposta);
		novaProposta.setStatus(statusConsulta);

		URI uri = uriBuilder.path("/propostas/{id}").buildAndExpand(novaProposta.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	private StatusProposta consultaStatusCliente(Proposta proposta) {
		ConsultaDocumentoClientRequest consulta = new ConsultaDocumentoClientRequest(proposta.getNome(),
				proposta.getDocumento(), String.valueOf(proposta.getId()));

		try {
			String statusDaConsulta = consultaDocumentoFeignClient.consultaDocumento(consulta)
					.getResultadoSolicitacao();
			return "COM_RESTRICAO".equals(statusDaConsulta) ? StatusProposta.NAO_ELEGIVEL : StatusProposta.ELEGIVEL;
		} catch (FeignException e) {
			return StatusProposta.NAO_ELEGIVEL;
		}
	}
}

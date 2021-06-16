package br.com.zupacademy.mariel.propostas.clientsfeign.comunicabloqueiodecartao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Component
@FeignClient(name = "legado-cartoes", url = "http://localhost:8888/api", path = "/cartoes")
public interface ComunicaBloqueioDeCartaoFeignClient {

	@PostMapping("/{cartaoId}/bloqueios")
	SolicitacaoBloqueioResponse bloqueiaCartao(@PathVariable String cartaoId, 
			@RequestBody SolicitacaoBloqueioRequest request);
}

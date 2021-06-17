package br.com.zupacademy.mariel.propostas.clientsfeign.comunicaavisodeviagem;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Configuration
@FeignClient(name = "avisosdeviagem-api", url = "http://localhost:8888/api", path = "/cartoes")
public interface ComunicaAvisoDeViagenFeignClient {
	
	@PostMapping("/{cartaoId}/avisos")
	AvisoViagemClientResponse comunicaAvisoViagem(@PathVariable String cartaoId, @RequestBody AvisoViagemClientRequest aviso);
}

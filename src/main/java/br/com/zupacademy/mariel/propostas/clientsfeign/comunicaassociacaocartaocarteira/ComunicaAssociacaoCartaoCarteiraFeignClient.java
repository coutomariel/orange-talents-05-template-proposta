package br.com.zupacademy.mariel.propostas.clientsfeign.comunicaassociacaocartaocarteira;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Configuration
@FeignClient(name = "carteiras-api", url = "http://localhost:8888/api", path = "/cartoes")
public interface ComunicaAssociacaoCartaoCarteiraFeignClient {

	@PostMapping("/{cartaoId}/carteiras")
	AssociacaoClientResponse comunicaAvisoViagem(@PathVariable String cartaoId, @RequestBody AssociacaoClientRequest associacao);
}
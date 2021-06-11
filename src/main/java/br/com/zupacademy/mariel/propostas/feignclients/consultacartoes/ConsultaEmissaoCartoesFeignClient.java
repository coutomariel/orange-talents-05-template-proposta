package br.com.zupacademy.mariel.propostas.feignclients.consultacartoes;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.zupacademy.mariel.propostas.novaspropostas.entities.Cartao;

@Component
@FeignClient(name = "emissor-cartoes", url = "http://localhost:8888/api", path = "/cartoes")
public interface ConsultaEmissaoCartoesFeignClient {

	@GetMapping("/?idProposta={idProposta}")
	Cartao consultaCartaoParaProposta(@PathVariable("idProposta") String idProposta);

}

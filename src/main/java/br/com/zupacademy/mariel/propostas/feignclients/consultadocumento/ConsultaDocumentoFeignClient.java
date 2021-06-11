package br.com.zupacademy.mariel.propostas.feignclients.consultadocumento;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Component
@FeignClient(name = "analise-documento", url = "http://localhost:9999/api", path = "/solicitacao")
public interface ConsultaDocumentoFeignClient {

	@Bean
	@PostMapping
	ConsultaDocumentoClientResponse consultaDocumento(@RequestBody ConsultaDocumentoClientRequest request);

}

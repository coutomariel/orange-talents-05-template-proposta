package br.com.zupacademy.mariel.propostas.health;

import java.net.Socket;

import org.springframework.boot.actuate.health.Health;
import org.springframework.cloud.client.discovery.health.DiscoveryHealthIndicator;
import org.springframework.stereotype.Component;


@Component
public class HealthCheckServicoConsultaDocumentoProposta implements DiscoveryHealthIndicator{

	private final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(HealthCheckServicoConsultaDocumentoProposta.class);

	  private static final String URL 
	    = "http://localhost:9999/api/solicitacao";

	  @Override
	  public Health health() {
	    // check if url shortener service url is reachable
	    try (Socket socket = 
	        new Socket(new java.net.URL(URL).getHost(),9999)) {
	    } catch (Exception e) {
	      log.warn("Failed to connect to: {}",URL);
	      return Health.down()
	        .withDetail("error", e.getMessage())
	        .build();
	    }
	    return Health.up().build();
	  }

	@Override
	public String getName() {
		return "Serviço consulta doc da proposta";
	}

}

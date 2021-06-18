package br.com.zupacademy.mariel.propostas.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zupacademy.mariel.propostas.domain.entities.Carteira;
import br.com.zupacademy.mariel.propostas.domain.entities.TipoCarteira;

public interface CarteirasRepository extends JpaRepository<Carteira, Long> {

	boolean existsByTipoCarteiraAndCartaoId(TipoCarteira paypal, String cartaoId);

}

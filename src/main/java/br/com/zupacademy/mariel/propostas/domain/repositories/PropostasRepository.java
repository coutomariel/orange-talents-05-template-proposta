package br.com.zupacademy.mariel.propostas.domain.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zupacademy.mariel.propostas.domain.entities.Proposta;
import br.com.zupacademy.mariel.propostas.domain.entities.StatusProposta;

public interface PropostasRepository extends JpaRepository<Proposta, Long> {

	Optional<Proposta> findByDocumento(String documento);
	List<Proposta> findByStatusAndCartaoIsNull(StatusProposta elegivel);

}

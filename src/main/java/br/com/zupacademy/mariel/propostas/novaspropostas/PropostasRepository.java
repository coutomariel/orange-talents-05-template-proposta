package br.com.zupacademy.mariel.propostas.novaspropostas;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PropostasRepository extends JpaRepository<Proposta, Long> {

	Optional<Proposta> findByDocumento(String documento);

}

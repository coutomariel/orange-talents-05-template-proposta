package br.com.zupacademy.mariel.propostas.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zupacademy.mariel.propostas.domain.entities.Bloqueio;

public interface BloqueiosRepository extends JpaRepository<Bloqueio, Long> {

}

package br.com.zupacademy.mariel.propostas.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zupacademy.mariel.propostas.domain.entities.Cartao;

public interface CartaoRepository extends JpaRepository<Cartao, String>{

}

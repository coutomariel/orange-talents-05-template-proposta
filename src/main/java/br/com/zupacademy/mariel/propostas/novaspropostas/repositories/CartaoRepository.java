package br.com.zupacademy.mariel.propostas.novaspropostas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zupacademy.mariel.propostas.novaspropostas.entities.Cartao;

public interface CartaoRepository extends JpaRepository<Cartao, String>{

}

package com.transportadora.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.transportadora.model.Frete;

@Repository
public interface Fretes extends JpaRepository<Frete, Long> {

	public List<Frete> findByDescricaoContaining(String descricao);
	
}

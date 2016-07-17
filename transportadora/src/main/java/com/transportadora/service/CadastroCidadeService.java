package com.transportadora.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.transportadora.model.Cidade;
import com.transportadora.repository.Cidades;
import com.transportadora.repository.filter.CidadeFilter;

@Service
public class CadastroCidadeService {

	@Autowired
	private Cidades cidades;
	
	public void salvar(Cidade cidade) {
		try {
			cidades.save(cidade);
			// gerar um log
		} catch (Exception e) {
			throw e;
		}
	}

	public void excluir(Long codigo) {
		cidades.delete(codigo);
	}
	
	public List<Cidade> filtrar(CidadeFilter filtro) {
		String nome = filtro.getNome() == null ? "%" : filtro.getNome();
		return cidades.findByNomeContaining(nome);
	}
	
	public List<Cidade> filtrar() {
		return cidades.findAll();
	}
	
}

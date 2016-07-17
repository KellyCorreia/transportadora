package com.transportadora.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.transportadora.model.Frete;
import com.transportadora.repository.Cidades;
import com.transportadora.repository.Clientes;
import com.transportadora.repository.Fretes;
import com.transportadora.repository.filter.FreteFilter;

@Service
public class CadastroFreteService {

	@Autowired
	private Fretes fretes;
	
	public void salvar(Frete frete) {
		try {
			fretes.save(frete);
		} catch (Exception e) {
			throw e;
		}
	}

	public void excluir(Long codigo) {
		fretes.delete(codigo);
	}
	
	public List<Frete> filtrar(FreteFilter filtro) {
		String descricao = filtro.getDescricao() == null ? "%" : filtro.getDescricao();
		return fretes.findByDescricaoContaining(descricao);
	}

	public void calcularPreco(Frete frete) {
		frete.setPreco(frete.getPesoTotal().multiply(new BigDecimal("0.1")).add(frete.getCidade().getTaxa()));
		System.out.println("PESO = " + frete.getPreco());
	}
}

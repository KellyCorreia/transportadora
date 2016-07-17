package com.transportadora.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.transportadora.model.Cliente;
import com.transportadora.repository.Clientes;
import com.transportadora.repository.filter.ClienteFilter;

@Service
public class CadastroClienteService {

	@Autowired
	private Clientes clientes;
	
	public void salvar(Cliente cliente) {
		try {
			clientes.save(cliente);
			// gerar um log
		} catch (Exception e) {
			throw e;
		}
	}

	public void excluir(Long codigo) {
		clientes.delete(codigo);
	}
	
	public List<Cliente> filtrar(ClienteFilter filtro) {
		String nome = filtro.getNome() == null ? "%" : filtro.getNome();
		return clientes.findByNomeContaining(nome);
	}
	
	public List<Cliente> filtrar() {
		return clientes.findAll();
	}
	
}

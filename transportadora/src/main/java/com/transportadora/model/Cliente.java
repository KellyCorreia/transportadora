package com.transportadora.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;


@Entity
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@NotEmpty(message = "Nome é obrigatório")
	@Size(max = 60, message = "O nome não pode conter mais de 60 caracteres")
	private String nome;
	
	@NotEmpty(message = "Endereço é obrigatório")
	@Size(max = 100, message = "O endereço não pode conter mais de 100 caracteres")
	private String endereco;
	
	@NotEmpty(message = "Telefone é obrigatório")
	@Size(max = 13, min = 8, message = "O telefone deve conter entre 8 e 13 caracteres")
	private String telefone;
	
	@OneToMany (mappedBy = "cliente", targetEntity = Frete.class, 
			fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	List<Frete> historicoFretes;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public List<Frete> getHistoricoFretes() {
		return historicoFretes;
	}

	public void setHistoricoFretes(List<Frete> historicoFretes) {
		this.historicoFretes = historicoFretes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

}

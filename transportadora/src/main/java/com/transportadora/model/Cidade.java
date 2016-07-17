package com.transportadora.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.NumberFormat;

@Entity
public class Cidade {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@NotEmpty(message = "Nome é obrigatório")
	@Size(max = 60, message = "O nome não pode conter mais de 60 caracteres")
	private String nome;
	
	@Enumerated(EnumType.STRING)
	private EstadosBrasileiros estado;
	
	@NotNull(message = "A taxa de entrega é obrigatória")
	@DecimalMin(value = "0.01", message = "A taxa de entrega não pode ser menor que 0,01")
	@DecimalMax(value = "9999999.99", message = "A taxa de entrega não pode ser maior que 9.999.999,99")
	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal taxa;
	
	@OneToMany (mappedBy = "cidade", targetEntity = Frete.class, 
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

	public EstadosBrasileiros getEstado() {
		return estado;
	}

	public void setEstado(EstadosBrasileiros estado) {
		this.estado = estado;
	}

	public BigDecimal getTaxa() {
		return taxa;
	}

	public void setTaxa(BigDecimal taxa) {
		this.taxa = taxa;
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
		Cidade other = (Cidade) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

}

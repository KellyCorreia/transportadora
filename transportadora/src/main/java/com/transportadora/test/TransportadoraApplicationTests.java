package com.transportadora.test;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.transportadora.TransportadoraApplication;
import com.transportadora.model.Cidade;
import com.transportadora.repository.filter.CidadeFilter;
import com.transportadora.service.CadastroCidadeService;

import org.junit.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TransportadoraApplication.class)
@WebAppConfiguration
public class TransportadoraApplicationTests {
	
	@Test
	public void contextLoads() {
	}
	
	/*@Test
	public void DeveSalvarCidade(){
		Cidade c = new Cidade();
		CadastroCidadeService servico = new CadastroCidadeService();
		
		c.setNome("Vitória");
		c.setTaxa(new BigDecimal("20.7"));
		
		servico.salvar(c);
		
		CidadeFilter filtro = new CidadeFilter();
		filtro.setNome("Vitória");
		
		List<Cidade> lista = servico.filtrar(filtro);
		
		Assert.assertTrue(lista.size()>0);
		
	}*/

}

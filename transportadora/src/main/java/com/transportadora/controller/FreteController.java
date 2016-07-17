package com.transportadora.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.transportadora.model.Cidade;
import com.transportadora.model.Cliente;
import com.transportadora.model.Frete;
import com.transportadora.repository.filter.FreteFilter;
import com.transportadora.service.CadastroCidadeService;
import com.transportadora.service.CadastroClienteService;
import com.transportadora.service.CadastroFreteService;


@Controller
@RequestMapping("/fretes")
public class FreteController {
	
	private static final String CADASTRO_VIEW = "CadastroFrete";
	
	@Autowired
	private CadastroFreteService cadastroFreteService;
	
	@Autowired
	private CadastroClienteService cadastroClienteService;
	
	@Autowired
	private CadastroCidadeService cadastroCidadeService;

	@RequestMapping("/novo")
	public String novo(Frete frete) {
		return CADASTRO_VIEW;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Frete frete, Errors errors, RedirectAttributes attributes) {
		if (errors.hasErrors()) {
			return CADASTRO_VIEW;
		}
		
		try {
			cadastroFreteService.calcularPreco(frete);
			cadastroFreteService.salvar(frete);
			attributes.addFlashAttribute("mensagem", "Frete salvo com sucesso!");
			return "redirect:/fretes/novo";
		
		} catch (Exception e) {
			errors.reject("Erro: ", e.getMessage());
			return CADASTRO_VIEW;
		}
	}
	
	@RequestMapping
	public ModelAndView pesquisar(@ModelAttribute("filtro") FreteFilter filtro) {
		List<Frete> todasFretes = cadastroFreteService.filtrar(filtro);
		
		ModelAndView mv = new ModelAndView("PesquisaFretes");
		mv.addObject("fretes", todasFretes);
		return mv;
	}
	
	@RequestMapping("{codigo}")
	public ModelAndView edicao(@PathVariable("codigo") Frete frete) {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW); 
		mv.addObject(frete);
		return mv;
	}
	
	@RequestMapping(value="{codigo}", method = RequestMethod.DELETE)
	public String excluir(@PathVariable Long codigo, RedirectAttributes attributes) {
		cadastroFreteService.excluir(codigo);
		
		attributes.addFlashAttribute("mensagem", "Frete excluído com sucesso!");
		return "redirect:/fretes";
	}
	
	@ModelAttribute("todosClientes")
	public List<Cliente> todosClientes() {
		List<Cliente> clientes = cadastroClienteService.filtrar();
		System.out.println("=================TODOS OS CLIENTES: "+clientes.size());
		return clientes;
	}
	
	@ModelAttribute("todasCidades")
	public List<Cidade> todasCidades() {
		List<Cidade> cidades = cadastroCidadeService.filtrar();
		return cidades;
	}
	
}

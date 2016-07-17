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

import com.transportadora.model.Cliente;
import com.transportadora.repository.filter.ClienteFilter;
import com.transportadora.service.CadastroClienteService;


@Controller
@RequestMapping("/clientes")
public class ClienteController {
	
	private static final String CADASTRO_VIEW = "CadastroCliente";
	
	@Autowired
	private CadastroClienteService cadastroClienteService;

	@RequestMapping("/novo")
	public String novo(Cliente cliente) {
		return CADASTRO_VIEW;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Cliente cliente, Errors errors, RedirectAttributes attributes) {
		if (errors.hasErrors()) {
			return CADASTRO_VIEW;
		}
		
		try {
			cadastroClienteService.salvar(cliente);
			attributes.addFlashAttribute("mensagem", "Cliente salvo com sucesso!");
			return "redirect:/clientes/novo";
		
		} catch (Exception e) {
			errors.reject("Erro: ", e.getMessage());
			return CADASTRO_VIEW;
		}
	}
	
	@RequestMapping
	public ModelAndView pesquisar(@ModelAttribute("filtro") ClienteFilter filtro) {
		List<Cliente> todosClientes = cadastroClienteService.filtrar(filtro);
		
		ModelAndView mv = new ModelAndView("PesquisaClientes");
		mv.addObject("clientes", todosClientes);
		return mv;
	}
	
	@RequestMapping("{codigo}")
	public ModelAndView edicao(@PathVariable("codigo") Cliente cliente) {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW); 
		mv.addObject(cliente);
		return mv;
	}
	
	@RequestMapping(value="{codigo}", method = RequestMethod.DELETE)
	public String excluir(@PathVariable Long codigo, RedirectAttributes attributes) {
		cadastroClienteService.excluir(codigo);
		
		attributes.addFlashAttribute("mensagem", "Cliente excluído com sucesso!");
		return "redirect:/clientes";
	}
	
}

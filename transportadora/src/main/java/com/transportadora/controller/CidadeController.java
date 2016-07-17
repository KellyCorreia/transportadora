package com.transportadora.controller;

import java.util.Arrays;
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
import com.transportadora.model.EstadosBrasileiros;
import com.transportadora.repository.filter.CidadeFilter;
import com.transportadora.service.CadastroCidadeService;


@Controller
@RequestMapping("/cidades")
public class CidadeController {
	
	private static final String CADASTRO_VIEW = "CadastroCidade";
	
	@Autowired
	private CadastroCidadeService cadastroCidadeService;

	@RequestMapping("/novo")
	public String novo(Cidade cidade) {
		return CADASTRO_VIEW;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Cidade cidade, Errors errors, RedirectAttributes attributes) {
		if (errors.hasErrors()) {
			return CADASTRO_VIEW;
		}
		
		try {
			cadastroCidadeService.salvar(cidade);
			attributes.addFlashAttribute("mensagem", "Cidade salva com sucesso!");
			return "redirect:/cidades/novo";
		
		} catch (Exception e) {
			errors.reject("Erro: ", e.getMessage());
			return CADASTRO_VIEW;
		}
	}
	
	@RequestMapping
	public ModelAndView pesquisar(@ModelAttribute("filtro") CidadeFilter filtro) {
		List<Cidade> todasCidades = cadastroCidadeService.filtrar(filtro);
		
		ModelAndView mv = new ModelAndView("PesquisaCidades");
		mv.addObject("cidades", todasCidades);
		return mv;
	}
	
	@RequestMapping("{codigo}")
	public ModelAndView edicao(@PathVariable("codigo") Cidade cidade) {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW); 
		mv.addObject(cidade);
		return mv;
	}
	
	@RequestMapping(value="{codigo}", method = RequestMethod.DELETE)
	public String excluir(@PathVariable Long codigo, RedirectAttributes attributes) {
		cadastroCidadeService.excluir(codigo);
		
		attributes.addFlashAttribute("mensagem", "Cidade excluída com sucesso!");
		return "redirect:/cidades";
	}
	
	@ModelAttribute("todosEstadosBrasileiros")
	public List<EstadosBrasileiros> todosEstadosBrasileiros() {
		return Arrays.asList(EstadosBrasileiros.values());
	}
	
}

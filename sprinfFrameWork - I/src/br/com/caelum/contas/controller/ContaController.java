package br.com.caelum.contas.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.caelum.contas.dao.ContaDAO;
import br.com.caelum.contas.modelo.Conta;

@Controller
public class ContaController {
	
	private ContaDAO dao;
	
	@Autowired
	public ContaController(ContaDAO dao){
		this.dao = dao;
	}

	// ContaDAO dao = new ContaDAO();

	@RequestMapping("formulario")
	public String formulario() {
		return "conta/formulario";
	}

	@RequestMapping("adicionaConta")
	public String adiciona(@Valid Conta conta, BindingResult result) {

		if (result.hasErrors()) {
			return "conta/formulario";
		}

		dao.adiciona(conta);

		return "conta/conta-adicionada";
	}

	@RequestMapping("listaContas")
	public ModelAndView listaContas() {

		List<Conta> contas = dao.lista();

		ModelAndView mv = new ModelAndView("conta/lista-contas");

		mv.addObject("contas", contas);
		return mv;
	}

	@RequestMapping("removeConta")
	public String remove(Conta conta) {

		dao.remove(conta);
		return "redirect:listaContas";
	}

	@RequestMapping("pagaConta")
	public void pagaConta(Conta conta, HttpServletResponse response) {

		dao.paga(conta.getId());
		response.setStatus(200);
	}

}

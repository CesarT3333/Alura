package br.com.casadocodigo.loja.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.casadocodigo.loja.dao.ProdutoDAO;
import br.com.casadocodigo.loja.modelo.Produto;

@Controller
public class HomeController {

	@Autowired
	ProdutoDAO produtoDao;

	@Cacheable(value = "produtosHome")
	@RequestMapping("/")
	public ModelAndView index() {

		ModelAndView mv = new ModelAndView("home");
		List<Produto> lista = produtoDao.listar();

		mv.addObject("produtos", lista);

		return mv;
	}

}

package br.com.casadocodigo.loja.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import br.com.casadocodigo.loja.dao.ProdutoDAO;
import br.com.casadocodigo.loja.modelo.CarrinhoCompras;
import br.com.casadocodigo.loja.modelo.CarrinhoItem;
import br.com.casadocodigo.loja.modelo.Produto;
import br.com.casadocodigo.loja.modelo.TipoPreco;

@Controller
@RequestMapping("/carrinho")
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class CarrinhoComprasController {

	@Autowired
	private CarrinhoCompras carrinho;

	@Autowired
	private ProdutoDAO produtoDao;

	@RequestMapping("/add")
	public ModelAndView add(Integer produtoId, TipoPreco tipo) {
		ModelAndView modelAndView = new ModelAndView("redirect:/carrinho");
		CarrinhoItem carrinhoItem = criaItem(produtoId, tipo);
		carrinho.add(carrinhoItem);
		return modelAndView;
	}

	private CarrinhoItem criaItem(Integer id, TipoPreco tipoPreco) {

		Produto produto = produtoDao.find(id);
		CarrinhoItem carrinhoItem = new CarrinhoItem(produto, tipoPreco);

		carrinho.add(carrinhoItem);

		return carrinhoItem;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView itens() {
		ModelAndView mv = new ModelAndView("/carrinho/itens");

		return mv;
	}

	@RequestMapping("/remover")
	public ModelAndView remover(Integer produtoId, TipoPreco tipoPreco) {
		carrinho.remover(produtoId, tipoPreco);

		return new ModelAndView("redirect:carrinho");
	}
}

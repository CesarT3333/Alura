package br.com.casadocodigo.loja.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.loja.dao.ProdutoDAO;
import br.com.casadocodigo.loja.infra.FileSaver;
import br.com.casadocodigo.loja.modelo.Autor;
import br.com.casadocodigo.loja.modelo.Produto;
import br.com.casadocodigo.loja.modelo.TipoPreco;
import br.com.casadocodigo.loja.validation.ProdutoValidation;

@Controller
@RequestMapping("/produtos")
public class ProdutosController {

	@Autowired
	ProdutoDAO dao;

	@Autowired
	private FileSaver fileSaver;

	@InitBinder
	public void initBinder(WebDataBinder binder) {

		binder.addValidators(new ProdutoValidation());
	}

	@RequestMapping("/detalhe/{id}")
	public ModelAndView detalhe(@PathVariable("id") Integer id) {

		ModelAndView mv = new ModelAndView("produtos/detalhe");

		Produto produto = dao.find(id);

		mv.addObject("produto", produto);

		return mv;
	}
	
//	@RequestMapping("/{id}")
//	@ResponseBody
//	public Produto detalheJson(@PathVariable("id") Integer id) {
//
//		return dao.find(id);
//	}

	@RequestMapping("/form")
	public ModelAndView form(Produto produto) {

		ModelAndView mv = new ModelAndView("produtos/form");
		mv.addObject("tipos", TipoPreco.values());
		mv.addObject("autores", Autor.class);

		return mv;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView listar() {

		List<Produto> lista = dao.listar();

		ModelAndView mv = new ModelAndView("produtos/lista");
		mv.addObject("produtos", lista);

		return mv;
	}

	@RequestMapping(method = RequestMethod.POST)
	@CacheEvict(value = "produtosHome", allEntries = true)
	public ModelAndView gravar(@Valid Produto produto, BindingResult result, RedirectAttributes redirectAttributes,
			MultipartFile sumario) {

		System.out.println(sumario.getOriginalFilename());
		if (result.hasErrors()) {
			System.out.println("oe");
			return form(produto);
		}

		String path = fileSaver.write("arquivo-sumairio", sumario);
		produto.setSumarioPath(path);

		dao.grava(produto);
		redirectAttributes.addFlashAttribute("sucesso", "Produto cadastrado com sucesso!");
		return new ModelAndView("redirect:produtos");
	}

}

package br.com.casadocodigo.loja.controller;

import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.loja.modelo.CarrinhoCompras;
import br.com.casadocodigo.loja.modelo.DadosPagamentos;

@Controller
@RequestMapping("/pagamento")
public class PagamentoController {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private CarrinhoCompras carrinho;

	@RequestMapping(value = "/finalizar", method = RequestMethod.POST)
	public Callable<ModelAndView> finalizar(RedirectAttributes model) {

		return () -> {

			String uri = "http://book-payment.herokuapp.com/payment";
			ModelAndView mv = new ModelAndView("redirect:/produtos");

			try {
				String response = restTemplate.postForObject(uri, new DadosPagamentos(carrinho.getTotal()),
						String.class);
				model.addFlashAttribute("sucesso", "Pagamento Realizado com Sucesresponseso");
				System.out.println(response);
				return mv;

			} catch (HttpClientErrorException e) {
				e.printStackTrace();
				model.addFlashAttribute("falha", "Valor maior que o permitido");
				return mv;
			}
		};

	}

}

package br.com.caelum.livraria.webservice;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import br.com.caelum.livraria.dao.LivroDao;
import br.com.caelum.livraria.modelo.Livro;

@WebService
@Stateless
public class LivrariaWS {

	@Inject
	LivroDao livroDao;

	@WebResult(name = "Livros")
	public List<Livro> getLivroPeloNome(@WebParam(name = "Titulo") String nome) {

		System.out.println("Procurando livro pelo nome " + nome);

		return livroDao.livrosPeloNome(nome);

	}

}

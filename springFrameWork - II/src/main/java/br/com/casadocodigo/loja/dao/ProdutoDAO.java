package br.com.casadocodigo.loja.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.casadocodigo.loja.modelo.Produto;

@Repository
@Transactional // Do spring
public class ProdutoDAO {

	@PersistenceContext
	private EntityManager em;

	public void grava(Produto p) {

		em.persist(p);

	}

	public List<Produto> listar() {

		String jstl = "SELECT p FROM Produto p";

		List<Produto> lista = em.createQuery(jstl, Produto.class).getResultList();

		return lista;
	}

	public Produto find(Integer id) {
		return em.createQuery("select distinct(p) from Produto p join fetch p.preco preco where p.id = :id",
				Produto.class).setParameter("id", id).getSingleResult();

	}
}

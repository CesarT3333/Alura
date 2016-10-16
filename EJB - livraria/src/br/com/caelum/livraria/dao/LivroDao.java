package br.com.caelum.livraria.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.caelum.livraria.modelo.Livro;

@Stateless
public class LivroDao {

	@PersistenceContext
	private EntityManager em;

	public void salva(Livro livro) {
		em.persist(livro);
	}

	public List<Livro> todosLivros() {
		return em.createQuery("SELECT l FROM Livro l", Livro.class).getResultList();
	}

	public List<Livro> livrosPeloNome(String nome) {
		TypedQuery<Livro> query = this.em.createQuery("select l from Livro l where l.titulo like :pTitulo", Livro.class)
				.setParameter("pTitulo", "%" + nome + "%");

		return query.getResultList();

	}

}

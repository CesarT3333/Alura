package br.com.caelum.livraria.dao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.caelum.livraria.interceptador.Loginterceptador;
import br.com.caelum.livraria.modelo.Autor;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
//@Interceptors({ Loginterceptador.class })
public class AutorDao {

	@PersistenceContext
	private EntityManager em;

	@PostConstruct
	void aposCriacao() {
		System.out.println("Eita");
	}

	@TransactionAttribute(TransactionAttributeType.MANDATORY)
	public void salva(Autor autor) {
		em.persist(autor);
	}

	public List<Autor> todosAutores() {
		String jpql = "SELECT a FROM Autor a";
		return em.createQuery(jpql, Autor.class).getResultList();
	}

	public Autor buscaPelaId(Integer autorId) {
		// String jpql = "SELECT a FROM Autor a WHERE id = :pIdAutor";
		// try {
		// TypedQuery<Autor> query = em.createQuery(jpql,
		// Autor.class).setParameter("pIdAutor", autorId);
		// return query.getSingleResult();
		//
		// } catch (Exception e) {
		// return null;
		// }
		Autor autor = em.find(Autor.class, autorId);
		return autor;
	}

}

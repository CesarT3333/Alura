package br.com.caelum.livraria.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.livraria.dao.AutorDao;
import br.com.caelum.livraria.dao.DAO;
import br.com.caelum.livraria.modelo.Autor;
import br.com.caelum.livraria.tx.Transacional;

@Named
@ViewScoped
public class AutorBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Autor autor = new Autor();
	private Integer autorId;

	@Inject
	private AutorDao dao;

	public AutorBean(AutorDao dao) {
		this.dao = dao;
	}

	public List<Autor> getAutores() {
		return this.dao.listaTodos();
	}

	@Transacional
	public String gravar() {
		System.out.println("Gravando autor " + this.getAutor().getNome());

		if (this.getAutor().getId() == null) {
			this.dao.adiciona(this.getAutor());
		} else {
			this.dao.atualiza(this.getAutor());
		}

		this.setAutor(new Autor());

		return "livro?faces-redirect=true";
	}

	public void carregar(Autor autor) {
		System.out.println("Carregando autor");
		this.setAutor(autor);
	}

	public void remover(Autor autor) {
		System.out.println("Removendo autor");
		this.dao.remove(autor);
	}

	public void carregaAutorPelaId() {
		this.setAutor(this.dao.buscaPorId(autorId));
	}

	public Integer getAutorId() {
		return autorId;
	}

	public void setAutorId(Integer autorId) {
		this.autorId = autorId;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

	public Autor getAutor() {
		return autor;
	}

}

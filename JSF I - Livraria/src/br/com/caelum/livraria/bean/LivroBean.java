package br.com.caelum.livraria.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import br.com.caelum.livraria.dao.DAO;
import br.com.caelum.livraria.modelo.Autor;
import br.com.caelum.livraria.modelo.Livro;

// @RequestScope cria um bean a cada request
@ManagedBean
@ViewScoped // Permite o bean receber mais de um request
public class LivroBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Livro livro = new Livro();
	private Integer autorId;
	private Integer livroId;

	public String formAutor() { // Paginação. Retorna uma string com o
								// direcionamento contendo a pagina do autor
		System.out.println("Chamada do formulario Autor");
		return "autor?faces-redirect=true";
	}

	// removerAutorLivro(autor)
	public void removerAutorLivro(Autor autor) {
		// Péssima prática
		// this.livro.getAutores().remove(autor);
		this.livro.removeAutor(autor);
	}
	
	public void carregaLivroPelaId(){
		this.livro = new DAO<Livro>(Livro.class).buscaPorId(livroId);
	}

	public Livro getLivro() {
		return livro;
	}

	public void deletaLivro(Livro livro) {
		System.out.println("Removendo o Livro");
		new DAO<Livro>(Livro.class).remove(livro);
	}

	// public void carregarLivro(Livro livro) {
	// System.out.println("Carregando Livro");
	// this.livro = livro;
	// }

	public void setLivro(Livro livro) { // para o setListener da tela *--*
		this.livro = livro;
	}
	

	public void gravarAutor() {
		// busca do dao do autor uma lista de autores
		Autor autor = new DAO<Autor>(Autor.class).buscaPorId(this.autorId);

		// relaciona o autor com o livro
		this.livro.adicionaAutor(autor);
	}

	public List<Livro> getLivros() {
		return new DAO<Livro>(Livro.class).listaTodos();
	}

	public List<Autor> getAutoresDoLivro() {
		return this.livro.getAutores();
	}

	public List<Autor> getAutores() {
		return new DAO<Autor>(Autor.class).listaTodos();
	}

	public void gravar() {
		System.out.println("Gravando livro " + this.livro.getTitulo());

		if (livro.getAutores().isEmpty()) {
			FacesContext.getCurrentInstance().addMessage("autor",
					new FacesMessage("Livro deve ter pelo menos um autor"));
			return;
		}
		if (livro.getId() == null) {
			new DAO<Livro>(Livro.class).adiciona(this.livro);
		} else {
			new DAO<Livro>(Livro.class).atualiza(this.livro);
		}

		this.livro = new Livro();
	}

	public Integer getAutorId() {
		return autorId;
	}

	public void setAutorId(Integer autorId) {
		this.autorId = autorId;
	}

	public void comecaComDigitoUm(FacesContext fc, UIComponent component, Object value) throws ValidatorException {
		String valor = value.toString();
		if (!valor.startsWith("1")) {
			throw new ValidatorException(new FacesMessage("Deve começar com 1"));
		}

	}

	public Integer getLivroId() {
		return livroId;
	}

	public void setLivroId(Integer livroId) {
		this.livroId = livroId;
	}
}

package br.com.caelum.livraria.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import br.com.caelum.livraria.dao.AutorDao;
import br.com.caelum.livraria.dao.LivroDao;
import br.com.caelum.livraria.modelo.Autor;
import br.com.caelum.livraria.modelo.Livro;
import br.com.caelum.livraria.tx.Transacional;

// @RequestScope cria um bean a cada request
@Named
@ViewScoped // Permite o bean receber mais de um request
public class LivroBean implements Serializable {
	
	@Inject
	FacesContext context;

	private static final long serialVersionUID = 1L;
	private Livro livro = new Livro();
	private Integer autorId;
	private Integer livroId;
	private List<Livro> livros;

	@Inject
	private LivroDao livroDao;

	@Inject
	private AutorDao autorDao;

	public String formAutor() { // Paginação. Retorna uma string com o
								// direcionamento contendo a pagina do autor
		System.out.println("Chamada do formulario Autor");
		return "autor?faces-redirect=true";
	}

	public void removerAutorLivro(Autor autor) {
		this.livro.removeAutor(autor);
	}

	public void carregaLivroPelaId() {
		this.livroDao.buscaPorId(livroId);
	}

	public void carregar(Livro livro){
		this.livro = this.livroDao.buscaPorId(getLivroId());
	}

	@Transacional
	public void deletaLivro(Livro livro) {
		System.out.println("Removendo o Livro");

		this.livroDao.remove(livro);

		this.livros = livroDao.listaTodos();
	}

	public void gravarAutor() {
		Autor autor = autorDao.buscaPorId(this.autorId);
		this.livro.adicionaAutor(autor);
	}

	public List<Livro> getLivros() {

		if (livros == null) {
			this.livros = livroDao.listaTodos();
		}

		return livros;
	}

	public List<Autor> getAutoresDoLivro() {
		return this.livro.getAutores();
	}

	public List<Autor> getAutores() {
		return this.autorDao.listaTodos();
	}

	@Transacional
	public void gravar() {
		System.out.println("Gravando livro " + this.livro.getTitulo());

		if (livro.getAutores().isEmpty()) {
			context.addMessage("autor",
					new FacesMessage("Livro deve ter pelo menos um autor"));
			return;
		}
		// DAO<Livro> dao = new DAO<Livro>(Livro.class);
		if (livro.getId() == null) {
			livroDao.adiciona(this.livro);
			this.livros = livroDao.listaTodos();
		} else {
			livroDao.atualiza(this.livro);
		}

		this.livro = new Livro();
	}

	public void comecaComDigitoUm(FacesContext fc, UIComponent component, Object value) throws ValidatorException {
		String valor = value.toString();
		if (!valor.startsWith("1")) {
			throw new ValidatorException(new FacesMessage("Deve comecar com 1"));
		}

	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) { // para o setListener da tela *--*
		this.livro = livro;
	}

	public Integer getLivroId() {
		return livroId;
	}

	public void setLivroId(Integer livroId) {
		this.livroId = livroId;
	}

	public Integer getAutorId() {
		return autorId;
	}

	public void setAutorId(Integer autorId) {
		this.autorId = autorId;
	}
}

package br.com.caelum.livraria.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.management.Query;

import br.com.caelum.livraria.dao.UsuarioDao;
import br.com.caelum.livraria.modelo.Usuario;

@ManagedBean
@ViewScoped
public class LoginBean {

	private Usuario usuario = new Usuario();

	public String efetuaLogin() {

		boolean existe = new UsuarioDao().existe(this.usuario);
		FacesContext context = FacesContext.getCurrentInstance();

		if (existe) {
			context.getExternalContext().getSessionMap().put("usuarioLogado", this.usuario);

			return "livro?faces-redirect=true";
		}

		System.out.println("Logado com Sucesso!" + this.usuario.getEmail());
		context.addMessage(null, new FacesMessage("Usuário não encontrado =/"));
		return "login?Faces-redirect=true";
	}

	public String deslogar() {

		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getSessionMap().remove("usuarioLogado");

		return "login?Faces-redirect=true";

	}

	public Usuario getUsuario() {
		return usuario;
	}

}

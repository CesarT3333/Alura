package br.com.caelum.contas.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.caelum.contas.dao.UsuarioDAO;
import br.com.caelum.contas.modelo.Usuario;

@Controller
public class usuarioController {

	@RequestMapping("/loginForm")
	public String formulario() {
		return "usuario/login";
	}
	
	
	@RequestMapping("/efetuaLogin")
	public String efetuaLogin(Usuario usuario, HttpSession session){
		
		session.setAttribute("usuarioLogado", usuario);
		UsuarioDAO dao = new UsuarioDAO();
		
		if(dao.existeUsuario(usuario)){
			return "menu";
		}
		return "redirect:loginForm";
	}
	
	@RequestMapping("/efetuaLogout")
	public String efetuaLogOut(Usuario usuario, HttpSession session){
		
		session.setAttribute("usuarioDeslogado", usuario);
		return "redirect:loginForm";
	}

}

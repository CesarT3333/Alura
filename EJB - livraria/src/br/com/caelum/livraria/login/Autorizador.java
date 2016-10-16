package br.com.caelum.livraria.login;

import javax.el.ELContext;
import javax.el.ELResolver;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

public class Autorizador implements PhaseListener {

	private static final long serialVersionUID = 1L;

	public void afterPhase(PhaseEvent event) {

		FacesContext context = event.getFacesContext();

		if ("/login.xhtml".equals(context.getViewRoot().getViewId())) {
			return;
		}

		ELContext elContext = context.getELContext();
		ELResolver elResolver = context.getApplication().getELResolver();
		UsuarioLogadoBean usuarioLogado = (UsuarioLogadoBean) elResolver.getValue(elContext, null, "usuarioLogadoBean");

		// Usando o usuarioLogado que foi injetado
		if (!usuarioLogado.isLogado()) {

			NavigationHandler handler = context.getApplication().getNavigationHandler();
			handler.handleNavigation(context, null, "login?faces-redirect=true");

			// efetua renderizacao da tela
			context.renderResponse();
		}
	}

	public void beforePhase(PhaseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public PhaseId getPhaseId() {
		// TODO Auto-generated method stub
		return null;
	}

}

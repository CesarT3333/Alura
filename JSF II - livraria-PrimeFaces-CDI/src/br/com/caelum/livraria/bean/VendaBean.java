package br.com.caelum.livraria.bean;

import java.io.Serializable;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import br.com.caelum.livraria.modelo.Venda;

@Named
@ViewScoped
public class VendaBean implements Serializable {

	@Inject
	EntityManager manager;

	private static final long serialVersionUID = 1L;

	public BarChartModel getVendasModel() {
		BarChartModel model = new BarChartModel();
		model.setAnimate(true);

		ChartSeries vendaSerie = new ChartSeries();
		vendaSerie.setLabel("Vendas 2016");

		List<Venda> vendas = getVendas();
		for (Venda venda : vendas) {
			vendaSerie.set(venda.getLivro().getTitulo(), venda.getQuantidade());
		}
		model.addSeries(vendaSerie);

		return model;
	}

	public List<Venda> getVendas() {

		String jpql = "select v from Venda v";

		List<Venda> vendas = this.manager.createQuery(jpql, Venda.class).getResultList();
		return vendas;
	}
}

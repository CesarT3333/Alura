package br.com.casadocodigo.loja.modelo;

import java.math.BigDecimal;

public class DadosPagamentos {

	private BigDecimal value;

	public DadosPagamentos(BigDecimal value) {

		this.value = value;
	}

	public DadosPagamentos() {

	}

	public BigDecimal getValue() {
		return value;
	}

}

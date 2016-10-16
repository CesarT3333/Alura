package br.com.caelum.livraria.interceptador;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class Loginterceptador {

	@AroundInvoke
	public Object intercepta(InvocationContext context) throws Exception {

		long millis = System.currentTimeMillis();

		Object o = context.proceed();

		String metodo = context.getMethod().getName();
		String classe = context.getTarget().getClass().getSimpleName();

		System.out.println("Nome da Classe: " + classe + "\n Nome do Método: " + metodo);
		System.out.println("Tempo gasto:" + (System.currentTimeMillis() - millis));

		return o;

	}

}

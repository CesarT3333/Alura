package br.com.casadocodigo.loja.modelo;


import javax.persistence.Embeddable;

@Embeddable 
public class Autor {

	private String nome;


	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}

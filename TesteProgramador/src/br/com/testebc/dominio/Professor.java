package br.com.testebc.dominio;

public class Professor extends Pessoa {
	String titulacao;
	
	public Professor() {}
	
	public Professor(String nome, String cpf, String email, String titulacao, Telefones telefones) {
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.titulacao = titulacao;
		this.telefones = telefones;
	}

	public String getTitulacao() {
		return titulacao;
	}

	public void setTitulacao(String titulacao) {
		this.titulacao = titulacao;
	}
}

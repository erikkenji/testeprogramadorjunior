package br.com.testebc.dominio;

public class Aluno extends Pessoa {
	String curso;
	
	public Aluno() {}
	
	public Aluno(String nome, String cpf, String email, String curso, Telefones telefones) {
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.curso = curso;
		this.telefones = telefones;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}
}

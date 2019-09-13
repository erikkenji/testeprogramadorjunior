package br.com.testebc.util;

import java.util.ArrayList;

import br.com.testebc.dominio.EntidadeDominio;
import br.com.testebc.dominio.Pessoa;

public class Resultado {
	String resultado;
	Pessoa pessoa;	
	ArrayList<EntidadeDominio> listResultado;
	
	public String getResultado() {
		return resultado;
	}
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}
	public ArrayList<EntidadeDominio> getListResultado() {
		return listResultado;
	}
	public void setListResultado(ArrayList<EntidadeDominio> listResultado) {
		this.listResultado = listResultado;
	}
	public Pessoa getPessoa() {
		return pessoa;
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
}

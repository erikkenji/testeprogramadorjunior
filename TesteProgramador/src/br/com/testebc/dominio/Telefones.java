package br.com.testebc.dominio;

import java.util.ArrayList;

public class Telefones {
	String tipo;
	ArrayList<String> tel, cel, outros;

	public Telefones() {
		this.tel = new ArrayList<String>();
		this.cel = new ArrayList<String>();
		this.outros = new ArrayList<String>();
	}
	
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public ArrayList<String> getTel() {
		return tel;
	}

	public void setTel(ArrayList<String> tel) {
		this.tel = tel;
	}

	public ArrayList<String> getCel() {
		return cel;
	}

	public void setCel(ArrayList<String> cel) {
		this.cel = cel;
	}

	public ArrayList<String> getOutros() {
		return outros;
	}

	public void setOutros(ArrayList<String> outros) {
		this.outros = outros;
	}

	public void setTelefones(String tipo, String numero) {
		if(tipo.equals("Residencial")) {
			tel.add(numero);
		}else if(tipo.equals("Celular")){
			cel.add(numero);
		}else if(tipo.equals("Outros")){
			outros.add(numero);
		}
	}
}

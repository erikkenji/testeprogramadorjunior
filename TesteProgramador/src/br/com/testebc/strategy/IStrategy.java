package br.com.testebc.strategy;

import br.com.testebc.dominio.EntidadeDominio;

public interface IStrategy {
	
	public String processar(EntidadeDominio entidade);
	
}

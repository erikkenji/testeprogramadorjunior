package br.com.testebc.command;

import br.com.testebc.dominio.EntidadeDominio;
import br.com.testebc.util.Resultado;

public interface ICommand {
	public Resultado execute(EntidadeDominio entidade);
}

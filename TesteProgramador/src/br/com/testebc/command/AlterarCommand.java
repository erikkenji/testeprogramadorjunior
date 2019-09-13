package br.com.testebc.command;

import br.com.testebc.dominio.EntidadeDominio;
import br.com.testebc.util.Resultado;

public class AlterarCommand extends AbstractCommand {

	@Override
	public Resultado execute(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		return fachada.alterar(entidade);
	}

}

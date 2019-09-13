package br.com.testebc.command;

import br.com.testebc.dominio.EntidadeDominio;
import br.com.testebc.util.Resultado;

public class ExcluirCommand extends AbstractCommand{

	@Override
	public Resultado execute(EntidadeDominio entidade) {
		return fachada.excluir(entidade);
	}

}

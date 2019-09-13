package br.com.testebc.command;

import br.com.testebc.dominio.EntidadeDominio;
import br.com.testebc.util.Resultado;

public class ConsultarCommand extends AbstractCommand {

	@Override
	public Resultado execute(EntidadeDominio entidade) {
		return fachada.consultar(entidade);
	}

}

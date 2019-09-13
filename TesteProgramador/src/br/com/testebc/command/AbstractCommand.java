package br.com.testebc.command;

import br.com.testebc.controller.Fachada;
import br.com.testebc.controller.IFachada;

public abstract class AbstractCommand  implements ICommand{
	protected IFachada fachada = new Fachada();
}

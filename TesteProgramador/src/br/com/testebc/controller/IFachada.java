package br.com.testebc.controller;

import br.com.testebc.dominio.EntidadeDominio;
import br.com.testebc.util.Resultado;

public interface IFachada {
	public Resultado salvar(EntidadeDominio entidade);
	public Resultado alterar(EntidadeDominio entidade);
	public Resultado excluir(EntidadeDominio entidade);
	public Resultado consultar(EntidadeDominio entidade);
	public Resultado editar(EntidadeDominio entidade);
}

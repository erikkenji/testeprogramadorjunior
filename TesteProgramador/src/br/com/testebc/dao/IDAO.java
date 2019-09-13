package br.com.testebc.dao;

import java.util.ArrayList;

import br.com.testebc.dominio.EntidadeDominio;
import br.com.testebc.util.Resultado;

public interface IDAO {
	public boolean salvar(EntidadeDominio entidade);
	public boolean alterar(EntidadeDominio entidade);
	public Resultado excluir(EntidadeDominio entidade);
	public ArrayList<EntidadeDominio> consultar(EntidadeDominio entidade);
}

package br.com.testebc.strategy;

import java.util.ArrayList;

import br.com.testebc.dao.DAOPessoa;
import br.com.testebc.dominio.EntidadeDominio;
import br.com.testebc.dominio.Pessoa;

public class ValidarExistencia implements IStrategy{

	@Override
	public String processar(EntidadeDominio entidade) {
		Pessoa pessoa = (Pessoa) entidade;
		DAOPessoa vpes = new DAOPessoa();
		ArrayList<EntidadeDominio> vList = vpes.consultar();
		if(vList != null) {
			for(EntidadeDominio e : vList) {
				Pessoa f = (Pessoa) e;
				if(f.getCpf().equals(pessoa.getCpf())) {
					return "JÁ EXISTE UM REGISTRO DESTA PESSOA!!</br>";
				}
			}		
				return null;
		}else {
				return null;
		}

	}
}

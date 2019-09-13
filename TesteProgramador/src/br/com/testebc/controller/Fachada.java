package br.com.testebc.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.testebc.dao.DAOPessoa;
import br.com.testebc.dao.IDAO;
import br.com.testebc.dominio.Aluno;
import br.com.testebc.dominio.EntidadeDominio;
import br.com.testebc.dominio.Pessoa;
import br.com.testebc.dominio.Professor;
import br.com.testebc.strategy.IStrategy;
import br.com.testebc.strategy.ValidarExistencia;
import br.com.testebc.util.Resultado;

public class Fachada implements IFachada {
	
	private Map<String, IDAO> daos;
	private Map<String, Map<String, List<IStrategy>>> rns;
	
	public Fachada() {
		daos = new HashMap<String, IDAO>();
		
		rns = new HashMap<String, Map<String, List<IStrategy>>>();
		
		DAOPessoa funDao = new DAOPessoa();
		
		daos.put(Aluno.class.getName(), funDao);
		daos.put(Professor.class.getName(), funDao);
		daos.put(EntidadeDominio.class.getName(), funDao);
		
		ValidarExistencia vExist = new ValidarExistencia();
		
		List<IStrategy> rnsSalvarPessoa = new ArrayList<IStrategy>();		
		rnsSalvarPessoa.add(vExist);
		
		Map<String, List<IStrategy>> rnsPessoa = new HashMap<String, List<IStrategy>>();
		
		rnsPessoa.put("SALVAR", rnsSalvarPessoa);
		
		rns.put(Aluno.class.getName(), rnsPessoa);
		rns.put(Professor.class.getName(), rnsPessoa);
	}
	
	@Override
	public Resultado salvar(EntidadeDominio entidade) {
		Resultado resultado = new Resultado();
		String nomeClasse = entidade.getClass().getName();
		
		resultado.setResultado(executarRegras(entidade, "SALVAR"));
		
		if(resultado.getResultado() == null) {
			IDAO dao = daos.get(nomeClasse);
			Pessoa f = (Pessoa) entidade;
			
				if(dao.salvar(f)) {
					resultado.setResultado("Cadastrado efetuado com sucesso!");
					return resultado;
				}else {
					resultado.setResultado("Não foi possivel realizar o cadastro!!");
					return resultado;
				}				
		}else {
			return resultado;
		}
		
	}

	@Override
	public Resultado alterar(EntidadeDominio entidade) {
		Resultado resultado = new Resultado();
		String nomeClasse = entidade.getClass().getName();
		
		resultado.setResultado(executarRegras(entidade, "ALTERAR"));
		
		if(resultado.getResultado() == null) {
			IDAO dao = daos.get(nomeClasse);
			Pessoa f = (Pessoa) entidade;
				if(dao.alterar(f)) {
					resultado.setResultado("Alterado com sucesso!!</br>");
					return resultado;
				}else {
					resultado.setResultado("Não foi possivel realizar a alteração!!</br>");
					return resultado;
				}				
		}else {
			return resultado;
		}
	}

	@Override
	public Resultado excluir(EntidadeDominio entidade) {
		Resultado resultado = new Resultado();
		
		String nomeClasse = entidade.getClass().getName();
		IDAO dao = daos.get(nomeClasse);
		resultado = dao.excluir(entidade);
		return resultado;
	}

	@Override
	public Resultado consultar(EntidadeDominio entidade) {
		Resultado resultado = new Resultado();
		String nomeClasse = entidade.getClass().getName();
		IDAO dao = daos.get(nomeClasse);
		resultado.setListResultado(dao.consultar(entidade));
		return resultado;
	}

	@Override
	public Resultado editar(EntidadeDominio entidade) {
		
		Resultado resultado = new Resultado();
		
		String nomeClasse = entidade.getClass().getName();
		IDAO dao = daos.get(nomeClasse);
		
		for(EntidadeDominio e : dao.consultar(entidade)) {
			if(e.getId() == entidade.getId()) {
				if(nomeClasse.equals("br.com.testebc.dominio.Aluno")) {
					Aluno aluno;
					aluno = (Aluno) e;
					resultado.setPessoa(aluno);
				}else if(nomeClasse.equals("br.com.testebc.dominio.Professor")) {
					Professor professor;
					professor = (Professor) e;
					resultado.setPessoa(professor);
				}
			}
		}		
		
		return resultado;
		
	}

	private String executarRegras(EntidadeDominio entidade, String operacao) {
		String nomeClasse = entidade.getClass().getName();
		StringBuilder msg = new StringBuilder();
		
		Map<String, List<IStrategy>> regrasOperacao = rns.get(nomeClasse);
		
		if(regrasOperacao != null) {
			List<IStrategy> regras = regrasOperacao.get(operacao);
			
			for(IStrategy s: regras) {
				String m = s.processar(entidade);
				
				if(m != null) {
					msg.append(m);
				}
			}
		}
		
		if(msg.length()>0) {
			return msg.toString();
		}else {
			return null;
		}
	}
}

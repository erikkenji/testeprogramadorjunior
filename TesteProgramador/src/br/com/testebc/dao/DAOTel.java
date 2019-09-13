package br.com.testebc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import br.com.testebc.dominio.EntidadeDominio;
import br.com.testebc.dominio.Pessoa;
import br.com.testebc.util.Conection;
import br.com.testebc.util.Resultado;

public class DAOTel implements IDAO{
	private Connection conn;
	
	public DAOTel() {}
	
	public DAOTel(Connection conn){
		this.conn = conn;
	}

	@Override
	public boolean salvar(EntidadeDominio entidade) {
		Pessoa p = (Pessoa) entidade;
		
		try {
			
			for(String t : p.getTel().getTel()) {
				String sql = "INSERT INTO telefones"
						+ " (id_pessoa, tipo, numero) VALUES "
						+ " (?,?,?)";
				PreparedStatement pstm =
						conn.prepareStatement(sql);
				
				pstm.setInt(1, p.getId());
				pstm.setString(2, "Residencial");
				pstm.setString(3, t);
				pstm.execute();
			}
			for(String t : p.getTel().getCel()) {
				String sql = "INSERT INTO telefones"
						+ " (id_pessoa, tipo, numero) VALUES "
						+ " (?,?,?)";
				PreparedStatement pstm =
						conn.prepareStatement(sql);
				
				pstm.setInt(1, p.getId());
				pstm.setString(2, "Celular");
				pstm.setString(3, t);
				pstm.execute();
			}
			for(String t : p.getTel().getOutros()) {
				String sql = "INSERT INTO telefones"
						+ " (id_pessoa, tipo, numero) VALUES "
						+ " (?,?,?)";
				PreparedStatement pstm =
						conn.prepareStatement(sql);
				
				pstm.setInt(1, p.getId());
				pstm.setString(2, "Outros");
				pstm.setString(3, t);
				pstm.execute();
			}
			return true;
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		}finally{
			Conection.fechar(conn);
		}
	}

	@Override
	public boolean alterar(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Resultado excluir(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<EntidadeDominio> consultar(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		return null;
	}

}

package br.com.testebc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import br.com.testebc.dominio.Aluno;
import br.com.testebc.dominio.EntidadeDominio;
import br.com.testebc.dominio.Pessoa;
import br.com.testebc.dominio.Professor;
import br.com.testebc.dominio.Telefones;
import br.com.testebc.util.Conection;
import br.com.testebc.util.Resultado;

import java.sql.ResultSet;
import java.sql.Statement;


public class DAOPessoa implements IDAO{

	@Override
	public boolean salvar(EntidadeDominio entidade) {
		String classe = entidade.getClass().getName();
		Pessoa p = (Pessoa) entidade;
		Connection conn = null;
		try {
			conn = Conection.getConexao();
						
			String sql = "INSERT INTO pessoa"
					+ " (nome, cpf, email) VALUES "
					+ " (?,?,?)";
			PreparedStatement pstm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			pstm.setString(1, p.getNome());
			pstm.setString(2, p.getCpf());
			pstm.setString(3, p.getEmail());
			
			pstm.executeUpdate();
			
			ResultSet rs = pstm.getGeneratedKeys();
			
			int idFun=0;
			if(rs.next())
				idFun = rs.getInt(1);
			    p.setId(idFun);
			if(classe.equals("br.com.testebc.dominio.Aluno")){
				Aluno a = (Aluno) p;
				if(a.getCurso() != null) {			    
				    sql = "INSERT INTO alunos"
							+ " (id_pessoa, curso) VALUES "
							+ " (?,?)";
					pstm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
					
					pstm.setInt(1, p.getId());				
					pstm.setString(2, a.getCurso());
					
					pstm.executeUpdate(); 
				}
			}
			
			if(classe.equals("br.com.testebc.dominio.Professor")) {
				Professor prof = (Professor) p;
				if(prof.getTitulacao() != null) {			    
				    sql = "INSERT INTO professores"
							+ " (id_pessoa, titulacao) VALUES "
							+ " (?,?)";
					pstm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
					
					pstm.setInt(1, p.getId());				
					pstm.setString(2, prof.getTitulacao());
					
					pstm.executeUpdate(); 
				}
			}
				
			DAOTel daotel = new DAOTel(conn);    
			daotel.salvar(entidade);
			
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
		Pessoa fun = (Pessoa) entidade;
		Connection conn = null;
		try {
			conn = Conection.getConexao();
			
			String sql = "UPDATE funcionarios"
					+ " SET nome = ?, cpf = ?, dtcontratacao = ?, matricula = ?, email = ?, senha = ?, cadastrante = ?, cargo = ?, setor = ?, regional = ?, status = ? "
					+ " WHERE func_id = ?";
			PreparedStatement pstm =
					conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			pstm.setString(1, fun.getNome());
			pstm.setString(2, fun.getCpf());
			pstm.setInt(12, fun.getId());
			
			pstm.executeUpdate();
			
			return true;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		}finally{
			Conection.fechar(conn);
		}
	}

	@Override
	public Resultado excluir(EntidadeDominio entidade) {
		String classe = entidade.getClass().getName();
		Resultado resultado = new Resultado();
		Connection conn= null;
		
			if(classe.equals("br.com.testebc.dominio.Aluno")) {
				try {
					Aluno aluno = (Aluno) entidade;
					conn = Conection.getConexao();
					String sql = "DELETE FROM pessoa WHERE id_pessoa = ?";
					PreparedStatement pstm = conn.prepareStatement(sql);
					
					pstm.setInt(1, aluno.getId());
					pstm.executeUpdate();
					
					sql = "DELETE FROM alunos WHERE id_pessoa = ?";
					pstm = conn.prepareStatement(sql);
					
					pstm.setInt(1, aluno.getId());
					pstm.executeUpdate();
					
					sql = "DELETE FROM telefones WHERE id_pessoa = ?";
					pstm = conn.prepareStatement(sql);
					
					pstm.setInt(1, aluno.getId());
					pstm.executeUpdate();
					
					resultado.setResultado("Excluido com sucesso");
					return resultado;
					
				} catch (Exception e) {
					System.err.println(e.getMessage());
					resultado.setResultado(e.getMessage());
					return resultado;
				}finally{
					Conection.fechar(conn);
				}
			}else {
				try {
					Professor professor = (Professor) entidade;
					conn = Conection.getConexao();
					String sql = "DELETE FROM pessoa WHERE id_pessoa = ?";
					PreparedStatement pstm = conn.prepareStatement(sql);
					
					pstm.setInt(1, professor.getId());
					pstm.executeUpdate();
					
					sql = "DELETE FROM professores WHERE id_pessoa = ?";
					pstm = conn.prepareStatement(sql);
					
					pstm.setInt(1, professor.getId());
					pstm.executeUpdate();
					
					sql = "DELETE FROM telefones WHERE id_pessoa = ?";
					pstm = conn.prepareStatement(sql);
					
					pstm.setInt(1, professor.getId());
					pstm.executeUpdate();
					
					resultado.setResultado("Excluido com sucesso");
					return resultado;
					
				} catch (Exception e) {
					System.err.println(e.getMessage());
					resultado.setResultado(e.getMessage());
					return resultado;
				}finally{
					Conection.fechar(conn);
				}
			}
		
	}


	public ArrayList<EntidadeDominio> consultar() {
		Connection conn= null;	
		
		StringBuilder sql = new StringBuilder();
				
		sql.append("SELECT * FROM pessoa");
			
		try {
			conn = Conection.getConexao();
			
			PreparedStatement pstm = conn.prepareStatement(sql.toString());
			
			
			
			int id = 0;
			String nome = null, cpf = null, email = null;
			
			ResultSet rs = pstm.executeQuery();
			
			ArrayList<EntidadeDominio> funcionarios = new ArrayList<EntidadeDominio>();	
			
			while(rs.next()) {
				id = rs.getInt("id_pessoa");
				nome = rs.getString("nome");
				cpf = rs.getString("cpf");
				email = rs.getString("email");
				
				Pessoa pessoa = new Pessoa(nome, cpf, email);
				
				pessoa.setId(id);
				
				funcionarios.add(pessoa);
					
				}
			
			
			return funcionarios;
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		}finally{
			Conection.fechar(conn);
		}		
	}

	@SuppressWarnings("null")
	@Override
	public ArrayList<EntidadeDominio> consultar(EntidadeDominio entidade) {
		String classe = entidade.getClass().getName();
		Connection conn= null;	
		
		StringBuilder sql = new StringBuilder();
				
		if(classe.equals("br.com.testebc.dominio.Aluno")) {
			sql.append("select p.id_pessoa, p.nome, p.cpf, p.email, a.curso, t.tipo, t.numero from pessoa as p inner join alunos as a\r\n" + 
				"on p.id_pessoa = a.id_pessoa inner join telefones as t on p.id_pessoa = t.id_pessoa\r\n" + 
				"order by p.id_pessoa");
			
			try {
				conn = Conection.getConexao();
				
				PreparedStatement pstm = conn.prepareStatement(sql.toString());
							
				int id = 0;
				String nome = null, cpf = null, email = null, curso = null, tipo = null, numero = null;
				
				ResultSet rs = pstm.executeQuery();
				
				ArrayList<EntidadeDominio> pessoas = new ArrayList<EntidadeDominio>();	
				while(rs.next()) {
					
					id = rs.getInt("id_pessoa");
					nome = rs.getString("nome");
					cpf = rs.getString("cpf");
					email = rs.getString("email");
					curso = rs.getString("curso");
					tipo = rs.getString("tipo");
					numero = rs.getString("numero");
					Telefones telefones = new Telefones();
					
						int count=0;
						for(EntidadeDominio a: pessoas) {
							Aluno al = (Aluno) a;
							if(id == al.getId()) {
								al.getTel().setTelefones(tipo, numero);
								count++;
							}
						}
						if(count == 0) {
							telefones.setTelefones(tipo, numero);
							Aluno aluno = new Aluno(nome, cpf, email, curso, telefones);				
							aluno.setId(id);				
							
							pessoas.add(aluno);
						}
					
				}
				
				
				return pessoas;
				
			} catch (Exception e) {
				System.err.println(e.getMessage());
				return null;
			}finally{
				Conection.fechar(conn);
			}
		}else {
			sql.append("select p.id_pessoa, p.nome, p.cpf, p.email, pro.titulacao, t.tipo, t.numero from pessoa as p inner join professores as pro\r\n" + 
					"on p.id_pessoa = pro.id_pessoa inner join telefones as t on p.id_pessoa = t.id_pessoa\r\n" + 
					"order by p.id_pessoa");
			
			try {
				conn = Conection.getConexao();
				
				PreparedStatement pstm = conn.prepareStatement(sql.toString());
							
				int id = 0;
				String nome = null, cpf = null, email = null, titulacao = null, tipo = null, numero = null;
				
				ResultSet rs = pstm.executeQuery();
				
				ArrayList<EntidadeDominio> pessoas = new ArrayList<EntidadeDominio>();	
				while(rs.next()) {
					
					id = rs.getInt("id_pessoa");
					nome = rs.getString("nome");
					cpf = rs.getString("cpf");
					email = rs.getString("email");
					titulacao = rs.getString("titulacao");
					tipo = rs.getString("tipo");
					numero = rs.getString("numero");
					Telefones telefones = new Telefones();
					
						int count=0;
						for(EntidadeDominio p: pessoas) {
							Professor pro = (Professor) p;
							if(id == pro.getId()) {
								pro.getTel().setTelefones(tipo, numero);
								count++;
							}
						}
						if(count == 0) {
							telefones.setTelefones(tipo, numero);
							Professor professor = new Professor(nome, cpf, email, titulacao, telefones);				
							professor.setId(id);				
							
							pessoas.add(professor);
						}
					
				}
				
				
				return pessoas;
				
			} catch (Exception e) {
				System.err.println(e.getMessage());
				return null;
			}finally{
				Conection.fechar(conn);
			}
		}
			
				
	}


}

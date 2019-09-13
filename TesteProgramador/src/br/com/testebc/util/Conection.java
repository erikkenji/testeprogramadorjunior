package br.com.testebc.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conection {
	public static Connection getConexao(){
		String server = "localhost";
		String banco = "testebc";
		String usuario = "postgres";
		String senha = "erikkenji";
		try {
			Class.forName("org.postgresql.Driver");
			String path = "jdbc:postgresql://"+server+"/"+banco;
			Connection conn = DriverManager.getConnection(
					path,usuario,senha
					);
			return conn;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		}
	}
	public static void fechar(Connection conn){
		if(conn!=null){
			try {
				conn.close();
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}
	}
}

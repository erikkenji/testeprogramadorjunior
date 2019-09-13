package br.com.testebc.viewhelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.testebc.dominio.Aluno;
import br.com.testebc.dominio.EntidadeDominio;
import br.com.testebc.dominio.Telefones;
import br.com.testebc.util.Resultado;

public class VHSalvarAluno implements IViewHelper{

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		String nome, cpf, curso, email, tel[], cel[], outros[];
		nome = request.getParameter("txtNome");
		cpf = request.getParameter("txtCpf");
		curso = request.getParameter("txtCurso");
		email = request.getParameter("txtEmail");
		tel = request.getParameterValues("telefone[Residencial][]");
		cel = request.getParameterValues("telefone[Celular][]");
		outros = request.getParameterValues("telefone[Outro][]");
		
		Telefones telefones = new Telefones();
		if(tel != null) {
			List<String> list = Arrays.asList(tel);
			ArrayList<String> t = new ArrayList<String>(list);
			telefones.setTel(t);
		}
		if(cel != null) {
			List<String> list = Arrays.asList(cel);
			ArrayList<String> t = new ArrayList<String>(list);
			telefones.setCel(t);
		}
		if(outros != null) {
			List<String> list = Arrays.asList(outros);
			ArrayList<String> t = new ArrayList<String>(list);
			telefones.setOutros(t);
		}		
		
		Aluno aluno = new Aluno(nome, cpf, email, curso, telefones);
		
		return aluno;
	}

	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		if(resultado.getResultado() != null) {			
			request.setAttribute("salvar", resultado.getResultado());
			RequestDispatcher rd= request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);
		}
		
	}

}

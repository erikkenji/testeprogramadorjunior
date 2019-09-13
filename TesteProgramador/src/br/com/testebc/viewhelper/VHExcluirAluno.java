package br.com.testebc.viewhelper;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.testebc.dominio.Aluno;
import br.com.testebc.dominio.EntidadeDominio;
import br.com.testebc.util.Resultado;

public class VHExcluirAluno implements IViewHelper{

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		Aluno aluno = new Aluno();
		aluno.setId(Integer.parseInt(request.getParameter("id")));
		return aluno;
	}

	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		if(resultado.getResultado() != null) {
			request.setAttribute("excluido", resultado.getResultado());
			RequestDispatcher rd= request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);
		}else{
			PrintWriter out = response.getWriter();
			out.print(resultado.getResultado());
		}
	}

}

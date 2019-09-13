package br.com.testebc.viewhelper;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.testebc.dominio.EntidadeDominio;
import br.com.testebc.dominio.Professor;
import br.com.testebc.util.Resultado;

public class VHEditarProfessor implements IViewHelper{

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		Professor professor = new Professor();
		professor.setId(Integer.parseInt(request.getParameter("id")));
		return professor;
	}

	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		if(resultado.getPessoa() != null) {
			request.setAttribute("professor", resultado.getPessoa());
			RequestDispatcher rd= request.getRequestDispatcher("editar-professor.jsp");
			rd.forward(request, response);
		}else{
			PrintWriter out = response.getWriter();
			out.print(resultado.getResultado());
		}
	}

}

package br.com.testebc.viewhelper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.testebc.command.AlterarCommand;
import br.com.testebc.command.ConsultarCommand;
import br.com.testebc.command.EditarCommand;
import br.com.testebc.command.ExcluirCommand;
import br.com.testebc.command.ICommand;
import br.com.testebc.command.SalvarCommand;
import br.com.testebc.dominio.EntidadeDominio;
import br.com.testebc.util.Resultado;

@WebServlet(urlPatterns = {"/salvar", "/salvarProfessor", "/editarAluno", "/editarProfessor", "/excluirAluno", "/excluirProfessor"}) 
public class CtrlView extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	private static Map<String, ICommand> commands;
	private static Map<String, IViewHelper> vhs;
       
    public CtrlView() {  
    	
    	vhs = new HashMap<String, IViewHelper>();
    	
    	vhs.put("/TesteProgramador/salvar", new VHSalvarAluno());
    	vhs.put("/TesteProgramador/salvarProfessor", new VHSalvarProfessor());
    	vhs.put("/TesteProgramador/editarAluno", new VHEditarAluno());
    	vhs.put("/TesteProgramador/editarProfessor", new VHEditarProfessor());
    	vhs.put("/TesteProgramador/excluirAluno", new VHExcluirAluno());
    	vhs.put("/TesteProgramador/excluirProfessor", new VHExcluirProfessor());
    	vhs.put("/TesteProgramador/alterar", new VHAlterarAluno());
    	
    	commands = new HashMap<String, ICommand>();    	
    	
    	commands.put("SALVAR", new SalvarCommand());
    	commands.put("EXCLUIR", new ExcluirCommand());
    	commands.put("CONSULTAR", new ConsultarCommand());
    	commands.put("ALTERAR", new AlterarCommand());
    	commands.put("EDITAR", new EditarCommand());
    	
    }
    
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String uri = request.getRequestURI();
		String operacao = request.getParameter("txtOp");
		
		IViewHelper vh = vhs.get(uri);
		
		EntidadeDominio entidade =  vh.getEntidade(request);

		ICommand command = commands.get(operacao);
		
		Resultado resultado = command.execute(entidade);
		
		vh.setView(resultado, request, response);
	}

}

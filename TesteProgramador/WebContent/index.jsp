<%@ page import="java.io.PrintWriter, br.com.testebc.dominio.*, java.util.*, br.com.testebc.dao.*, br.com.testebc.util.*, br.com.testebc.command.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<html lang="pt-BR">

<head>
    <!-- Required meta tags -->
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    <link rel="stylesheet"
        href="https://cdnjs.cloudflare.com/ajax/libs/open-iconic/1.1.1/font/css/open-iconic-bootstrap.min.css"
        integrity="sha256-BJ/G+e+y7bQdrYkS2RBTyNfBHpA9IuGaPmf9htub5MQ=" crossorigin="anonymous" />

    <title>Teste de Programador</title>
</head>

<body>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand" href="#">
            <img src="https://brazcubas.br/wp-content/uploads/2019/02/logo_educacao_colorido-2.svg" width="200" />
        </a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="#">Alunos</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="professores.jsp">Professores</a>
                </li>
            </ul>
        </div>
    </nav>
    <p>&nbsp;</p>
    <div class="container">
        <div class="row">
            <div class="col-12">
                <div class="row">
                    <div class="col-10">
                        <h1>Alunos</h1>
                    </div>
                    <div class="col-2 text-right">
                        <a href="novo-aluno.html" class="btn btn-primary">
                            <span class="oi oi-plus"></span>
                        </a>
                    </div>
                </div>
                <% 
                	if(request.getAttribute("excluido") != null){
                		out.print(request.getAttribute("excluido"));
                	}	
                	if(request.getAttribute("salvar") != null){
                		out.print(request.getAttribute("salvar"));
                	}
                %>
                <div class="table-responsive">
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th scope="col">Cód.</th>
                                <th scope="col">Nome</th>
                                <th scope="col">CPF</th>
                                <th scope="col">Curso</th>
                                <th scope="col">E-mail</th>
                                <th scope="col">Telefone</th>
                                <th scope="col">Ações</th>
                            </tr>
                        </thead>
                        <tbody>
                        <%
                        	Resultado resultado = new Resultado();
                        	ConsultarCommand consulta = new ConsultarCommand();
                        	Aluno entidade = new Aluno();
                        	resultado = consulta.execute(entidade);
                            ArrayList<EntidadeDominio> alunos = resultado.getListResultado();
                        
							if (alunos != null) {			
								StringBuilder sbRegistro = new StringBuilder();
								StringBuilder sbLink = new StringBuilder();
				
								
									for (int i = 0; i < alunos.size(); i++) {
										Aluno a = (Aluno) alunos.get(i);
										sbRegistro.setLength(0);
										sbLink.setLength(0);
				
				
										sbRegistro.append("<TR ALIGN='CENTER'>");	
				
										sbRegistro.append("<TD>");
										sbRegistro.append(sbLink.toString());
										sbRegistro.append(a.getId());			
										sbRegistro.append("</TD>");
				
										sbRegistro.append("<TD>");
										sbRegistro.append(sbLink.toString());
										sbRegistro.append(a.getNome());
										sbRegistro.append("</TD>");
				
										sbRegistro.append("<TD>");
										sbRegistro.append(sbLink.toString());
										sbRegistro.append(a.getCpf());
										sbRegistro.append("</TD>");
										
										sbRegistro.append("<TD>");
										sbRegistro.append(sbLink.toString());
										sbRegistro.append(a.getCurso());
										sbRegistro.append("</TD>");

										sbRegistro.append("<TD>");
										sbRegistro.append(sbLink.toString());
										sbRegistro.append(a.getEmail());
										
										ArrayList<String> tel = a.getTel().getTel(), cel = a.getTel().getCel(), outros = a.getTel().getOutros();										
										sbRegistro.append("<TD>");
											sbRegistro.append("<table>");
												if(tel != null){
													for(String s : tel){
														sbRegistro.append("<TR>");
														sbRegistro.append(sbLink.toString());
														sbRegistro.append("Residencial: " + s);	
														sbRegistro.append("</BR>");
														sbRegistro.append("</TR>");
													}
												}
												if(cel != null){
													for(String s : cel){
														sbRegistro.append("<TR>");
														sbRegistro.append(sbLink.toString());
														sbRegistro.append("Celular: " + s);	
														sbRegistro.append("</BR>");
														sbRegistro.append("</TR>");
													}
												}
												if(outros != null){
													for(String s : outros){
														sbRegistro.append("<TR>");
														sbRegistro.append(sbLink.toString());
														sbRegistro.append("Outros: " + s);	
														sbRegistro.append("</BR>");
														sbRegistro.append("</TR>");
													}
												}
											sbRegistro.append("</table>");
										sbRegistro.append("</TD>");										
										
										sbRegistro.append("<TD>");
										sbRegistro.append(sbLink.toString());
										sbRegistro.append("<form action='editarAluno' method='POST'>");
										sbRegistro.append("<input type='hidden' name='id' value='");
										sbRegistro.append(a.getId());
										sbRegistro.append("'/>");
										sbRegistro.append("<input class='btn btn-primary' type='submit'id='txtOp' name='txtOp' value='EDITAR'/>");
										sbRegistro.append("</form >");
										
										sbRegistro.append(sbLink.toString());
										sbRegistro.append("<form action='excluirAluno' method='POST'>");
										sbRegistro.append("<input type='hidden' name='id' value='");
										sbRegistro.append(a.getId());
										sbRegistro.append("'/>");
										sbRegistro.append("<input class='btn btn-danger' type='submit' id='txtOp' name='txtOp' value='EXCLUIR'/>");
										sbRegistro.append("</form >");
										sbRegistro.append("</TD>");
										
										sbRegistro.append("</TR>");
				
										out.print(sbRegistro.toString());
				
									}
								}else{
									out.print("Não há aluno cadastrado ainda.");
								}

			
						%>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>


    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
</body>

</html>
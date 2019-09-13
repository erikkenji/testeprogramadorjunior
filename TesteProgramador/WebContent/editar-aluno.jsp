<%@ page import="java.io.PrintWriter, br.com.testebc.dominio.*, java.util.*, br.com.testebc.dao.*, br.com.testebc.util.*, br.com.testebc.command.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!doctype html>
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

    <template id="telefone">
        <div class="form-group">
            <label for="telefone" class="sr-only">Telefone</label>
            <div class="input-group">
                <div class="input-group-prepend">
                    <span class="input-group-text" id="validationTooltipUsernamePrepend">{{tipoTelefone}}</span>
                </div>
                <input type="phone" class="form-control" id="telefone" name="telefone[{{tipoTelefone}}][]" aria-describedby="phoneHelp"
                    placeholder="Digite um telefone">
                <div class="input-group-append">
                    <button type="button" class="btn btn-danger removerTelefone"><span class="oi oi-minus"></span></button>
                </div>
            </div>
            <small id="phoneHelp" class="form-text text-muted">Não compartilhe esse telefone com
                nínguem.</small>
        </div>
    </template>
    <%		
		Aluno aluno = (Aluno) request.getAttribute("aluno"); 
	%>
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
                    <a class="nav-link" href="index.jsp">Alunos<span class="sr-only">(current)</span></a>
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
                <h1>Editar Aluno</h1>
            </div>
            <div class="col-12">
                <form action="alterarAluno" method="POST">
                    <div class="form-group">
                        <label for="nome">Nome</label>
                        <input type="text" class="form-control" id="txtNome" name="txtNome" placeholder="Digite um nome" value="<%
                        	if(aluno!=null){
                				out.println(aluno.getNome());
                			}
                        %>">
                    </div>
                    <div class="form-group">
                        <label for="cpf">CPF</label>
                        <input type="text" class="form-control" id="txtCpf" name="txtCpf" placeholder="Digite um CPF" readonly value="<%
                        	if(aluno!=null){
                				out.println(aluno.getCpf());
                			}
                        %>">
                    </div>
                    <div class="form-group">
                        <label for="curso">Curso</label>
                        <select type="text" class="form-control" id="curso" name="curso">
                            <option>Selecione um Curso</option>
                            <option <% 
                            	if(aluno!=null && aluno.getCurso().equals("Administração")){
                            		out.println(" selected='selected'");
                            	}
                            %>>Administração</option>
                            <option <% 
                            	if(aluno!=null && aluno.getCurso().equals("Pedagogia")){
                            		out.println(" selected='selected'");
                            	}
                            %>>Pedagogia</option>
                            <option <% 
                            	if(aluno!=null && aluno.getCurso().equals("Corte e Costura")){
                            		out.println(" selected='selected'");
                            	}
                            %>>Corte e Costura</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="email">E-mail</label>
                        <input type="email" class="form-control" id="txtEmail" name="txtEmail" aria-describedby="emailHelp"
                            placeholder="Digite um e-mail" value="<%
                        	if(aluno!=null){
                				out.println(aluno.getEmail());
                			}
                        %>">
                        <small id="emailHelp" class="form-text text-muted">Não compartilhe esse e-mail com
                            nínguem.</small>
                    </div>

                    
                    <div class="row">
                        <div class="col-10"><h2>Telefones</h2></div>
                        <div class="col-2 text-right">
                            <div class="dropdown">
                                <button class="btn btn-primary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                                    <span class="oi oi-plus"></span>
                                </button>
                                <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                                    <a class="dropdown-item addTel" href="#">Residencial</a>
                                    <a class="dropdown-item addTel" href="#">Celular</a>
                                    <a class="dropdown-item addTel" href="#">Outro</a>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div id="telefones">
                        <small>Utilize o <span class="oi oi-plus"></span> para adicionar mais telefones.</small>
                        <div class="form-group">
                            <label for="telefone" class="sr-only">Telefone</label>
                            <div class="input-group">
                                <div class="input-group-prepend">
                                    <span class="input-group-text" id="validationTooltipUsernamePrepend">Residencial</span>
                                </div>
                                <input type="phone" class="form-control" id="telefone" name="telefone[Residencial][]" aria-describedby="phoneHelp"
                                    placeholder="Digite um telefone" value="(11) 6633-6622">
                                <div class="input-group-append">
                                    <button type="button" class="btn btn-danger removerTelefone"><span class="oi oi-minus"></span></button>
                                </div>
                            </div>
                            <small id="phoneHelp" class="form-text text-muted">Não compartilhe esse telefone com
                                nínguem.</small>
                        </div>
                    </div>
                    <a href="index.jsp" class="btn btn-secondary">Cancelar</a>
                    <button type="submit" class="btn btn-primary">Cadastrar</button>                    
                </form>
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
    <script type="text/javascript">
        const adicionaTelefone = (type) => {
            let telefoneTemplate = document.querySelector('template#telefone').innerHTML;
            let html = telefoneTemplate.replace(/{{tipoTelefone}}/g, type);
            $('#telefones').append(html);
        };

        const remove = (element) => {
            console.log(element);
            $(element).parents('.form-group').remove();
        }

        const addRemoveListener = () => {
            $('.removerTelefone').click(event => {
                event.preventDefault();
                remove(event.target);
            });
        }

        $('.addTel').click( event => {
            event.preventDefault();
            adicionaTelefone(event.target.innerHTML);
            addRemoveListener();
        })
        addRemoveListener();

    </script>
</body>

</html>
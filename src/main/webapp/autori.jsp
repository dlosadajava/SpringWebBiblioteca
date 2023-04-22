<%@page import="it.etlabora.SpringWebBiblioteca.modello.Libro"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@page import="it.etlabora.SpringWebBiblioteca.modello.Autore"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
</head>
<body class="home">
	<div class="container-fluid display-table">

		<div class="row display-table-row">

			<!--  barra lateral izquierda oscura -->
			<%@ include file="menu-sidebar.jsp"%>



			<div class="col-md-10 col-sm-11 display-table-cell v-align">

				<%@ include file="header.jsp"%>
				<%
					List<Autore> listAutori = (List<Autore>) request.getAttribute("autori");
					
					%>
					
				<main>
					

					<!--START TABELLA AUTORI -->
						
							<h2>Find autori by ?</h2>
							<hr>
							<div class="row">
								<div class="col-sm-10">
									<div class="panel panel-primary filterable">
										<div class="panel-heading">
											<h3 class="panel-title">Autori
												<a href="inserisce-autore.jsp">
													<span class="glyphicon btn-glyphicon glyphicon-plus-sign img-circle"></span>
													Add
												 </a>
											</h3>
											
											<!-- class="glyphicon btn-glyphicon glyphicon-plus-sign img-circle" -->

										</div>

										<form action="autori" method="get" autocomplete="off">
											<table class="table">
												<thead>
													<tr>

														<th>
														<input type="text" class="form-control"	placeholder="NOME" name="nome" id="nome">
														</th>
														<th>
														<input type="text" class="form-control"	placeholder="COGNOME" name="cognome" id="cognome" >
														</th>
														<th>
														<!-- <input type="date" class="form-control"	placeholder="DATA DI NASCITA" name="data">
														 -->
														 <p>DATA NASCITA</p>
														 
														 
														 </th>
														
																						
														
															
															
														<td><button class="btn btn-default btn-xs btn-filter">
																<span class="glyphicon glyphicon-filter"></span> Filter
															</button></td>
														<td></td>
													</tr>
												</thead>
												</form>
												<tbody>
													<%
													for (Autore autore : listAutori) {
													%>
													<tr>
														<td><%=autore.getNome()%></td>
														<td><%=autore.getCognome()%></td>
														<td><%=autore.getData_nascita()%></td>
														
														<td>
														<form action="ShowLibriDiUnAutore" method="get">
														
															<button type="submit" class="btn icon-btn btn-primary" name="idAutore" value="<%=autore.getId()%>">
																<span class="glyphicon btn-glyphicon glyphicon-list img-circle"></span>
															</button>
															<input type="hidden" name="nomeAutore"
																value="<%=autore.getNome()%>">
															
															
														</form>
														</td>
														<td>
														<form action="ViewAndEditSchedaAutore">
														
															<button type="submit" class="btn icon-btn btn-primary" name="idAutore" value="<%=autore.getId()%>">
																<span class="glyphicon btn-glyphicon glyphicon-eye-open img-circle"></span>
															</button>
															
														</form>
														</td>
														<td>
														<form action="DeleteAutore">
																			
															<button type="submit" class="btn icon-btn btn-danger" name="idAutore" value="<%=autore.getId()%>">
																<span class="glyphicon btn-glyphicon glyphicon-trash img-circle"></span>
															</button>										
															
														</form>
														</td>
													</tr>
													<%
													}
													%>
												</tbody>
											</table>
										
									</div>
								</div>
							</div>
						
					</div>
					<!--END TABELLA AUTORI -->
			
			
			
			
			
			
			
				</main>

			</div>
		</div>

	</div>
	<link
		href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css"
		rel="stylesheet" id="bootstrap-css">
	<script
		src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
	<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
	<link
		href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css"
		rel="stylesheet"
		integrity="sha384-T8Gy5hrqNKT+hzMclPo118YTQO6cYprQmhrYwIiQ/3axmI1hQomh7Ud2hPOy8SP1"
		crossorigin="anonymous">

	<link rel="stylesheet" href="css/home.css">
	<script src="js/script.js"></script>
</body>
</html>
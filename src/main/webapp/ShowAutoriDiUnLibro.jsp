<%@page import="java.util.List"%>
<%@page import="it.etlabora.SpringWebBiblioteca.modello.Autore"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
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
					List<Autore> listAutoribyIdLIbro = (List<Autore>) request.getAttribute("listAutoribyIdLIbro");
					String 	titoloLibro=(String)request.getAttribute("titoloLibro");
					%>
					
				<main>
					

					<!--START TABELLA AUTORI -->
						
							<h2>Autori del Libro </h2>
							<p><%=titoloLibro %></p>
							<hr>
							<div class="row">
								<div class="col-sm-10">
									<div class="panel panel-primary filterable">
										<div class="panel-heading">
											<h3 class="panel-title">Autori
												<!-- <a href="inserisce-autore.jsp">
													<span class="glyphicon btn-glyphicon glyphicon-plus-sign img-circle"></span>
													Add
												 </a> -->
											</h3>
											
											<!-- class="glyphicon btn-glyphicon glyphicon-plus-sign img-circle" -->

										</div>

										<form action="libri" method="get">
											<table class="table">
												<thead>
													<tr>

														<th><p >NOME</p>
														</th>
														<th>
														<p >COGNOME</p></th>
														<th>
														<!-- <input type="date" class="form-control"	placeholder="DATA DI NASCITA" name="data">
														 -->
														<p>DATA NASCITA</p> 
														 
														 </th>
														
														
													</tr>
												</thead>
												<tbody>
													<%
													for (Autore autore : listAutoribyIdLIbro) {
													%>
													<tr>
														<td><%=autore.getNome()%></td>
														<td><%=autore.getCognome()%></td>
														<td><%=autore.getData_nascita()%></td>
																											
														
													</tr>
													<%
													}
													%>
												</tbody>
											</table>
										</form>
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
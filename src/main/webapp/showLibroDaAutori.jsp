<%@page import="it.etlabora.SpringWebBiblioteca.modello.Libro"%>
<%@page import="it.etlabora.SpringWebBiblioteca.modello.Autore"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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

<%

//listLibribyIdAutore
String nomeAutore= (String)request.getAttribute("nomeAutore");
List<Libro> listLibribyIdAutore = (List<Libro>) request.getAttribute("listLibribyIdAutore");

%>


<main>
					

					<!--START TABELLA AUTORI -->
						
							<h2>Libri del Autore </h2>
							<p><%=nomeAutore %></p>
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
<th><p >ISBN</p>
														</th>
														<th><p >TITOLO</p>
														</th>
														<th>
														<p >DESCRIZIONE</p></th>
														<th>
														<!-- <input type="date" class="form-control"	placeholder="DATA DI NASCITA" name="data">
														 -->
														<p>NUMERO PAGINA</p> 
														 
														 </th>
														 <th>
														 <p>DATA</p> 
														 
														 </th>
														 <th>
														 <p>GENERE</p> 
														 
														 </th>
														
														
													</tr>
												</thead>
												<tbody>
													<%
													for (Libro l : listLibribyIdAutore) {
													%>
													<tr>
														<td><%=l.getIsbn() %></td>
<td><%=l.getTitolo()%></td>
<td><%=l.getDescrizione()%></td>
<td><%=l.getNumero_pagine()%></td>
<td><%=l.getData_pubblicazione()%></td>
<td><%=l.getGenere().getNome()%></td>
																											
														
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
<%@page import="it.etlabora.SpringWebBiblioteca.modello.Autore"%>
<%@page import="it.etlabora.SpringWebBiblioteca.modello.Genere"%>
<%@page import="it.etlabora.SpringWebBiblioteca.modello.Libro"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body class="home" onload="fieldRed()">
	<div class="container-fluid display-table">

		<div class="row display-table-row">

			<!--  barra lateral izquierda oscura -->
			<%@ include file="menu-sidebar.jsp"%>



			<div class="col-md-10 col-sm-11 display-table-cell v-align">

				<%@ include file="header.jsp"%>

				<main>


					<%
					//Libro libro = (Libro) request.getAttribute("libro");
					//request.setAttribute("libro", libro);
					%>


					<div id="loginbox" style="margin-top: 50px;"
						class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
						<div class="panel panel-info">
							<div class="panel-heading">
								<div class="panel-title">
									<h2 class="text-center">Schieda Libro</h2>
									<h3>Edit Libro</h3>
								</div>

							</div>

							<div style="padding-top: 30px" class="panel-body">

								<div style="display: none" id="login-alert"
									class="alert alert-danger col-sm-12"></div>

								<form method="POST" action="ViewAndEditSchedaLibro"
									class="form-horizontal" role="form" id="form-edit-libro">

									<div class="form-group">
										<input type="hidden" name="idLibro" value="${libro.id}">

										<div class="col-md-9">
											<input type="text" class="form-control" name="isbn" id="isbn"
												value="${libro.isbn}" size="13"  readonly>
										</div>
										<!-- glyphicon glyphicon-pencil -->
										<button type="button" id="buttonedit"
											onclick="ShowEditInputIsbn()"
											class="btn icon-btn btn-primary">
											<span
												class="glyphicon btn-glyphicon glyphicon-pencil img-circle"></span>
										</button>
									</div>

									<div class="form-group">

										<div class="col-md-9">

											<input type="text" class="form-control" name="titolo"
												id="titolo" value="${libro.titolo}" readonly>
										</div>
										<!-- glyphicon glyphicon-pencil -->
										<button type="button" onclick="ShowEditInputTitolo()"
											class="btn icon-btn btn-primary">
											<span
												class="glyphicon btn-glyphicon glyphicon-pencil img-circle"></span>
										</button>

									</div>

									<!-- descrizione -->
									<div class="form-group">

										<div class="col-md-9">
											<textarea rows="5" cols="5" class="form-control" name="descrizione"
												id="descrizione" readonly>${libro.descrizione}</textarea>

										</div>
										<!-- glyphicon glyphicon-pencil -->
										<button type="button" onclick="ShowEditInputDescrizione()"
											class="btn icon-btn btn-primary">
											<span
												class="glyphicon btn-glyphicon glyphicon-pencil img-circle"></span>
										</button>

									</div>


									<div class="form-group">

										<div class="col-md-9">

											<input type="text" class="form-control" name="numero_pagine"
												id="numero_pagine" value="${libro.numero_pagine}" readonly>
										</div>
										<!-- glyphicon glyphicon-pencil -->
										<button type="button" onclick="ShowEditInputNumeroPagina()"
											class="btn icon-btn btn-primary">
											<span
												class="glyphicon btn-glyphicon glyphicon-pencil img-circle"></span>
										</button>

									</div>
									<div class="form-group">

										<div class="col-md-9">
											<select name="genere" class="form-control" id="genere" disabled>
												<option disabled selected value> -- select an genere -- </option>
												<c:forEach var="genere" items="${listGenere}">

													<c:if test="${genere.nome==libro.genere.nome}">
														<option  selected value="${genere.id}" >${ genere.nome}</option>
													</c:if>

													<c:if test="${genere.nome!=libro.genere.nome}">
														<option  value="${ genere.id}" >${ genere.nome}</option>
													</c:if>

												</c:forEach>
											</select>
										</div>
										
										
										
										
										<!-- glyphicon glyphicon-pencil -->
										<button type="button" onclick="ShowEditInputGenere()"
											class="btn icon-btn btn-primary">
											<span
												class="glyphicon btn-glyphicon glyphicon-pencil img-circle"></span>
										</button>

									</div>
									<div class="form-group">

										<div class="col-md-9">
											<select name="autore" class="form-control select-autore" id="autore" disabled multiple style="height: 500px;">
												<option disabled selected value> -- select an autore -- </option>
												<c:forEach var="autoriDelLibro" items="${listAutoreDelLibro}">
														
															
															
																<option selected value="${autoriDelLibro.id}">${autoriDelLibro.id}--${autoriDelLibro.nome}${autoriDelLibro.cognome}	</option>
															
															
													
													</c:forEach>
													<c:forEach var="autore" items="${listAutore}">
													
															<option  value="${autore.id}">${autore.id}--${autore.nome}${autore.cognome}	</option>
														
															
														</c:forEach>
														
												
											</select>
										</div>
										<!-- glyphicon glyphicon-pencil -->
										<button type="button" onclick="ShowEditInputAutore()"
											class="btn icon-btn btn-primary">
											<span
												class="glyphicon btn-glyphicon glyphicon-pencil img-circle"></span>
										</button>

									</div>
									
									<!--data_pubblicazione  -->
									<!--<fmt:formatDate value="${libro.data_pubblicazione}" pattern="yyyy-MM-dd" />-->
									
									<input type="date" name="data_pubblicazione" value="${libro.data_pubblicazione}" id="data_pubblicazione">

									<div style="margin-top: 10px" class="form-group">
										<!-- Button -->

										<div class="col-sm-12 controls">

											<button type="submit" id="btn-login"
												class="btn btn-lg btn-success btn-block" onclick="EnableAllField()">Edit</button>



										</div>
									</div>

								</form>



							</div>
						</div>
					</div>






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
	<script src="js/buttonedit.js"></script>


</body>
</html>
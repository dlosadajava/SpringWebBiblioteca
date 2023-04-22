<%@page import="it.etlabora.SpringWebBiblioteca.modello.Autore"%>
<%@page import="it.etlabora.SpringWebBiblioteca.modello.Genere"%>
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

				<%@ include file="header.jsp"%>

				<main>
					<%
					List<Genere> listGenere = (List<Genere>) request.getAttribute("listGenere");
					List<Autore> listAutore = (List<Autore>) request.getAttribute("listAutore");
					
			
					
					%>
					
					<!-- FORMULARIO DI INSERIRE -->

					
						<div id="loginbox" style="margin-top: 50px;"
							class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
							<div class="panel panel-info">
								<div class="panel-heading">
									<div class="panel-title">Inserisce un nuovo libro</div>
									<div
										style="float: right; font-size: 80%; position: relative; top: -10px">
										<a href="libri">Lista di libri?</a>
									</div>
								</div>

								<div style="padding-top: 30px" class="panel-body">

									<div style="display: none" id="login-alert"
										class="alert alert-danger col-sm-12"></div>

									<form method="POST" action="InserisceLibro"
										class="form-horizontal" role="form">

										<div style="margin-bottom: 25px" class="input-group">
											<input type="text" class="form-control" name="isbn" value=""
												placeholder="isbn">
										</div>
										<div style="margin-bottom: 25px" class="input-group">
											<input type="text" class="form-control" name="titolo"
												value="" placeholder="titolo">
										</div>
										<div style="margin-bottom: 25px" class="input-group">
									<!-- <input type="text" class="form-control" name="descrizione"
												value="" placeholder="descrizione"> -->		
												<textarea rows="5" cols="" class="form-control" name="descrizione"
												value="" placeholder="descrizione"></textarea>
										</div>

										<div style="margin-bottom: 25px" class="input-group">
											<input type="number" class="form-control" name="numeroPagina"
												value="" placeholder="numeroPagina">
										</div>
										<div style="margin-bottom: 25px" class="input-group">
											<select name="genere"  class="form-control">
												<option selected>Genere</option>
												<%
												for (Genere genere : listGenere) {
												%>
												<option value="<%=genere.getId()%>"><%=genere.getNome()%></option>
												<%
												}
												%>

											</select>
										</div>
										<div style="margin-bottom: 25px" class="input-group" >
											<select name="autore"  class="form-control" multiple>
												<option selected>Autore</option>
												<%
												for (Autore autore : listAutore) {
												%>
												<option value="<%=autore.getId()%>"><%=autore.getNome()+" "+autore.getCognome()%></option>
												<%
												}
												%>

											</select>
										</div>


										<div style="margin-bottom: 25px" class="input-group">
											Data Publicazione <input type="date" name="dataP"
												class="form-control">
										</div>



										<div style="margin-top: 10px" class="form-group">
											<!-- Button -->

											<div class="col-sm-12 controls">

												<button type="submit" id="btn-login"
													class="btn btn-lg btn-success btn-block">SEND</button>



											</div>
										</div>



									</form>



								</div>
							</div>
						</div>
						
					

				<!--END FORMULARIO DI INSERIRE -->
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
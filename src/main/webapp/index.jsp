<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>HOME</title>
</head>
<body class="home">
	<div class="container-fluid display-table">

		<div class="row display-table-row">

			<!--  barra lateral izquierda oscura -->
			<%@ include file="menu-sidebar.jsp"%>



			<div class="col-md-10 col-sm-11 display-table-cell v-align">

				<%@ include file="header.jsp"%>


				<main>
					<!--START contenuto che varia -->
					<div class="user-dashboard">


						<!-- nome utente e logout -->
						<%
						String username = (String) session.getAttribute("unsername");
						%>
						<h1>
							Hello,<%=username%></h1>



						<!--START SCHIEDA LIBRO E AUTORE -->
						<div class="row">
							<div class="col-md-6 col-sm-6 col-xs-12 gutter">

								<div class="sales">
									<h2>Libri</h2>
									<a class="btn icon-btn btn-success button-add" href="InserisceLibro">
										<span
										class="glyphicon btn-glyphicon glyphicon-plus img-circle text-success">
									</span> Add
									</a>
									
									<table class="table" >
									<thead>
										<tr>
											<th>ISBN</th>
											<th>TITOLO</th>
											<th>GENERE</th>
											<th>DATA</th>
											<th></th>
											<th></th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="libro" items="${listLibro}">
											<tr>
												<td>${libro.isbn}</td>
												<td><c:out value="${libro.titolo}"></c:out></td>
												<td>${libro.genere.nome}</td>
												<td><fmt:formatDate value="${libro.data_pubblicazione}"
														pattern="yyy/MM/dd" /></td>

											</tr>






										</c:forEach>

									</tbody>
								</table>
								</div>

								


							</div>

							<div class="col-md-6 col-sm-6 col-xs-12 gutter">

								<div class="sales report">
									<h2>Autori</h2>
									<a class="btn icon-btn btn-success button-add" href="InserisceAutore">
										<span
										class="glyphicon btn-glyphicon glyphicon-plus img-circle text-success">
									</span>Add
									</a>
									
											<table class="table" >
									<thead>
										<tr>
											<th>Nome</th>
											<th>Data di nascita</th>
											<th></th>
											<th></th>
										</tr>

									</thead>
									<tbody>
										<c:forEach var="autore" items="${listAutore}">

											<tr>
												<td>${autore.nome}&nbsp;&nbsp;${autore.cognome}</td>
												<td><fmt:formatDate value="${autore.data_nascita}"
														pattern="yyyy/MM/dd" /></td>
												
											</tr>
										</c:forEach>
									</tbody>
								</table>
								</div>

						
							</div>
						</div>

						<!--END SCHIEDA LIBRO E AUTORE -->




					</div>

					<!-- END contenuto che varia -->

				</main>



			</div>
		</div>

	</div>





	<link href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
	<script
		src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
	<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
	<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css" rel="stylesheet"
		integrity="sha384-T8Gy5hrqNKT+hzMclPo118YTQO6cYprQmhrYwIiQ/3axmI1hQomh7Ud2hPOy8SP1"
		crossorigin="anonymous">

	<link rel="stylesheet" href="css/home.css">
	<script src="js/script.js"></script>



</body>
</html>
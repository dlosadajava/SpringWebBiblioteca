<%@page import="it.etlabora.SpringWebBiblioteca.modello.Genere"%>
<%@page import="it.etlabora.SpringWebBiblioteca.modello.Libro"%>
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
<% List<Libro> libri=(List<Libro>)request.getAttribute("libri"); %>
<% List<Genere> listGeneri=(List<Genere>)request.getAttribute("generi"); %>
<div class="container">
    <h3>Find libri</h3>
    <hr>
    <div class="row">
        <div class="panel panel-primary filterable">
            <div class="panel-heading">
                <h3 class="panel-title">libri</h3>
                
            </div>
            <table class="table">
                <thead>
                    <tr >
   
                        <th><input type="text" class="form-control" placeholder="ISVN"></th>
                        <th><input type="text" class="form-control" placeholder="titolo"></th>
                        <th><input type="text" class="form-control" placeholder="Genere"></th>
                        <td></td>
						<td></td>
                    </tr>
                </thead>
                <tbody>
                <% for(Libro libro: libri) {%>
                    <tr>
                        <td><%=libro.getIsbn() %></td>
                        <td><%=libro.getTitolo() %></td>
                        <td><%=libro.getGenere().getNome() %></td>
                        <a class="btn icon-btn btn-primary"
													href="InserisceAutore"> View </a></td>
												<td><input value="${libro.id}" name="idLibro">
													<a class="btn icon-btn btn-danger" href="DeleteLibro"><span
														class="glyphicon btn-glyphicon glyphicon-trash img-circle text-danger"></span></a>
												</td>
                    </tr>
                    <%} %>
                </tbody>
            </table>
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



</body>
</html>
<%@page import="it.etlabora.SpringWebBiblioteca.modello.Autore"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@page import="it.etlabora.SpringWebBiblioteca.modello.Libro"%>
<%@page import="it.etlabora.SpringWebBiblioteca.modello.Genere"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>

<script> 
var listaN= new Array(); 
<%
List<Libro> libri = (List<Libro>) request.getAttribute("libri");
if(libri!=null)
for(int i=0;i<libri.size();i++){
%>

listaN[<%=i%>]="<%=libri.get(i).getTitolo()%>";
<%
}
%>



var listIsbn =null;
var listTitolo =null;

//este codigo se puede hacer igual que como el ejemplo de arriba
	function mostrarIsbn(){
		<%
					List<Libro> libriSBN = (List<Libro>) request.getAttribute("libri");
					%>
					var listaN='<%=libriSBN%>';
					listIsbn = listaN.split(", libro ");
					
					for(var i=0;i<listIsbn.length;i++){
					var is=listIsbn[i].split("isbn=");
					var req1=is[1].split(",");
					listIsbn[i]=req1[0];
					//alert("solo isbn"+req1[0]);	
					}
	}			
	
	
	//por hacer
	
		
	
	
	
					//no usado
	function mostrarGenere() {
		
		<%
		List<Genere> listG = (List<Genere>) request.getAttribute("generi");
		%>
		var lista="<%=listG%>";
		
		listIsbn = lista.split(", Genere ");
		//listIsbn = lista.split(", libro ");
			
		//[Genere [id=1, nome=genere1], Genere [id=2, nome=genere2], Genere [id=3, nome=genere3]]
		for(var i=0;i<listIsbn.length;i++){
			var g=listIsbn[i].split("nome=");
			//var g=listIsbn[i].split("isbn=");
			var g1=g[1].split("]");
			//alert("solo genero"+g1[0]);
			listIsbn[i]=g1[0];
			console.log("Dentro del metodo mostrar, listIsbn en i "+listIsbn[i]);
			//alert(listIsbn[i]);
			
			}
	}
	
	
	
	//mostrarGenere();
	
	mostrarIsbn();
	mostrarTitolo();
	
		
	
	






</script>


<!-- <body class="home"  onload="mostrar('<%=listG%>')"> -->



<!-- <body class="home"  onload="mostrar('<%=listG%>')"> -->
<body class="home">
	<div class="container-fluid display-table">

		<div class="row display-table-row">

			<!--  barra lateral izquierda oscura -->
			<%@ include file="menu-sidebar.jsp"%>



			<div class="col-md-10 col-sm-11 display-table-cell v-align">

				<%@ include file="header.jsp"%>
				<!-- CONTENUTO che VARIA -->
				<main>


					<%
					 libri = (List<Libro>) request.getAttribute("libri");
					%>
					<%
					List<Genere> listGeneri = (List<Genere>) request.getAttribute("generi");
					%>
					<%
					List<Autore> lisAutori = (List<Autore>) request.getAttribute("autori");
					%>
					<div>

						

						<!--START TABELLA LIBRI -->

						<h2>Find libri by ?</h2>
						<hr>
						<div class="row">
							<div class="col-sm-10">
								<div class="panel panel-primary filterable">
									<div class="panel-heading">
										<h3 class="panel-title">
											libri <a href="InserisceLibro"> <span
												class="glyphicon btn-glyphicon glyphicon-plus-sign img-circle"></span>
												Add
											</a>
										</h3>

										<!-- class="glyphicon btn-glyphicon glyphicon-plus-sign img-circle" -->

									</div>

									<form action="libri" method="get" autocomplete="off">
									
										<table class="table">
											<thead>
												<tr>

													<th><input type="text" class="form-control"
														placeholder="ISBN" name="isbn" id="isbn"></th>
													<th><input type="text" class="form-control"
														placeholder="Titolo" name="titolo" id="titolo"></th>
													<th><select name="genere" class="form-control"
														placeholder="Genere">
															<option disabled selected value> -- genere -- </option>
															<%
																for (Genere genere : listGeneri) {
																%>
															<option value="<%=genere.getNome()%>"><%=genere.getNome()%></option>
															<%
																}
																%>
													</select></th>

													<th><select name="autore" class="form-control"
														placeholder="Autore">
															<option disabled selected value> -- autore -- </option>
															<%
																for (Autore autore : lisAutori) {
																%>
															<option value="<%=autore.getNome()%>"><%=autore.getNome()%></option>
															<%
																}
																%>
													</select></th>



													<td>
													
													
													<button class="btn btn-default btn-xs btn-filter">
															<span class="glyphicon glyphicon-filter"></span> Filter
														</button>
													
													
													
													</td>
													<td></td>
												</tr>
											</thead>
											
									</form>
											<tbody>
												<%
													for (Libro libro : libri) {
													%>
												<tr>
													<td><%=libro.getIsbn()%></td>
													<td><%=libro.getTitolo()%></td>
													<td><%=libro.getGenere().getNome() %></td>


													<!-- SHOW AUTORI DEL LIBRO -->
													<td>
														<form action="ShowAutoriDiUnLibro">

															<button title="Autori del Libri" type="submit"
																class="btn icon-btn btn-primary" name="idLibro"
																value="<%=libro.getId()%>">

																<span
																	class="glyphicon btn-glyphicon glyphicon-list img-circle"></span>
															</button>

															<input type="hidden" name="titoloLibro"
																value="<%=libro.getTitolo()%>">
															

														</form>
													</td>
													<!-- Show altra info del libro -->
													<!-- glyphicon glyphicon-info-sign -->
													



													<!-- SHOW TUTTI CAMPI DEL LIBRO E UPDATE -->
													<td>
														<form method="get" action="ViewAndEditSchedaLibro">

															<button title="Edit Libro" type="submit"
																class="btn icon-btn btn-primary" name="idLibro"
																value="<%=libro.getId()%>">

																<span
																	class="glyphicon btn-glyphicon glyphicon-eye-open img-circle"></span>
															</button>

															<input type="hidden" name="titoloLibro"
																value="<%=libro.getTitolo()%>">

														</form>
													</td>

													<td>
														<form action="DeleteLibro">
															<!-- <input type="hidden" name="idLibro" value="<%=libro.getId()%>"> -->
															<!--<input class="btn icon-btn btn-danger" type="submit">  -->
															<button title="Delete Libro" type="submit"
																class="btn icon-btn btn-danger" name="idLibro"
																value="<%=libro.getId()%>">
																<span
																	class="glyphicon btn-glyphicon glyphicon-trash img-circle"></span>
															</button>

														</form>
													</td>
												</tr>
												<%
												}
												%>
											</tbody>
										</table>
									<!--</form>  -->
								</div>
							</div>
						</div>

					</div>
					<!--END TABELLA LIBRI -->
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
	<link rel="stylesheet" href="css/style.css">
	<!-- javascript que muestra los div con display none -->
	<script src="js/popup.js"></script>
	<script src="js/script.js"></script>

</body>
</html>
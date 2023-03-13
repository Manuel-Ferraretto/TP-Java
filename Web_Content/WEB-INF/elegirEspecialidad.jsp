<%@page import="java.util.LinkedList"%>
<%@page import="entities.Especialidad"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Elegir especialidad</title>

<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet" href="styles/estilos.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<body>
	<%
		LinkedList<Especialidad> especialidades = (LinkedList<Especialidad>)request.getAttribute("especialidades");
		Boolean aviso = (Boolean)request.getAttribute("habilitarMensaje");
	%>
 
	<form action="ReservarTurno" method="post">


		<div class="container">
      
 			<table class="table table-striped">
    			<thead>
      				<tr>
        				<th>Nombre</th>
        				<th>Seleccione una especialidad</th>
     				 </tr>
    			</thead>
    
    			<tbody>
   					 <% for (Especialidad e:especialidades) { %> 
      				<tr>
        				<td><%=e.getNombre() %></td>
        				<td><button class="btn btn-success" name="codigo_esp" type="submit" value="<%=e.getCodigo_esp() %>">Aceptar</button></td> 
      				</tr>
    				 <% } %> 
   				</tbody> 
  			</table>
  			<button class="btn btn-success" name="codigo_esp" type="submit" value="0">Volver atras</button>
		</div>
	</form>
	<br></br>
	<% if (aviso == true){ %>
			<p class="text-warning">"El usuario ya tiene un turno pendiente para dicha especialidad</p>
	<% }%>

</body>

</html>
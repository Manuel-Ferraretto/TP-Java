<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@page import="entities.Turnos"%>
 <%@page import="entities.Paciente"%>
  <%@page import="entities.Profesional"%>
 <%@page import="java.util.LinkedList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Turnos</title>
</head>

<meta charset="ISO-8859-1">

<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet" href="styles/estilos.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>


<body>

	<% 
		LinkedList<Turnos> turnos = (LinkedList<Turnos>)request.getAttribute("turnosProfesional");
		LinkedList<Paciente> pacientes = (LinkedList<Paciente>)request.getAttribute("pacientes");
		Profesional prof = (Profesional)session.getAttribute("profesional");
	%>
	
	
	<form action="CancelarTurno" method="post">
		<h2>Turnos de <%=prof.getNombre()%> <%=prof.getApellido() %></h2>
		<div class="container">
 			<table class="table table-striped">
    			<thead>
      				<tr>
      					<th>Nombre paciente</th>
      					<th>Apellido paciente</th>
        				<th>Fecha turno</th>
        				<th>Hora turno</th>
     				 </tr>
    			</thead>
    
    			<tbody>
    
   					 <% for (Turnos t:turnos) { %> 

      				<tr>
        				<td><%=t.getFecha_turno() %></td>
        				<td><%=t.getHora_turno() %></td>
      				</tr>
    				 <% } %>
   				</tbody> 
  			</table>
  			<button class="input-button" name="nro_turno" type="submit" value="0">Volver atras</button>
		</div>
	</form>

</body>
</html>
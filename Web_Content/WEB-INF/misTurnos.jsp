<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@page import="entities.Turnos"%>
 <%@page import="entities.Paciente"%>
 <%@page import="java.util.LinkedList"%>
 <%@page import="java.time.LocalDate"%>
 <%@page import="java.time.LocalTime"%>
 <%@page import="java.sql.Date" %>
 <%@page import="java.sql.Time" %>
  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Mis turnos</title>
</head>

<meta charset="ISO-8859-1">

<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet" href="styles/estilos.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>


<body>

	<% 
		LinkedList<Turnos> turnos = (LinkedList<Turnos>)request.getAttribute("turnosPaciente");
		Paciente pac = (Paciente)session.getAttribute("usuario");
	%>
	
	
	<form action="CancelarTurno" method="post">
		<h2>Turnos de <%=pac.getNombre()%> <%=pac.getApellido() %></h2>
		<div class="container">
 			<table class="table table-striped">
    			<thead>
      				<tr>
        				<th>Fecha turno</th>
        				<th>Hora turno</th>
        				<th>Numero turno</th>
        				<th>Nombre del profesional</th>
        				<th>Seleccione su turno</th>
     				 </tr>
    			</thead>
    
    			<tbody>
    
   					 <% for (Turnos t:turnos) { %> 
					
					<% Date sqlDate = null; %>
					<% Time sqlTime = null; %>
					
					<% sqlDate = Date.valueOf(t.getFecha_turno()); %>
					<% sqlTime = Time.valueOf(t.getHora_turno()); %>
					
      				<tr>
        				<td><%=sqlDate%></td>
        				<td><%=sqlTime %></td>
        				<td><%=t.getNumero() %></td>
        				<td><%=t.getProfesional().getNombre()%> <%=t.getProfesional().getApellido() %> </td>
        				<td><button class="btn btn-danger" name="nro_turno" type="submit" value="<%=t.getNumero() %>">Cancelar</button></td> 
      				</tr>
    				 <% } %>
   				</tbody> 
  			</table>
  			<button class="input-button" name="nro_turno" type="submit" value="0">Volver atras</button>
		</div>
	</form>

</body>
</html>
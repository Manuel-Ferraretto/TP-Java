<%@page import="java.util.LinkedList"%>
<%@page import="entities.Turnos"%>
<%@page import="entities.Profesional"%>
<%@page import="entities.Paciente"%>
<%@page import="java.sql.Date" %>
<%@page import="java.sql.Time" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Listado profesionales</title>

<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet" href="styles/estilos.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>

<body>
	<%
		LinkedList<Turnos> turnos = (LinkedList<Turnos>)request.getAttribute("turnosDisponibles");
		Profesional prof = (Profesional)request.getAttribute("profesional");
		Paciente pac = (Paciente)session.getAttribute("usuario");
		
		Integer id_pac = (Integer)session.getAttribute("id_paciente");
	%>

		<h2>Profesional: <%=prof.getNombre() %> <%=prof.getApellido() %></h2>

		<div class="container">
 			<table class="table table-striped" id="myTable">
    			<thead>
      				<tr>
        				<th>Fecha turno</th>
        				<th>Hora turno</th>
        				<th>Seleccione su turno</th>
     				 </tr>
    			</thead>
    
    			<tbody>
    
   					 <% for (Turnos t:turnos) { %> 
   					 
   					 <% 
						Date sqlDate = null; 
						Time sqlTime = null;
						sqlDate = Date.valueOf(t.getFecha_turno());
						sqlTime = Time.valueOf(t.getHora_turno());
					%>

      				<tr>
        				<td><%=sqlDate %></td>
        				<td><%=sqlTime %></td>
        				<td>
	        				<form action="AsignarTurno" method="post">
	        					<button class="btn btn-success" id="reservar" name="nro_turno" type="submit" value="1">Reservar</button>
	        					<input type="hidden" name="date" id="date" value="<%=sqlDate%>">
	        					<input type="hidden" name="time" id="time" value="<%=sqlTime%>">
	        					<input type="hidden" name="matricula_profesional" value="<%=prof.getMatricula() %>">
	        				</form>
        				</td> 
      				</tr>
    				 <% } %>
   				</tbody> 
  			</table>
  			<button class="btn btn-success" name="nro_turno" type="submit" value="0">Volver atras</button>
		</div>


<script>
/*
	$(document).ready(function()){
		$("#reservar").on('click', function()){
			var turnoDetails = {};
			var currentRow = $(this).closest("tr");
			var fecha = currentRow.find("td:eq(0)").text();
			var hora = currentRow.find("td:eq(1)").text();
			
			$("#date").val(fecha);
			$("#time").val(hora);
			});	
		});
*/
	
</script>
	
</body>

</html>
<%@page import="java.util.LinkedList"%>
<%@page import="entities.Profesional"%>
<%@page import="entities.Especialidad"%>
<%@page import="entities.Turnos"%>
<%@page import="entities.Paciente"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Historia clinica</title>

<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<body>
	<%
		LinkedList<Profesional> profesionales = (LinkedList<Profesional>)request.getAttribute("profesionales");
		LinkedList<Especialidad> nombres_especialidad = (LinkedList<Especialidad>)request.getAttribute("especialidades");
		LinkedList<Turnos> fecha_turnos = (LinkedList<Turnos>)request.getAttribute("turnos");
		Paciente pac = (Paciente)session.getAttribute("usuario");

	%>
	
<div class="container">
      <h1>Historia clinica de: <%=pac.getNombre()%> <%=pac.getApellido() %></h1>
  <table class="table table-striped">
    <thead>
      <tr>
        <th>Nombre Profesional</th>
        <th>Apellido Profesional</th>
        <th>Fecha Turno</th>
        <th>Nombre Especialidad</th>
      </tr>
    </thead>
    
    <tbody>
    
    <% for (Profesional p:profesionales) { %> 	
      	<tr>
        	<td><%=p.getNombre() %></td>
        	<td><%=p.getApellido() %></td>
        	        	
      <% for (Turnos t:fecha_turnos) { 
    	  if (t.getMatricula_prof() == p.getMatricula()){
      %>       		 
        	<td><%=t.getFecha_turno() %></td>
        	
      	</tr>
     <% } %>
     <% } %>
    <% } %>
   </tbody>
   
  </table>
</div>
		
</body>

</html>
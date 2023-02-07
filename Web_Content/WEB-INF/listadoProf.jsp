<%@page import="java.util.LinkedList"%>
<%@page import="entities.Profesional"%>
<%@page import="entities.Especialidad"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Listado profesionales</title>

<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<body>
	<%
		LinkedList<Profesional> profesionales = (LinkedList<Profesional>)request.getAttribute("listaProfesionales");
		Especialidad esp = (Especialidad)request.getAttribute("especialidad");
	%>
	
<div class="container">
      
  <table class="table table-striped">
    <thead>
      <tr>
        <th>Nombre</th>
        <th>Apellido</th>
        <th>Email</th>
        <th>Especialidad</th>
      </tr>
    </thead>
    
    <tbody>
    
    <% for (Profesional p:profesionales) { %> 	
      	<tr>
        	<td><%=p.getNombre() %></td>
        	<td><%=p.getApellido() %></td>
        	<td><%=p.getEmail()%></td>
        	<td><%=esp.getNombre()%></td>
      	</tr>
     <% } %>
   </tbody>
   
  </table>
</div>
		
</body>

</html>
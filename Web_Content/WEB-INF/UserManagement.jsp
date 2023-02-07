<%@page import="java.util.LinkedList"%>
<%@page import="entities.Paciente"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Java Web</title>

<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

	<%
		Paciente pac = (Paciente)session.getAttribute("usuario");
		LinkedList<Paciente> pacientes = (LinkedList<Paciente>)request.getAttribute("listaPacientes");
	%>


</head>
<body>

<h3><strong>Bienvenido <%=pac.getNombre()%> <%=pac.getApellido() %></strong></h3>

<div class="container">
      
  <table class="table table-striped">
    <thead>
      <tr>
        <th>Id</th>
        <th>DNI</th>
        <th>Nombre</th>
        <th>Apellido</th>
        <th>Contacto</th>
        <th>Email</th>
      </tr>
    </thead>
    
    <tbody>
    
    <% for (Paciente p:pacientes) { %>
    
      <tr>
      	<td><%=p.getId() %>
        <td><%=p.getDni() %></td>
        <td><%=p.getNombre() %></td>
        <td><%=p.getApellido() %></td>
        <td><%=p.getNum_tel() %></td>
        <td><%=p.getEmail() %></td>
      </tr>
     <% } %>
   </tbody>
   
  </table>
</div>
		
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.LinkedList"%>
<%@page import="entities.Administradores"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<title>Listado de administradores</title>
<%
    //Socio s = (Socio)session.getAttribute("usuario");
        	LinkedList<Administradores> ad = (LinkedList<Administradores>)request.getAttribute("listaAdministradores");
    %>
</head>
<body>
<br>
<p class="text-end"><a href="panel.jsp">Volver al panel administrativo</a>.</p>
<div>
<br>
<%if ((request.getAttribute("retro"))!=null) { %>
		<p class="text-center"> <%=request.getAttribute("retro")%> </p>		
	<% } %>
	<br>
      <form action="AdministradoresServlet" method="POST">
        <input class="btn btn-success" type="submit" name="accion" value="agregar">
      </form>
  </div>
    <div class="table-responsive">
    <table class="table">
      <thead>
        <tr class="table-info">
        <th scope="col">Id</th>
          <th scope="col">Username</th>
          <th scope="col">Contraseña</th>
           <th scope="col">Acciones</th>
        </tr>
      </thead>
      <tbody>
      <% for (Administradores adm : ad) { %>
        <tr>
        	<td> <%=adm.getId() %></td>
          <td><%=adm.getUsername()%></td>
          <td><%=adm.getPassword()%></td>          
          <td>
            <form action="AdministradoresServlet" method="POST">
              <input type="hidden" name="id2" value="<%=adm.getId()%>">
              <input class="btn btn-warning" type="submit" name="accion" value="Editar">
              <input class="btn btn-danger" type="submit" name="accion" value="Eliminar">
          </form>
          </td>
        </tr>
        <% } %>
      </tbody>
    </table>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>


</body>
</html>
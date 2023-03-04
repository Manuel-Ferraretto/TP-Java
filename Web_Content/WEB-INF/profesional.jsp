<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@page import="java.util.LinkedList"%>
<%@page import="entities.Profesional"%>
<%@page import="entities.Especialidad"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<title>Listado de profesionales</title>
<%
    //Socio s = (Socio)session.getAttribute("usuario");     	
LinkedList<Profesional> lp = (LinkedList<Profesional>)request.getAttribute("listaProfesionales");
    %>
</head>
<body>
<br>
<p class="text-end"><a href="panel.jsp">Volver al panel administrativo</a></p>
<div>

<br>
<%if ((request.getAttribute("muestraMensaje"))!=null) { %>
		<p class="alert alert-primary" role="alert"> <%=request.getAttribute("muestraMensaje")%> </p>		
	<% } %>
	<p class="text-center"> Advertencia: Recuerde que al eliminar un profesional de la clínica todos sus turnos serán eliminados</p>
	<br>
      <form action="ProfesionalesServlet" method="POST">
        <input class="btn btn-success" type="submit" name="accion" value="agregar">
      </form>
     <br>
</div>
  
  <div class="input-group mb-3">
  	<div class="input-group-prepend">
    	<span class="input-group-text" id="inputGroup-sizing-default">Filtrar</span>
  	</div>
  		<input type="text" id="myInput" onkeyup="myFunction()" placeholder="Ingrese especialidad">
  </div>
      
    <div class="table-responsive">
    <table class="table" id="myTable">
      <thead>
        <tr class="table-info">
        <th scope="col">Matricula</th>
          <th scope="col">Nombre</th>
          <th scope="col">Apellido</th>
          <th scope="col">Especialidad</th>
          <th scope="col">Email</th>
          <th scope="col">Estado</th>
           <th scope="col">Acciones</th>
        </tr>
      </thead>
      <tbody>
      <% for (Profesional p : lp) { %>
        <tr>
        	<td> <%=p.getMatricula() %></td>
          	<td><%=p.getNombre()%></td>
          	<td><%=p.getApellido()%></td>
         	<td><%=p.getEspecialidad().getNombre() %>
          	<td><%=p.getEmail()%></td>
            <% 
            	String est;
            	if (p.getEstado()==1) {est="Activo";} 
            	else {est="Inactivo";}
            %> 
            <td> <%= est %></td>      
          <td>
          
            <form action="ProfesionalesServlet" method="POST">
              <input type="hidden" name="matricula" value="<%=p.getMatricula()%>">
              <input class="btn btn-warning" type="submit" name="accion" value="Editar">
              <input class="btn btn-danger" type="submit" name="accion" value="Eliminar">
              <input class="btn btn-primary" type="submit" name="accion" value="Modificar estado">
          </form>
          </td>
        </tr>
        <% } %>
      </tbody>
    </table>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
	
	<script>
		function myFunction() {
  		// Declare variables
  		var input, filter, table, tr, td, i, txtValue;
  		input = document.getElementById("myInput");
  		filter = input.value.toUpperCase();
  		table = document.getElementById("myTable");
  		tr = table.getElementsByTagName("tr");

  		// Loop through all table rows, and hide those who don't match the search query
  		for (i = 0; i < tr.length; i++) {
    		td = tr[i].getElementsByTagName("td")[3];
    		if (td) {
      		txtValue = td.textContent || td.innerText;
      		if (txtValue.toUpperCase().indexOf(filter) > -1) {
        		tr[i].style.display = "";
      		} else {
        		tr[i].style.display = "none";
      			}
    		}
  			}
		}
	</script>

	<script>
		var inputs = document.getElementsByTagName('input');
		for(i=0; i<inputs.length; i++){
    		inputs[i].setAttribute('size',inputs[i].getAttribute('placeholder').length);
		}
	</script>


</body>
</html>
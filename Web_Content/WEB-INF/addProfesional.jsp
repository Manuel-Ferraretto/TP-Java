<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@page import="entities.Especialidad" %>
	<%@page import="java.util.LinkedList"%>
	<%@page import="java.time.LocalTime"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<title>Agregar profesional</title>
<%
    //Socio s = (Socio)session.getAttribute("usuario");
LinkedList<Especialidad> le = (LinkedList<Especialidad>)request.getAttribute("listaEspecialidades");
LinkedList<LocalTime> horas = (LinkedList<LocalTime>)request.getAttribute("horas");

%>
</head>
<body>
<br>
<p class="text-end"><a href= "ProfesionalesServlet?accion=listar" >Volver al Listado</a></p>

	<%if ((request.getAttribute("muestraMensaje"))!=null) { %>
		<p class="alert alert-primary" role="alert"> <%=request.getAttribute("muestraMensaje")%> </p>		
	<% } %>



<div class="container">
		<div class="row">
			<div class="col-2"></div>
			<div class="col-7">
				<div class="card text-center">
					<div class="card-header">

						<h3 class="display-6">Agregar Profesional</h3>
					</div>
					<div class="card-body">
	<form action="ProfesionalesServlet" method="Post">
		<div>
			<label for="matricula">Ingrese matricula del profesional</label> <br>
			<input type="text" class="form-control" name="matricula"> <br>
		</div>
		
		<div>
			<label for="nombre">Ingrese el nombre del profesional</label> <br>
			<input type="text" class="form-control" name="nombre"> <br>
		</div>
		
		<div>
			<label for="apellido">Ingrese el apellido del profesional</label> <br>
			<input type="text" class="form-control" name="apellido"> <br>
		</div>
		
		<div>
			<label for="apellido">Ingrese el email del profesional</label> <br>
			<input type="text" class="form-control" name="email"> <br>
		</div>
						
		<div>
			Seleccione especialidad del nuevo profesional
			<select name="codigo_especialidad" class="form-select" aria-label="Default select example">
				<% for (Especialidad e: le) { %>
					<option value="<%=e.getCodigo_esp() %>"><%=e.getNombre() %></option>
					 <% } %>
			</select>
		</div>
		
		<div>
			Seleccione hora inicio de jornada laboral:
			<select name="hora_inicio" class="form-select" aria-label="Default select example">   		
	        <% for (LocalTime horaInicio: horas) { %>              	
                	<option value="<%=horaInicio%>"><%=horaInicio%></option>
                <% } %>
             </select>
		</div>
		
		<div>
			Seleccione hora fin de jornada laboral:   		
	        <select name="hora_fin" class="form-select" aria-label="Default select example">   		
	        <% for (LocalTime horaFin: horas) { %>              	
                	<option value=<%=horaFin%>><%=horaFin%></option>
                <% } %>
             </select>
		</div>    
        <br><br>
		<input class="btn btn-primary btn-lg" type="submit" name="accion"
			value="Add">
	</form>
	</div>
				</div>
			</div>
			<div class="col-3"></div>
		</div>
	</div>
	<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>

	
</body>
</html>
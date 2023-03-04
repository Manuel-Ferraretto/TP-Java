<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="entities.Profesional"%>
    <%@page import="entities.Especialidad"%>
    <%@page import="java.util.LinkedList"%>
    <%@page import="java.time.LocalTime"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<title>Editar profesional</title>
</head>
<body>
<br>
<p class="text-end"><a href= "ProfesionalesServlet?accion=listar" >Volver al Listado</a></p>
<%
	if ((request.getAttribute("error")) != null) {
	%>
	<p class="text-center">
		<%=request.getAttribute("error")%>
	</p>
	<%
	}
	%>
	<%
	Profesional p = (Profesional) request.getAttribute("profesional");
	LinkedList<Especialidad> le = (LinkedList<Especialidad>)request.getAttribute("listaEspecialidades");
	LinkedList<LocalTime> horas = (LinkedList<LocalTime>)request.getAttribute("horas");
	%>
	<div class="container">
		<div class="row">
			<div class="col-2"></div>
			<div class="col-7">
				<div class="card text-center">
					<div class="card-header">

						<h3 class="display-6">Modificar Profesional</h3>
					</div>
					<div class="card-body">
						<form action="ProfesionalesServlet" method="POST">
						
							<input type="hidden" name="matricula" value="<%=p.getMatricula()%>">
							<div class="mb-3">
								<label for="exampleInputPassword1" class="form-label">Matricula</label>
								<input type="text" class="form-control" name="matricula"
									value="<%=p.getMatricula()%>">
							</div>
							
							<div class="mb-3">
								<label for="exampleInputPassword1" class="form-label">Nombre</label>
								<input type="text" class="form-control" name="nombre"
									value="<%=p.getNombre()%>">
							</div>
							
							<div class="mb-3">
								<label for="exampleInputPassword1" class="form-label">Apellido</label>
								<input type="text" class="form-control" name="apellido"
									value="<%=p.getApellido()%>">
							</div>
							
							<div class="mb-3">
								<label for="exampleInputPassword1" class="form-label">Email</label>
								<input type="text" class="form-control" name="email"
									value="<%=p.getEmail()%>">
							</div>
							
							<div>
							<label for="estado">Ingrese especialidad del profesional</label>
							 <% String tipo;
									for (Especialidad e : le) { 
								 if(e.getCodigo_esp()==p.getEspecialidad().getCodigo_esp()){tipo="checked";}else{tipo="";}%>
								<div class="form-check">
									<input class="form-check-input" type="radio" name="especialidad"
										id="exampleRadios1" value="<%=e.getCodigo_esp() %>" <%= tipo%> > <label
										class="form-check-label" for="exampleRadios1"> <%=e.getNombre() %> </label>
								</div>
							<%} %>
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
							
							<input class="btn btn-primary btn-lg" type="submit" name="accion" value="Actualizar">
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
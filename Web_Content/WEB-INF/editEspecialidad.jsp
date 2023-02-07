<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@page import="entities.Especialidad"%>
  <%@page import="entities.Valor_especialidad"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<title>Editar especialidad</title>
</head>
<body>
<br>
<p class="text-end"><a href= "EspecialidadesServlet?accion=listar" >Volver al Listado</a></p>
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
	Especialidad e = (Especialidad) request.getAttribute("especialidad");
	Valor_especialidad ve=(Valor_especialidad)request.getAttribute("valor");
	%>
<div class="container">
		<div class="row">
			<div class="col-2"></div>
			<div class="col-7">
				<div class="card text-center">
					<div class="card-header">

						<h3 class="display-6">Actualizar precio</h3>
					</div>
					<div class="card-body">
						<form action="EspecialidadesServlet" method="POST">
							<input type="hidden" name="id" value="<%=e.getCodigo_esp()%>">
							
							<div class="mb-3">
								<label for="exampleInputPassword1" class="form-label">Nombre</label>
								<input type="text" class="form-control" name="nombre" id="inputNombre"
									value="<%=e.getNombre()%>">
							</div>
							
							<div class="mb-3">
								<label for="exampleInputPassword1" class="form-label">Valor</label>
								<input type="number" min="1" class="form-control" name="valor1"
									value="<%=ve.getValor()%>">
							</div>
							
							<input class="btn btn-primary btn-lg" type="submit" name="accion"
								value="Actualizar">
						</form>
					</div>
				</div>
			</div>
			<div class="col-3"></div>
		</div>
	</div>
	
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
<script>
	document.getElementById('inputNombre').disabled = true;
</script>

</body>
</html>
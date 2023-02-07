<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<title>Clinica: panel administrativo</title>
</head>
<body>
<h3 class="text-center">Menu principal Administrador</h3>
<br>
<div class="row  row-cols-1 row-cols-md-3 g-4">
  <div class="col">
    <div class="card text-white bg-dark mb-3" style="max-width: 18rem;">
  <div class="card-header">OPCION 1</div>
  <div class="card-body">
    <h5 class="card-title">Especialidades</h5>
    <p class="card-text">Listar,agregar, modificar o eliminar especialidades</p>
    <a href="EspecialidadesServlet?accion=listar" class="btn btn-primary">Detalle</a>
  </div>
</div>
  </div>
  <div class="col">
   <div class="card text-white bg-dark mb-3" style="max-width: 18rem;">
  <div class="card-header">OPCION 2</div>
  <div class="card-body">
    <h5 class="card-title">Obras Sociales</h5>
    <p class="card-text">Listar,agregar, modificar o eliminar obras sociales</p>
    <a href="ObrasSocialesServlet?accion=listar" class="btn btn-primary">Detalle</a>
  </div>
</div>
  </div>
  <div class="col">
    <div class="card text-white bg-dark mb-3" style="max-width: 18rem;">
  <div class="card-header">OPCION 3</div>
  <div class="card-body">
    <h5 class="card-title">Administradores</h5>
    <p class="card-text">Listar,agregar, modificar o eliminar Administradores</p>
    <a href= "AdministradoresServlet?accion=listar" class="btn btn-primary">Detalle</a>
  </div>
</div>
  </div>
  
  <div class="col">
    <div class="card text-white bg-dark mb-3" style="max-width: 18rem;">
  <div class="card-header">OPCION 4</div>
  <div class="card-body">
    <h5 class="card-title">Profesionales</h5>
    <p class="card-text">Listar,agregar, modificar o eliminar profesionales</p>
    <a href="ProfesionalesServlet?accion=listar" class="btn btn-primary">Detalle</a>
  </div>
</div>
  </div>
  <div class="col">
    <div class="card text-white bg-dark mb-3" style="max-width: 18rem;">
  <div class="card-header">OPCION 5</div>
  <div class="card-body">
    <h5 class="card-title">Turnos</h5>
    <p class="card-text">Listar todos los turnos <br> <br></p>
    <a href= "TurnosServlet?accion=listar" class="btn btn-primary">Detalle</a>
  </div>
</div>
  </div>
</div>
	
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>        
</body>
</html>
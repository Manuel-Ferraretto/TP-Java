<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<title>Agregar obra social</title>
</head>
<body>
<br>
<p class="text-end"><a href= "ObrasSocialesServlet?accion=listar" >Volver al Listado</a></p>
<div class="container">
<div class="row">
			<div class="col-2"></div>
			<div class="col-7">
<div class="card text-center">
  <div class="card-body">
    <div class="card-header">

						<h3 class="display-6">Agregar obra social</h3>
					</div>
     <form action="ObrasSocialesServlet" method="Post">
     <div>
          <label for="nombre">Ingrese el nombre de la nueva obra social</label> 
          <br>
          <input type="text" class="form-control" name="nombre_os">
         
        </div>
        <input class="btn btn-primary btn-lg" type="submit" name="accion" value="Add">
     </form>
  </div>
</div>
</div>
</div>
</div>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>


</body>
</html>
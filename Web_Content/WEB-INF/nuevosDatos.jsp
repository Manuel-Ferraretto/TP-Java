<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@page import="entities.Paciente"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Actualización de datos</title>
</head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet" href="styles/estilos.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<body>

	<h2>Dejar en blanco si no desea modificar el dato</h2>
	 <section class="form-login">
       <form action="ActualizarDatos" method="post">
        
        <h3>Ingrese sus nuevos datos</h3>

        <label for="inputEmail">Email</label>
        <input type="email" name="email" id="inputEmail" class="input" placeholder="Ingrese su email">
        
        <label for="inputPass">Password</label>
        <input type="password" name="password" id="inputPass" class="input" placeholder="Ingrese su clave">
        
         <label for="inputNum">Número de teléfono</label>
        <input name="numtel" id="inputNum" class="input" placeholder="Ingrese su número de teléfono">
        
        
        <button type="submit" class="input-button">Aceptar</button>

       </form>
    </section>

</body>
</html>
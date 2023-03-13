<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Clinica privada Rosario</title>
    <link rel="stylesheet" href="styles/estilos.css">
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>

<body>
<div class="form-login">
	
	<form action="PacienteServlet" method="post">
	
	<h3>MENU PRINCIPAL</h3>
	
		<button type="submit" name="opcion" value="reservar" class="input-button">Reservar turno</button>
        <button type="submit" name="opcion" value="misturnos" class="input-button">Mis turnos</button>
        <button type="submit" name="opcion" value="misdatos" class="input-button">Actualizar mis datos</button>
        <br><br><br>
        <div class="text-center">
        	<button type="submit" name="opcion" value="signout" class="btn btn-danger">Cerrar sesion</button>
       	</div>
    </form> 
</div>
	
</body>
</html>
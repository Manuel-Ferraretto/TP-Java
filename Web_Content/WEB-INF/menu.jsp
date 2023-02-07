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
</head>

<body>
<div class="form-login">
	
	<form action="Listar_pacientes" method="post">
	
	<h3>Menu principal</h3>
	
		<a href="listar_pacientes?opcion=reservar" type="button" class="input-button">Reservar turno</a>
     
        <a href="listar_pacientes?opcion=listar"><button type="button" class="input-button">Listar pacientes</button></a>
        
       
    </form> 
</div>
	
</body>
</html>
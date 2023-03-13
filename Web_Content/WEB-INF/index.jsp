<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Inicio de sesión</title>
</head>
<body>
	
	<%
		Boolean aviso = (Boolean)request.getAttribute("habilitarMensaje");
	%>
	
	<% if (aviso == true){ %>
			<p class="text-warning">"El usuario ya tiene un turno pendiente para dicha especialidad</p>
	<% }%>
	

	 <div class="form-login">
       <form action="signin" method="post">
        
        <h3>Inciar sesion</h3>

        <label for="inputEmail">Nombre de usuario</label>
        <input type="text" name="username" id="inputEmail" class="input" placeholder="Ingrese su username">
        
        <label for="inputPass">Clave</label>
        <input type="password" name="password" id="inputPass" class="input" placeholder="Ingrese su clave">

        <button type="submit" class="input-button">Ingresar</button>
        <p><a href="nuevaCuenta.html">Crear una cuenta</a></p>
        <p><a href="#">¿Olvidó su clave?</a></p>
       </form>
    </div>
	
</body>
</html>
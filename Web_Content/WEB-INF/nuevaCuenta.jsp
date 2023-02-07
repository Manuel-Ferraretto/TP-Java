 <%@page import="entities.ObraSocial"%>
 <%@page import="java.util.LinkedList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Nueva Cuenta</title>
</head>

<meta charset="ISO-8859-1">

<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet" href="styles/estilos.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<body>

	<% 
		LinkedList<ObraSocial> obras_sociales = (LinkedList<ObraSocial>)request.getAttribute("obras_sociales");
	%>



	 <div class="form-createaccount">
        <form action="CrearCuenta" method="post">

                <h3>CREAR CUENTA</h3>
  

                <input type="text" name="nombre" class="input" placeholder="Ingrese su nombre">

                <input type="text" name="apellido" class="input" placeholder="Ingrese su apellido">

                <input type="text" name="dni" class="input" placeholder="Ingrese su numero de documento">
                
                <input type="email" name="email" class="input" placeholder="Input your email">

                <input type="password" name="clave" class="input" placeholder="Ingrese su clave">
                
                <input type="tel" name="celular" class="input" placeholder="Ingrese su numero de celular">
                
                <label for="os">Obra social</label>
                	<% for (ObraSocial os:obras_sociales) { %>              	
                		<div class="form-check">
                			<input class="form-check-input" type="radio" name="codigoObraSocial"
                					id="<%=os.getNombre() %>" value="<%=os.getId_obra_social() %>" requiered>
                			<label class="form-check-label" for="<%=os.getNombre() %>"><%=os.getNombre() %></label>
                		</div>
                		
                		<% } %>
                		
                <div class="text-center"><button type="submit" class="btn btn-success">Aceptar</button>
                	<br><br>
                	<p class="message"><a href="index.html"><b>Iniciar sesión</b></a></p>
                </div>
        </form>
    </div>
	
</body>
</html>
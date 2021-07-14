<%@ page import="entidad.Clientes"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
<jsp:include page="Menu.html"></jsp:include>

	<h1 style="text-align:center"> Administrador de Clientes</h1>
	<br>
	<br>
	<div style="display:flex; align-items: flex-start; width: 100%;">
	<div style="height: 100%; width: 50%; text-align:center; float:left;">

<form method="post" action="ServletClientes">
	<h2>Dar alta Cliente</h2>
	<br>
	<br>
	<b>Ingrese Dni: </b>
	<input type="Tel" name="TxtDni" required>
	<br>
	<br>
	<b>Ingrese CUIL: </b>
	<input type="Tel" name="TxtCuil" required>
	<br>
	<br>
	<b>Ingrese Nombre:</b>
	<input type="text" name="TxtNombre" required>
	<br>
	<br>
	<b>Ingrese Apellido: </b>
	<input type="text" name="TxtApellido" required>
	<br>
	<br>
	<b>Ingrese Usuario: </b>
	<input type="text" name="TxtUsu" required>
	<br>
	<br>
	<b>Ingrese clave: </b>
	<input type="password" name="TxtPass" required>
	<br>
	<br>
	<b>Ingrese clave nuevamente: </b>
	<input type="password" name="TxtPass2" required>
	<br>
	<br>
	<b>Ingrese sexo: </b>
		<select name="Sexo">
		<option>Femenino</option>
		<option>Masculino</option>
		<option>Otro</option>
		</select>
	<br>
	<br>
	<b>Ingrese nacionalidad: </b>
		<select name="Nacionalidad">
		<option>Argentina</option>
		<option>Brasil</option>
		<option>Chile</option>
		<option>Bolivia</option>
		<option>Peru</option>
		<option>Colombia</option>
		<option>Paraguay</option>
		<option>Uruguay</option>
		</select>
	<br>
	<br>
	<b>Fecha de Nacimiento</b>
	<input type="date" name="TxtFecnac" required>
	<br>
	<br>
	<b>Ingrese Direccion: </b>
	<input type="text" name="TxtDom">
	<br>
	<br>
	<b>Ingrese Localidad: </b>
	<input type="text" name="TxtLoc">
	<br>
	<br>
	<b>Ingrese Provincia: </b>
		<select name="Provincia">
		<option>Ciudad de Buenos Aires</option>
		<option>Buenos Aires</option>
		<option>Cordoba</option>
		<option>Santa Fe</option>
		<option>Corrientes</option>
		<option>Mendoza</option>
		</select>
	<br>
	<br>
	<b>Ingrese Email: </b>
	<input type="email" name="TxtEmail">
	<br>
	<br>
	<b>Ingrese Telefono: </b>
	<input type="Tel" name="TxtTel">
	<br>
	<br>
	<input type="submit" name="crear" value="Crear">
	</form>

</div>

<div style="height: 100%;  width: 50%; float:right; text-align:center;">

<% 
	Clientes cliente = new Clientes();
	if(request.getAttribute("Cliente_DNI") != null)
	{
		cliente = (Clientes) request.getAttribute("Cliente_DNI");
	}
	
	String estado;
	if(cliente.getEstado() == -1)
		estado = "";
	else if(cliente.getEstado() == 1)
		estado = "Activo";
		else
			estado = "Inactivo";
%>

<form method="get" action="ServletClientes">
	<h2>Modificar Cliente</h2>
	<br>
	<br>
	<b>Ingrese Dni: </b>
	<input type="Tel" name="TxtDni" required value=<%=cliente.getDni()%>>
	<input type="submit" name="generar" value="Buscar">
	<br>
	<br>
<% 
	if(request.getAttribute("Mensaje_Modificar") != null)
	{
		String Mensaje = request.getAttribute("Mensaje_Modificar").toString();
		if(Mensaje == "0")
		{
%>
		<b>Usuario no encontrado </b>
<%
		}
	
		if(Mensaje == "1")
		{
%>
		<b>Usuario modificado </b>
<%
		}
	}
%>
</form>

<% 
	if(cliente.getDni() != "")
	{
%>
<form method="post" action="ServletClientes">
	<input type="hidden" name="TxtDni" value=<%=cliente.getDni()%>>
	<br>
	<br>
	<b>Ingrese CUIL: </b>
	<input type="Tel" name="TxtCuil" required value=<%=cliente.getCuil()%>>
	<br>
	<br>
	<b>Ingrese Nombre:</b>
	<input type="text" name="TxtNombre" required value=<%=cliente.getNombre()%>>
	<br>
	<br>
	<b>Ingrese Apellido: </b>
	<input type="text" name="TxtApellido" required value=<%=cliente.getApellido()%>>
	<br>
	<br>
	<b>Ingrese clave</b>
	<input type="text" name="TxtPass" required value=<%=cliente.getContraseña()%>>
	<br>
	<br>
	<b>Ingrese clave nuevamente: </b>
	<input type="text" name="TxtPass2" required value=<%=cliente.getContraseña()%>>
	<br>
	<br>
	<b>Ingrese sexo: </b>
	<input type="text" list="Generos" required name="Sexo" value=<%=cliente.getSexo()%>>
		<datalist id="Generos">
			<option value="Femenino">
			<option value="Masculino">
			<option value="Otro">
		</datalist>
	<br>
	<br>
	<b>Ingrese nacionalidad: </b>
	<input type="text" list="Paises" name="Nacionalidad" required value=<%=cliente.getNacionalidad()%>>
		<datalist id="Paises">
			<option value="Argentina">
			<option value="Brasil">
			<option value="Chile">
			<option value="Bolivia">
			<option value="Peru">
			<option value="Colombia">
			<option value="Paraguay">
			<option value="Uruguay">
		</datalist>
	<br>
	<br>
	<b>Fecha de Nacimiento</b>
	<input type="date" name="TxtFecnac" required value=<%=cliente.getFecha_Nac()%>>
	<br>
	<br>
	<b>Ingrese Direccion: </b>
	<input type="text" name="TxtDom" value=<%=cliente.getDireccion()%>>
	<br>
	<br>
	<b>Ingrese Localidad: </b>
	<input type="text" name="TxtLoc" value=<%=cliente.getLocalidad()%>>
	<br>
	<br>
	<b>Ingrese Provincia: </b>
	<input type="text" list="Provincias" name="Provincia" value=<%=cliente.getProvincia()%>>
		<datalist id="Provincias">
			<option value="Ciudad de Buenos Aires">
			<option value="Buenos Aires">
			<option value="Cordoba">
			<option value="Santa Fe">
			<option value="Corrientes">
			<option value="Mendoza">
		</datalist>
	<br>
	<br>
	<b>Ingrese Email: </b>
	<input type="email" name="TxtEmail" value=<%=cliente.getCorreo()%>>
	<br>
	<br>
	<b>Ingrese Telefono: </b>
	<input type="Tel" name="TxtTel" value=<%=cliente.getTelefono()%>>
	<br>
	<br>
	<b>Ingrese Estado: </b>
	<input type="text" list="Estados" name="Estado" required value=<%=estado%>>
		<datalist id="Estados">
			<option label="Activo" value="1">
			<option label="Inactivo" value="0">
		</datalist>
	<br>
	<br>
	<input type="submit" name="Modificar" value="Modificar">
</form>

<% 
	}
%>

</div>
</div>
</body>
</html>
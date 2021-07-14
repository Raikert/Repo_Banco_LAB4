<%@ page import="java.util.ArrayList" %>
<%@ page import="entidad.Cliente" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<style>
	.controles
	{
		border-spacing: 0 25px;
		margin:auto; 
		text-align:center;
	}
	.clientes
	{
		margin:auto;
		width:1000px;
		height:250px; 
		display:block; 
		overflow: auto;
		border-style: inset;
        border-width: 5px;
		border-color:#229941;
		border-collapse:collapse;
		background-color:#a3998c;
		
	}
	.clientes th
	{
		background-color:#f0c711;
		border:1px solid black;
	}
	.clientes td
	{
		background-color:white;
		border:1px solid black;
		padding:5px 10px;
	}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
<jsp:include page="Menu.html"></jsp:include>

<% 
	ArrayList<Cliente> clientes = new ArrayList<Cliente>();
	
	if(request.getAttribute("Array_Clientes") != null)
	{
		clientes = (ArrayList<Cliente>) request.getAttribute("Array_Clientes");
	}
%>

<div style="height: 100%; width: 100%; text-align:center">
	<h1>Buscador de Clientes</h1>
	<b>1) Filtre por uno o varios campos <br>2) No hace falta un valor exacto <br>3) Si solo pulsa "Buscar" obtendra todos los clientes </b>
	<br>
	<br>
<% 
	if(request.getAttribute("Filas") != null)
	{
%>
		<b>Se encontraron <%=request.getAttribute("Filas")%> Clientes</b>
		<br>
		<br>
<% 
	}
%>
	<form method="post" action="ServletClientes">
		<input type="submit" name="enviar" value="Buscar" style="width:100px">
		<br>
		<br>
		<table class="clientes">
			<tr>
				<th>
				DNI
				</th>
				<th>
				CUIL
				</th>
				<th>
				Nombre
				</th>
				<th>
				Apellido
				</th>
				<th>
				Genero
				</th>
				<th>
				Nacionalidad
				</th>
				<th>
				Fecha de nacimiento
				</th>
				<th>
				Direccion
				</th>
				<th>
				Localidad
				</th>
				<th>
				Provincia
				</th>
				<th>
				Email
				</th>
				<th>
				Telefono
				</th>
				<th>
				Usuario
				</th>
				<th>
				Contraseña
				</th>
				<th>
				Estado
				</th>
			</tr>
			<tr>
				<td>
				<input type="text" name="TxtDni" pattern="[0-9]{0,8}" title="El DNI solo debe tener numeros, hasta un maximo de 8 digitos">
				</td>
				<td>
				<input type="text" name="TxtCuil" pattern="[0-9]{0,11}" title="El CUIL solo debe tener numeros, hasta un maximo de 11 digitos">
				</td>
				<td>
				<input type="text" name="TxtNombre" pattern=".{0,50}" title="Maximo de 50 caracteres alcanzado">
				</td>
				<td>
				<input type="text" name="TxtApellido" pattern=".{0,50}" title="Maximo de 50 caracteres alcanzado">
				</td>
				<td>
				<select name="Sexo">
					<option hidden value="0">Filtrar genero</option>
					<option>Femenino</option>
					<option>Masculino</option>
					<option>Otro</option>
				</select>
				</td>
				<td>
				<select name="Nacionalidad">
					<option hidden value="0">Filtrar nacionalidad</option>
					<option>Argentina</option>
					<option>Brasil</option>
					<option>Chile</option>
					<option>Bolivia</option>
					<option>Peru</option>
					<option>Colombia</option>
					<option>Paraguay</option>
					<option>Uruguay</option>
				</select>
				</td>
				<td>
				<input type="date" name="TxtFecnac1">
				<br>
				<input type="date" name="TxtFecnac2">
				</td>
				<td>
				<input type="text" name="TxtDom" pattern=".{0,50}" title="Maximo de 50 caracteres alcanzado">
				</td>
				<td>
				<input type="text" name="TxtLoc" pattern=".{0,50}" title="Maximo de 50 caracteres alcanzado">
				</td>
				<td>
				<select name="Provincia">
					<option hidden value="0">Filtrar provincia</option>
					<option>Ciudad de Buenos Aires</option>
					<option>Buenos Aires</option>
					<option>Cordoba</option>
					<option>Santa Fe</option>
					<option>Corrientes</option>
					<option>Mendoza</option>
				</select>
				</td>
				<td>
				<input type="email" name="TxtEmail" pattern=".{0,50}" title="Maximo de 50 caracteres alcanzado">
				</td>
				<td>
				<input type="text" name="TxtTel" pattern="[0-9]{0,10}" title="El Telefono solo debe tener numeros, hasta un maximo de 10 digitos">
				</td>
				<td>
				<input type="text" name="TxtUsu" pattern=".{0,20}" title="Maximo de 20 caracteres alcanzado">
				</td>
				<td>
				<input type="text" name="TxtPass" pattern=".{0,20}" title="Maximo de 20 caracteres alcanzado">
				</td>
				<td>
				<select name="Estado">
					<option hidden value="0">Filtrar estado</option>
					<option>Activo</option>
					<option>Inactivo</option>
				</select>
				</td>
			</tr>
<% 
	if(!clientes.isEmpty())
	{
		for(Cliente cliente : clientes)
		{
%>
			<tr>
				<td>
				<%=cliente.getDni()%>
				</td>
				<td>
				<%=cliente.getCuil()%>
				</td>
				<td>
				<%=cliente.getNombre()%>
				</td>
				<td>
				<%=cliente.getApellido()%>
				</td>
				<td>
				<%=cliente.getSexo()%>
				</td>
				<td>
				<%=cliente.getNacionalidad()%>
				</td>
				<td>
				<%=cliente.getFec_nacimiento()%>
				</td>
				<td>
				<%=cliente.getDireccion()%>
				</td>
				<td>
				<%=cliente.getLocalidad()%>
				</td>
				<td>
				<%=cliente.getProvincia()%>
				</td>
				<td>
				<%=cliente.getCorreo_electronico()%>
				</td>
				<td>
				<%=cliente.getTelefono()%>
				</td>
				<td>
				<%=cliente.getUsuario()%>
				</td>
				<td>
				<%=cliente.getContraseña()%>
				</td>
				<td>
				<%=cliente.getEstado()%>
				</td>
			</tr>
<%
		}
	}
%>
		</table>
	</form>
</div>
</body>
</html>
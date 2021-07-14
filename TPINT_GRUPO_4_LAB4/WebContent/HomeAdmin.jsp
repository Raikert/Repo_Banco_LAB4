<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
<jsp:include page="Menu.html"></jsp:include>
<center>

<h1>Buscador de Clientes</h1>
<br>
<br>
<form action="">
<b>N° Cliente: </b>
<input type="text" name="TxtNrocliente">
<br>
<br>
<b>Nombre: &nbsp;&nbsp;&nbsp;</b>
<input type="text" name="TxtNombre">
<br>
<br>
<b>DNI: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b>
<input type="text" name="TxtDni">
<br>
<br>
<b>Fecha nacimiento: </b>
<input type="text" name="TxtFecini"> A <input type="text" name="TxtFecfin">
<br>
<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<input type="submit" name="enviar" value="Buscar" style="width:150px">
</form>
<br>
<br>
<table border="1" style="text-align: center;">
	<tr><td>N° Cliente</td><td>DNI</td><td>Nombre</td><td>Apellido</td><td>Fecha Nacimiento</td><td>Telefono</td><td>Email</td><td>Usuario</td></tr>
	<tr><td>156682</td><td>27698187</td><td>Jose</td><td>Gomez</td><td>12/01/1982</td><td>-</td><td>Josegomez@gmail.com</td><td>Jgomez</td></tr>
	<tr><td>126697</td><td>11874211</td><td>Mario</td><td>Amalfi</td><td>25/08/1961</td><td>1533685212</td><td>-</td><td>Mamalfi</td></tr>
	<tr><td>089992</td><td>94882311</td><td>Ana</td><td>Lopez</td><td>12/10/1988</td><td>1530537756</td><td>Anitabelive@yahoo.com</td><td>Anlpz</td></tr>
	<tr><td>215231</td><td>20336582</td><td>Ricardo</td><td>Krammer</td><td>08/03/1975</td><td>-</td><td>Richard02@hotmail.com</td><td>RickyK</td></tr>
</table>
</center>
</body>
</html>
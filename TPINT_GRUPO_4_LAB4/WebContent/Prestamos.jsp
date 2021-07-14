<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
<center>
<h1>Solicitar Prestamos</h1>
<br>
<form action="">
<b>Seleccione cuenta: </b>
<select name="cuenta">
	<option>108</option>
	<option>154</option>
	<option>288</option>
</select>
<br>
<br>
<b>Ingrese Monto a pedir: $</b>
<input type="text" name="TxtMonto">
<br>
<br>
<b>Seleccione cantidad cuotas: </b>
<select name="cuotas">
	<option>6</option>
	<option>12</option>
	<option>18</option>
	<option>24</option>
	<option>36</option>
</select>&nbsp;
<input type="submit" name="generar" value="Calcular interes">
<br>
<br>
<b>Monto total a pagar: $</b>&nbsp;<b>36.000</b>
<br>
<br>
<b>Monto por cuota: $</b>&nbsp;<b>6.000</b>
<br>
<br>
<input type="submit" name="enviar" value="Enviar Solicitud">
</form>
</center>
</body>
</html>
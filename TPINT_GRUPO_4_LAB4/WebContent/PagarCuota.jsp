<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
<jsp:include page="Menu2.html"></jsp:include>
<center>
<h1>Pago de Cuotas</h1>
<br>
<br>
<div style="display:flex; align-items: flex-end; width: 100%;">
<div style="height: 100%; width: 40%; text-align:center; float:left;">
<form action="Home.jsp" method="POST" style="text-align:center;">
<b>Seleccione Prestamo: </b>	
<select name="prestamo">
	<option>15560</option>
	<option>11255</option>
	<option>03814</option>
</select>
<input type="submit" name="generar" value="Consulta">
<br>
<br>
<b>Seleccione cuotas a pagar: </b>
<select name="cuotas">
	<option>1</option>
	<option>2</option>
	<option>3</option>
	<option>4</option>
	<option>5</option>
</select>
<br>
<br>
<b>Seleccione cuenta a debitar:</b>
<select name="cuenta">
	<option>108</option>
	<option>154</option>
	<option>288</option>
</select>
<br>
<br>
<input type="submit" name="pagar" value="pagar" style="width:150px">
</form>
</div>
<div style="height: 100%;  width: 60%; float:right; text-align:right;">
<table border="1" style="text-align: center;">
	<tr><td>ID</td><td>Importe</td><td>Cuotas pendientes</td><td>Valor cuota</td></tr>
	<tr><td>15560</td><td>$100.000</td><td>6</td><td>$10.000</td></tr>
	<tr><td>11255</td><td>$80.000</td><td>2</td><td>$8.000</td></tr>
	<tr><td>03814</td><td>$150.000</td><td>1</td><td>$30.000</td></tr>
</table>
</div>
</div>
</center>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="https://kit.fontawesome.com/7181f6836f.js" crossorigin="anonymous"></script>
</head>
<body>
<jsp:include page="Menu2.html"></jsp:include>
<center>
<h1><center>Bienvenido Ramiro Deocares</center></h1>
<br>
<br>
<form action="Home.jsp" method="POST" style="text-align:center;">
	<b>Seleeciones una cuenta</b>
	<select name="cuenta">
		<option>108</option>
		<option>154</option>
		<option>288</option>
	</select>
	<br>
	<input type="submit" name="generar" value="Mostrar">
</form>
<br>
<br>
<div class="wrapper" style="width:25%; text-align:center; align:center;">
	<div style="display: flex" align-items: flex-end;>
	<div style="margin-left: 10px; font-size:18px; font-weight:bolder; width: 50%; text-align:right">Saldo: $</div>
	<div style="margin-left: 5px; font-size:18px; font-weight:bolder; width: 20%; text-align:left">1000</div>
	</div>
	<br>	
</div>
	
<h2>Ultimos Movimientos</h2>
<table border="1">
	<tr><td>Fecha</td><td>Detalle</td><td>Importe</td><td>Tipo</td></tr>
	<tr><td>02/06/2021</td><td>Pago cuota 2/6 prestamo</td><td>$2000</td><td>Pago Prestamo</td></tr>
	<tr><td>15/05/2021</td><td>Tranferencia 154->212</td><td>$15000</td><td>Transferencia</td></tr>
	<tr><td>03/05/2021</td><td>Pago cuota 1/6 prestamo</td><td>$2000</td><td>Pago Prestamo</td></tr>
	<tr><td>12/04/2021</td><td>Deposito</td><td>$10000</td><td>Alta Prestamo</td></tr>
	<tr><td>26/02/2021</td><td>Deposito</td><td>$10000</td><td>Alta Cuenta</td></tr>
</table>
</center>
</body>
</html>
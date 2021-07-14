<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Banco Deocares & Asociados</title>
</head>
<body>
<center>
<jsp:include page="Menu2.html"></jsp:include>
<h1>Historial de Movimientos</h1>
<div style="display:flex; align-items: flex-end; width: 100%;">
<div style="height: 100%; width: 40%; text-align:center; float:left;">
<form action="Home.jsp" method="POST" style="text-align:center;">
	<b>Seleecione una cuenta: </b>
	<select name="cuenta">
		<option>108</option>
		<option>154</option>
		<option>288</option>
	</select>
	<br>
	<br>
	<b>Seleccione tipo de transaccion: </b>
	<select name="tipo">
		<option>Todas</option>
		<option>Transferencias</option>
		<option>Pago Cuotas</option>
		<option>Prestamos</option>
	</select>
	<br>
	
	<b>Monto minimo: </b>
	<input type="text" name="TxtMin">
	<br>
	<b>Monto maximo: </b>
	<input type="text" name="TxtMax">
	<br>
	<b>Fecha: </b>
	<input type="text" name="TxtFecIni">
	<b> &nbsp; Y &nbsp;</b>
	<input type="text" name="TxtFecFin">
	<br>
	<b>Cantidad de resultados: </b>
	<b>10 </b>
	<input type="radio" name="RdCant" checked>
	<b>25 </b>
	<input type="radio" name="RdCant">
	<b>50 </b>
	<input type="radio" name="RdCant"> &nbsp;&nbsp;&nbsp; 
	<input type="submit" name="Cargar" value="Generar"> 
</form>
</div>
<div style="height: 100%;  width: 60%; float:right; text-align:right;">
<table border="1" style="text-align: center;">
	<tr><td>Fecha</td><td>Detalle</td><td>Importe</td><td>Tipo</td></tr>
	<tr><td>02/06/2021</td><td>Pago cuota 2/6 prestamo</td><td>$2000</td><td>Pago Prestamo</td></tr>
	<tr><td>15/05/2021</td><td>Tranferencia 154->212</td><td>$15000</td><td>Transferencia</td></tr>
	<tr><td>03/05/2021</td><td>Pago cuota 1/6 prestamo</td><td>$2000</td><td>Pago Prestamo</td></tr>
	<tr><td>12/04/2021</td><td>Deposito</td><td>$10000</td><td>Alta Prestamo</td></tr>
	<tr><td>26/02/2021</td><td>Deposito</td><td>$10000</td><td>Alta Cuenta</td></tr>
</table>
</div>
</div>
</center>
</body>
</html>
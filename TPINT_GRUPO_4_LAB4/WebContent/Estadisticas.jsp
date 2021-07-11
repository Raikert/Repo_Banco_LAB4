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

<h1>Estadisticas</h1>
<br>
<br>
<div style="display:flex; align-items: flex-end; width: 100%;">
<div style="width: 32%; text-align:center; float:center;">
<h2>Ingresos y Egresos</h2>
<br>
<br>
<b>Seleccione Fecha: </b>
<input type="text" name="TxtFecini">
<b> y </b>
<input type="text" name="TxtFecfin">
<br>
<br>
<input type="submit" name="Cargar1" value="Calcular">
<br>
<b>Ingresos: $</b><b>1.000.520</b>
<br>
<b>Egresos: $</b><b>900.350</b>
<br>
<b>Balance: $</b><b>100.170</b>
</div>
<div style="width: 35%; float:right; text-align:center;">
<h2>Cantidad de cuentas</h2>
<br>
<br>
<b>Seleccione fecha: </b>
<input type="text" name="TxtFecini2">
<b> y </b>
<input type="text" name="TxtFecfin2">
<br>
<br>
<b>Seleccione Saldo: $</b>
<input type="text" name="TxtSdmin">
<b> y $</b>
<input type="text" name="TxtSdmax">
<br>
<br>
<b>Seleccione Tipo Cuenta: </b>
<select>
<option>Cuenta Corriente</option>
<option>Caja de Ahorro</option>
</select>
<br>
<br>
<input type="submit" name="Cargar2" value="Calcular">
<br>
<br>
<b>Cantidad de Cuentas: </b><b>120</b>
<br>
<b>Saldo Promedio: $</b><b>31.000</b>
</div>
<div style="width: 33%; float:left; text-align:center;">
<h2>Cantidad de Prestamos</h2>
<br>
<br>
<b>Seleccione fecha: </b>
<input type="text" name="TxtFecini3">
<b> y </b>
<input type="text" name="TxtFecfin3">
<br>
<br>
<b>Seleccione Monto: $</b>
<input type="text" name="TxtMtmin">
<b> y $</b>
<input type="text" name="TxtMtmax">
<br>
<br>
<b>Seleccione Cant Cuotas</b>
<select>
<option>Todas</option>
<option>6</option>
<option>12</option>
<option>18</option>
<option>24</option>
<option>36</option>
</select>
<br>
<br>
<input type="submit" name="Cargar3" value="Calcular">
<br>
<br>
<b>Cantidad Prestamos: </b><b>25</b>
<br>
<b>Monto Promedio: $</b><b>200.000</b>
<br>
<b>Cantidad Pagos</b><b>18</b>
<br>
<b>Cantidad Pendientes</b><b>2</b>
<br>
<b>Cantidad Activos</b><b>1</b>
<br>
<b>Cantidad Rechazados</b><b>4</b>
</div>
</div>
</center>
</body>
</html>
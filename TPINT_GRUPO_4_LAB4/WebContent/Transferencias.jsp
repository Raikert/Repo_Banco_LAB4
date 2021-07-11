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
<h1>Transferencias</h1>
<br>
<form action="">
<b>Seleccione una cuenta: </b>
<select name="cuenta">
	<option>108</option>
	<option>154</option>
	<option>288</option>
</select>
<input type="submit" style="width: 100px" name="cargar" value="Cargar">
<br>
<br>
<b>Saldo: $</b>&nbsp;<b>1500</b>
<br>
<br>
<b>Ingrese una cuenta destino: </b>
<input type="text" name="TxtDestino">
<br>
<br>
<b>Ingrese monto a transferir: $</b>
<input type="text" name="TxtMonto">
<br>
<br>
<input type="submit" name="Enviar" value="Transferir">
</form>
</center>
</body>
</html>
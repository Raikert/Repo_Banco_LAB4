<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.util.ArrayList"%>
<%@page import="datos.ClienteDao"%>
<%@page import="negocio.ClienteNeg"%>
<%@page import="negocioImpl.ClienteNegImpl"%>
<%@page import="java.sql.ResultSet"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
<jsp:include page="Menu2.html"></jsp:include>
<center>
<h1>Historial De Prestamos Pedidos</h1>
<br>
<br>
<form action="">
<b>Seleccione cuenta: </b>
		<%  
		ClienteNeg cliNeg = new ClienteNegImpl();
		ResultSet rs = null;
		rs = cliNeg.obtenerCuentas(request.getSession().getAttribute("dnidni").toString());
		
		%>
	<select>
		<option> seleccione una cuenta  </option>
		<%
		while(rs.next()){
		%>
		<option value="<%=rs.getInt("Ncuenta_Cu")%>"><%=rs.getInt("Ncuenta_Cu")%> </option>
		<% } %>
	</select>
<input type="submit" name="generar" value="Consulta">
<br>
<br>
<table border="1" style="text-align: center;">
	<tr><td>Fecha</td><td>Estado</td><td>Importe Pedido</td><td>Importe a Pagar</td><td>Valor Cuota</td><td>Cuotas</td><td>Cuotas Pagas</td></tr>
	<tr><td>12/05/2021</td><td>Pendiente</td><td>$100.000</td><td>$120.000</td><td>$10.000</td><td>12</td><td>0</td></tr>
	<tr><td>10/01/2021</td><td>Aprobado</td><td>$80.000</td><td>$96.000</td><td>$16.000</td><td>6</td><td>4</td></tr>
	<tr><td>08/07/2020</td><td>Rechazado</td><td>$120.000</td><td>-</td><td>-</td><td>-</td><td>-</td></tr>
	<tr><td>01/02/2020</td><td>Pago</td><td>$50.000</td><td>$60.000</td><td>$10.000</td><td>6</td><td>6</td></tr>
	<tr><td>15/05/2019</td><td>Pago</td><td>$60.000</td><td>$72.000</td><td>$6.000</td><td>6</td><td>6</td></tr>
	<tr><td>14/10/2018</td><td>Rechazado</td><td>$200.000</td><td>-</td><td>-</td><td>-</td><td>-</td></tr>	
</table>
</form>
</center>
</body>
</html>
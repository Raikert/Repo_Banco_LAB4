<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.util.ArrayList"%>
<%@page import="datos.ClienteDao"%>
<%@page import="negocio.ClienteNeg"%>
<%@page import="negocioImpl.ClienteNegImpl"%>
<%@page import="datosImpl.PrestamoDaoImpl"%>
<%@page import="java.sql.ResultSet"%>
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
<form action="servletcuotas" method="GET" style="text-align:center;">
<b>Seleccione Cuenta: </b>	
		<%  
		ClienteNeg cliNeg = new ClienteNegImpl();
		ResultSet rs = null;
		rs = cliNeg.obtenerCuentas(request.getSession().getAttribute("dnidni").toString());
		
		%>
	<select name="cuenta">		
		<%
		while(rs.next()){
		%>
		<option><%=rs.getInt("Ncuenta_Cu")%> </option>
		<% } %>
	</select>
<br>
<br>
<b>Seleccione cuotas a pagar: </b>
<select name="cuotas">
	<option>1</option>
	<option>2</option>
	<option>3</option>
</select>
<br>
<br>
<b>Seleccione Prestamo a pagar:</b>
<%PrestamoDaoImpl prest = new PrestamoDaoImpl();
ResultSet rs2 = null;
rs2= prest.obtenerIDPrestamosPagar(request.getSession().getAttribute("dnidni").toString());
%>
<select name="prestamo">
<%while (rs2.next()){ %>
	<option><%=rs2.getInt("ID_Pr") %></option>
<%} %>
</select>
<br>
<br>
<input type="submit" name="pagar" value="pagar" style="width:150px">
<%
int filas = 1;
if(request.getAttribute("cantfilas")!=null)
 filas = Integer.parseInt(request.getAttribute("cantfilas").toString());

%>

<%if (filas==0){
	%>
	No se puedo realizar el pago
	<%} %>
</form>
</div>
<div style="height: 100%;  width: 60%; float:right; text-align:right;">
<table border="1" style="text-align: center;">
<%PrestamoDaoImpl prest2 = new PrestamoDaoImpl();
ResultSet rs3 =null;
rs3=prest2.obtenerPrestamosPagar(request.getSession().getAttribute("dnidni").toString());
%>
	<tr><td>ID</td><td>Importe</td><td>Cuotas totales</td><td>Valor cuota</td><td>Cuotas Pagadas</td></tr>
	<%while(rs3.next()){ %>
	<tr><td><%=rs3.getInt("ID_Pr")%></td><td><%=rs3.getFloat("importe_Int_Pr") %></td><td><%=rs3.getString("cuotas_Pr") %></td><td><%=rs3.getFloat("montoxMes_Pr") %></td><td><%=rs3.getString("Cuota_pagada_Pr") %></td></tr>
	<%} %>
</table>
</div>
</div>
</center>
</body>
</html>
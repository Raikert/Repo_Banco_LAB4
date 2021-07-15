<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="presentacion.controller.ServletPrestamos" %>
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
	<h1>Solicitar Prestamos</h1>
	<br>
	<form method="post" action="ServletPrestamos">
		<b>Seleccione cuenta: </b>
		<%  
		ClienteNeg cliNeg = new ClienteNegImpl();
		ResultSet rs = null;
		rs = cliNeg.obtenerCuentas(request.getSession().getAttribute("dnidni").toString());
		
		%>
		<select name="cuenta">
			<option> seleccione una cuenta  </option>
			<%
			while(rs.next()){
			%>
			<option value="<%=rs.getInt("Ncuenta_Cu")%>"><%=rs.getInt("Ncuenta_Cu")%> </option>
			<% } %>
		</select>
		<br><br>
		<b>Ingrese Monto a pedir: $</b>
		<input type="text" name="txtMonto">
		<br><br>
		<b>Seleccione cantidad cuotas: </b>
		<select name="cuotas">
			<option>6</option>
			<option>12</option>
			<option>18</option>
			<option>24</option>
			<option>36</option>
		</select>&nbsp;
		<input type="submit" name="btnCalcular" value="Calcular interes">
		<br><br>
		<%!Double importe_pagar = 0.00; %>
			<%
				if(request.getAttribute("importe_pagar")!=null){
					importe_pagar = (Double) request.getAttribute("importe_pagar");
				}
			 %>
		<b>Monto total a pagar: $</b>&nbsp;<b><%= importe_pagar %></b>
		<br><br>
		<%!Double montoxmes = 0.00; %>
			<%
				if(request.getAttribute("montoxmes")!=null){
					montoxmes = (Double) request.getAttribute("montoxmes");
				}
			 %>
		<b>Monto por cuota: $</b>&nbsp;<b><%= montoxmes %></b>
		<br><br>
		<input type="submit" name="btnEnviar" value="Enviar Solicitud">
		<br>
		<%!String mensaje=""; %>
			<%
				if(request.getAttribute("mensaje")!=null){
					mensaje = (String) request.getAttribute("mensaje");
				}
				 %>
			<b><%=mensaje %></b>
	</form>
</center>
</body>
</html>
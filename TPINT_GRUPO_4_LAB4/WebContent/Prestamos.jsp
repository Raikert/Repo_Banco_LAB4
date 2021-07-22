<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="presentacion.controller.ServletPrestamos" %>
<%@page import="java.util.ArrayList"%>
<%@page import="datos.ClienteDao"%>
<%@page import="negocio.ClienteNeg"%>
<%@page import="negocioImpl.ClienteNegImpl"%>
<%@page import="entidad.Prestamo" %>
<%@page import="java.sql.ResultSet"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	</head>
	<body>
	<jsp:include page="Menu2.html"></jsp:include>
	<center>
	
	<%! int [] cuotas = {6, 12, 18, 24, 36}; %>
	<h1>Solicitar Prestamos</h1>
	<br>
	<form method="post" action="ServletPrestamos">
		<b>Seleccione cuenta: </b>
		<%  
		ClienteNeg cliNeg = new ClienteNegImpl();
		ResultSet rs = null;
		rs = cliNeg.obtenerCuentas(request.getSession().getAttribute("dnidni").toString());
		
		%>
		
		<%
		Prestamo p;
		if(request.getAttribute("prestamo")!=null){
			p = (Prestamo) request.getAttribute("prestamo");
		} else {
			p = new Prestamo(0,0,0);
				p.setImportePedido(0);
				p.setImportePagar(0);
				p.setCuotas(0);
				p.setMontoxMes(0);
		}
	
	 %>
		<select name="cuenta">
			<option value = ""></option>
			<%
			while(rs.next()){
				if(rs.getInt("Ncuenta_Cu")==p.getCuenta()){
			%>
			<option value="<%=rs.getInt("Ncuenta_Cu")%>" selected><%=rs.getInt("Ncuenta_Cu")%> </option>
			<% } else {
					%>
			<option value="<%=rs.getInt("Ncuenta_Cu")%>"><%=rs.getInt("Ncuenta_Cu")%> </option>
				<% 
			}
			
			} %>
		</select>
		<br><br>
		<b>Ingrese Monto a pedir: $</b>
		<input type="text" name="txtMonto" pattern="[0-9]+" title="Igrese un dato numerico" value=<%=p.getImportePedido() %> request>
		<br><br>
		<b>Seleccione cantidad cuotas: </b>
		<select name="cuotas">
		<% 
			for(int cuota : cuotas){
				if(cuota == p.getCuotas()) {
				%>
				<option selected> <%=cuota%></option>
				<% 
				} else {
				%>
				<option> <%=cuota%></option>
				<%
				}
			}
			%>
		</select>&nbsp;
		<input type="submit" name="btnCalcular" value="Calcular interes">
		<br><br>
		
		<b>Monto total a pagar: $</b>&nbsp;<b><%= p.getImportePagar() %></b>
		<br><br>
		
		<b>Monto por cuota: $</b>&nbsp;<b><%= p.getMontoxMes() %></b>
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
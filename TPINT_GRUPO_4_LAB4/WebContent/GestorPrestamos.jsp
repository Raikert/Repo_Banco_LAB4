

<%@page import="java.util.ArrayList"%>
<%@page import="presentacion.controller.ServletPrestamos" %>
<%@page import="entidad.Prestamo" %>
<%@page import="negocio.PrestamoNeg" %>
<%@page import="negocioImpl.PrestamoNegImpl" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<style>
	table
	{
		margin:auto;
		display:block;
		max-width:600px;
		max-height:200px;
		overflow-y: auto;
		border-style: inset;
        border-width: 5px;
		border-color:#229941;
		border-collapse:collapse;
		background-color:#a3998c;
		
	}
	th
	{
		background-color:#f0c711;
		border:1px solid black;
	}
	td
	{
		background-color:white;
		border:1px solid black;
		padding:5px 10px;
	}
</style>
	</head>
	<body>
	<jsp:include page="Menu.html"></jsp:include>
	
		<center>
		<form method="get" action="ServletPrestamos">
			<%! String advertencia=""; %>
			<%
				if(request.getAttribute("label") != null){
					advertencia = (String) request.getAttribute("label");
				} else { advertencia=""; }
			 %>
			<h1>Gestor de Prestamos</h1> <br> <br>
			<b>Escriba el ID del prestamo: </b> <input type="text" name="txtPrestamo" pattern="[0-9]+" title="Ingrese un ID numerico" required><br><br>
			<b>Seleccione un estado: </b>
			<select name="estado" id="estado">
				<option>Aprobado</option>
				<option>Rechazado</option>
			</select><br><br>
			<input type="submit" name="btnModificar" value="Cargar estado"> <br>
			<label><%=advertencia%></label>
		</form>
		<%
			ArrayList<Prestamo> listap = null;
			PrestamoNeg pr = new PrestamoNegImpl();
			listap = pr.obtenerPendientes();
			 %>
		<table border="1" style="text-align: center;">
			<tr><td>ID</td><td>Fecha</td><td>Cliente</td><td>Importe Pedido</td><td>Importe a Pagar</td><td>Valor Cuota</td><td>Cuotas</td></tr>
			<% if (listap!=null){
				for(Prestamo p : listap) { %>
			<tr> <td><%= p.getId() %></td> <td> <%= p.getFecha() %></td> <td> <%= p.getDni() %></td> <td><%= "$" + p.getImportePedido() %></td> <td><%= "$" + p.getImportePagar() %></td> <td><%= "$" + p.getMontoxMes() %></td> <td><%= p.getCuotas() %></td> </tr>
			<% } } %>
		</table>
		</center>
	</body>
</html>
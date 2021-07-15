<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@page import="java.util.ArrayList"%>
<%@page import="datos.ClienteDao"%>
<%@page import="negocio.ClienteNeg"%>
<%@page import="negocioImpl.ClienteNegImpl"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="entidad.Prestamo"%>
<%@page import="java.sql.SQLException"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style>
	table
	{
		margin:auto;
		display:block;
		max-width:800px;
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
<jsp:include page="Menu2.html"></jsp:include>

<%  
		ClienteNeg cliNeg = new ClienteNegImpl();
		
		ResultSet rs;
		rs = cliNeg.obtenerCuentas(request.getSession().getAttribute("dnidni").toString());
		
		ArrayList<Prestamo> prestamos = new ArrayList<Prestamo>();
		
		if(request.getAttribute("historial_prestamos") != null)
			prestamos = (ArrayList<Prestamo>) request.getAttribute("historial_prestamos");
%>

<div style="height: 100%; width: 100%; text-align:center">
	<h1>Historial De Prestamos Pedidos</h1>
	<br>
	<br>
	<form method="post" action="ServletPrestamos">
	<input type="hidden" name="DNI_Cliente" value=<%=request.getSession().getAttribute("dnidni")%>>
		<b>Seleccione cuenta: </b>
		<select name="cuenta">
			<option hidden> seleccione una cuenta </option>
		<% 
			while(rs.next())
			{
		%>
			<option><%=rs.getInt("Ncuenta_Cu")%></option>
		<% 
			}
		%>
		</select>
		<input type="submit" name="generar" value="Consulta">
		<br>
		<br>
		
		<table>
			<tr>
				<td>
				Fecha de Pedido
				</td>
				<td>
				Plazo de Pago
				</td>
				<td>
				Estado
				</td>
				<td>
				Importe Pedido
				</td>
				<td>
				Importe a Pagar
				</td>
				<td>
				Valor Cuota
				</td>
				<td>
				Cuotas
				</td>
				<td>
				Cuotas Pagas
				</td>
			</tr>
<%
	if(!prestamos.isEmpty())
	{
		for(Prestamo prestamo : prestamos)
		{
%>
			<tr>
				<td>
				<%=prestamo.getFecha()%>
				</td>
				<td>
				<%=prestamo.getPlazo()%>
				</td>
				<td>
				<%=prestamo.getEstado()%>
				</td>
				<td>
				<%=prestamo.getImportePedido()%>
				</td>
				<td>
				<%=prestamo.getImportePagar()%>
				</td>
				<td>
				<%=prestamo.getMontoxMes()%>
				</td>
				<td>
				<%=prestamo.getCuotas()%>
				</td>
				<td>
				<%=prestamo.getCuotasPagadas()%>
				</td>
			</tr>
<% 
		}
	}
%>
		</table>
	</form>
</div>
</body>
</html>
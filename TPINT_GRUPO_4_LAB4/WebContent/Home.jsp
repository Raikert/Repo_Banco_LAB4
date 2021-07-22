<%@page import="entidad.Movimientos"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.sql.ResultSet"%>
<%@page import="datosImpl.ClienteDaoImpl"%>
<%@page import="entidad.Cliente"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="entidad.Cuentas"%>
<%@page import="java.util.ArrayList"%>
<%@page import="datos.ClienteDao"%>
<%@page import="negocio.ClienteNeg"%>
<%@page import="negocioImpl.ClienteNegImpl"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="https://kit.fontawesome.com/7181f6836f.js" crossorigin="anonymous"></script>
</head>
<body>
<jsp:include page="Menu2.html"></jsp:include>
<center>
<h1><center>Bienvenido <%= request.getSession().getAttribute("useruser") %></center></h1>
<br>
<br>
<form action="ServletClientes" method="post" style="text-align:center;">

	<b>Seleccione una cuenta</b>
	
		<%  
		ClienteNeg cliNeg = new ClienteNegImpl();
		ResultSet rs = null;
		rs = cliNeg.obtenerCuentas(request.getSession().getAttribute("dnidni").toString());
		
		%>
	<select name="ddlCuentas" id="ddlCuentas">
		<option>seleccione una cuenta</option>
		<%
		while(rs.next()){
		%>
		<option value="<%=rs.getInt("Ncuenta_Cu")%>"><%=rs.getInt("Ncuenta_Cu")%> </option>
		<% } %>
	</select>
	<br>
	<input type="submit" name="BTgenerar" value="Mostrar">
</form>
<br>
<br>
<% 
if(request.getAttribute("saldosaldo")==null){
	
	request.setAttribute("saldosaldo", "");
}


%>
<div class="wrapper" style="width:25%; text-align:center; align:center;">
	<div style="display: flex" align-items: flex-end;>
	<div style="margin-left: 10px; font-size:18px; font-weight:bolder; width: 50%; text-align:right">Saldo: $</div>
	<div style="margin-left: 5px; font-size:18px; font-weight:bolder; width: 20%; text-align:left"><%=request.getAttribute("saldosaldo") %></div>
	</div>
	<br>	
</div>
	
<h2>Ultimos Movimientos</h2>
<table border="1">

<%
ArrayList<Movimientos> listaMovimientos = null;
if(request.getAttribute("movimientosmovimientos")!=null)
	
	listaMovimientos = (ArrayList<Movimientos>) request.getAttribute("movimientosmovimientos");

		
%>

	<tr> <th>Fecha</th><th>Detalle</th><th>Importe</th><th>Tipo</th> </tr>
	<%
	if(listaMovimientos != null)
	for (Movimientos mov : listaMovimientos){ %>
	<tr> <td><%= mov.getFecha() %></td><td><%=mov.getDetalle() %></td><td><%=mov.getImporte() %></td><td><%=mov.getTipo_Mov() %></td> </tr>
	<%} %>
</table>
</center>
</body>
</html>
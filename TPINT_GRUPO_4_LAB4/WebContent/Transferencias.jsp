<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.util.ArrayList"%>
<%@page import="datos.ClienteDao"%>
<%@page import="negocio.ClienteNeg"%>
<%@page import="negocioImpl.ClienteNegImpl"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
<jsp:include page="Menu2.html"></jsp:include>
<center>
<h1>Transferencias</h1>
<br>
<form action="ServletClientes" method="post">
<b>Seleccione una cuenta: </b>
		<%  
		ClienteNeg cliNeg = new ClienteNegImpl();
		ResultSet rs = null;
		rs = cliNeg.obtenerCuentas(request.getSession().getAttribute("dnidni").toString());
		
		%>
	<select name="ddlCuentas" id="ddlCuentas">
		<option> seleccione una cuenta  </option>
		<%
		while(rs.next()){
		%>
		<option value="<%=rs.getInt("Ncuenta_Cu")%>"><%=rs.getInt("Ncuenta_Cu")%> </option>
		<% } %>
	</select>
<input type="submit" style="width: 100px" name="BTcargar" value="Cargar">
<br>
<br>

<% 
if(request.getSession().getAttribute("saldosaldo")==null){
	
	request.getSession().setAttribute("saldosaldo", "");
}


%>
<b>Saldo: $</b>&nbsp;<b><%=request.getSession().getAttribute("saldosaldo") %></b>
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
<input type="submit" name="BTenviar" value="Transferir">
</form>
</center>
</body>
</html>
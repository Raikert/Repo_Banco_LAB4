<%@page import="entidad.Cuentas"%>
<%@page import="datos.CuentasDao"%>
<%@page import="entidad.CuentasAdmDao"%>
<%@page import="java.util.ArrayList"%>

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
<h1>Administrador de Cuentas</h1>
<br>
<br>
<div style="display:flex; align-items: flex-end; width: 100%;">
<div style="height: 100%; width: 50%; text-align:center; float:left;">
<form action="ServletCuentas" method="get">
<h2>Crear Cuenta</h2>
<br>
<br>
<b>Ingrese Dni del cliente:&nbsp;&nbsp; </b>
<input type="text" name="TxtDni" style="width:100px" required>
<br>
<br>
<b>Ingrese tipo cuenta: </b>
<select name="DdlTipo" id="ddltipo">
<option value="Caja de Ahorro">Caja de Ahorro</option>
<option value="Cuenta Corriente">Cuenta Corriente</option>
</select>
<br>
<br>
<b>Ingrese CBU: </b>
<input type="text" name="TxtCbu" required>
<br>
<br>
<input type="submit" name="crear" value="Crear">
</form>
<%
int filas = 0;
if(request.getAttribute("cantfilas")!=null)
 filas = Integer.parseInt(request.getAttribute("cantfilas").toString());

%>

<%if (filas==1){
	%>
	Cuenta creada exitosamente
	<%} %>

</div>
<div style="height: 100%;  width: 50%; float:right; text-align:center;">
<form action="ServletCuentas" method="get">
<h2>Gestor de Cuentas</h2>
<br>
<br>
<b>Seleccione cuenta a modificar: </b>
<%
ArrayList<Cuentas> listacuentas;

CuentasAdmDao Ucu= new CuentasAdmDao();
listacuentas= Ucu.obtenerCuentas();
%>
<select name="ddlNcuenta">
<% for( Cuentas cuenta : listacuentas)
	{%>
<option><%=cuenta.getNcuenta() %></option>
<% }%>
</select>
<br>
<br>
<b>Ingrese tipo de cuenta: </b>
<select name="dlltipoupdate">
<option value="Caja de Ahorro">Caja de Ahorro</option>
<option value="Cuenta Corriente">Cuenta Corriente</option>
</select>
<br>
<br>
<b>Ingrese CBU: </b>
<input type="text" name="TxtCbu2" required>
<br>
<br>
<input type="submit" name="Modificar" value="Modificar">
</form>
</div>
</div>
</center>
</body>
</html>
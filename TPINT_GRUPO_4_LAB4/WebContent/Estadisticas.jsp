<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    %>
    <%@page import="java.util.ArrayList"%>
    <%@page import="datos.MovimientosDao"%>
    <%@page import="datosImpl.MovimientosDaoImpl"%>
    <%@page import="datos.CuentasDao"%>
    <%@page import="datosImpl.CuentasDaoImpl"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
<jsp:include page="Menu.html"></jsp:include>
<center>
<h1>Estadisticas</h1>
<br>
<br>
<div style="display:flex; align-items: flex-end; width: 100%;">
<div style="width: 32%; text-align:center; float:center;">
<h2>Ingresos y Egresos</h2>
<form action="ServletMovimientos" method="post">
<br>
<br>
<b>Seleccione Fecha: </b>
<input type="text" name="TxtFecini">
<b> y </b>
<input type="text" name="TxtFecfin">
<br>
<br>
<input type="submit" name="Cargar1" value="Calcular">
<br>
<% 	ArrayList<Double> movimientos = new ArrayList<Double>();
	if(request.getAttribute("mov")!=null)
	{
		movimientos=(ArrayList<Double>)request.getAttribute("mov");
	}else
	{
		movimientos.add(0.0);
		movimientos.add(0.0);
	 }%>
<b>Ingresos: $</b><b><%=movimientos.get(0)%></label>---</b>
<br>
<b>Egresos: $</b><b><%=movimientos.get(1)%></b>
<br>
<b>Balance: $</b><b><%=movimientos.get(0)-movimientos.get(1)%></b>
</div>
</form>
<div style="width: 35%; float:right; text-align:center;">
<form action="ServletCuentas" method="post">
<h2>Cantidad de cuentas</h2>
<br>
<br>
<b>Seleccione fecha: </b>
<input type="text" name="TxtFecini2">
<b> y </b>
<input type="text" name="TxtFecfin2">
<br>
<br>
<b>Seleccione Saldo: $</b>
<input type="text" name="TxtSdmin">
<b> y $</b>
<input type="text" name="TxtSdmax">
<br>
<br>
<b>Seleccione Tipo Cuenta: </b>
<select id="Selectc" name="Selectc">
<option value="t">Todas</option>
<option value="Caja de Ahorro">Cuenta Corriente</option>
<option value="Cuenta Corriente">Caja de Ahorro</option>
</select>
<br>
<br>
<input type="submit" name="Cargar2" value="Calcular">
<br>
<% 	ArrayList<Double> cuentas = new ArrayList<Double>();
	if(request.getAttribute("cuentas")!=null)
	{
		cuentas=(ArrayList<Double>)request.getAttribute("cuentas");
	}else
	{
		cuentas.add(0.0);
		cuentas.add(0.0);
	 }%>
<br>
<b>Cantidad de Cuentas: </b><b><%=cuentas.get(0)%></b>
<br>
<b>Saldo Promedio: $</b><b><%=cuentas.get(1)%></b>
</form>
</div>
<div style="width: 33%; float:left; text-align:center;">
<form action="ServletPrestamos" method="post">
<h2>Cantidad de Prestamos</h2>
<br>
<br>
<b>Seleccione fecha: </b>
<input type="text" name="TxtFecini3">
<b> y </b>
<input type="text" name="TxtFecfin3">
<br>
<br>
<b>Seleccione Monto: $</b>
<input type="text" name="TxtMtmin">
<b> y $</b>
<input type="text" name="TxtMtmax">
<br>
<br>
<b>Seleccione Cant Cuotas</b>
<select name="cuotas">
<option value="0">Todas</option>
<option value="6">6</option>
<option value="12">12</option>
<option value="18">18</option>
<option value="24">24</option>
<option value="36">36</option>
</select>
<br>
<br>
<input type="submit" name="Cargar3" value="Calcular">
<br>
<% 	ArrayList<Integer> prestamos = new ArrayList<Integer>();
	if(request.getAttribute("prestamos")!=null)
	{
		prestamos=(ArrayList<Integer>)request.getAttribute("prestamos");
	}else
	{
		prestamos.add(0);
		prestamos.add(0);
		prestamos.add(0);
		prestamos.add(0);
		prestamos.add(0);
		prestamos.add(0);
	 }%>
<br>
<b>Cantidad Prestamos: </b><b><%=prestamos.get(0)%></b>
<br>
<b>Monto Promedio: $</b><b><%=prestamos.get(5)%></b>
<br>
<b>Cantidad Pagos</b><b><%=prestamos.get(4)%></b>
<br>
<b>Cantidad Pendientes</b><b><%=prestamos.get(2)%></b>
<br>
<b>Cantidad Activos</b><b><%=prestamos.get(1)%></b>
<br>
<b>Cantidad Rechazados</b><b><%=prestamos.get(3)%></b>
</form>

</div>
</div>
</center>
</body>
</html>
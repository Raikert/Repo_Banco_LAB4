<%@page import="entidad.Cuentas"%>
<%@page import="datos.CuentasDao"%>
<%@page import="datosImpl.CuentasDaoImpl"%>

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
<form action="AdminCuenta.jsp" method="get">
<h2>Crear Cuenta</h2>
<br>
<br>
<b>Ingrese Dni del cliente:&nbsp;&nbsp; </b>
<input type="text" name="TxtDni" style="width:100px">
<br>
<br>
<b>Ingrese tipo cuenta: </b>
<select name="DdlTipo" id="ddltipo">
<option value="caja de Ahorro">Caja de Ahorro</option>
<option value="Cuenta Corriente">Cuenta Corriente</option>
</select>
<br>
<br>
<b>Ingrese CBU: </b>
<input type="text" name="TxtCbu">
<br>
<br>
<input type="submit" name="crear" value="Crear">
</form>
 <% /*
	int filas;
	if(request.getParameter("crear")!=null){
		Cuentas c = new Cuentas();
		c.setDNI(request.getParameter("TxtDni"));
		c.setTipo(request.getParameter("DdlTipo"));
		c.setCbu(request.getParameter("TxtCbu"));
		CuentasDao Ucuenta= new CuentasDao();
		filas = Ucuenta.agregarCuenta(c);
		
	} */
%> 


</div>
<div style="height: 100%;  width: 50%; float:right; text-align:center;">
<h2>Gestor de Cuentas</h2>
<br>
<br>
<b>Seleccione cuenta a modificar: </b>
<select>
<option>154</option>
<option>188</option>
<option>211</option>
</select>
<br>
<br>
<b>Ingrese tipo de cuenta: </b>
<select>
<option>Caja de Ahorro</option>
<option>Cuenta Corriente</option>
</select>
<br>
<br>
<b>Ingrese CBU: </b>
<input type="text" name="TxtCbu2">
<br>
<br>
<b>Seleccione estado: </b>
<select>
<option>Activa</option>
<option>Inactiva</option>
</select>
<br>
<br>
<input type="submit" name="Modificar" value="Modificar">
</div>
</div>
</center>
</body>
</html>
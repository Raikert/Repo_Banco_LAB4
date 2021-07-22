<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@page import="java.util.ArrayList"%>
    <%@page import="datos.MovimientosDao"%>
    <%@page import="datosImpl.MovimientosDaoImpl"%>
    <%@page import="entidad.Movimientos"%>
     
     <% 
   	MovimientosDao o = new MovimientosDaoImpl();
	ArrayList<String> lista = new ArrayList<String>();
	ArrayList<Integer> lista2 = new ArrayList<Integer>();
	lista.addAll(o.HistorialGeneral());
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
<jsp:include page="Menu.html"></jsp:include>
<center>
<h1>Historial de Movimientos</h1>
<div style="display:flex; align-items: flex-end; width: 100%;">
<div style="height: 100%; width: 40%; text-align:center; float:left;">
<form action="ServletMovimientos" method="post" style="text-align:center;">
	<b>Seleciones un cliente: </b>
	<select name="cliente">
		<% for(int i=0;i<lista.size();i++){
			 %>
			<option value="<%= lista.get(i) %>"><%=lista.get(i)%></option>
		 <%}%>
	</select>
	<input type="submit" name="cargar" value="Cargar">
</form>
<form action="ServletMovimientos" method="post" style="text-align:center;">
	<br>
	<br>
	<% 
		String cl;
		if(request.getAttribute("cl")!=null){
			cl=(String)request.getAttribute("cl");
			lista2.addAll(o.CuentasHG(cl));
		}
	%>
	<b>Selecione una cuenta: </b>
	<select name="cuenta">
		<% for(int i=0;i<lista2.size();i++){
			 %>
			<option value="<%= lista2.get(i) %>"><%=lista2.get(i)%></option>
		 <%}%>
	</select>
	<br>
	<br>
	<b>Seleccione tipo de transaccion: </b>
	<select name="tipo">
		<option value="transferencia">Transferencias</option>
		<option value="pago de prestamo">Pago Cuotas</option>
		<option value="alta prestamo">Prestamos</option>
		<option value="alta cuenta">Creacion Cuenta</option>
	</select>
	<br>
	
	<b>Monto minimo: </b>
	<input type="text" name="TxtMin">
	<br>
	<b>Monto maximo: </b>
	<input type="text" name="TxtMax">
	<br>
	<b>Fecha: </b>
	<input type="text" name="TxtFecIni">
	<b> &nbsp; Y &nbsp;</b>
	<input type="text" name="TxtFecFin">
	<br>
	<b>Cantidad de resultados: </b>
	<b>10 </b>
	<input type="radio" name="RdCant" value="10" checked>
	<b>25 </b>
	<input type="radio" name="RdCant" value="25">
	<b>50 </b>
	<input type="radio" name="RdCant" value="50"> &nbsp;&nbsp;&nbsp; 
	<input type="submit" name="Cargar3" value="Generar"> 
</form>
</div>
<% 
	ArrayList<Movimientos> listam = new ArrayList<Movimientos>();
	if(request.getAttribute("lista")!=null)
	{
		listam=(ArrayList<Movimientos>) request.getAttribute("lista");
	}
	
%>
<div style="height: 100%;  width: 60%; float:right; text-align:right;">
<table border="1" style="text-align: center;">
	<tr><td>Fecha</td><td>Detalle</td><td>Importe</td><td>Tipo</td></tr>
	<% 
		for(Movimientos mov: listam)
		{
	%>
	<tr> <td><%=mov.getFecha() %></td> <td><%=mov.getDetalle() %></td> <td><%=mov.getImporte() %></td> <td><%=mov.getTipo_Mov() %></td> </tr> 
	<% 
		}
	%>
</table>
</div>
</div>
</center>
</body>
</html>
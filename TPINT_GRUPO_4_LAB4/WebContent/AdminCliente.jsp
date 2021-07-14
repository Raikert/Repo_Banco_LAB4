<%@ page import="entidad.Cliente"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<style>
	table
	{
		border-spacing: 0 25px;
		margin:auto; 
		text-align:center;
	}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
<jsp:include page="Menu.html"></jsp:include>

<% 
	String mensaje = "";
	
	if(request.getAttribute("Validaciones") != null)
	{
		boolean [] error_count = (boolean []) request.getAttribute("Validaciones");
		
		if(error_count[0])
			mensaje = "La operacion se ejecuto exitosamente";
		else
			mensaje = "\\nSe encontraron errores:\\n";
		
		if(error_count[1])
		{
			mensaje+= "-No puede usar ese numero de DNI, ya se encuentra registrado\\n";
		}
		if(error_count[2])
		{
			mensaje+= "-No puede usar ese numero de CUIL, ya se encuentra registrado\\n";
		}
		if(error_count[3])
		{
			mensaje+= "-No puede usar ese nombre de Usuario, ya se encuentra registrado\\n";
		}
		if(error_count[4])
		{
			mensaje+= "-Las contraseñas no coinciden, por favor ingresar nuevamente";
		}
%>
		<script>
			alert("<%=mensaje%>");
		</script>
<%
	}
	
	Cliente cliente1 = new Cliente(), cliente2 = new Cliente();
			
	if(request.getAttribute("Cliente1") != null)
		cliente1 = (Cliente) request.getAttribute("Cliente1");
	else
	 cliente1.inicializar();
	 
	if(request.getAttribute("Cliente2") != null)
		cliente2 = (Cliente) request.getAttribute("Cliente2");
	else
	 cliente2.inicializar();
				
	String [] Generos = {"Femenino", "Masculino", "Otro"};
	String [] Paises = {"Argentina", "Brasil", "Chile", "Bolivia", "Peru", "Colombia", "Paraguay", "Uruguay"};
	String [] Provincias = {"Ciudad de Buenos Aires", "Buenos Aires", "Cordoba", "Santa Fe", "Corrientes", "Mendoza"};
	String [] Estados = {"Activo", "Inactivo"};
%>

<h1 style="text-align:center"> Administrador de Clientes</h1>
<br>
<br>
	<div style="height: 100%; width: 50%; float:left; text-align:center">
		<form method="post" action="ServletClientes">
			<h2>Dar alta Cliente</h2>
			<table>
				<tr>
					<td>
					<b>Ingrese Dni: </b>
					</td>
					<td>
					<input type="text" name="TxtDni" pattern="[0-9]{8}" title="El DNI solo debe tener numeros, y debe contener 8 digitos" value="<%=cliente1.getDni() %>" required>
					</td>
				</tr>
				<tr>
					<td>
					<b>Ingrese CUIL: </b>
					</td>
					<td>
					<input type="text" name="TxtCuil" pattern="[0-9]{11}" title="El CUIL solo debe tener numeros, y debe contener 11 digitos" value="<%=cliente1.getCuil() %>" required>
					</td>
				</tr>
				<tr>
					<td>
					<b>Ingrese Nombre:</b>
					</td>
					<td>
					<input type="text" name="TxtNombre" pattern=".{0,50}" title="Maximo de 50 caracteres alcanzado" value="<%=cliente1.getNombre() %>" required>
					</td>
				</tr>
				<tr>
					<td>
					<b>Ingrese Apellido: </b>
					</td>
					<td>
					<input type="text" name="TxtApellido" pattern=".{0,50}" title="Maximo de 50 caracteres alcanzado" value="<%=cliente1.getApellido() %>" required>
					</td>
				</tr>
				<tr>
					<td>
					<b>Ingrese Usuario: </b>
					</td>
					<td>
					<input type="text" name="TxtUsu" pattern=".{0,20}" title="Maximo de 20 caracteres alcanzado" value="<%=cliente1.getUsuario() %>" required>
					</td>
				</tr>
				<tr>
					<td>
					<b>Ingrese clave: </b>
					</td>
					<td>
					<input type="password" name="TxtPass" pattern=".{0,20}" title="Maximo de 20 caracteres alcanzado" value="<%=cliente1.getContraseña() %>" required>
					</td>
				</tr>
				<tr>
					<td>
					<b>Ingrese clave <br>nuevamente: </b>
					</td>
					<td>
					<input type="password" name="TxtPass2" pattern=".{0,20}" title="Maximo de 20 caracteres alcanzado" value="<%=cliente1.getContraseña() %>" required>
					</td>
				</tr>
				<tr>
					<td>
					<b>Ingrese sexo: </b>
					</td>
					<td>
					<select name="Sexo">
				<% 
					for(String genero : Generos) 
					{
						if(genero.equals(cliente1.getSexo()))
						{
				%>
						<option selected><%=genero%></option>
				<%
						}
						else
						{
				%>
						<option><%=genero%></option>
				<%
						}
					}
				%>
					</select>
					</td>
				</tr>
				<tr>
					<td>
					<b>Ingrese nacionalidad: </b>
					</td>
					<td>
					<select name="Nacionalidad">
				<% 
					for(String pais : Paises) 
					{
						if(pais.equals(cliente1.getNacionalidad()))
						{
				%>
						<option selected><%=pais%></option>
				<%
						}
						else
						{
				%>
						<option><%=pais%></option>
				<%
						}
					}
				%>
					</select>
					</td>
				</tr>
				<tr>
					<td>
					<b>Fecha de Nacimiento</b>
					</td>
					<td>
					<input type="date" name="TxtFecnac" value="<%=cliente1.getFec_nacimiento() %>" required>
					</td>
				</tr>
				<tr>
					<td>
					<b>Ingrese Direccion: </b>
					</td>
					<td>
					<input type="text" name="TxtDom" pattern=".{0,50}" title="Maximo de 50 caracteres alcanzado" value="<%=cliente1.getDireccion() %>">
					</td>
				</tr>
				<tr>
					<td>
					<b>Ingrese Localidad: </b>
					</td>
					<td>
					<input type="text" name="TxtLoc" pattern=".{0,50}" title="Maximo de 50 caracteres alcanzado" value="<%=cliente1.getLocalidad() %>">
					</td>
				</tr>
				<tr>
					<td>
					<b>Ingrese Provincia: </b>
					</td>
					<td>
					<select name="Provincia">
				<% 
					for(String provincia : Provincias) 
					{
						if(provincia.equals(cliente1.getProvincia()))
						{
				%>
						<option selected><%=provincia%></option>
				<%
						}
						else
						{
				%>
						<option><%=provincia%></option>
				<%
						}
					}
				%>
					</select>
					</td>
				</tr>
				<tr>
					<td>
					<b>Ingrese Email: </b>
					</td>
					<td>
					<input type="email" name="TxtEmail" pattern=".{0,50}" title="Maximo de 50 caracteres alcanzado" value="<%=cliente1.getCorreo_electronico() %>">
					</td>
				</tr>
				<tr>
					<td>
					<b>Ingrese Telefono: </b>
					</td>
					<td>
					<input type="text" name="TxtTel" pattern="[0-9]{10}" title="El Telefono solo debe tener numeros, y debe contener 10 digitos" value="<%=cliente1.getTelefono() %>">
					</td>
				</tr>
			</table>
			<input type="submit" name="crear" value="Crear" style="width:75px">
			<br>
			<br>
		</form>
	</div>

	<div style="height: 100%;  width: 50%; float:right; text-align:center;">
		<form method="get" action="ServletClientes">
			<h2>Modificar Cliente</h2>
            <table>
            	<tr>
					<td>
					<b>Ingrese Dni: </b>
					</td>
					<td>
					<input type="text" name="TxtDni" pattern="[0-9]{8}" title="El DNI solo debe tener numeros, y debe contener 8 digitos" required>
					<input type="submit" name="generar" value="Buscar">
					</td>
				</tr>
			</table>
		
		<%
		
			if(request.getAttribute("Mensaje_Modificar") != null)
			{
				String Mensaje = request.getAttribute("Mensaje_Modificar").toString();
				if(Mensaje.equals("0"))
				{
		%>
			<b>Usuario no encontrado </b>
		<%
				}
			
				if(Mensaje.equals("1"))
				{
		%>
			<b>Usuario modificado </b>
		<%
				}
			}
		%>
		</form>

		<% 
			if(!cliente2.getDni().equals(""))
			{
		%>
		
		<form method="post" action="ServletClientes">
			<input type="hidden" name="TxtDni" value="<%=cliente2.getDni()%>">
			<table>
            	<tr>
					<td>
					<b>Ingrese CUIL: </b>
					</td>
					<td>
					<input type="Tel" name="TxtCuil" pattern="[0-9]{11}" title="El CUIL solo debe tener numeros, y debe contener 11 digitos" required value="<%=cliente2.getCuil()%>">
					</td>
				</tr>
				<tr>
					<td>
					<b>Ingrese Nombre:</b>
					</td>
					<td>
					<input type="text" name="TxtNombre" pattern=".{0,50}" title="Maximo de 50 caracteres alcanzado" required value="<%=cliente2.getNombre()%>">
					</td>
				</tr>
				<tr>
					<td>
					<b>Ingrese Apellido: </b>
					</td>
					<td>
					<input type="text" name="TxtApellido" pattern=".{0,50}" title="Maximo de 50 caracteres alcanzado" required value="<%=cliente2.getApellido()%>">
					</td>
				</tr>
				<tr>
					<td>
					<b>Ingrese clave</b>
					</td>
					<td>
					<input type="text" name="TxtPass" pattern=".{0,20}" title="Maximo de 20 caracteres alcanzado" required value="<%=cliente2.getContraseña()%>">
					</td>
				</tr>
				<tr>
					<td>
					<b>Ingrese clave nuevamente: </b>
					</td>
					<td>
					<input type="text" name="TxtPass2" pattern=".{0,20}" title="Maximo de 20 caracteres alcanzado" required value="<%=cliente2.getContraseña()%>">
					</td>
				</tr>
				<tr>
					<td>
					<b>Ingrese sexo: </b>
					</td>
					<td>
					<select name="Sexo">
					
				<% 
					for(String genero : Generos) 
					{
						if(genero.equals(cliente2.getSexo()))
						{
				%>
						<option selected><%=genero%></option>
				<%
						}
						else
						{
				%>
						<option><%=genero%></option>
				<%
						}
					}
				%>
				
					</select>
					</td>
				</tr>
				<tr>
					<td>
					<b>Ingrese nacionalidad: </b>
					</td>
					<td>
					<select name="Nacionalidad">
					
				<% 
					for(String pais : Paises) 
					{
						if(pais.equals(cliente2.getNacionalidad()))
						{
				%>
						<option selected><%=pais%></option>
				<%
						}
						else
						{
				%>
						<option><%=pais%></option>
				<%
						}
					}
				%>
				
					</select>
					</td>
				</tr>
				<tr>
					<td>
					<b>Fecha de Nacimiento</b>
					</td>
					<td>
					<input type="date" name="TxtFecnac" required value="<%=cliente2.getFec_nacimiento()%>">
					</td>
				</tr>
				<tr>
					<td>
					<b>Ingrese Direccion: </b>
					</td>
					<td>
					<input type="text" name="TxtDom" pattern=".{0,50}" title="Maximo de 50 caracteres alcanzado" value="<%=cliente2.getDireccion()%>">
					</td>
				</tr>
				<tr>
					<td>
					<b>Ingrese Localidad: </b>
					</td>
					<td>
					<input type="text" name="TxtLoc" pattern=".{0,50}" title="Maximo de 50 caracteres alcanzado" value="<%=cliente2.getLocalidad()%>">
					</td>
				</tr>
				<tr>
					<td>
					<b>Ingrese Provincia: </b>
					</td>
					<td>
					<select name="Provincia">
					
				<% 
					for(String provincia : Provincias) 
					{
						if(provincia.equals(cliente2.getProvincia()))
						{
				%>
						<option selected><%=provincia%></option>
				<%
						}
						else
						{
				%>
						<option><%=provincia%></option>
				<%
						}
					}
				%>
				
					</select>
					</td>
				</tr>
				<tr>
					<td>
					<b>Ingrese Email: </b>
					</td>
					<td>
					<input type="email" name="TxtEmail" pattern=".{0,50}" title="Maximo de 50 caracteres alcanzado" value="<%=cliente2.getCorreo_electronico()%>">
					</td>
				</tr>
				<tr>
					<td>
					<b>Ingrese Telefono: </b>
					</td>
					<td>
					<input type="Tel" name="TxtTel" pattern="[0-9]{10}" title="El Telefono solo debe tener numeros, y debe contener 10 digitos" value="<%=cliente2.getTelefono()%>">
					</td>
				</tr>
				<tr>
					<td>
					<b>Ingrese Estado: </b>
					</td>
					<td>
					<select name="Estado">
					
				<% 
					for(String estado : Estados) 
					{
						if(estado.equals(cliente2.getEstado()))
						{
				%>
						<option selected><%=estado%></option>
				<%
						}
						else
						{
				%>
						<option><%=estado%></option>
				<%
						}
					}
				%>
				
					</select>
					</td>
				</tr>
			</table>
			<input type="submit" name="Modificar" value="Modificar" style="width:100px">
		</form>

<%
	}
%>
	</div>
</body>
</html>
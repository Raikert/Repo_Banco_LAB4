package datosImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import datos.ClienteDao;
import entidad.Cliente;
import entidad.Movimientos;

public class ClienteDaoImpl implements ClienteDao{

	private Conexion cn;
	
	
	@Override
	public boolean grabarMovimiento(Movimientos mov) {
		boolean estado=true;

		cn = new Conexion();
		cn.Open();	

		String query = "INSERT INTO movimientos (Fecha,Detalle,Importe,Tipo_Movimiento,Origen,Destino) VALUES ('"+ mov.getFecha() +"','"+mov.getDetalle()+"',"+mov.getImporte()+",'"+mov.getTipo_Mov()+"', '"+mov.getOrigen()+"','"+mov.getDestino()+"')";
		System.out.println(query);
		try
		 {
			estado=cn.execute(query);
		 }
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			cn.close();
		}
		return estado;
		
		
	}

	@Override
	public boolean actualizarCuentas(Float saldo,String cuenta) {
		boolean estado=true;

		cn = new Conexion();
		cn.Open();	

		String query = "UPDATE  cuentas SET cuentas.Saldo_Cu= cuentas.Saldo_Cu+'"+saldo+"' WHERE cuentas.Ncuenta_Cu='"+cuenta+"'";
		try
		 {
			estado=cn.execute(query);
		 }
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			cn.close();
		}
		return estado;
	}
	
	
	@Override
	public boolean actualizarCuentas2(Float saldo,String cuenta) {
		boolean estado=true;

		cn = new Conexion();
		cn.Open();	

		String query = "UPDATE  cuentas SET cuentas.Saldo_Cu= cuentas.Saldo_Cu-'"+saldo+"' WHERE cuentas.Ncuenta_Cu='"+cuenta+"'";
		try
		 {
			estado=cn.execute(query);
		 }
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			cn.close();
		}
		return estado;
	}

	
	
	@Override
	public String comprobarSaldo(String cuenta) {
		cn = new Conexion();
		cn.Open();
		
		String saldo="0";
		
		try
		{
			ResultSet rs = cn.query("select cuentas.Saldo_Cu FROM cuentas WHERE cuentas.Ncuenta_Cu ='"+cuenta+"'");
			rs.next();
			
			saldo = Float.toString(rs.getFloat("cuentas.Saldo_Cu"));
			 
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			cn.close();
		}
		return saldo;
		
	}






	@Override
	public String comprobarCuenta(String cuenta) {
		cn = new Conexion();
		cn.Open();
		
		String cuentas="0";
		
		try
		{
			ResultSet rs = cn.query("select cuentas.Ncuenta_Cu FROM cuentas WHERE cuentas.Ncuenta_Cu ='"+cuenta+"'");
			rs.next();
			
			cuentas = Integer.toString(rs.getInt("cuentas.Ncuenta_Cu"));
			 
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			cn.close();
		}
		return cuentas;
	}






	@Override
	public ArrayList<Movimientos> obtenerMovimientosCuenta(String cuenta) {
		
		cn = new Conexion();
		cn.Open();
		
		
		ArrayList<Movimientos> lista = new ArrayList<Movimientos>();
		
		try
		{
			ResultSet rs = cn.query("select movimientos.Fecha, movimientos.Detalle, movimientos.Importe, movimientos.Tipo_Movimiento FROM movimientos WHERE movimientos.Origen='"+cuenta+"'");
			
			while(rs.next()) {
				Movimientos mov = new Movimientos();
				mov.setFecha(rs.getDate("movimientos.Fecha"));
				mov.setDetalle(rs.getString("movimientos.Detalle"));
				mov.setImporte(rs.getDouble("movimientos.Importe"));
				mov.setTipo_Mov(rs.getString("movimientos.Tipo_Movimiento"));
				
				lista.add(mov);				
			}

			
			 
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			cn.close();
		}
		return lista;
		
		
	}
	

	
	
	

	@Override
	public String obtenerSaldoCuenta(String cuenta) {
		cn = new Conexion();
		cn.Open();
		
		String saldo="0";
		
		try
		{
			ResultSet rs = cn.query("select cuentas.Saldo_Cu FROM cuentas WHERE cuentas.Ncuenta_Cu='"+cuenta+"'");
			rs.next();
			saldo = Float.toString(rs.getFloat("cuentas.Saldo_Cu"));
		
			
			 
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			cn.close();
		}
		return saldo;
	}


	
	
	@Override
	public ResultSet obtenerCuentas(String dni) {
		ResultSet rs2 = null;
		cn = new Conexion();
		cn.Open();
		try {
			 rs2 = cn.query("Select cuentas.Ncuenta_Cu FROM cuentas WHERE cuentas.DNI_Cu = '" + dni +"'");
			 return rs2;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	
		
		return null;
		
	}
	
	public Cliente obtenerUsuarioContraseña(String usuario, String contraseña) 
	{
		cn = new Conexion();
		cn.Open();
		
		Cliente cli = new Cliente();
		try
		{
			ResultSet rs = cn.query("Select clientes.DNI_Cli, clientes.Nombre_Cli, clientes.Apellido_Cli,clientes.Usuario_Cli, clientes.Contraseña_Cli from clientes where clientes.Usuario_Cli='"+usuario+"' && clientes.Contraseña_Cli='"+contraseña+"'");
			rs.next();
			
			cli.setDni(rs.getString("clientes.DNI_Cli"));
			cli.setNombre(rs.getString("clientes.Nombre_Cli"));
			cli.setApellido(rs.getString("clientes.Apellido_Cli"));
			cli.setUsuario(rs.getString("clientes.Usuario_Cli"));
			cli.setContraseña(rs.getString("clientes.Contraseña_Cli"));
			 
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			cn.close();
		}
		return cli;
		
	}
	
	public void agregarCliente(Cliente cliente) 
	{
		cn = new Conexion();
		cn.Open();
		
		String query = "Insert into Clientes(DNI_Cli, CUIL_Cli, Nombre_Cli, Apellido_Cli, Sexo_Cli, "
				+ "Nacionalidad_Cli, Fecha_Nac_Cli, Direccion_Cli, Localidad_Cli, Provincia_Cli, "
				+ "CorreoE_Cli, Telefono_Cli, Usuario_Cli, Contraseña_Cli, Estado_Cli) values "
				
				+ "('" + cliente.getDni() + "','" + cliente.getCuil() + "','" + cliente.getNombre()
				+ "','" + cliente.getApellido() + "','" + cliente.getSexo() + "','" + cliente.getNacionalidad()
				+ "','" + cliente.getFec_nacimiento() + "','" + cliente.getDireccion() + "','" + cliente.getLocalidad()
				+ "','" + cliente.getProvincia() + "','" + cliente.getCorreo_electronico() + "','" + cliente.getTelefono()
				+ "','" + cliente.getUsuario() + "','" + cliente.getContraseña() + "'," + cliente.getEstado() + ")";
		
		cn.execute(query);
		cn.close();
	}
	
	public void modificarCliente(Cliente cliente)
	{
		cn = new Conexion();
		cn.Open();
		
		String query = "Update Clientes set CUIL_Cli='" + cliente.getCuil() + "', Nombre_Cli='" + cliente.getNombre() + "', Apellido_Cli='" 
	            + cliente.getApellido() + "', Sexo_Cli='" + cliente.getSexo() + "', Nacionalidad_Cli='"+ cliente.getNacionalidad() + "', Fecha_Nac_Cli='" 
				+ cliente.getFec_nacimiento() + "', Direccion_Cli='" + cliente.getDireccion() + "', Localidad_Cli='" + cliente.getLocalidad() + "', Provincia_Cli='" 
				+ cliente.getProvincia() + "', CorreoE_Cli='" + cliente.getCorreo_electronico() + "', Telefono_Cli='" + cliente.getTelefono() + "', Contraseña_Cli='" 
				+ cliente.getContraseña() + "', Estado_Cli=" + cliente.getEstado() + " where DNI_Cli='" + cliente.getDni() + "'";
		
		cn.execute(query);
		cn.close();
	}
	
	public void cargar_Cliente(Cliente cliente, ResultSet rs) 
	{
		try
		{
			cliente.setCuil(rs.getString("CUIL_Cli"));
			cliente.setNombre(rs.getString("Nombre_Cli"));
			cliente.setApellido(rs.getString("Apellido_Cli"));
			cliente.setSexo(rs.getString("Sexo_Cli"));
			cliente.setNacionalidad(rs.getString("Nacionalidad_Cli"));
			cliente.setFec_nacimiento(rs.getString("Fecha_Nac_Cli"));
			cliente.setDireccion(rs.getString("Direccion_Cli"));
			cliente.setLocalidad(rs.getString("Localidad_Cli"));
			cliente.setProvincia(rs.getString("Provincia_Cli"));
			cliente.setCorreo_electronico(rs.getString("CorreoE_Cli"));
			cliente.setTelefono(rs.getString("Telefono_Cli"));
			cliente.setUsuario(rs.getString("Usuario_Cli"));
			cliente.setContraseña(rs.getString("Contraseña_Cli"));
			
			if(rs.getString("Estado_Cli").equals("1"))
				cliente.setEstado("Activo");
			else
				cliente.setEstado("Inactivo");
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public Cliente Buscar_DNI(Cliente cliente)
	{
		cn = new Conexion();
		cn.Open();
		
		String consulta = "Select * from Clientes where DNI_Cli='" + cliente.getDni() + "'";
		
		ResultSet rs = cn.query(consulta);
		try 
		{
			if(rs.next())
			{
				cargar_Cliente(cliente, rs);
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		cn.close();
		return cliente;
	}

	@Override
	public boolean buscar_Coincidencia(String campo, String valor) 
	{
		cn = new Conexion();
		cn.Open();
		boolean row_encontrada = false;
		
		ResultSet rs = cn.query("Select * from Clientes where " + campo + "='" + valor + "'");
		try 
		{
			if(rs.next())
				row_encontrada = true;
					
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			cn.close();
		}
		
		return row_encontrada;
	}
	
	@Override
	public ArrayList<Cliente> buscar_Clientes(ArrayList<Cliente> clientes, HttpServletRequest request) 
	{
		String [] name_request = {"TxtDni", "TxtCuil", "TxtNombre", "TxtApellido", "Sexo", "Nacionalidad", "TxtFecnac1",
				"TxtFecnac2" ,"TxtDom", "TxtLoc", "Provincia", "TxtEmail", "TxtTel", "TxtUsu", "TxtPass", "Estado"};
		
		String [] name_db = {"Dni_Cli", "CUIL_Cli", "Nombre_Cli", "Apellido_Cli", "Sexo_Cli", "Nacionalidad_Cli", "Fecha_Nac_Cli",
				"Fecha_Nac_Cli" ,"Direccion_Cli", "Localidad_Cli", "Provincia_Cli", "CorreoE_Cli", "Telefono_Cli", "Usuario_Cli", 
				"Contraseña_Cli", "Estado_Cli"};
		
		String consulta = "Select * from Clientes";
		String valor_request;
		boolean segmento_WHERE = false;
		boolean segmento_AND = false;
		
		for(int i=0; i<16; i++) 
		{
			valor_request = request.getParameter(name_request[i]);
			
				if(!valor_request.equals("0") && !valor_request.equals(""))
				{
					if(!segmento_WHERE)
					{
						consulta += " where ";
						segmento_WHERE = true;
					}
					
					if(segmento_AND)
						consulta+= " and ";
					
					switch(i)
					{
					case 4:
					case 5:
					case 10:
						consulta+= name_db[i] + "='" + valor_request + "'";
						break;
					case 6:
						consulta+= name_db[i] + ">='" + valor_request + "'";
						break;
					case 7:
						consulta += name_db[i] + "<='" + valor_request + "'";
						break;
					case 15:
						String estado;
						
						if(valor_request.equals("Activo"))
							estado = "1";
						else
							estado = "0";
						
						consulta+= name_db[i] + "=" + estado;
						break;
					default:
						consulta+= name_db[i] + " like '" + valor_request + "%'";
						break;
					}
					
					if(!segmento_AND)
						segmento_AND = true;
				}
		}
		
		cn = new Conexion();
		cn.Open();
		
		ResultSet rs = cn.query(consulta);
		try 
		{
			while(rs.next())
			{
				Cliente cliente = new Cliente();
					
				cliente.setDni(rs.getString("Dni_Cli"));
				cargar_Cliente(cliente, rs);
				
				clientes.add(cliente);
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			cn.close();
		}
		
		return clientes;
	}
}

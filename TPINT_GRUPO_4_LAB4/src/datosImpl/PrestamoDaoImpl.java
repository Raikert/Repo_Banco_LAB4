package datosImpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import datos.*;


import entidad.Cliente;
import entidad.Prestamo;

public class PrestamoDaoImpl implements PrestamoDao{
	
	Conexion cn = new Conexion();
	private static final String obtenerPendientes= "SELECT * FROM final_lab_4.PRESTAMOS WHERE estado_Pr = 'pendiente'";
	private static final String modificarEstado= "CALL Modificar_Estado_Prestamo(?,?)";
	private static final String agregarPrestamo="CALL Nuevo_Prestamo(?,?,?,?,?)";
	
	private static final String todosc= "CALL Contar_Cantidad_Prestamos_Cuotas(?,?,?,?,?)";
	private static final String todos= "CALL Contar_Cantidad_Prestamos(?,?,?,?)";
	private static final String pagosc= "CALL Contar_Cantidad_Prestamos_Pagos_Cuotas(?,?,?,?,?)";
	private static final String pagos= "CALL Contar_Cantidad_Prestamos_pagos(?,?,?,?)";
	private static final String pendientesc= "CALL Contar_Cantidad_Prestamos_Pendientes_Cuotas(?,?,?,?,?)";
	private static final String pendientes= "CALL Contar_Cantidad_Prestamos_pendientes(?,?,?,?)";
	private static final String rechazadosc= "CALL Contar_Cantidad_Prestamos_Rechazados_Cuotas(?,?,?,?,?)";
	private static final String rechazados= "CALL Contar_Cantidad_Prestamos_Rechazados(?,?,?,?)";
	private static final String activosc= "CALL Contar_Cantidad_Prestamos_Aprobados_Cuotas(?,?,?,?,?)";
	private static final String activos= "CALL Contar_Cantidad_Prestamos_Aprobados(?,?,?,?)";
	private static final String promedioc= "CALL Obtener_Promedio_Prestamos_Cuotas(?,?,?,?,?)";
	private static final String promedio= "CALL Obtener_Promedio_Prestamos(?,?,?,?)";

	
	//HACER CAMBIOS EN LOS METODOS DE CONTAR, PORQUE HACE UN SETSTRING CUANDO DEBE REALIZAR UN SETDATE PARA REALIZAR
	//EL PROCEDIMIENTO
	@Override
	public List<Prestamo> obtenerPendientes() {
		cn.Open();
		List<Prestamo> lista = new ArrayList<Prestamo>();
		try {
			ResultSet rs = cn.query(obtenerPendientes);
			while(rs.next()) {
				Prestamo p = new Prestamo(rs.getInt("ID_Pr"), rs.getInt("DNI_Pr"), rs.getInt("Ncuenta_Pr"));
				p.setImportePedido(rs.getDouble("importe_Ped_Pr"));
				p.setImportePagar(rs.getDouble("importe_Int_Pr"));
				p.setMontoxMes(rs.getDouble("montoxMes_Pr"));
				p.setCuotas(rs.getInt("cuotas_Pr"));
				p.setEstado(rs.getString("estado_Pr"));
				p.setFecha(rs.getDate("fecha_Pr"));
				lista.add(p);
			} 
			} catch (Exception e) {
				e.printStackTrace();
			}
		cn.close();
		return lista;
	}



	@Override
	public boolean cambiarEstado(int id, String estado) {
		
		Connection c = cn.Open();
		boolean b=false;
		try {
			CallableStatement cst = c.prepareCall(modificarEstado);
			cst.setInt(1, id);
			cst.setString(2, estado.toLowerCase());
			cst.execute();
			b=true;
		} catch (Exception e) {
			
		}
		cn.close();
		return b;
	}



	@Override
	public boolean agregarPrestamo(Prestamo p) {
		Connection c = cn.Open();
		boolean b=false;
		try {
			CallableStatement cst = c.prepareCall(agregarPrestamo);
			cst.setInt(1, p.getCuenta());
			cst.setDouble(2, p.getImportePagar());
			cst.setDouble(3, p.getImportePedido());
			cst.setDouble(4, p.getMontoxMes());
			cst.setInt(5, p.getCuotas());
			cst.execute();
			b=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		cn.close();
		return b;
	}



	@Override
	public int Cant_Prestamos(String ini, String fin, double monmin, double monmax, int cuotas) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		
		CallableStatement statement;
		Connection conexion = Conexion2.getConexion().getSQLConexion();
		int n=0;
		try
		{
			if(cuotas!=0){
				statement = conexion.prepareCall(todosc);
				statement.setInt(5, cuotas);
			}else
			{
				statement = conexion.prepareCall(todos);
			}
			statement.setString(1, ini);
			statement.setString(2, fin);
			statement.setDouble(3, monmin);
			statement.setDouble(4, monmax);
			
			ResultSet rs = statement.executeQuery();
			
			if(rs.next())
			{
				n=rs.getInt(1);
			}		
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			try {
				conexion.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return n;
	}



	@Override
	public int Cant_Pagos(String ini, String fin, double monmin, double monmax, int cuotas) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		
		CallableStatement statement;
		Connection conexion = Conexion2.getConexion().getSQLConexion();
		int n=0;
		try
		{
			if(cuotas!=0){
				statement = conexion.prepareCall(pagosc);
				statement.setInt(5, cuotas);
			}else
			{
				statement = conexion.prepareCall(pagos);
			}
			statement.setString(1, ini);
			statement.setString(2, fin);
			statement.setDouble(3, monmin);
			statement.setDouble(4, monmax);
			
			ResultSet rs = statement.executeQuery();
			
			if(rs.next())
			{
				n=rs.getInt(1);
			}		
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			try {
				conexion.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return n;
	}



	@Override
	public int Cant_Pendientes(String ini, String fin, double monmin, double monmax, int cuotas) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		
		CallableStatement statement;
		Connection conexion = Conexion2.getConexion().getSQLConexion();
		int n=0;
		try
		{
			if(cuotas!=0){
				statement = conexion.prepareCall(pendientesc);
				statement.setInt(5, cuotas);
			}else
			{
				statement = conexion.prepareCall(pendientes);
			}
			statement.setString(1, ini);
			statement.setString(2, fin);
			statement.setDouble(3, monmin);
			statement.setDouble(4, monmax);
			
			ResultSet rs = statement.executeQuery();
			
			if(rs.next())
			{
				n=rs.getInt(1);
			}		
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			try {
				conexion.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return n;
	}



	@Override
	public int Cant_Rechazados(String ini, String fin, double monmin, double monmax, int cuotas) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		
		CallableStatement statement;
		Connection conexion = Conexion2.getConexion().getSQLConexion();
		int n=0;
		try
		{
			if(cuotas!=0){
				statement = conexion.prepareCall(rechazadosc);
				statement.setInt(5, cuotas);
			}else
			{
				statement = conexion.prepareCall(rechazados);
			}
			statement.setString(1, ini);
			statement.setString(2, fin);
			statement.setDouble(3, monmin);
			statement.setDouble(4, monmax);
			
			ResultSet rs = statement.executeQuery();
			
			if(rs.next())
			{
				n=rs.getInt(1);
			}		
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			try {
				conexion.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return n;
	}



	@Override
	public int Cant_Activos(String ini, String fin, double monmin, double monmax, int cuotas) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		
		CallableStatement statement;
		Connection conexion = Conexion2.getConexion().getSQLConexion();
		int n=0;
		try
		{
			if(cuotas!=0){
				statement = conexion.prepareCall(activosc);
				statement.setInt(5, cuotas);
			}else
			{
				statement = conexion.prepareCall(activos);
			}
			statement.setString(1, ini);
			statement.setString(2, fin);
			statement.setDouble(3, monmin);
			statement.setDouble(4, monmax);
			
			ResultSet rs = statement.executeQuery();
			
			if(rs.next())
			{
				n=rs.getInt(1);
			}		
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			try {
				conexion.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return n;
	}



	@Override
	public int Promedio(String ini, String fin, double monmin, double monmax, int cuotas) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		
		CallableStatement statement;
		Connection conexion = Conexion2.getConexion().getSQLConexion();
		int n=0;
		int c=0;
		try
		{
			if(cuotas!=0){
				statement = conexion.prepareCall(promedioc);
				statement.setInt(5, cuotas);
			}else
			{
				statement = conexion.prepareCall(promedio);
			}
			statement.setString(1, ini);
			statement.setString(2, fin);
			statement.setDouble(3, monmin);
			statement.setDouble(4, monmax);
			
			ResultSet rs = statement.executeQuery();
			
			while(rs.next())
			{
				n+=rs.getInt(1);
				c++;
			}		
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			try {
				conexion.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return (int)(n/c);
	}

	public void cargar_Prestamo(Prestamo prestamo, ResultSet rs)
	{
		try
		{
			prestamo.setFecha(rs.getDate("fecha_Pr"));
			prestamo.setPlazo(rs.getDate("plazo_pago_Pr"));
			prestamo.setEstado(rs.getString("estado_Pr"));
			prestamo.setImportePedido(rs.getDouble("importe_Ped_Pr"));
			prestamo.setImportePagar(rs.getDouble("importe_Int_Pr"));
			prestamo.setMontoxMes(rs.getDouble("montoxMes_Pr"));
			prestamo.setCuotas(rs.getInt("cuotas_Pr"));
			prestamo.setCuotasPagadas(rs.getInt("cuota_pagada_Pr"));
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<Prestamo> obtenerPrestamosCli(String DNI, String Cuenta) 
	{
		ArrayList<Prestamo> prestamos = new ArrayList<Prestamo>();
		
		String consulta = "Select * from Prestamos where DNI_Pr='" + DNI + "' and NCuenta_Pr=" + Cuenta;
		
		cn = new Conexion();
		cn.Open();
		
		ResultSet rs = cn.query(consulta);
		try 
		{
			while(rs.next())
			{
				Prestamo prestamo = new Prestamo();
				cargar_Prestamo(prestamo, rs);
				prestamos.add(prestamo);
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
		
		return prestamos;
	}
	
	public ResultSet obtenerIDPrestamosPagar(String dni) {
		ResultSet rs2 = null;
		cn = new Conexion();
		cn.Open();
		try {
			 rs2 = cn.query("Select prestamos.ID_Pr FROM prestamos WHERE prestamos.DNI_Pr = '" + dni +"' AND estado_Pr = 'aprobado' OR estado_Pr = 'pagando'");
			 return rs2;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	
		
		return null;
		
	}
	
	public ResultSet obtenerPrestamosPagar(String dni) {
		ResultSet rs2 = null;
		cn = new Conexion();
		cn.Open();
		try {
			 rs2 = cn.query("Select ID_Pr,importe_Int_Pr,cuotas_Pr,Cuota_pagada_Pr,montoxMes_Pr  FROM prestamos WHERE prestamos.DNI_Pr = '" + dni +"' and estado_Pr = 'aprobado' OR estado_Pr = 'pagando'");
			 return rs2;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	
		
		return null;
		
	}
	
	public int modificarCuenta(int cuotas, int idprestamo, int cuenta)
	{
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		int filas=0;
		Connection con = null;
		try
		{
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/FINAL_LAB_4","root","root");
			Statement st = con.createStatement();
			String query = "update prestamos set cuota_pagada_Pr = cuota_pagada_Pr + "+cuotas+ ",estado_Pr = 'pagando' where ID_Pr = "+idprestamo;
			filas=st.executeUpdate(query);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return filas;
	}
	
	
	public int RestarSaldoCuenta(int cuotas, int idprestamo, int cuenta)
	{
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		int filas=0;
		Connection con = null;
		try
		{
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/FINAL_LAB_4","root","root");
			Statement st = con.createStatement();
			String query = "update cuentas set Saldo_Cu = Saldo_Cu-((select montoxMes_Pr from prestamos where ID_Pr ="+idprestamo+")*"+cuotas+") where Ncuenta_Cu="+cuenta;
			filas=st.executeUpdate(query);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return filas;
	}
	
	
	public float obtenerSaldocuentaApagar(int cuenta) {
		ResultSet rs2 = null;
		float saldo = 0;
		cn = new Conexion();
		cn.Open();
		try {
			 rs2 = cn.query("Select Saldo_Cu  FROM cuentas WHERE cuentas.Ncuenta_cu ="+cuenta);
			 while (rs2.next()) {
			 saldo = rs2.getFloat("Saldo_Cu");
			 }
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	
		
		return saldo;
		
	}
	
	
	public float obtenerImporteApagar(int prestamo) {
		ResultSet rs2 = null;
		float importe = 0;
		cn = new Conexion();
		cn.Open();
		try {
			
			 rs2 = cn.query("Select montoxMes_Pr  FROM prestamos WHERE prestamos.ID_Pr ="+prestamo);
			 while (rs2.next()) {
			 importe = rs2.getFloat("montoxMes_Pr");
			 }
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	
		
		return importe;
		
	}
	
	
}

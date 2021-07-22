package datosImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import datos.*;
import entidad.*;
import datosImpl.*;
import datosImpl.Conexion2;

public class MovimientosDaoImpl implements MovimientosDao  {
	
	private static final String consul= "SELECT * from movimientos";
	private static final String egresos= "select Importe from movimientos WHERE Tipo_Movimiento = 'alta prestamo' and Fecha >= ? and Fecha < ?";
	private static final String Ingresos= "select Importe from movimientos WHERE Tipo_Movimiento != 'alta prestamo' and Tipo_Movimiento != 'Transferencia' and Fecha >= ? and Fecha < ?";
	private static final String bclientes= "select DNI_Cli from clientes";
	private static final String bcuentast= "select Ncuenta_Cu from cuentas";
	private static final String bcuentas= "select Ncuenta_Cu from cuentas where DNI_Cu=?";
	private static final String bhistorialg= "select * from movimientos where Tipo_Movimiento=? and Importe>=? and Importe<=? and Fecha>=? and Fecha<=? and Origen=? limit ?";

	
	@Override
	public ArrayList<Movimientos> Obtener_Tabla() {
		ArrayList<Movimientos> lista = new ArrayList<Movimientos>();
		Connection conexion = Conexion2.getConexion().getSQLConexion();
		Statement st;
		ResultSet rs;
		try {
			
			st = conexion.createStatement();
			rs = st.executeQuery(consul);
			while(rs.next()) {
				Movimientos x = new Movimientos();
				x.setFecha(rs.getDate("Fecha"));
				x.setDetalle(rs.getString("Detalle"));
				x.setImporte(rs.getDouble("Importe"));
				x.setTipo_Mov(rs.getString("Tipo_Movimiento"));
				x.setOrigen(rs.getString("Origen"));
				x.setDestino(rs.getString("Destino"));
				lista.add(x);
				
			}
			
			
		} catch (SQLException e) { 
			e.printStackTrace();
			try {
				conexion.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		return lista;
	}
	
	public double Egresos(String ini, String fin)
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		
		PreparedStatement statement;
		Connection conexion = Conexion2.getConexion().getSQLConexion();
		double n=0;
		try
		{
			statement = conexion.prepareStatement(egresos);
			statement.setString(1, ini);
			statement.setString(2, fin);
			ResultSet rs = statement.executeQuery();
			
			while(rs.next())
			{
				n+=rs.getDouble(1);
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
	
	public double Ingresos(String ini, String fin)
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		
		
		PreparedStatement statement;
		Connection conexion = Conexion2.getConexion().getSQLConexion();
		double n=0;
		try
		{
			statement = conexion.prepareStatement(Ingresos);
			statement.setString(1, ini);
			statement.setString(2, fin);
			ResultSet rs = statement.executeQuery();
			
			while(rs.next())
			{
				n+=rs.getDouble(1);
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
	
	public ArrayList<String> HistorialGeneral() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		ArrayList<String> lista = new ArrayList<String>();
		Connection conexion = Conexion2.getConexion().getSQLConexion();
		Statement st;
		ResultSet rs;
		try {
			
			st = conexion.createStatement();
			rs = st.executeQuery(bclientes);
			while(rs.next()) {

				lista.add(rs.getString("DNI_Cli"));	
			}
		} catch (SQLException e) { 
			e.printStackTrace();
			try {
				conexion.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return lista;
	}
	
	public ArrayList<Integer> CuentasHG(String opcion) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		ArrayList<Integer> lista = new ArrayList<Integer>();
		if(opcion!="todos")
		{
			PreparedStatement statement;
			Connection conexion = Conexion2.getConexion().getSQLConexion();
			try
			{
				statement = conexion.prepareStatement(bcuentas);
				statement.setString(1, opcion);
				ResultSet rs = statement.executeQuery();
				
				while(rs.next())
				{
					lista.add(rs.getInt("Ncuenta_Cu"));
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
		}else
		{
			Connection conexion = Conexion2.getConexion().getSQLConexion();
			Statement st;
			ResultSet rs;
			try {
				
				st = conexion.createStatement();
				rs = st.executeQuery(bcuentast);
				while(rs.next()) {

					lista.add(rs.getInt("Ncuenta_Cu"));	
				}
			} catch (SQLException e) { 
				e.printStackTrace();
				try {
					conexion.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
		return lista;
	}
	
	public ArrayList<Movimientos> BMovimientos(String ini, String fin,String tipo,double min,double max,int cant,String cue)
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		ArrayList<Movimientos> lista = new ArrayList<Movimientos>();
		
		PreparedStatement statement;
		Connection conexion = Conexion2.getConexion().getSQLConexion();
		lista.clear();
		try
		{
			statement = conexion.prepareStatement(bhistorialg);
			statement.setString(1, tipo);
			statement.setDouble(2, min);
			statement.setDouble(3, max);
			statement.setString(4, ini);
			statement.setString(5, fin);
			statement.setString(6, cue);
			statement.setInt(7, cant);
			ResultSet rs = statement.executeQuery();
			
			while(rs.next())
			{
				Movimientos mov = new Movimientos();
				mov.setTipo_Mov(rs.getString("Tipo_Movimiento"));
				mov.setImporte(rs.getDouble("Importe"));
				mov.setFecha(rs.getDate("Fecha"));
				mov.setOrigen(rs.getString("Origen"));
				mov.setDestino(rs.getString("Destino"));
				mov.setDetalle(rs.getString("Detalle"));
				
				lista.add(mov);
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
		return lista;
	}
	
}

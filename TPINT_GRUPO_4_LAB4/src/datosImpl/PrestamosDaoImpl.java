package datosImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import datos.*;
import entidad.*;
import datosImpl.*;

public class PrestamosDaoImpl implements PrestamosDao{

	private static final String todosc= "select count(ID) from prestamos where Fecha >= ? and Fecha < ? and Importe_Int >= ? and Importe_Int <? and Cuotas = ?";
	private static final String todos= "select count(ID) from prestamos where Fecha >= ? and Fecha < ? and Importe_Int >= ? and Importe_Int <? ";
	private static final String pagosc= "select count(ID) from prestamos where Fecha >= ? and Fecha < ? and Importe_Int >= ? and Importe_Int <? and Cuotas = ? and Estado= 'Pago'";
	private static final String pagos= "select count(ID) from prestamos where Fecha >= ? and Fecha < ? and Importe_Int >= ? and Importe_Int <? and Estado= 'Pago'";
	private static final String pendientesc= "select count(ID) from prestamos where Fecha >= ? and Fecha < ? and Importe_Int >= ? and Importe_Int <? and Cuotas = ? and Estado= 'Pendiente'";
	private static final String pendientes= "select count(ID) from prestamos where Fecha >= ? and Fecha < ? and Importe_Int >= ? and Importe_Int <? and Estado= 'Pendiente'";
	private static final String rechazadosc= "select count(ID) from prestamos where Fecha >= ? and Fecha < ? and Importe_Int >= ? and Importe_Int <? and Cuotas = ? and Estado= 'Rechazado'";
	private static final String rechazados= "select count(ID) from prestamos where Fecha >= ? and Fecha < ? and Importe_Int >= ? and Importe_Int <? and Estado= 'Rechazado'";
	private static final String activosc= "select count(ID) from prestamos where Fecha >= ? and Fecha < ? and Importe_Int >= ? and Importe_Int <? and Cuotas = ? and Estado= 'Activo'";
	private static final String activos= "select count(ID) from prestamos where Fecha >= ? and Fecha < ? and Importe_Int >= ? and Importe_Int <? and Estado= 'Activo'";
	private static final String promedioc= "select Importe_Int from prestamos where Fecha >= ? and Fecha < ? and Importe_Int >= ? and Importe_Int <? and Cuotas = ?";
	private static final String promedio= "select Importe_Int from prestamos where Fecha >= ? and Fecha < ? and Importe_Int >= ? and Importe_Int <?";
	
	
	public int Cant_Prestamos(String ini, String fin, double monmin, double monmax, int cuotas)
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		
		PreparedStatement statement;
		Connection conexion = Conexion2.getConexion().getSQLConexion();
		int n=0;
		try
		{
			if(cuotas!=0){
				statement = conexion.prepareStatement(todosc);
				statement.setInt(5, cuotas);
			}else
			{
				statement = conexion.prepareStatement(todos);
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
	
	public int Cant_Pagos(String ini, String fin, double monmin, double monmax, int cuotas)
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		
		PreparedStatement statement;
		Connection conexion = Conexion2.getConexion().getSQLConexion();
		int n=0;
		try
		{
			if(cuotas!=0){
				statement = conexion.prepareStatement(pagosc);
				statement.setInt(5, cuotas);
			}else
			{
				statement = conexion.prepareStatement(pagos);
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
	
	public int Cant_Pendientes(String ini, String fin, double monmin, double monmax, int cuotas)
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		
		PreparedStatement statement;
		Connection conexion = Conexion2.getConexion().getSQLConexion();
		int n=0;
		try
		{
			if(cuotas!=0){
				statement = conexion.prepareStatement(pendientesc);
				statement.setInt(5, cuotas);
			}else
			{
				statement = conexion.prepareStatement(pendientes);
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
	
	public int Cant_Rechazados(String ini, String fin, double monmin, double monmax, int cuotas)
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		
		PreparedStatement statement;
		Connection conexion = Conexion2.getConexion().getSQLConexion();
		int n=0;
		try
		{
			if(cuotas!=0){
				statement = conexion.prepareStatement(rechazadosc);
				statement.setInt(5, cuotas);
			}else
			{
				statement = conexion.prepareStatement(rechazados);
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
	
	public int Cant_Activos(String ini, String fin, double monmin, double monmax, int cuotas)
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		
		PreparedStatement statement;
		Connection conexion = Conexion2.getConexion().getSQLConexion();
		int n=0;
		try
		{
			if(cuotas!=0){
				statement = conexion.prepareStatement(activosc);
				statement.setInt(5, cuotas);
			}else
			{
				statement = conexion.prepareStatement(activos);
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
	
	public int Promedio(String ini, String fin, double monmin, double monmax, int cuotas)
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		
		PreparedStatement statement;
		Connection conexion = Conexion2.getConexion().getSQLConexion();
		int n=0;
		int c=0;
		try
		{
			if(cuotas!=0){
				statement = conexion.prepareStatement(promedioc);
				statement.setInt(5, cuotas);
			}else
			{
				statement = conexion.prepareStatement(promedio);
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
}

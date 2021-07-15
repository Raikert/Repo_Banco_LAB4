package datosImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion2 {
	public static Conexion2 instancia;
	private Connection connection;
	
	private Conexion2()
	{
		try
		{
			this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/final_lab_4","root","root");
			this.connection.setAutoCommit(false);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	public static Conexion2 getConexion()   
	{								
		if(instancia == null)
		{
			instancia = new Conexion2();
		}
		return instancia;
	}

	public Connection getSQLConexion() 
	{
		return this.connection;
	}
	
	public void cerrarConexion()
	{
		try 
		{
			this.connection.close();
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		instancia = null;
	}

}

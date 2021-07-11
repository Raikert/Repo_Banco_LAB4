package datosImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexion 
{
	private String host = "jdbc:mysql://localhost:3306/";
	private String user = "root";
	private String pass = "root";
	private String dbName = "final_lab_4";
	
	protected Connection connection;
	
	public Connection Open()
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			this.connection = DriverManager.getConnection(host + dbName, user, pass);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return this.connection;
	}
	
	public ResultSet query(String query)
	{
		try
		{
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(query);
			return rs;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		ResultSet rs = null;
		return rs;
		
	}
	
	public boolean execute(String query)
	{
		Statement st;
		boolean save = true;
		
		try 
		{
			st = connection.createStatement();
		    st.executeUpdate(query);
		}
		catch(SQLException e)
		{
			save = false;
			e.printStackTrace();
		}
		
		return save;
	}
	
	public boolean close()
	{
		boolean ok = true;
		
		try 
		{
			connection.close();
		}
		catch(SQLException e)
		{
			ok = false;
			e.printStackTrace();
		}
		
		return ok;
	}
}

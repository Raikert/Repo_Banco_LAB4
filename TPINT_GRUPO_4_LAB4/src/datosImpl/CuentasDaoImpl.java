package datosImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import datos.*;
import entidad.*;
import datosImpl.*;
public class CuentasDaoImpl implements CuentasDao{

	private static final String cantidad= "Select count(DNI_Cu) from cuentas where Fec_creacion_Cu >= ? and Fec_creacion_Cu < ? and Saldo_Cu >= ? and Saldo_Cu <? and TipoCuenta_Cu!=?";
	private static final String saldo= "Select Saldo_Cu from cuentas where Fec_creacion_Cu >= ? and Fec_creacion_Cu < ? and Saldo_Cu >= ? and Saldo_Cu <? and TipoCuenta_Cu!=?";


	public CuentasDaoImpl() {
		
	}
	
	public double Cant_Cuentas(String ini, String fin, double salmin, double salmax, String tipo)
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
			statement = conexion.prepareStatement(cantidad);
			statement.setString(1, ini);
			statement.setString(2, fin);
			statement.setDouble(3, salmin);
			statement.setDouble(4, salmax);
			statement.setString(5, tipo);
			ResultSet rs = statement.executeQuery();
			
			if(rs.next())
			{
				n=rs.getDouble(1);
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
	
	public double Saldo_Prom(String ini, String fin, double salmin, double salmax, String tipo)
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
		int c=0;
		try
		{
			statement = conexion.prepareStatement(saldo);
			statement.setString(1, ini);
			statement.setString(2, fin);
			statement.setDouble(3, salmin);
			statement.setDouble(4, salmax);
			statement.setString(5, tipo);
			ResultSet rs = statement.executeQuery();
			
			while(rs.next())
			{
				n+=rs.getDouble(1);
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
		return (n/c);
	}
}

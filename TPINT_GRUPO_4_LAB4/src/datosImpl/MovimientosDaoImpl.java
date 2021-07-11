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
	
}

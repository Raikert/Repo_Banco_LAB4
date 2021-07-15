package entidad;


	import java.sql.CallableStatement;
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.Statement;
	import java.util.ArrayList;

 import entidad.Cuentas;




	public class CuentasAdmDao {
		
		private String host = "jdbc:mysql://localhost:3306/";
		private String user = "root";
		private String pass = "root";
		private String dbName = "FINAL_LAB_4";
		
		
		
		
		public int agregarCuenta(Cuentas cuenta)
		{
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
			int filas=0;
			Connection cn = null;
			try
			{
				cn = DriverManager.getConnection(host+dbName, user,pass);
				Statement st = cn.createStatement();
				String query = "Insert into Cuentas(DNI_Cu,TipoCuenta_Cu,CBU_Cu) values ('"+cuenta.getDNI()+"','"+cuenta.getTipo()+"','"+cuenta.getCbu()+"')";
				filas=st.executeUpdate(query);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			return filas;
		}
		
		public ArrayList<Cuentas> obtenerCuentas() {

			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			ArrayList<Cuentas> lista = new ArrayList<Cuentas>();
			Connection conn = null;
			try{
				conn = DriverManager.getConnection(host + dbName, user, pass);
				Statement st = conn.createStatement();
				
				ResultSet rs = st.executeQuery("SELECT DNI_Cu, TipoCuenta_Cu, Ncuenta_Cu, CBU_Cu FROM cuentas");
				
				while(rs.next()){
					
					Cuentas CuentasRs = new Cuentas();
					CuentasRs.setDNI(rs.getString("DNI_Cu"));
					CuentasRs.setTipo(rs.getString("TipoCuenta_Cu"));
					CuentasRs.setNcuenta(rs.getInt("Ncuenta_Cu"));
					CuentasRs.setCbu(rs.getString("CBU_Cu"));
					
					lista.add(CuentasRs);
				}
				conn.close();
			}catch(Exception e){
				e.printStackTrace();
			}finally{
			
			}
			
			return lista;
		}
		
		public int modificarCuenta(Cuentas cuenta)
		{
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
			int filas=0;
			Connection cn = null;
			try
			{
				cn = DriverManager.getConnection(host+dbName, user,pass);
				Statement st = cn.createStatement();
				String query = "update cuentas set TipoCuenta_cu = '"+cuenta.getTipo()+"', CBU_Cu='"+cuenta.getCbu()+"' where Ncuenta_Cu = "+cuenta.getNcuenta() ;
				filas=st.executeUpdate(query);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			return filas;
		}
		
		

	}




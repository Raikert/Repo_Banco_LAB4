package datosImpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import datos.*;



import entidad.Prestamo;

public class PrestamoDaoImpl implements PrestamoDao{
	
	Conexion cn = new Conexion();
	private static final String obtenerPendientes= "SELECT * FROM banco.prestamos WHERE estado_Pr = 'pendiente'";
	private static final String modificarEstado= "CALL Modificar_Estado_Prestamo(?,?)";


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
		
		return b;
	}

	
	
	
}

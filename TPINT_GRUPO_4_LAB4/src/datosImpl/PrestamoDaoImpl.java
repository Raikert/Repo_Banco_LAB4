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
	private static final String agregarPrestamo="CALL Nuevo_Prestamo(?,?,?,?,?,?,?)";

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
			cst.setString(2, Integer.toString(p.getDni()));
			cst.setDate(3, p.getFechaDate());
			cst.setDouble(4, p.getImportePagar());
			cst.setDouble(5, p.getImportePedido());
			cst.setDouble(6, p.getMontoxMes());
			cst.setInt(7, p.getCuotas());
			cst.execute();
			b=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		cn.close();
		return b;
	}

	
	
	
}

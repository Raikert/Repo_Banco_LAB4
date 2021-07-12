package negocioImpl;

import java.util.ArrayList;
import java.util.Calendar;

import entidad.Prestamo;
import datos.*;
import datosImpl.*;
import negocio.*;
public class PrestamoNegImpl implements PrestamoNeg {

	private PrestamoDao prDao= new PrestamoDaoImpl();
	
	
	@Override
	public ArrayList<Prestamo> obtenerPendientes() {
		return (ArrayList<Prestamo>) prDao.obtenerPendientes();
	}


	@Override
	public boolean cambiarEstado(int id, String estado) {
		return prDao.cambiarEstado(id, estado);
	}


	@Override
	public boolean agregarPrestamo(int nCuenta, double importe_pedido, int cuotas, double importe_pagar,
			double montoxmes) {
		Calendar calendar = Calendar.getInstance();
		java.util.Date currentDate = calendar.getTime();
		java.sql.Date date = new java.sql.Date(currentDate.getTime());
		//Eliminar despues lo anterior ya que la fecha ahora es automatica en el sql, modificar el procedimiento
		
		Prestamo p = new Prestamo(0,0,nCuenta);
		p.setImportePedido(importe_pedido);
		p.setCuotas(cuotas);
		p.setImportePagar(importe_pagar);
		p.setMontoxMes(montoxmes);
		p.setFecha(date);
		return prDao.agregarPrestamo(p);
	}

	
	
	
}

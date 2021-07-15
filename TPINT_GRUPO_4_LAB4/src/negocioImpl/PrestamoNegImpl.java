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
		
		Prestamo p = new Prestamo(0,0,nCuenta);
		p.setImportePedido(importe_pedido);
		p.setCuotas(cuotas);
		p.setImportePagar(importe_pagar);
		p.setMontoxMes(montoxmes);
		return prDao.agregarPrestamo(p);
	}


	@Override
	public int Cant_Prestamos(String ini, String fin, double monmin, double monmax, int cuotas) {
		return prDao.Cant_Prestamos(ini, fin, monmin, monmax, cuotas);
	}


	@Override
	public int Cant_Pagos(String ini, String fin, double monmin, double monmax, int cuotas) {
		return prDao.Cant_Pagos(ini, fin, monmin, monmax, cuotas);
	}


	@Override
	public int Cant_Pendientes(String ini, String fin, double monmin, double monmax, int cuotas) {
		return prDao.Cant_Pendientes(ini, fin, monmin, monmax, cuotas);
	}


	@Override
	public int Cant_Rechazados(String ini, String fin, double monmin, double monmax, int cuotas) {
		return prDao.Cant_Rechazados(ini, fin, monmin, monmax, cuotas);
	}


	@Override
	public int Cant_Activos(String ini, String fin, double monmin, double monmax, int cuotas) {
		return prDao.Cant_Activos(ini, fin, monmin, monmax, cuotas);
	}


	@Override
	public int Promedio(String ini, String fin, double monmin, double monmax, int cuotas) {
		return prDao.Promedio(ini, fin, monmin, monmax, cuotas);
	}

	@Override
	public ArrayList<Prestamo> obtenerPrestamosCli(String DNI, String Cuenta) 
	{
		return prDao.obtenerPrestamosCli(DNI, Cuenta);
	}
}

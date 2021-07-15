package negocio;

import java.util.ArrayList;

import entidad.Prestamo;

public interface PrestamoNeg {
	
	public ArrayList<Prestamo> obtenerPendientes();
	public ArrayList<Prestamo> obtenerPrestamosCli(String DNI, String Cuenta);
	public boolean cambiarEstado(int id, String estado);
	public boolean agregarPrestamo(int nCuenta, double importe_pedido, int cuotas, double importe_pagar, double montoxmes);
	
	public int Cant_Prestamos(String ini, String fin, double monmin, double monmax, int cuotas);
	public int Cant_Pagos(String ini, String fin, double monmin, double monmax, int cuotas);
	public int Cant_Pendientes(String ini, String fin, double monmin, double monmax, int cuotas);
	public int Cant_Rechazados(String ini, String fin, double monmin, double monmax, int cuotas);
	public int Cant_Activos(String ini, String fin, double monmin, double monmax, int cuotas);
	public int Promedio(String ini, String fin, double monmin, double monmax, int cuotas);
}

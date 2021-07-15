package datos;

import java.util.ArrayList;
import java.util.List;

import entidad.Prestamo;

public interface PrestamoDao {

	public List<Prestamo> obtenerPendientes();
	public ArrayList<Prestamo> obtenerPrestamosCli(String DNI, String Cuenta);
	public boolean cambiarEstado(int id, String estado);
	public boolean agregarPrestamo(Prestamo p);
	
	public int Cant_Prestamos(String ini, String fin, double monmin, double monmax, int cuotas);
	public int Cant_Pagos(String ini, String fin, double monmin, double monmax, int cuotas);
	public int Cant_Pendientes(String ini, String fin, double monmin, double monmax, int cuotas);
	public int Cant_Rechazados(String ini, String fin, double monmin, double monmax, int cuotas);
	public int Cant_Activos(String ini, String fin, double monmin, double monmax, int cuotas);
	public int Promedio(String ini, String fin, double monmin, double monmax, int cuotas);
	
}

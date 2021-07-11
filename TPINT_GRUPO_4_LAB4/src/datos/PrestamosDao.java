package datos;

public interface PrestamosDao {
	public int Cant_Prestamos(String ini, String fin, double monmin, double monmax, int cuotas);
	public int Cant_Pagos(String ini, String fin, double monmin, double monmax, int cuotas);
	public int Cant_Pendientes(String ini, String fin, double monmin, double monmax, int cuotas);
	public int Cant_Rechazados(String ini, String fin, double monmin, double monmax, int cuotas);
	public int Cant_Activos(String ini, String fin, double monmin, double monmax, int cuotas);
	public int Promedio(String ini, String fin, double monmin, double monmax, int cuotas);
}

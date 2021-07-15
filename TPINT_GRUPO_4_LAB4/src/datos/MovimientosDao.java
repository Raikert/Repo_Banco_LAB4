package datos;

import java.util.ArrayList;
import entidad.*;

public interface MovimientosDao {
	
	public ArrayList<Movimientos> Obtener_Tabla();
	public double Egresos(String ini, String fin);
	public double Ingresos(String ini, String fin);
	public ArrayList<String> HistorialGeneral();
	public ArrayList<Integer> CuentasHG(String opcion);
	public ArrayList<Movimientos> BMovimientos(String ini, String fin,String tipo,double min,double max,int cant,String cue);
}

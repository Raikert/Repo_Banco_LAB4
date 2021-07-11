package datos;

import java.util.ArrayList;
import entidad.*;

public interface MovimientosDao {
	
	public ArrayList<Movimientos> Obtener_Tabla();
	public double Egresos(String ini, String fin);
	public double Ingresos(String ini, String fin);
}

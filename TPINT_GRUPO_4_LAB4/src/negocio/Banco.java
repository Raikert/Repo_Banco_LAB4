package negocio;

import java.util.ArrayList;
import entidad.*;

public interface Banco {

	public void refrescar_tabla();
	public ArrayList<Movimientos> Obtener_tabla();

}

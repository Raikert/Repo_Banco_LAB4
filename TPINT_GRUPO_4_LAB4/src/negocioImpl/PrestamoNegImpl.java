package negocioImpl;

import java.util.ArrayList;





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

	
	
	
}

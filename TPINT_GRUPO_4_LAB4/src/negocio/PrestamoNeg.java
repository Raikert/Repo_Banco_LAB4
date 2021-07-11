package negocio;

import java.util.ArrayList;

import entidad.Prestamo;

public interface PrestamoNeg {
	
	public ArrayList<Prestamo> obtenerPendientes();
	public boolean cambiarEstado(int id, String estado);
}

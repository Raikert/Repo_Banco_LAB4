package datos;


import java.util.List;

import entidad.Prestamo;

public interface PrestamoDao {

	public List<Prestamo> obtenerPendientes();
	public boolean cambiarEstado(int id, String estado);
	public boolean agregarPrestamo(Prestamo p);
	
}

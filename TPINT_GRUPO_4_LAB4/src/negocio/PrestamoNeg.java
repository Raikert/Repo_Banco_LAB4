package negocio;

import java.util.ArrayList;

import entidad.Prestamo;

public interface PrestamoNeg {
	
	public ArrayList<Prestamo> obtenerPendientes();
	public boolean cambiarEstado(int id, String estado);
	public boolean agregarPrestamo(int nCuenta, double importe_pedido, int cuotas, double importe_pagar, double montoxmes);
}

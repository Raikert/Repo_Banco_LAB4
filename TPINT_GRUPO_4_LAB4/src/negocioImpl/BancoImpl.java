package negocioImpl;

import java.util.ArrayList;
import datos.*;
import entidad.*;
import datosImpl.*;
import negocio.*;
import javax.swing.DefaultListModel;


public class BancoImpl implements Banco{
	
	MovimientosDao mdao = new MovimientosDaoImpl();
	private ArrayList<Movimientos> alMovimientos;
	private DefaultListModel<Movimientos> dlmMovimientos;

	public BancoImpl(){
		refrescar_tabla();
	}

	@Override
	public void refrescar_tabla() {
		this.alMovimientos = mdao.Obtener_Tabla();
		
	}

	@Override
	public ArrayList<Movimientos> Obtener_tabla() {
		// TODO Auto-generated method stub
		return null;
	}
}

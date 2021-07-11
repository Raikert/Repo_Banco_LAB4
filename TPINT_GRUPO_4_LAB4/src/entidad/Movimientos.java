package entidad;

import java.sql.Date;

public class Movimientos {

	private Date Fecha;
	private String Detalle;
	private double Importe;
	private String Tipo_Mov;
	private String Origen;
	private String Destino;
	
	public Movimientos()
	{
		
	}

	public Movimientos(Date fecha, String detalle, double importe, String tipo_Mov, String origen, String destino) {
		super();
		Fecha = fecha;
		Detalle = detalle;
		Importe = importe;
		Tipo_Mov = tipo_Mov;
		Origen = origen;
		Destino = destino;
	}

	public Date getFecha() {
		return Fecha;
	}

	public void setFecha(Date fecha) {
		Fecha = fecha;
	}

	public String getDetalle() {
		return Detalle;
	}

	public void setDetalle(String detalle) {
		Detalle = detalle;
	}

	public double getImporte() {
		return Importe;
	}

	public void setImporte(double importe) {
		Importe = importe;
	}

	public String getTipo_Mov() {
		return Tipo_Mov;
	}

	public void setTipo_Mov(String tipo_Mov) {
		Tipo_Mov = tipo_Mov;
	}

	public String getOrigen() {
		return Origen;
	}

	public void setOrigen(String origen) {
		Origen = origen;
	}

	public String getDestino() {
		return Destino;
	}

	public void setDestino(String destino) {
		Destino = destino;
	}

	@Override
	public String toString() {
		return "Movimientos [Fecha=" + Fecha + ", Detalle=" + Detalle + ", Importe=" + Importe + ", Tipo_Mov="
				+ Tipo_Mov + ", Origen=" + Origen + ", Destino=" + Destino + "]";
	}

	
	
	
}

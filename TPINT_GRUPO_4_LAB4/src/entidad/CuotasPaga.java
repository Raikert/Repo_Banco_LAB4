package entidad;

import java.sql.Date;

public class CuotasPaga {

	private final int idPrestamo;
	private Date fecha;
	private int cuotaPagada;
	private int cuotasTotales;
	
	public CuotasPaga(int idPrestamo, Date fecha, int cuotaPagada, int cuotasTotales) {
		this.idPrestamo = idPrestamo;
		this.fecha = fecha;
		this.cuotaPagada = cuotaPagada;
		this.cuotasTotales = cuotasTotales;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public int getCuotaPagada() {
		return cuotaPagada;
	}

	public void setCuotaPagada(int cuotaPagada) {
		this.cuotaPagada = cuotaPagada;
	}

	public int getCuotasTotales() {
		return cuotasTotales;
	}

	public void setCuotasTotales(int cuotasTotales) {
		this.cuotasTotales = cuotasTotales;
	}

	public int getIdPrestamo() {
		return idPrestamo;
	}
	
	
	
}

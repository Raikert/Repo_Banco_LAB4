package entidad;

import java.sql.Date;
import java.text.SimpleDateFormat;



public class Prestamo {

	private final int id;
	private final int dni;
	private final int cuenta;
	
	
	private double importePedido;
	private double importePagar;
	private double montoxMes;

	
	private int cuotas;
	private int cuotasPagadas;
	
	
	private Date plazo;
	private Date fecha;
	private String estado;
	
	public Prestamo() {
		this.id = 0;
		this.dni = 0;
		this.cuenta = 0;
	}
	
	public Prestamo(int id, int dni, int cuenta) {
		this.id = id;
		this.dni = dni;
		this.cuenta = cuenta;
	}
	
	public Prestamo(int id, int dni, int cuenta, double importePedido, double importePagar, int cuotas, Date fecha, String estado) {
		this.id = id;
		this.dni = dni;
		this.cuenta = cuenta;
		this.importePedido = importePedido;
		this.importePagar = importePagar;
		this.cuotas = cuotas;
		this.fecha = fecha;
		this.estado = estado;
	}

	public double getImportePedido() {
		return importePedido;
	}

	public void setImportePedido(double importePedido) {
		this.importePedido = importePedido;
	}

	public double getImportePagar() {
		return importePagar;
	}

	public void setImportePagar(double importePagar) {
		this.importePagar = importePagar;
	}

	public double getMontoxMes() {
		return montoxMes;
	}

	public void setMontoxMes(double montoxMes) {
		this.montoxMes = montoxMes;
	}

	public int getCuotas() {
		return cuotas;
	}

	public void setCuotas(int cuotas) {
		this.cuotas = cuotas;
	}

	public int getCuotasPagadas() {
		return cuotasPagadas;
	}

	public void setCuotasPagadas(int cuotasPagadas) {
		this.cuotasPagadas = cuotasPagadas;
	}

	public Date getPlazo() {
		return plazo;
	}

	public void setPlazo(Date plazo) {
		this.plazo = plazo;
	}

	public String getFecha() {
		return new SimpleDateFormat("dd/MM/yyyy").format(this.fecha);
	}
	
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Date getFechaDate() {
		return fecha;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public int getId() {
		return id;
	}

	public int getDni() {
		return dni;
	}

	public int getCuenta() {
		return cuenta;
	}

	@Override
	public String toString() {
		return id + " " + fecha.toString() + " " + cuenta + " " + importePedido
				+ " " + importePagar + " " + montoxMes + " " + cuotas;
	}
	
	
}

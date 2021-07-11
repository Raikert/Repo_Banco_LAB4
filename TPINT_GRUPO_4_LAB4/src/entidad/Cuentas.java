package entidad;

public class Cuentas {
	
	
	private String DNI;
	private String fecha;
	private String tipo;
	private int Ncuenta;
	private String cbu;
	private float Saldo;
	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getNcuenta() {
		return Ncuenta;
	}

	public void setNcuenta(int ncuenta) {
		Ncuenta = ncuenta;
	}

	public String getCbu() {
		return cbu;
	}

	public void setCbu(String cbu) {
		this.cbu = cbu;
	}

	public float getSaldo() {
		return Saldo;
	}

	public void setSaldo(float saldo) {
		Saldo = saldo;
	}

	
	public Cuentas() {
		
	}
	
	public Cuentas(String dni,String Tipo, String Cbu) {
		this.setDNI(dni);
		this.tipo=Tipo;
		this.cbu=Cbu;
		
	}

	public String getDNI() {
		return DNI;
	}

	public void setDNI(String dNI) {
		DNI = dNI;
	}
	
	
	
	

}

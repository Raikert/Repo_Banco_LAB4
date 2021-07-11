package datos;

public interface CuentasDao {
	
	public double Cant_Cuentas(String ini, String fin, double salmin, double salmax, String tipo);
	public double Saldo_Prom(String ini, String fin, double salmin, double salmax, String tipo);

}

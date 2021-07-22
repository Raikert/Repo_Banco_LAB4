package datos;

import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import entidad.Cliente;
import entidad.Movimientos;

public interface ClienteDao 
{
	public Cliente obtenerUsuarioContraseña(String usuario,String contraseña);
	public ResultSet obtenerCuentas(String dni);
	public ArrayList<Movimientos> obtenerMovimientosCuenta(String cuenta);
	public String obtenerSaldoCuenta(String cuenta);
	public String comprobarSaldo(String cuenta);
	public String comprobarCuenta(String cuenta);
	public boolean grabarMovimiento(Movimientos mov);
	public boolean actualizarCuentas(Float saldo,String cuenta);
	public boolean actualizarCuentas2(Float saldo,String cuenta);
	
	public void agregarCliente (Cliente cliente);
	public void cargar_Cliente(Cliente cliente, ResultSet rs);
	public Cliente Buscar_DNI (Cliente cliente);
	public boolean buscar_Coincidencia(String campo , String valor);
	public ArrayList<Cliente> buscar_Clientes(ArrayList<Cliente> clientes, HttpServletRequest request);
	public void modificarCliente(Cliente cliente);
}

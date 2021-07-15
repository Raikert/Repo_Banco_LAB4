package negocio;

import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import entidad.Cliente;

public interface ClienteNeg {

	public Cliente obtenerUsuarioContraseña(String usuario,String contraseña);
	public ResultSet obtenerCuentas(String dni);
	
	public Cliente cargarCliente(Cliente cliente, HttpServletRequest request);
	public void agregarCliente (Cliente cliente);
	public Cliente Buscar_DNI (Cliente cliente);
	public boolean buscar_Coincidencia(String campo , String valor);
	public ArrayList<Cliente> buscar_Clientes(ArrayList<Cliente> clientes, HttpServletRequest request);
	public void modificarCliente(Cliente cliente);
}

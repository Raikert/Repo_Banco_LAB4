package datos;

import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import entidad.Cliente;

public interface ClienteDao 
{
	public Cliente obtenerUsuarioContraseña(String usuario,String contraseña);
	public void agregarCliente (Cliente cliente);
	public void cargar_Cliente(Cliente cliente, ResultSet rs);
	public Cliente Buscar_DNI (Cliente cliente);
	public boolean buscar_Coincidencia(String campo , String valor);
	public ArrayList<Cliente> buscar_Clientes(ArrayList<Cliente> clientes, HttpServletRequest request);
	public void modificarCliente(Cliente cliente);
}

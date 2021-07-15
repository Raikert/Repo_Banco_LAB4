package negocioImpl;

import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import datosImpl.ClienteDaoImpl;
import entidad.Cliente;
import negocio.ClienteNeg;

public class ClienteNegImpl implements ClienteNeg
{
	public ClienteDaoImpl cdao = new ClienteDaoImpl();
			
	@Override
	public ResultSet obtenerCuentas(String dni) {
		
		return cdao.obtenerCuentas(dni);
	}
	
	
	@Override
	public Cliente obtenerUsuarioContraseña(String usuario, String contraseña) {
		
		return cdao.obtenerUsuarioContraseña(usuario, contraseña);
	}
	
	@Override
	public Cliente Buscar_DNI(Cliente cliente)
	{
		return cdao.Buscar_DNI(cliente);
	}

	@Override
	public boolean buscar_Coincidencia(String campo, String valor) {

		return cdao.buscar_Coincidencia(campo, valor);
	}
	
	@Override
	public Cliente cargarCliente(Cliente cliente, HttpServletRequest request)
	{
		cliente.setDni(request.getParameter("TxtDni"));
		cliente.setCuil(request.getParameter("TxtCuil"));
		cliente.setNombre(request.getParameter("TxtNombre"));
		cliente.setApellido(request.getParameter("TxtApellido"));
		cliente.setSexo(request.getParameter("Sexo"));
		cliente.setNacionalidad(request.getParameter("Nacionalidad"));
		cliente.setFec_nacimiento(request.getParameter("TxtFecnac"));
		cliente.setDireccion(request.getParameter("TxtDom"));
		cliente.setLocalidad(request.getParameter("TxtLoc"));
		cliente.setProvincia(request.getParameter("Provincia"));
		cliente.setCorreo_electronico(request.getParameter("TxtEmail"));
		cliente.setTelefono(request.getParameter("TxtTel"));
		cliente.setUsuario(request.getParameter("TxtUsu"));
		cliente.setContraseña(request.getParameter("TxtPass"));
		
		if(request.getParameter("Estado") != null)
		{
			if(request.getParameter("Estado").equals("Activo"))
				cliente.setEstado("1");
			else
				cliente.setEstado("0");
		}
		else
			cliente.setEstado("1");
		
		return cliente;
	}

	@Override
	public void agregarCliente(Cliente cliente)
	{
		cdao.agregarCliente(cliente);
	}

	@Override
	public void modificarCliente(Cliente cliente)
	{
		cdao.modificarCliente(cliente);
	}

	@Override
	public ArrayList<Cliente> buscar_Clientes(ArrayList<Cliente> clientes, HttpServletRequest request)
	{
		return cdao.buscar_Clientes(clientes, request);
	}
}

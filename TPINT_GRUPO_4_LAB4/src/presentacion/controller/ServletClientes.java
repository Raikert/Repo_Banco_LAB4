package presentacion.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Cliente;
import negocioImpl.ClienteNegImpl;
import negocio.ClienteNeg;

@WebServlet("/ServletClientes")
public class ServletClientes extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
	ClienteNeg cliNeg = new ClienteNegImpl();
  
    public ServletClientes()
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		if (request.getParameter("inicio")!=null)
		{
			RequestDispatcher dispatcher = request.getRequestDispatcher("Home.jsp");
			dispatcher.forward(request, response);
		}
		if (request.getParameter("BtHistorial")!=null)
		{
			RequestDispatcher dispatcher = request.getRequestDispatcher("Historial.jsp");
			dispatcher.forward(request, response);
		}
		if (request.getParameter("btTransferencias")!=null)
		{
			RequestDispatcher dispatcher = request.getRequestDispatcher("Transferencias.jsp");
			dispatcher.forward(request, response);
		}
		if (request.getParameter("btSolicitarPrestamo")!=null)
		{
			RequestDispatcher dispatcher = request.getRequestDispatcher("GestorPrestamos.jsp");
			dispatcher.forward(request, response);
		}
		if (request.getParameter("btPrestamosPedidos")!=null)
		{
			RequestDispatcher dispatcher = request.getRequestDispatcher("PrestamoPedido.jsp");
			dispatcher.forward(request, response);
		}
		if (request.getParameter("btPagarCuotas")!=null)
		{
			RequestDispatcher dispatcher = request.getRequestDispatcher("PagarCuota.jsp");
			dispatcher.forward(request, response);
		}
		
		if(request.getParameter("generar") != null)
		{
			Cliente cliente = new Cliente();
			
			cliente.setDni(request.getParameter("TxtDni"));
			cliNeg.Buscar_DNI(cliente);
			
			if(cliente.getCuil() != null)
			{
				request.setAttribute("Cliente2", cliente);
			}
			else 
			{
				String Mensaje = "0";
				request.setAttribute("Mensaje_Modificar", Mensaje);
			}
			
			RequestDispatcher rd = request.getRequestDispatcher("/AdminCliente.jsp");
			rd.forward(request, response);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		if (request.getParameter("BtLogin")!=null)
		{
			if (request.getParameter("user").equals("admin") && request.getParameter("pass").equals("admin")) 
			{
		    	RequestDispatcher dispatcher = request.getRequestDispatcher("Home.jsp");
				dispatcher.forward(request, response);
			}
			else 
			{
				try 
				{
					Cliente x = new Cliente();
					Cliente y = new Cliente();
					x.setUsuario(request.getParameter("user"));
					x.setContraseña(request.getParameter("pass"));
					y = cliNeg.obtenerUsuarioContraseña(x.getUsuario(), x.getContraseña());
					
					if (y.getUsuario().equals(request.getParameter("user")) && y.getContraseña().equals(request.getParameter("pass"))) 
					{
						RequestDispatcher dispatcher = request.getRequestDispatcher("Index.jsp");
						dispatcher.forward(request, response);
					}
				} 
				catch (Exception e)
				{
					RequestDispatcher dispatcher = request.getRequestDispatcher("Erroracceso.jsp");
					dispatcher.forward(request, response);
				}
			}
		}
		
		if(request.getParameter("crear") != null)
		{
			Cliente cliente = new Cliente();
			cliNeg.cargarCliente(cliente, request);
			boolean [] error_count = {true, false, false, false, false};
			
			if(cliNeg.buscar_Coincidencia("Dni_Cli" , request.getParameter("TxtDni").toString()))
			{
				error_count[0] = false;
				error_count[1] = true;
				cliente.setDni("");
			}
			
			if(cliNeg.buscar_Coincidencia("Cuil_Cli" , request.getParameter("TxtCuil").toString()))
			{
				error_count[0] = false;
				error_count[2] = true;
				cliente.setCuil("");
			}
			
			if(cliNeg.buscar_Coincidencia("Usuario_Cli" , request.getParameter("TxtUsu").toString()))
			{
				error_count[0] = false;
				error_count[3] = true;
				cliente.setUsuario("");
			}
			
			if(!request.getParameter("TxtPass").equals(request.getParameter("TxtPass2")))
			{
				error_count[0] = false;
				error_count[4] = true;
				cliente.setContraseña("");
			}
			
			if(error_count[0])
			{
				cliNeg.agregarCliente(cliente);
				
				request.setAttribute("Validaciones", error_count);
			}
			else 
			{
				request.setAttribute("Cliente1", cliente);
				request.setAttribute("Validaciones", error_count);
			}
			
			RequestDispatcher rd = request.getRequestDispatcher("/AdminCliente.jsp");
			rd.forward(request, response);
		}
		
		if(request.getParameter("Modificar") != null)
		{
			Cliente cliente = new Cliente();
			cliNeg.cargarCliente(cliente, request);
			
			boolean [] error_count = {true, false, false, false, false};
			
			if(cliNeg.buscar_Coincidencia("Cuil_Cli" , request.getParameter("TxtCuil").toString()))
			{
				error_count[0] = false;
				error_count[2] = true;
				cliente.setCuil("");
			}
			
			if(!request.getParameter("TxtPass").equals(request.getParameter("TxtPass2")))
			{
				error_count[0] = false;
				error_count[4] = true;
				cliente.setContraseña("");
			}
			
			
			if(error_count[0])
			{
				cliNeg.modificarCliente(cliente);
				
				String Mensaje = "1";
				request.setAttribute("Mensaje_Modificar", Mensaje);
				
				request.setAttribute("Validaciones", error_count);
			}
			else 
			{
				request.setAttribute("Cliente2", cliente);
				request.setAttribute("Validaciones", error_count);
			}
			
			RequestDispatcher rd = request.getRequestDispatcher("/AdminCliente.jsp");
			rd.forward(request, response);
		}
		
		if(request.getParameter("enviar") != null) 
		{
			ArrayList<Cliente> clientes = new ArrayList<Cliente>();
			
			cliNeg.buscar_Clientes(clientes, request);
			
			if(!clientes.isEmpty()) 
			{
				request.setAttribute("Array_Clientes", clientes);
				request.setAttribute("Filas", String.valueOf(clientes.size()));
			}
			else
			{
				request.setAttribute("Filas", "0");
			}
			
			RequestDispatcher rd = request.getRequestDispatcher("/HomeAdmin.jsp");
			rd.forward(request, response);
		}
	}
}

package presentacion.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import entidad.Cliente;
import negocioImpl.ClienteNegImpl;
import negocio.ClienteNeg;

import entidad.*;
import datosImpl.*;
import datos.*;


@WebServlet("/ServletClientes")
public class ServletClientes extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	ClienteNeg cliNeg = new ClienteNegImpl();
  
    public ServletClientes() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		
		
		
		
		
		if(request.getParameter("generar") != null)
		{
			Clientes cliente = new Clientes();
			ClientesDao clienteDao = new ClientesImpl();
			
			cliente.setDni(request.getParameter("TxtDni"));
			cliente = clienteDao.Buscar_DNI(cliente);
			
			if(cliente.getCuil() != "") 
			{
				request.setAttribute("Cliente_DNI", cliente);
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


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		if (request.getParameter("BtLogin")!=null)
		{
			if (request.getParameter("user").equals("admin") && request.getParameter("pass").equals("admin")) {
				
		    	RequestDispatcher dispatcher = request.getRequestDispatcher("HomeAdmin.jsp");
				dispatcher.forward(request, response);
				
			}
			else {
				try {
					
					Cliente x = new Cliente();
					Cliente y = new Cliente();
					x.setUsuario(request.getParameter("user"));
					x.setContraseña(request.getParameter("pass"));
					y = cliNeg.obtenerUsuarioContraseña(x.getUsuario(), x.getContraseña());
					
					if (y.getUsuario().equals(request.getParameter("user")) && y.getContraseña().equals(request.getParameter("pass"))) {
						RequestDispatcher dispatcher = request.getRequestDispatcher("Home.jsp");
						dispatcher.forward(request, response);
					}
					
					
				} catch (Exception e) {
					RequestDispatcher dispatcher = request.getRequestDispatcher("Erroracceso.jsp");
					dispatcher.forward(request, response);
				}

			}
	
			
			
		}
		
		
		if(request.getParameter("crear") != null)
		{
			Clientes cliente = new Clientes();
			ClientesDao clienteDao = new ClientesImpl();
			clienteDao.cargarCliente(cliente, request);
			clienteDao.agregarCliente(cliente);
			
			RequestDispatcher rd = request.getRequestDispatcher("/AdminCliente.jsp");
			rd.forward(request, response);
		}
		
		if(request.getParameter("Modificar") != null)
		{
			Clientes cliente = new Clientes();
			ClientesDao clienteDao = new ClientesImpl();
			clienteDao.cargarCliente(cliente, request);
			clienteDao.modificarCliente(cliente);
			
			String Mensaje = "1";
			request.setAttribute("Mensaje_Modificar", Mensaje);
			
			RequestDispatcher rd = request.getRequestDispatcher("/AdminCliente.jsp");
			rd.forward(request, response);
		}
	 
		
	}

}

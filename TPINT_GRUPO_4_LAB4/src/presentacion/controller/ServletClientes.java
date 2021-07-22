package presentacion.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Cliente;
import entidad.Movimientos;
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
		
		
		if (request.getParameter("BTcargar")!=null) {
			
			if (request.getParameter("ddlCuentas")!="seleccione una cuenta") {
				
				String saldo = cliNeg.obtenerSaldoCuenta(request.getParameter("ddlCuentas"));
				request.getSession().setAttribute("cuentaElegida", request.getParameter("ddlCuentas"));
				request.getSession().setAttribute("saldosaldo", saldo);
		    	RequestDispatcher dispatcher = request.getRequestDispatcher("Transferencias.jsp");
				dispatcher.forward(request, response);
			}
		}
		
		
		if (request.getParameter("BTenviar")!=null) {
			
			if(request.getParameter("ddlCuentas")!="seleccione una cuenta") {
				
				try {
					String cuenta = request.getParameter("TxtDestino");
					float monto = Float.parseFloat(request.getParameter("TxtMonto"));
					String cuenta2 = cliNeg.comprobarCuenta(cuenta);
					float monto2 = Float.parseFloat(cliNeg.comprobarSaldo(request.getSession().getAttribute("cuentaElegida").toString()));

					
					if (cuenta.equals(cuenta2) && Float.compare(monto,monto2)<=0) {
						
						
						
						Movimientos mov = new Movimientos();
						
						mov.setFecha(Date.valueOf("2021-07-22"));
						mov.setDetalle("transferencia");
						mov.setImporte(monto);
						mov.setTipo_Mov("transferencia");
						mov.setOrigen(request.getSession().getAttribute("cuentaElegida").toString());
						mov.setDestino(cuenta);
						
						cliNeg.grabarMovimiento(mov);
						cliNeg.actualizarCuentas(monto, cuenta);
						cliNeg.actualizarCuentas2(monto, request.getSession().getAttribute("cuentaElegida").toString());
						
						
						
					}
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}

				RequestDispatcher dispatcher = request.getRequestDispatcher("Transferencias.jsp");
				dispatcher.forward(request, response);
				
			}
		}
		
		if (request.getParameter("BTgenerar")!=null) {
			
			if(request.getParameter("ddlCuentas")!="seleccione una cuenta") {
				
				
				
				ArrayList<Movimientos> lista  = cliNeg.obtenerMovimientosCuenta(request.getParameter("ddlCuentas"));
				String saldo = cliNeg.obtenerSaldoCuenta(request.getParameter("ddlCuentas"));
				
				request.setAttribute("movimientosmovimientos", lista);
				request.setAttribute("saldosaldo", saldo);
				
		    	RequestDispatcher dispatcher = request.getRequestDispatcher("Home.jsp");
				dispatcher.forward(request, response);
				
			}
			
		}
		
		
		if (request.getParameter("BtLogin")!=null)
		{
			if (request.getParameter("user").equals("admin") && request.getParameter("pass").equals("admin")) 
			{
		    	RequestDispatcher dispatcher = request.getRequestDispatcher("HomeAdmin.jsp");
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
						request.getSession().setAttribute("useruser", y.getNombre() +" " + y.getApellido());
						request.getSession().setAttribute("dnidni",y.getDni());
						RequestDispatcher dispatcher = request.getRequestDispatcher("Home.jsp");
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
			
			if(!cliente.getCuil().equals(request.getParameter("TxtCuil")))
			{
				if(cliNeg.buscar_Coincidencia("Cuil_Cli" , request.getParameter("TxtCuil").toString()))
				{	
					error_count[0] = false;
					error_count[2] = true;
					cliente.setCuil("");
				}
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

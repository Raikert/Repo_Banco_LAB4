package presentacion.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.*;
import datosImpl.*;
import entidad.Cliente;
import entidad.Prestamo;
import negocio.PrestamoNeg;
import negocioImpl.PrestamoNegImpl;


/**
 * Servlet implementation class ServletPrestamos
 */
@WebServlet("/ServletPrestamos")
public class ServletPrestamos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private PrestamoNeg prestamo = new PrestamoNegImpl();
	ArrayList<Prestamo> prestamosPendientes = new ArrayList<Prestamo>();
	
	
    public ServletPrestamos() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("btnModificar")!=null) {
			int id;
			if(request.getParameter("txtPrestamo")== "") {
				id = 0;
			} else {
				id = Integer.parseInt(request.getParameter("txtPrestamo"));
			}
			String estado = request.getParameter("estado");
			boolean exito=false;
			prestamosPendientes = prestamo.obtenerPendientes();
			for (Prestamo pr : prestamosPendientes) {
				if (pr.getId() == id) {
					exito = prestamo.cambiarEstado(id, estado);
				}
			}
			if(exito) {
				request.setAttribute("label", "El prestamo fue modificado con exito!");
			} else {
				request.setAttribute("label", "Ingrese un ID de un prestamo pendiente existente");
			}
			RequestDispatcher rd = request.getRequestDispatcher("/GestorPrestamos.jsp");
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Integer> prestamos = new ArrayList<Integer>();
		if(request.getParameter("Cargar3")!=null)
		{
			String ini;
			String fin;
			double mmin;
			double mmax;
			int cuotas;
			if(request.getParameter("TxtFecini3")!="")
			{
				ini=request.getParameter("TxtFecini3");
			}else
			{
				ini="1900-01-01";
			}
			if(request.getParameter("TxtFecfin3")!="")
			{
				fin=request.getParameter("TxtFecfin3");
			}else
			{
				fin="2100-01-01";
			}
			if(request.getParameter("TxtMtmin")!="")
			{
				mmin=Integer.parseInt(request.getParameter("TxtMtmin"));
			}else
			{
				mmin=0;
			}
			if(request.getParameter("TxtMtmax")!="")
			{
				mmax=Integer.parseInt(request.getParameter("TxtMtmax"));
			}else
			{
				mmax=999999999;
			}
			cuotas=Integer.parseInt(request.getParameter("cuotas"));
			
			prestamos.add(prestamo.Cant_Prestamos(ini, fin, mmin, mmax, cuotas));
			prestamos.add(prestamo.Cant_Activos(ini, fin, mmin, mmax, cuotas));
			prestamos.add(prestamo.Cant_Pendientes(ini, fin, mmin, mmax, cuotas));
			prestamos.add(prestamo.Cant_Rechazados(ini, fin, mmin, mmax, cuotas));
			prestamos.add(prestamo.Cant_Pagos(ini, fin, mmin, mmax, cuotas));
			prestamos.add(prestamo.Promedio(ini, fin, mmin, mmax, cuotas));
			request.setAttribute("prestamos", prestamos);
			RequestDispatcher rd = request.getRequestDispatcher("Estadisticas.jsp");
			rd.forward(request, response);
		}
		
		if(request.getParameter("btnCalcular")!=null){
			Prestamo p = new Prestamo();
			int cuotas = 0, nCuenta = 0;
			double importe_pedido = 0, importe_pagar = 0, montoxmes = 0;
			Boolean f1 = false,f2 = false;
			if(request.getParameter("cuenta")!="") {
				nCuenta = Integer.parseInt(request.getParameter("cuenta"));
			}
			if(request.getParameter("cuotas")!=null && request.getParameter("cuotas")!="") {
				cuotas = Integer.parseInt(request.getParameter("cuotas"));
				f1 = true;
			}
			if(request.getParameter("txtMonto")!=null && request.getParameter("txtMonto")!="") {
				importe_pedido = Double.parseDouble(request.getParameter("txtMonto"));
				f2 = true;
			}
			importe_pagar = importe_pedido * 1.30; 
			montoxmes = importe_pagar/cuotas;
			if(f1 && f2) {
				Prestamo pres = new Prestamo(0,0,nCuenta);
				pres.setImportePedido(importe_pedido);
				pres.setImportePagar(importe_pagar);
				pres.setMontoxMes(montoxmes);
				pres.setCuotas(cuotas);
			request.setAttribute("prestamo", pres);
			}
			RequestDispatcher rd = request.getRequestDispatcher("Prestamos.jsp");
			rd.forward(request, response);
		}
		
		if(request.getParameter("btnEnviar")!=null) {
			int cuotas= 0, nCuenta = 0;
			double importe_pedido = 0, importe_pagar = 0, montoxmes = 0;
			Boolean f1 = false,f2 = false,f3 = false;
			String mensaje;
			if(request.getParameter("cuenta")!=null && request.getParameter("cuenta")!="" && request.getParameter("cuenta")!= "seleccione una cuenta") {
				nCuenta = Integer.parseInt(request.getParameter("cuenta"));
				f1 = true;
			}
			if(request.getParameter("cuotas")!=null && request.getParameter("cuotas")!="") {
				cuotas = Integer.parseInt(request.getParameter("cuotas"));
				f2 = true;
			}
			if(request.getParameter("txtMonto")!=null && request.getParameter("txtMonto")!="") {
				importe_pedido = Double.parseDouble(request.getParameter("txtMonto"));
				f3 = true;
			}
			importe_pagar = importe_pedido * 1.30;
			montoxmes = importe_pagar/cuotas;
			if(importe_pedido>=0 && f1 && f2 && f3) {
			if (prestamo.agregarPrestamo(nCuenta, importe_pedido, cuotas, importe_pagar, montoxmes)) {
				mensaje = "La solicitud del prestamo se realizo con exito!";
			} else {
				mensaje = "Error al solicitar el prestamo";
			}
			} else {
				mensaje = "Error al solicitar el prestamo";
			}
			request.setAttribute("mensaje", mensaje);
			RequestDispatcher rd = request.getRequestDispatcher("Prestamos.jsp");
			rd.forward(request, response);
		}
		
		if(request.getParameter("generar") != null)
		{
			ArrayList<Prestamo> prestamos_cliente = new ArrayList<Prestamo>();
			prestamos_cliente = prestamo.obtenerPrestamosCli(request.getParameter("DNI_Cliente"), request.getParameter("cuenta"));
			
			if(!prestamos_cliente.isEmpty()) 
			{
				request.setAttribute("historial_prestamos", prestamos_cliente);
			}
				
			
			RequestDispatcher rd = request.getRequestDispatcher("/PrestamoPedido.jsp");
			rd.forward(request, response);
		}
	}

}

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
			for (Prestamo p : prestamosPendientes) {
				if (id==p.getId()) {
					exito = prestamo.cambiarEstado(id, estado);
					
				}
			}
			if(exito) {
				request.setAttribute("label", "El prestamo fue modificado con exito!");
			} else {
				request.setAttribute("label", "Ingrese un ID de un prestamo pendiente existente");
			}
			prestamosPendientes = prestamo.obtenerPendientes();
			request.setAttribute("lista", prestamosPendientes);
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
		
		if(request.getParameter("btnCargar")!=null) {
			prestamosPendientes = prestamo.obtenerPendientes();
			request.setAttribute("lista", prestamosPendientes);
			RequestDispatcher rd = request.getRequestDispatcher("/GestorPrestamos.jsp");
			rd.forward(request, response);
		}
	}

}

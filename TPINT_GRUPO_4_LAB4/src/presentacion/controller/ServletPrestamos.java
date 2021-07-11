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


/**
 * Servlet implementation class ServletPrestamos
 */
@WebServlet("/ServletPrestamos")
public class ServletPrestamos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletPrestamos() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Integer> prestamos = new ArrayList<Integer>();
		if(request.getParameter("Cargar3")!=null)
		{
			PrestamosDao pdao = new PrestamosDaoImpl();
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
			
			prestamos.add(pdao.Cant_Prestamos(ini, fin, mmin, mmax, cuotas));
			prestamos.add(pdao.Cant_Activos(ini, fin, mmin, mmax, cuotas));
			prestamos.add(pdao.Cant_Pendientes(ini, fin, mmin, mmax, cuotas));
			prestamos.add(pdao.Cant_Rechazados(ini, fin, mmin, mmax, cuotas));
			prestamos.add(pdao.Cant_Pagos(ini, fin, mmin, mmax, cuotas));
			prestamos.add(pdao.Promedio(ini, fin, mmin, mmax, cuotas));
			request.setAttribute("prestamos", prestamos);
			RequestDispatcher rd = request.getRequestDispatcher("Estadisticas.jsp");
			rd.forward(request, response);
		}
	}

}

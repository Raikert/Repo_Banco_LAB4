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
import entidad.Movimientos;

/**
 * Servlet implementation class ServletMovimientos
 */
@WebServlet("/ServletMovimientos")
public class ServletMovimientos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletMovimientos() {
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
		
		ArrayList<Double> mov = new ArrayList<Double>();
		if(request.getParameter("Cargar1")!=null)
		{
			MovimientosDao mdao = new MovimientosDaoImpl();
			String ini;
			String fin;
			if(request.getParameter("TxtFecini")!="")
			{
				ini=request.getParameter("TxtFecini");
			}else
			{
				ini="1900-01-01";
			}
			if(request.getParameter("TxtFecfin")!="")
			{
				fin=request.getParameter("TxtFecfin");
			}else
			{
				fin="2100-01-01";
			}
			mov.add( mdao.Egresos(ini,fin));
			mov.add(mdao.Ingresos(ini,fin));
			request.setAttribute("mov", mov);
			RequestDispatcher rd = request.getRequestDispatcher("Estadisticas.jsp");
			rd.forward(request, response);
		}
		
		if(request.getParameter("cargar")!=null)
		{
			MovimientosDao mdao = new MovimientosDaoImpl();
			String cl="";
			if(request.getParameter("cliente")!="")
			{
				cl=request.getParameter("cliente");
			}
			request.setAttribute("cl", cl);
			RequestDispatcher rd = request.getRequestDispatcher("HistorialGeneral.jsp");
			rd.forward(request, response);
		}
		
		if(request.getParameter("Cargar3")!=null)
		{
			MovimientosDao mdao = new MovimientosDaoImpl();
			ArrayList<Movimientos> lista = new ArrayList<Movimientos>();

			String ini;
			String fin;
			double min;
			double max;
			String tip;
			int can;
			String cue;
			if(request.getParameter("TxtFecIni")!="")
			{
				ini=request.getParameter("TxtFecIni");
			}else
			{
				ini="1900-01-01";
			}
			if(request.getParameter("TxtFecFin")!="")
			{
				fin=request.getParameter("TxtFecFin");
			}else
			{
				fin="2100-01-01";
			}
			if(request.getParameter("TxtMin")!="")
			{
				min=Double.parseDouble(request.getParameter("TxtMin"));
			}else
			{
				min=0;
			}
			if(request.getParameter("TxtMax")!="")
			{
				max=Double.parseDouble(request.getParameter("TxtMax"));
			}else
			{
				max=99999999;
			}
				tip=request.getParameter("tipo");
				can=Integer.parseInt(request.getParameter("RdCant"));
				cue=request.getParameter("cuenta");
			
			lista=mdao.BMovimientos(ini, fin,tip, min, max, can,cue);
			
			request.setAttribute("lista", lista);
			RequestDispatcher rd = request.getRequestDispatcher("HistorialGeneral.jsp");
			rd.forward(request, response);
		}
		
		if(request.getParameter("Cargar4")!=null)
		{
			MovimientosDao mdao = new MovimientosDaoImpl();
			ArrayList<Movimientos> lista = new ArrayList<Movimientos>();

			String ini;
			String fin;
			double min;
			double max;
			String tip;
			int can;
			String cue;

			if(request.getParameter("TxtFecIni")!="")
			{
				ini=request.getParameter("TxtFecIni");
			}else
			{
				ini="1900-01-01";
			}
			if(request.getParameter("TxtFecFin")!="")
			{
				fin=request.getParameter("TxtFecFin");
			}else
			{
				fin="2100-01-01";
			}
			if(request.getParameter("TxtMin")!="")
			{
				min=Double.parseDouble(request.getParameter("TxtMin"));
			}else
			{
				min=0;
			}
			if(request.getParameter("TxtMax")!="")
			{
				max=Double.parseDouble(request.getParameter("TxtMax"));
			}else
			{
				max=99999999;
			}
				tip=request.getParameter("tipo");
				can=Integer.parseInt(request.getParameter("RdCant"));
				cue=request.getParameter("cuenta");

			lista=mdao.BMovimientos(ini, fin,tip, min, max, can,cue);
			
			request.setAttribute("lista", lista);
			RequestDispatcher rd = request.getRequestDispatcher("Historial.jsp");
			rd.forward(request, response);
		}
	}

}

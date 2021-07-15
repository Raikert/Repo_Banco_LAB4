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
import entidad.Cuentas;
import entidad.CuentasAdmDao;

/**
 * Servlet implementation class ServletCuentas
 */
@WebServlet("/ServletCuentas")
public class ServletCuentas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletCuentas() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int filas=0;
		if(request.getParameter("crear")!=null){
			Cuentas c = new Cuentas();
			c.setDNI(request.getParameter("TxtDni"));
			c.setTipo(request.getParameter("DdlTipo"));
			c.setCbu(request.getParameter("TxtCbu"));
			CuentasAdmDao Ucuenta= new CuentasAdmDao();
			filas = Ucuenta.agregarCuenta(c);
			request.setAttribute("cantfilas", filas);
			RequestDispatcher rd = request.getRequestDispatcher("/AdminCuenta.jsp");
			rd.forward(request, response);
			
		}
		
		if(request.getParameter("Modificar")!=null){
			Cuentas c = new Cuentas();
			c.setTipo(request.getParameter("dlltipoupdate"));
			c.setCbu(request.getParameter("TxtCbu2"));
			c.setNcuenta(Integer.parseInt(request.getParameter("ddlNcuenta")));
			CuentasAdmDao Ucuenta= new CuentasAdmDao();
			filas = Ucuenta.modificarCuenta(c);
			request.setAttribute("cantfilas", filas);
			RequestDispatcher rd = request.getRequestDispatcher("/AdminCuenta.jsp");
			rd.forward(request, response);
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Double> cuentas = new ArrayList<Double>();
		if(request.getParameter("Cargar2")!=null)
		{
			CuentasDao cdao = new CuentasDaoImpl();
			String ini;
			String fin;
			double smin;
			double smax;
			String tipo;
			if(request.getParameter("TxtFecini2")!="")
			{
				ini=request.getParameter("TxtFecini2");
			}else
			{
				ini="1900-01-01";
			}
			if(request.getParameter("TxtFecfin2")!="")
			{
				fin=request.getParameter("TxtFecfin2");
			}else
			{
				fin="2100-01-01";
			}
			if(request.getParameter("TxtSdmin")!="")
			{
				smin=Integer.parseInt(request.getParameter("TxtSdmin"));
			}else
			{
				smin=0;
			}
			if(request.getParameter("TxtSdmax")!="")
			{
				smax=Integer.parseInt(request.getParameter("TxtSdmax"));
			}else
			{
				smax=999999999;
			}
			tipo=request.getParameter("Selectc");
			
			cuentas.add(cdao.Cant_Cuentas(ini, fin, smin, smax, tipo));
			cuentas.add(cdao.Saldo_Prom(ini, fin, smin, smax, tipo));
			request.setAttribute("cuentas", cuentas);
			RequestDispatcher rd = request.getRequestDispatcher("Estadisticas.jsp");
			rd.forward(request, response);
		}
	}

}

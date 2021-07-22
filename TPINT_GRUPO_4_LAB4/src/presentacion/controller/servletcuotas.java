package presentacion.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datosImpl.PrestamoDaoImpl;

/**
 * Servlet implementation class servletcuotas
 */
@WebServlet("/servletcuotas")
public class servletcuotas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletcuotas() {

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("pagar")!=null){
			int filas=0;
			int cuota=0;
			int idprestamos=0;
			int cuentas=0;
			float saldo= 0;
			float importe= 0; 
			cuota=Integer.parseInt(request.getParameter("cuotas"));
			idprestamos=Integer.parseInt(request.getParameter("prestamo"));
			cuentas=Integer.parseInt(request.getParameter("cuenta"));
			PrestamoDaoImpl prest = new PrestamoDaoImpl();
			saldo=prest.obtenerSaldocuentaApagar(cuentas);
			importe= prest.obtenerImporteApagar(idprestamos)*cuota;
				
				if (saldo>=importe) {
				filas=prest.RestarSaldoCuenta(cuota, idprestamos, cuentas);
				filas=prest.modificarCuenta(cuota, idprestamos, cuentas);
				}
			request.setAttribute("cantfilas", filas);
			RequestDispatcher rd = request.getRequestDispatcher("/PagarCuota.jsp");
			rd.forward(request, response);
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

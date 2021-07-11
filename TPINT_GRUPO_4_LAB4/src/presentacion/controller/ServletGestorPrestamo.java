package presentacion.controller;

import java.io.IOException;
import java.util.ArrayList;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import entidad.Prestamo;
import negocio.*;
import negocioImpl.*;
@WebServlet("/ServletGestorPrestamo")
public class ServletGestorPrestamo extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	private PrestamoNeg prestamo = new PrestamoNegImpl();
	ArrayList<Prestamo> prestamosPendientes = new ArrayList<Prestamo>();
	
    public ServletGestorPrestamo() {
        super();
    }

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

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("btnCargar")!=null) {
			prestamosPendientes = prestamo.obtenerPendientes();
			request.setAttribute("lista", prestamosPendientes);
			RequestDispatcher rd = request.getRequestDispatcher("/GestorPrestamos.jsp");
			rd.forward(request, response);
		}
	}

}

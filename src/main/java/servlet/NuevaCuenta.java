package servlet;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.ObraSocial;
import logic.ObrasSocialesController;

/**
 * Servlet implementation class NuevaCuenta
 */
@WebServlet("/NuevaCuenta")
public class NuevaCuenta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NuevaCuenta() {
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
		LinkedList<ObraSocial> obras_sociales = new LinkedList<>();
		ObrasSocialesController os_ctrl = new ObrasSocialesController();
		
		obras_sociales = os_ctrl.getAll();
		request.setAttribute("obras_sociales", obras_sociales);
		request.getRequestDispatcher("WEB-INF/nuevaCuenta.jsp").forward(request, response);
		
		
		

	}

}

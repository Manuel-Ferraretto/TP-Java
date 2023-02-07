package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.Paciente;
import logic.ComunicacionDb;

/**
 * Servlet implementation class ActualizarDatos
 */
@WebServlet("/ActualizarDatos")
public class ActualizarDatos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ActualizarDatos() {
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
		Paciente p = new Paciente();
		Paciente pac = new Paciente();
		ComunicacionDb ctrl = new ComunicacionDb();
		HttpSession session = request.getSession();
		
		p = (Paciente) session.getAttribute("usuario");
		pac.setId(p.getId());
		pac.setEmail(request.getParameter("email"));
		if (pac.getEmail()==null) {pac.setEmail(p.getEmail());}
		
		pac.setPassword(request.getParameter("password"));
		if (pac.getPassword()==null) {pac.setPassword(p.getPassword());}
		
		pac.setNum_tel(request.getParameter("numtel"));
		if (pac.getNum_tel()==null) {pac.setNum_tel(p.getNum_tel());}
		
		try {
			ctrl.actualizarDatosPaciente(pac);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("confirmacionActualizacionDatos.html").forward(request, response);
	}

}

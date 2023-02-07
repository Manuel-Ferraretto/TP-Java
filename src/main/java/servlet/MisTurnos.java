package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.Paciente;
import entities.Turnos;
import logic.ComunicacionDb;

/**
 * Servlet implementation class MisTurnos
 */
@WebServlet("/MisTurnos")
public class MisTurnos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MisTurnos() {
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
		Paciente paciente = new Paciente();
		HttpSession session = request.getSession();
		ComunicacionDb ctrl = new ComunicacionDb();
		LinkedList<Turnos> turnos = new LinkedList<>();
		
		paciente = (Paciente) session.getAttribute("usuario");
		try {
			turnos = ctrl.getTurnosPaciente(paciente);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("misTurnos", turnos);
		request.getRequestDispatcher("WEB-INF/misTurnos.jsp").forward(request, response);
		
	}

}

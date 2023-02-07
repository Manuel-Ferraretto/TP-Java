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
 * Servlet implementation class CancelarTurno
 */
@WebServlet("/CancelarTurno")
public class CancelarTurno extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CancelarTurno() {
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
		ComunicacionDb ctrl = new ComunicacionDb();
		HttpSession session = request.getSession();
		//Turnos t = new Turno();
		
		
		
		int num_turno = Integer.parseInt(request.getParameter("nro_turno"));
		
		if (num_turno != 0) {
			p = (Paciente) session.getAttribute("usuario");
			try {
				ctrl.cancelarTurno(num_turno);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			request.getRequestDispatcher("cancelacion_turno.html").forward(request, response);
		}
		
		else {
			request.getRequestDispatcher("menu.html").forward(request, response);
		}
		
		
	}

}

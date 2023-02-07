package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.Paciente;
import entities.Profesional;
import entities.Turnos;
import logic.ComunicacionDb;
import logic.ProfesionalController;


@WebServlet("/ConfirmarTurno")
public class ConfirmarTurno extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfirmarTurno() {
        super();
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
		
		ComunicacionDb ctrl = new ComunicacionDb();
		Paciente paciente = new Paciente();
		ProfesionalController profesionalCtrl = new ProfesionalController();
		HttpSession session = request.getSession();
		paciente = (Paciente) session.getAttribute("usuario");
		int confirmacion = Integer.parseInt(request.getParameter("confirmar"));

		if (confirmacion == 1){
			Profesional profesional = profesionalCtrl.getByMatricula(request.getParameter("matricula_profesional"));
			Turnos turno = new Turnos(
										LocalDate.parse(request.getParameter("date")),
										LocalTime.parse(request.getParameter("time")),
										profesional,
										paciente
										);
			try {
				ctrl.asignarTurno(turno);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			request.getRequestDispatcher("confirmacion_turno.html").forward(request, response);
		}
		else request.getRequestDispatcher("menu.html").forward(request, response);

	}

}

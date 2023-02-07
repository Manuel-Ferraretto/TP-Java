package servlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.Especialidad;
import entities.Paciente;
import entities.Profesional;
import entities.Turnos;
import logic.ComunicacionDb;
import logic.EspecialidadesController;
import logic.ProfesionalController;
import logic.TurnosController;



@WebServlet({ "/PacienteServlet"})
public class PacienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public PacienteServlet() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String opc;
		opc = request.getParameter("opcion");
		Paciente p = new Paciente();
		Profesional prof = new Profesional();
		LinkedList<Turnos> turnosPacienteActual = new LinkedList<>();
		LinkedList<Turnos> turnosProfesionalActual = new LinkedList<>();
		LinkedList<Paciente> turnosPacientes = new LinkedList<>();
		LinkedList<Turnos> turnos = new LinkedList<>();
		LinkedList<Profesional> profesionales = new LinkedList<>();
		LinkedList<Especialidad> especialidades = new LinkedList<>();
		EspecialidadesController espCtrl = new EspecialidadesController();
		ComunicacionDb ctrl = new ComunicacionDb();
		TurnosController t_ctrl = new TurnosController();
		EspecialidadesController e_ctrl = new EspecialidadesController();
		ProfesionalController p_ctrl = new ProfesionalController();
		HttpSession session = request.getSession();
		p = (Paciente) session.getAttribute("usuario");
		
		if ("misturnos".equals(opc)) {	
			try {
				turnosPacienteActual = ctrl.getTurnosPaciente(p);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			request.setAttribute("turnosPaciente", turnosPacienteActual);
			request.setAttribute("paciente", p);
			request.getRequestDispatcher("WEB-INF/misTurnos.jsp").forward(request, response); 
		}
		
		if ("reservar".equals(opc)) {
			try {
				especialidades = espCtrl.getAll();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			request.setAttribute("especialidades", especialidades);
			request.getRequestDispatcher("WEB-INF/elegirEspecialidad.jsp").forward(request, response); 
		}
		 
		if ("misdatos".equals(opc)) {
			request.setAttribute("paciente", p);
			request.getRequestDispatcher("WEB-INF/nuevosDatos.jsp").forward(request, response); 
		}
		
		if ("turnos_profesional".equals(opc)) {
			prof = (Profesional)session.getAttribute("usuario");
			try {
				turnosProfesionalActual = ctrl.getTurnosProfesional(prof);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				turnosPacientes = ctrl.getTurnosPacientesProfActual(prof);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			request.setAttribute("turnosProfesional", turnosProfesionalActual);
			request.setAttribute("pacientes", turnosPacientes);
			request.getRequestDispatcher("WEB-INF/turnosProfesional.jsp").forward(request, response);
		}
		
		/*
		if ("hc".equals(opc)) {
			turnos = t_ctrl.getAllTurnosPacienteActual(p);
			try {
				especialidades = e_ctrl.getNombreEspecialidades(p);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			profesionales = p_ctrl.getNombres(p);
			request.setAttribute("usuario", p);
			request.setAttribute("turnos", turnos);
			request.setAttribute("especialidades", especialidades);
			request.setAttribute("profesionales", profesionales);
			request.getRequestDispatcher("WEB-INF/historiaClinica.jsp").forward(request, response);
		} */
		
		if ("reservar2".equals(opc)) {
			LocalDate initialDate = LocalDate.now().plusDays(7);
			LocalDate finalDate = initialDate.plus(Period.ofDays(5));
			LocalDate currentDate = initialDate;
			LocalTime finishTime = LocalTime.of(14, 00);


			while( currentDate.isAfter(finalDate) == false ) {
				LocalTime time = LocalTime.of(8, 00);
				while ( time.isAfter(finishTime) == false ) {
						Turnos t = new Turnos();
						t.setFecha_turno(currentDate);
						t.setHora_turno(time);				
						turnos.add(t);
						time = time.plusMinutes(45);
					}
					currentDate = currentDate.plusDays(1);
				}
			request.setAttribute("turnos", turnos);
			request.getRequestDispatcher("WEB-INF/pruebasConDates.jsp").forward(request, response);
			
		}
		
		if ("signout".equals(opc)) {
			session.invalidate();
			request.getRequestDispatcher("index.html").forward(request, response);
		}
		
	}
}

package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
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

@WebServlet({ "/BuscarTurno", "/buscarturno", "/buscarTurno", "/BUSCARTURNO" })
public class BuscarTurno extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public BuscarTurno() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LinkedList<Turnos> turnos = new LinkedList<>();
		LinkedList<Especialidad> especialidades = new LinkedList<>();
		EspecialidadesController espCtrl = new EspecialidadesController();
		TurnosController turnosCtrl = new TurnosController();
		ProfesionalController profesionalCtrl = new ProfesionalController();
		ComunicacionDb ctrl = new ComunicacionDb();
		Profesional profesionalElegido = new Profesional();
		Paciente pac = new Paciente();
		LocalDate initialDate = LocalDate.now().plusDays(7);
		LocalDate finalDate = initialDate.plus(Period.ofDays(5));
		LocalDate currentDate = initialDate;
		LocalTime finishTime;
		LocalTime time;
		String mat = request.getParameter("nro_matricula");
		Boolean availability = false;
	
		if (mat.equals("0")) {
			try {
				especialidades = espCtrl.getAll();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			request.setAttribute("especialidades", especialidades);
			request.getRequestDispatcher("WEB-INF/elegirEspecialidad.jsp").forward(request, response);
		}
		
		else {
			profesionalElegido = profesionalCtrl.getByMatricula(request.getParameter("nro_matricula"));
				
			finishTime = LocalTime.of(profesionalElegido.getHora_fin().getHour(), 
									  profesionalElegido.getHora_fin().getMinute());
	
			while( currentDate.isAfter(finalDate) == false ) {
				time = LocalTime.of(profesionalElegido.getHora_inicio().getHour(), 
									profesionalElegido.getHora_inicio().getMinute());
	
				if ( currentDate.getDayOfWeek().getValue() != 6 && currentDate.getDayOfWeek().getValue() != 7 ){
					while ( time.isAfter(finishTime) == false ) {
						Turnos t = new Turnos();
						t.setFecha_turno(currentDate);
						t.setHora_turno(time);
						t.setProfesional(profesionalElegido);
						try {
							availability = turnosCtrl.validateAvailability(t);
						} catch (SQLException e) {
							e.printStackTrace();
						}
						if (availability == true) {turnos.add(t);}
						time = time.plusMinutes(30);
					}
				}
				currentDate = currentDate.plusDays(1);
			}
			
			HttpSession session = request.getSession();
			pac = (Paciente) session.getAttribute("usuario");
			
			request.setAttribute("paciente", pac);
			request.setAttribute("turnosDisponibles", turnos);
			request.setAttribute("profesional", profesionalElegido);
			request.getRequestDispatcher("WEB-INF/turnosDisponibles.jsp").forward(request, response); 
		
		} //fin del else
	}

}

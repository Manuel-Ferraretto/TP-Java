package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
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
import logic.PacientesController;
import logic.ProfesionalController;
import logic.TurnosController;

@WebServlet({ "/ReservarTurno", "/reservarTurno", "/reservarturno" })
public class ReservarTurno extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ReservarTurno() {
        super(); }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath()); }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		LinkedList<Profesional> profesionales = new LinkedList<>();
		LinkedList<Turnos> turnosPacienteActual = new LinkedList<>();
		LinkedList<Especialidad> especialidades = new LinkedList<>();
		Paciente p = new Paciente();
		Especialidad esp = new Especialidad();
		ComunicacionDb ctrl = new ComunicacionDb();
		TurnosController turnoCtrl = new TurnosController();
		ProfesionalController profesionalCtrl = new ProfesionalController();
		EspecialidadesController espCtrl = new EspecialidadesController();
		PacientesController pacienteController = new PacientesController();
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		Boolean bandera = true;
		
		String op = request.getParameter("codigo_esp");
		int codigoEspecialidadElegida = Integer.parseInt(op);
		
		if (codigoEspecialidadElegida != 0) {
			profesionales = profesionalCtrl.getDisponiblesByEspecialidad(codigoEspecialidadElegida);
			p = (Paciente) session.getAttribute("usuario");
			try {
				turnosPacienteActual = pacienteController.getTurnosPaciente(p);
			} catch (SQLException e) {
				e.printStackTrace();
			}		
			if (turnosPacienteActual != null) {
				for (Turnos t: turnosPacienteActual) {
					if (t.getProfesional().getEspecialidad().getCodigo_esp() == codigoEspecialidadElegida) { 
						bandera = false; 
						break;
						}
					}
				}	
			if (bandera == true || turnosPacienteActual == null) {
					request.setAttribute("profesionales", profesionales);
					request.getRequestDispatcher("WEB-INF/elegirProfesional.jsp").forward(request, response); 
			}
			else {
				try {
					especialidades = espCtrl.getAll();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				request.setAttribute("especialidades", especialidades);
				//request.setAttribute("mensaje", "El usuario ya tiene un turno pendiente para la especialidad elegida");
				request.setAttribute("habilitarMensaje", true);
				RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/elegirEspecialidad.jsp");
				rd.include(request, response); 
			}
		} // fin del primer if 
		
		else {
			request.getRequestDispatcher("menu.html").forward(request, response); 
		}
	}

}

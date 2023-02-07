package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.Especialidad_ObralSocial;
import entities.ObraSocial;
import entities.Paciente;
import entities.Profesional;
import entities.Turnos;
import entities.Valor_especialidad;
import logic.ComunicacionDb;
import logic.ProfesionalController;

/**
 * Servlet implementation class AsignarTurno
 */
@WebServlet("/AsignarTurno")
public class AsignarTurno extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public AsignarTurno() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ComunicacionDb ctrl = new ComunicacionDb();
		Paciente paciente = new Paciente();
		Turnos turno = new Turnos();
		Profesional profesionalElegido = new Profesional();
		ObraSocial os = new ObraSocial();
		Valor_especialidad valor_esp = new Valor_especialidad();
		Especialidad_ObralSocial esp_os = new Especialidad_ObralSocial();
		Double precio_final;
		HttpSession session = request.getSession();
		LinkedList<Profesional> profesionales = new LinkedList<>();
		ProfesionalController profesionalCtrl = new ProfesionalController();
		//DateTimeFormatter formatter = new DateTimeFormatter("dd/MM/yyyy");

		int num_turno = Integer.parseInt(request.getParameter("nro_turno"));
		
		if (num_turno == 1) {
			turno.setFecha_turno(LocalDate.parse(request.getParameter("date")));
			turno.setHora_turno(LocalTime.parse(request.getParameter("time")));

			profesionalElegido = profesionalCtrl.getByMatricula(request.getParameter("matricula_profesional"));
		
			try {
				valor_esp = ctrl.getValorEspecialidad(profesionalElegido);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
			paciente = (Paciente) session.getAttribute("usuario");
			try {
				os = ctrl.getObraSocial(paciente);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
			try {
				esp_os = ctrl.getPorcentajeCobertura(profesionalElegido.getEspecialidad().getCodigo_esp(),
													 os.getId_obra_social());
			} catch (SQLException e) {
				e.printStackTrace();
			}
			precio_final = (double) (valor_esp.getValor() - (valor_esp.getValor() * esp_os.getProcentaje_cobertura())); 
		
			request.setAttribute("usuario", paciente);
			request.setAttribute("turno", turno);
			request.setAttribute("profesional", profesionalElegido);
			request.setAttribute("obra_social", os);
			request.setAttribute("valor_especialidad", valor_esp);
			request.setAttribute("precio_final", precio_final);
			request.setAttribute("date", request.getParameter("date"));
			request.setAttribute("time", request.getParameter("time"));
			request.getRequestDispatcher("WEB-INF/pruebas.jsp").forward(request, response); 
		
			}
		
			else {
			//Necesito el código de especialidad para la lista de profesionales
				request.setAttribute("profesionales", profesionales);
				request.getRequestDispatcher("WEB-INF/elegirProfesional.jsp").forward(request, response); 
			}
		}

}

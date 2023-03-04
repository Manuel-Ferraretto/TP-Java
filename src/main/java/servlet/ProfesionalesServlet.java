package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import entities.Especialidad;
import entities.Profesional;
import entities.Turnos;
import logic.EspecialidadesController;
import logic.ProfesionalController;
import logic.TurnosController;

/**
 * Servlet implementation class ProfesionalesServlet
 */
@WebServlet("/ProfesionalesServlet")
public class ProfesionalesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProfesionalesServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String accion = request.getParameter("accion");
		
		if (accion.equalsIgnoreCase("listar")) {
			ProfesionalController pc = new ProfesionalController();
			EspecialidadesController ec = new EspecialidadesController();
			LinkedList<Profesional> profesionales = null;

			profesionales = pc.getAll();
			request.setAttribute("listaProfesionales", profesionales);
			request.setAttribute("muestraMensaje", request.getAttribute("mensaje"));
			request.getRequestDispatcher("WEB-INF/profesional.jsp").forward(request, response);
			
			
		} else if (accion.equalsIgnoreCase("agregar")) {
			EspecialidadesController ec = new EspecialidadesController();
			LinkedList<Especialidad> especialidades = null;
			LinkedList<LocalTime> horas = new LinkedList<>();
			LocalTime finishTime = LocalTime.of(15, 00);
			LocalTime time = LocalTime.of(8, 00);
			
			/*
			while ( time.isAfter(finishTime) == false ) {
				horas.add(time);
				time.plusMinutes(45);
			} */
			
			horas.add(LocalTime.of(8, 00)); horas.add(LocalTime.of(8, 30)); horas.add(LocalTime.of(9, 00));
			horas.add(LocalTime.of(9, 30)); horas.add(LocalTime.of(10, 00)); horas.add(LocalTime.of(10, 30)); 
			horas.add(LocalTime.of(11, 00)); horas.add(LocalTime.of(11, 30)); horas.add(LocalTime.of(12, 00)); 
			horas.add(LocalTime.of(12, 30)); horas.add(LocalTime.of(13, 00)); horas.add(LocalTime.of(13, 30));
			horas.add(LocalTime.of(14, 00)); horas.add(LocalTime.of(14, 30)); horas.add(LocalTime.of(15, 00)); 
			horas.add(LocalTime.of(15, 30)); horas.add(LocalTime.of(16, 00)); horas.add(LocalTime.of(16, 30)); 
			horas.add(LocalTime.of(17, 00)); horas.add(LocalTime.of(17, 30)); horas.add(LocalTime.of(18, 00)); 
			
			try {
				especialidades = ec.getAll();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("listaEspecialidades", especialidades);
			request.setAttribute("horas", horas);
			request.getRequestDispatcher("WEB-INF/addProfesional.jsp").forward(request, response);
			
			
		} else if (accion.equalsIgnoreCase("Add")) {
			LocalTime horaInicio = LocalTime.parse(request.getParameter("hora_inicio"));
			LocalTime horaFin = LocalTime.parse(request.getParameter("hora_fin"));
			
			if (horaInicio.isBefore(horaFin)) {
				ProfesionalController profesionalController = new ProfesionalController();
				EspecialidadesController especialidadController = new EspecialidadesController();
				boolean existeMatricula = profesionalController.validarMatricula(request.getParameter("matricula"));
				boolean existeEmail = profesionalController.validarEmail(request.getParameter("email"));

					if (existeMatricula == false && existeEmail == false) {
						Especialidad especialidad = new Especialidad();
						try {
							especialidad = especialidadController.getByCodigo(
																Integer.parseInt(request.getParameter("codigo_especialidad")));
						} catch (NumberFormatException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						Profesional nuevoProfesional = new Profesional(
																		request.getParameter("matricula"),
																		request.getParameter("email"),
																		request.getParameter("nombre"),
																		request.getParameter("apellido"),
																		LocalTime.parse(request.getParameter("hora_inicio")),
																		LocalTime.parse(request.getParameter("hora_fin")),
																		especialidad
																		);
						profesionalController.add(nuevoProfesional);
						request.setAttribute("mensaje", "Profesional agregado correctamente.");
						request.getRequestDispatcher("ProfesionalesServlet?accion=listar").forward(request, response);
						} 
					else {
							request.setAttribute("estado", "Ya existe profesional con email o matricula ingresada");
							request.getRequestDispatcher("ProfesionalesServlet?accion=listar").forward(request, response);	
						}
				} else {
					request.setAttribute("mensaje", "La hora inicio debe ser anterior a la hora de fin de jornada laboral");
					request.getRequestDispatcher("ProfesionalesServlet?accion=agregar").forward(request, response);
				}

			
		} else if (accion.equalsIgnoreCase("Editar")) {
			Profesional p = new Profesional();
			ProfesionalController pc = new ProfesionalController();
			EspecialidadesController ec = new EspecialidadesController();
			LinkedList<Especialidad> especialidades = null;
			LinkedList<LocalTime> horas = new LinkedList<>();
			
			horas.add(LocalTime.of(8, 00)); horas.add(LocalTime.of(8, 30)); horas.add(LocalTime.of(9, 00));
			horas.add(LocalTime.of(9, 30)); horas.add(LocalTime.of(10, 00)); horas.add(LocalTime.of(10, 30)); 
			horas.add(LocalTime.of(11, 00)); horas.add(LocalTime.of(11, 30)); horas.add(LocalTime.of(12, 00)); 
			horas.add(LocalTime.of(12, 30)); horas.add(LocalTime.of(13, 00)); horas.add(LocalTime.of(13, 30));
			horas.add(LocalTime.of(14, 00)); horas.add(LocalTime.of(14, 30)); horas.add(LocalTime.of(15, 00)); 
			horas.add(LocalTime.of(15, 30)); horas.add(LocalTime.of(16, 00)); horas.add(LocalTime.of(16, 30)); 
			horas.add(LocalTime.of(17, 00)); horas.add(LocalTime.of(17, 30)); horas.add(LocalTime.of(18, 00)); 
			
			try {
				especialidades = ec.getAll();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			p = pc.getByMatricula(request.getParameter("matricula"));
			request.setAttribute("horas", horas);
			request.setAttribute("profesional", p);
			request.setAttribute("listaEspecialidades", especialidades);
			request.getRequestDispatcher("WEB-INF/editProfesional.jsp").forward(request, response);
			
			
		} else if (accion.equalsIgnoreCase("Actualizar")) {
			ProfesionalController profesionalController= new ProfesionalController();
			EspecialidadesController especialidadController = new EspecialidadesController();
			boolean matriculaValida = profesionalController.validarMatricula(request.getParameter("matricula"));
			boolean emailValido = profesionalController.validarEmail(request.getParameter("email"));
			
			if (matriculaValida == true && emailValido == true) {
				Especialidad especialidad = new Especialidad();
				try {
					especialidad = especialidadController.getByCodigo(
														Integer.parseInt(request.getParameter("codigo_especialidad")));
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				Profesional profesional = new Profesional(
															request.getParameter("matricula"),
															request.getParameter("email"),
															request.getParameter("nombre"),
															request.getParameter("apellido"),
															LocalTime.parse(request.getParameter("hora_inicio")),
															LocalTime.parse(request.getParameter("hora_fin")),
															especialidad
																);
				
				profesionalController.update(profesional, request.getParameter("matricula"));
				request.setAttribute("estado", "Profesional editado correctamente.");
				request.getRequestDispatcher("ProfesionalesServlet?accion=listar").forward(request, response);
			} else {
				request.setAttribute("estado", "Profesional ingresado ya existe con ese Email o Matricula.");
				request.getRequestDispatcher("ProfesionalesServlet?accion=listar").forward(request, response);
			}
			
			
		}else if(accion.equalsIgnoreCase("Eliminar")) {
			Profesional profesionalAEliminar = new Profesional();
			Turnos t = new Turnos();
			ProfesionalController profesionalController=new ProfesionalController();
			TurnosController turnosController = new TurnosController();
			
			profesionalAEliminar = profesionalController.getByMatricula(request.getParameter("matricula"));
			t.setProfesional(profesionalAEliminar);
			turnosController.deleteByMatricula(t.getProfesional().getMatricula());
			profesionalController.delete(profesionalAEliminar);
			request.setAttribute("estado", "Profesional eliminado exitosamente.");
			request.getRequestDispatcher("ProfesionalesServlet?accion=Listar").forward(request, response);
			
		
		} else if (accion.equalsIgnoreCase("Modificar estado")) {
			ProfesionalController profesionalController = new ProfesionalController();
			profesionalController.modificarEstado(request.getParameter("matricula"));
			request.setAttribute("estado", "Estado actualizado exitosamente");
			request.getRequestDispatcher("ProfesionalesServlet?accion=Listar").forward(request, response);
		}
		

	}
}

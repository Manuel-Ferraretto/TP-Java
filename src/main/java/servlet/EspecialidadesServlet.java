package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Especialidad;
import entities.Valor_especialidad;
import logic.EspecialidadesController;




/**
 * Servlet implementation class EspecialidadesServlet
 */
@WebServlet("/EspecialidadesServlet")
public class EspecialidadesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EspecialidadesServlet() {
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
			EspecialidadesController especialidadController = new EspecialidadesController();
			LinkedList<Valor_especialidad> valores=null;
			
			valores = especialidadController.getValoresActuales();
			
			request.setAttribute("listaValores", valores);
			request.setAttribute("retro", request.getAttribute("estado"));
			request.getRequestDispatcher("WEB-INF/especialidad.jsp").forward(request, response);
			//request.getRequestDispatcher("WEB-INF/especialidad_v2.jsp").forward(request, response);
			
		} else if (accion.equalsIgnoreCase("agregar")) {
			request.getRequestDispatcher("WEB-INF/addEspecialidad.jsp").forward(request, response);
			
			
		} else if (accion.equalsIgnoreCase("agregarNuevaEspecialidad")) {
			EspecialidadesController especialidadController = new EspecialidadesController();

			try {
				if (especialidadController.validarNombreNuevaEspecialidad(request.getParameter("nombre")) == true) {
					int valor_ingresado = Integer.parseInt(request.getParameter("valor"));
					Especialidad nueva_especialidad = new Especialidad(request.getParameter("nombre"));
					Valor_especialidad ve= new Valor_especialidad(nueva_especialidad, LocalDate.now(), valor_ingresado);
					especialidadController.add(nueva_especialidad,ve);
					request.setAttribute("estado", "Especialidad agregada correctamente.");
					request.getRequestDispatcher("EspecialidadesServlet?accion=listar").forward(request, response);
					
				} else {
					request.setAttribute("estado", "Especialidad ingresada ya existe.");
					request.getRequestDispatcher("EspecialidadesServlet?accion=listar").forward(request, response);
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			
		}else if (accion.equalsIgnoreCase("Editar")) {
			Especialidad e = new Especialidad();
			Valor_especialidad ve = new Valor_especialidad();
			EspecialidadesController ec=new EspecialidadesController();
			int codigo= Integer.parseInt(request.getParameter("id2"));
			e.setCodigo_esp(codigo);
			ve.setEspecialidad(e);
			try {
				e = ec.getByCodigo(codigo);
				ve = ec.getValorPorCodigo(ve);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			request.setAttribute("especialidad", e);
			request.setAttribute("valor", ve);
			request.getRequestDispatcher("WEB-INF/editEspecialidad.jsp").forward(request, response);
			
			
		}else if (accion.equalsIgnoreCase("Actualizar")) {
			EspecialidadesController especialidadController = new EspecialidadesController();
			Especialidad especialidadAEditar = new Especialidad();
			
			try {
				especialidadAEditar = especialidadController.getByCodigo(
																Integer.parseInt(request.getParameter("id")));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Valor_especialidad valor_especialidad = new Valor_especialidad(
																	especialidadAEditar, 
																	LocalDate.now(), 
																	Integer.parseInt(request.getParameter("valor1")));
			try {
				especialidadController.update(especialidadAEditar, valor_especialidad);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("estado", "La especialidad se ha actualizado exitosamente!");
			request.getRequestDispatcher("EspecialidadesServlet?accion=listar").forward(request, response);
					
		
		}else if(accion.equalsIgnoreCase("Eliminar")) {
			EspecialidadesController especialidadController = new EspecialidadesController();
			
			try {
				Integer numero = especialidadController.delete(Integer.parseInt(request.getParameter("id2")));
				
				if (numero==1) {
					request.setAttribute("estado", "Especialidad eliminada exitosamente.");
					request.getRequestDispatcher("EspecialidadesServlet?accion=Listar").forward(request, response);
				}else {
					request.setAttribute("estado", "No se pudo eliminar porque hay profesionales con esa especialidad.");
					request.getRequestDispatcher("EspecialidadesServlet?accion=Listar").forward(request, response);
			}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
		}

	}
}

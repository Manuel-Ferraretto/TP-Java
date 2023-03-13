package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import entities.Paciente;
import logic.ComunicacionDb;
import logic.ObrasSocialesController;
import logic.PacientesController;


@WebServlet({ "/CrearCuenta", "/crearcuenta", "/Crearcuenta", "/crearCuenta" })
public class CrearCuenta extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public CrearCuenta() {
        super(); 
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ComunicacionDb ctrl = new ComunicacionDb();
		PacientesController pacienteCtrl = new PacientesController();
		ObrasSocialesController osController = new ObrasSocialesController();
		PrintWriter out = response.getWriter();
		

		// Validar que el email o dni no esté ya en uso
		String email = request.getParameter("email");
		String dni = request.getParameter("clave");
	
		try {
			if (pacienteCtrl.existeUsuario(email, dni) == null) {
				Paciente pac = new Paciente(
								request.getParameter("nombre"),
								request.getParameter("apellido"),
								request.getParameter("dni"),
								request.getParameter("celular"),
								request.getParameter("email"),
								request.getParameter("clave"),
								osController.getByCodigo(Integer.parseInt(request.getParameter("codigo_os")))
						);			
				try {
					ctrl.altaPaciente(pac);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				request.getRequestDispatcher("bienvenido.html").forward(request, response); 
				}	
				
			else {
				out.print("El usuario ingresado ya existe"); 
				RequestDispatcher rd = request.getRequestDispatcher("\"WEB-INF/nuevaCuenta.jsp");
				rd.include(request, response); 
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}


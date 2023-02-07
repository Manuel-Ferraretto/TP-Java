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
		Paciente pac = new Paciente();
		Paciente pac_aux = new Paciente();
		Boolean encontrado = false;
		ComunicacionDb ctrl = new ComunicacionDb();
		PrintWriter out = response.getWriter();
		
		String nombre = request.getParameter("nombre");
		String apellido = request.getParameter("apellido");
		String dni = request.getParameter("dni");
		String cel = request.getParameter("celular");
		String email = request.getParameter("email");   
		String password = request.getParameter("clave");
		int id_os = Integer.parseInt(request.getParameter("obra_social"));
		
		// Validar que el usuario no exista
		pac_aux.setEmail(email);
		pac_aux.setPassword(password);
			
		try {
			pac_aux = ctrl.validateLogin(pac_aux);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (pac_aux != null){encontrado = true;}
		
		if (encontrado == false) {
			pac.setEmail(email);
			pac.setPassword(password);
			pac.setNombre(nombre);
			pac.setApellido(apellido);
			pac.setDni(dni);
			pac.setNum_tel(cel);
			pac.setId_obra_social(id_os);			
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
					
	}
}


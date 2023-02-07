package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Paciente;
import entities.Profesional;
import entities.Turnos;
import logic.PacientesController;
import logic.ProfesionalController;
import logic.TurnosController;



/**
 * Servlet implementation class TurnosServlet
 */
@WebServlet("/TurnosServlet")
public class TurnosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TurnosServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String accion = request.getParameter("accion");
		if (accion.equalsIgnoreCase("listar")) {
			TurnosController tc=new TurnosController();
			ProfesionalController pc=new ProfesionalController();
			PacientesController pac= new PacientesController();
			LinkedList<Turnos> turnos = null;
			LinkedList<Profesional> profesionales=null;
			LinkedList<Paciente> pacientes=null;
			turnos=tc.getAll();
			profesionales=pc.getAll();
			pacientes=pac.getAll();
			request.setAttribute("listaTurnos", turnos);
			request.setAttribute("listaPacientes", pacientes);
			request.setAttribute("listaProfesionales", profesionales);
			request.getRequestDispatcher("WEB-INF/TurnosTodos.jsp").forward(request, response);
		}
	}

}

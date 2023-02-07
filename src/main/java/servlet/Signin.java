package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Administradores;
import entities.Paciente;
import entities.Profesional;
import logic.AdministradorController;
import logic.ComunicacionDb;
import logic.ObrasSocialesController;

@WebServlet({ "/Signin", "/signin", "/SignIn", "/SIGNIN", "/signIn" })
public class Signin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Signin() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdministradorController AdmCtrl= new AdministradorController();
		ComunicacionDb ctrl = new ComunicacionDb();
		PrintWriter out = response.getWriter();
		
		String username = request.getParameter("username");   // Recuperar información de un formulario html
		String password = request.getParameter("password");
		
		Paciente pac = new Paciente(username, password);
		Administradores adm = new Administradores(username, password);
		
		try {
			adm = AdmCtrl.validate(adm);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(adm != null) {
			request.getSession().setAttribute("administrador", adm);
			request.getRequestDispatcher("panel.jsp").forward(request, response);	
		}
		
		else if(adm == null) {
			try {
				pac = ctrl.validateLogin(pac);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (pac != null){
			request.getSession().setAttribute("usuario", pac);
			request.getRequestDispatcher("menu.html").forward(request, response); 
			}
			else {
				out.print("Credenciales invalidas, ingrese nuevamente"); 
				RequestDispatcher rd = request.getRequestDispatcher("index.html");
				rd.include(request, response); 
			}

		}
	}
}



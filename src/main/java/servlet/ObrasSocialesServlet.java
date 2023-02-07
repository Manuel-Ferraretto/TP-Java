package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Especialidad;
import entities.Especialidad_ObralSocial;
import entities.ObraSocial;
import logic.EspecialidadesController;
import logic.ObrasSocialesController;

/**
 * Servlet implementation class ObrasSocialesServlet
 */
@WebServlet("/ObrasSocialesServlet")
public class ObrasSocialesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ObrasSocialesServlet() {
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
			ObrasSocialesController obc= new ObrasSocialesController();
			LinkedList<ObraSocial> obrasSociales=null;
			obrasSociales = obc.getAll();
			request.setAttribute("listaObrasSociales", obrasSociales);
			request.setAttribute("retro", request.getAttribute("estado"));
			request.getRequestDispatcher("WEB-INF/obraSocial.jsp").forward(request, response);
			
			
	}else if (accion.equalsIgnoreCase("agregar")) {
		request.getRequestDispatcher("WEB-INF/addObraSocial.jsp").forward(request, response);
		
		
	} else if (accion.equalsIgnoreCase("Add")) {
		boolean nombreValido;
		ObrasSocialesController obraSocialController = new ObrasSocialesController();
		nombreValido = obraSocialController.validarNombreNuevaOS(request.getParameter("nombre_os"));
		
		if (nombreValido == true) {
			ObraSocial nuevaObraSocial = new ObraSocial(request.getParameter("nombre_os"));
			obraSocialController.add(nuevaObraSocial);
			request.setAttribute("estado", "Obra Social agregada correctamente.");
			request.getRequestDispatcher("ObrasSocialesServlet?accion=listar").forward(request, response);
		} else {
			request.setAttribute("estado", "Obra social ingresada ya existe.");
			request.getRequestDispatcher("ObrasSocialesServlet?accion=listar").forward(request, response);
		}

		
	}else if (accion.equalsIgnoreCase("Editar")) {
		ObraSocial os =new ObraSocial();
		ObrasSocialesController obc =new ObrasSocialesController();

		os=obc.getByCodigo(Integer.parseInt(request.getParameter("id2")));
		request.setAttribute("obraSocial", os);
		request.getRequestDispatcher("WEB-INF/editObraSocial.jsp").forward(request, response);
		
		
	}else if (accion.equalsIgnoreCase("Actualizar")) {
		ObraSocial os=new ObraSocial();
		ObrasSocialesController obc=new ObrasSocialesController();
		ObrasSocialesController obraSocialController = new ObrasSocialesController();
		boolean nombreValido = obraSocialController.validarNombreNuevaOS(request.getParameter("nombre_os"));

		
		if (nombreValido == true) {
			ObraSocial ob= new ObraSocial();
			ob.setId_obra_social(Integer.parseInt(request.getParameter("id")));
			ob.setNombre(request.getParameter("nombre_os"));
			obc.update(ob);
			request.setAttribute("estado", "obra social actualizada correctamente.");
			request.getRequestDispatcher("ObrasSocialesServlet?accion=listar").forward(request, response);
		}else {
			request.setAttribute("estado", "No se puede actualizar.Ya existe obra social con ese nombre");
			request.getRequestDispatcher("ObrasSocialesServlet?accion=Listar").forward(request, response);
		}
		
		
	}else if(accion.equalsIgnoreCase("Eliminar")) {
		ObraSocial ob=new ObraSocial();
		ObrasSocialesController obc=new ObrasSocialesController();
		
		ob.setId_obra_social(Integer.parseInt(request.getParameter("id2")));
		obc.delete(ob);
		request.setAttribute("estado", "obras social eliminada exitosamente.");
		request.getRequestDispatcher("ObrasSocialesServlet?accion=Listar").forward(request, response);
		
		
	} else if(accion.equalsIgnoreCase("ListarEspecialidades")) {
		ObraSocial ob=new ObraSocial();
		ObrasSocialesController osc= new ObrasSocialesController();
		EspecialidadesController ec= new EspecialidadesController();
		LinkedList<Especialidad_ObralSocial> especialidadesIncluidas= null;
		LinkedList<Especialidad> especialidadesNoIncluidas= null;
		LinkedList<Especialidad> especialidades= null;

		ob.setId_obra_social(Integer.parseInt(request.getParameter("id2")));
		try {
			especialidades= ec.getAll();
			especialidadesIncluidas= osc.getEspecialidadesIncluidas(ob);
		especialidadesNoIncluidas= osc.getEspecialidadesNoIncluidas(ob);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("codigoObra", Integer.parseInt(request.getParameter("id2")));
		request.setAttribute("listaespecialidadesIncluidas", especialidadesIncluidas);
		request.setAttribute("listaespecialidadesNoIncluidas", especialidadesNoIncluidas);
		request.setAttribute("listaespecialidades", especialidades);
		request.setAttribute("retro", request.getAttribute("estado"));
		request.getRequestDispatcher("WEB-INF/especialidad_obrasocial.jsp").forward(request, response);
		
		
	}else if(accion.equalsIgnoreCase("EliminarEspecialidad")) {
		Especialidad_ObralSocial espob =new Especialidad_ObralSocial();
		ObrasSocialesController obc=new ObrasSocialesController();
		
		espob.setCod_especialidad(Integer.parseInt(request.getParameter("idEsp")));
		espob.setId_os(Integer.parseInt(request.getParameter("idObra")));
		obc.deleteMiEspecialidad(espob);
		request.setAttribute("estado", "especialidad eliminada correctamente de la obra social.");
		request.getRequestDispatcher("ObrasSocialesServlet?accion=listar").forward(request, response);
		
		
	}else if(accion.equalsIgnoreCase("AgregarEspecialidad")) {
		Especialidad_ObralSocial espob =new Especialidad_ObralSocial();
		ObrasSocialesController obc=new ObrasSocialesController();
		
		espob.setCod_especialidad(Integer.parseInt(request.getParameter("idEsp")));
		espob.setId_os(Integer.parseInt(request.getParameter("idObra")));
		espob.setProcentaje_cobertura(Float.parseFloat(request.getParameter("porcentaje")));
		obc.addMiEspecialidad(espob);
		request.setAttribute("estado", "especialidad agregada correctamente de la obra social.");
		request.getRequestDispatcher("ObrasSocialesServlet?accion=listar").forward(request, response);
	}

	}

}

package dataBase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;

import entities.ObraSocial;
import entities.Paciente;
import entities.Profesional;
import entities.Turnos;
import logic.ObrasSocialesController;
import logic.ProfesionalController;

public class DataPacientes {

	public Paciente validateLogin(Paciente p) throws SQLException {

		PreparedStatement stmt = null;
		ResultSet rs = null;
		Paciente pac = null;
		ObrasSocialesController osCtrl = new ObrasSocialesController();
		String consulta = "select id, dni, nombre, apellido, num_tel, email, id_obra_social \r\n"
				+ " from pacientes where email=? and password=?";
		try{
			// Crear la conexión
			stmt = DbConnector.getInstancia().getConn().prepareStatement(consulta);
			
			// Ejecutar la query
			stmt.setString(1, p.getEmail());
			stmt.setString(2, p.getPassword());
			rs = stmt.executeQuery();
			
			// Mapeo de ResultSet a objeto
			if(rs!= null && rs.next()) {
				ObraSocial obraSocial = osCtrl.getByCodigo(rs.getInt("id_obra_social"));
				pac = new Paciente(
									rs.getInt("id"),
									rs.getString("nombre"),
									rs.getString("apellido"),
									rs.getString("dni"),
									rs.getString("num_tel"),
									rs.getString("email"),
									obraSocial
									); 

						} // Fin del if
			
			// Cerrar recursos
			if(stmt!=null) {stmt.close();}
			if(rs!=null) {rs.close();}
			DbConnector.getInstancia().releaseConn(); 
											
		} catch(SQLException  ex) {
			// Errores
			System.out.println("SQLException: "+ ex.getMessage());
			System.out.println("SQLState: "+ ex.getSQLState());
			System.out.println("VendorError: "+ ex.getErrorCode());
		}
		return pac;
} // Fin del getById
	
	
	
	public void setNullOS(ObraSocial ob) {
		// TODO Auto-generated method stub
		PreparedStatement stmt= null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"update pacientes set id_obra_social=null where id_obra_social=? "
							);
			stmt.setInt(1, ob.getId_obra_social());
		
			stmt.executeUpdate();
			
		}  catch (SQLException e) {
            e.printStackTrace();
		} finally {
            try {
                if(rs!=null)rs.close();
                if(stmt!=null)stmt.close();
                DbConnector.getInstancia().releaseConn();
            } catch (SQLException e) {
            	e.printStackTrace();
            }
		}
		
	}

	public LinkedList<Paciente> getAll() {
		// TODO Auto-generated method stub
		Statement stmt=null;
		ResultSet rs=null;
		LinkedList<Paciente> pacientes = new LinkedList<>();
		ObrasSocialesController osController = new ObrasSocialesController();
		try {
					
		// Ejecutar la query
		stmt = DbConnector.getInstancia().getConn().createStatement();
		rs = stmt.executeQuery("select * from pacientes");
					
		// Mapeao de ResultSet a objeto
		if (rs!=null) {
			while(rs.next()) {
				ObraSocial osPacienteActual = new ObraSocial();
				osPacienteActual = osController.getByCodigo(rs.getInt("id"));
				Paciente p = new Paciente(
											rs.getInt("id"),
											rs.getString("nombre"),
											rs.getString("apellido"),
											rs.getString("dni"),
											rs.getString("email"),
											rs.getString("num_tel"),
											osPacienteActual);
				pacientes.add(p);
					} // Fin del while
		} // Fin del if
		
		
		} catch(SQLException  ex) {
			ex.printStackTrace();
			
		} finally {
			try {
				if(rs!=null) {rs.close();}
				if(stmt!=null) {stmt.close();}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		
		return pacientes;	
	}

	public Paciente getPacienteById(int ID) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Paciente pac = null;
		ObrasSocialesController osController = new ObrasSocialesController();
		String consulta = "select id, dni, nombre, apellido, email, num_tel, email, id_obra_social \r\n"
				+ " from pacientes where id=?";
		try{
			// Crear la conexión
			stmt = DbConnector.getInstancia().getConn().prepareStatement(consulta);
			
			// Ejecutar la query
			stmt.setInt(1, ID);
			rs = stmt.executeQuery();
			
			// Mapeo de ResultSet a objeto
			if(rs!= null && rs.next()) {
				ObraSocial osPacienteActual = new ObraSocial();
				osPacienteActual = osController.getByCodigo(rs.getInt("id"));
				pac = new Paciente(
									rs.getInt("id"),
									rs.getString("nombre"),
									rs.getString("apellido"),
									rs.getString("dni"),
									rs.getString("email"),
									rs.getString("num_tel"),
									osPacienteActual);
						} // Fin del if
			
			// Cerrar recursos
			if(stmt!=null) {stmt.close();}
			if(rs!=null) {rs.close();}
			DbConnector.getInstancia().releaseConn(); 
											
		} catch(SQLException  ex) {
			// Errores
			System.out.println("SQLException: "+ ex.getMessage());
			System.out.println("SQLState: "+ ex.getSQLState());
			System.out.println("VendorError: "+ ex.getErrorCode());
		}
		return pac;
	} // Fin del getById
	
	
	public Paciente existeUsuario(String email, String dni) throws SQLException {

		PreparedStatement stmt = null;
		ResultSet rs = null;
		Paciente pac = null;
		ObrasSocialesController osCtrl = new ObrasSocialesController();
		String consulta = "select id, dni, nombre, apellido, num_tel, email, id_obra_social \r\n"
				+ " from pacientes where email=? or dni=?";
		try{
			// Crear la conexión
			stmt = DbConnector.getInstancia().getConn().prepareStatement(consulta);
			
			// Ejecutar la query
			stmt.setString(1, email);
			stmt.setString(2, dni);
			rs = stmt.executeQuery();
			
			// Mapeo de ResultSet a objeto
			if(rs!= null && rs.next()) {
				ObraSocial obraSocial = osCtrl.getByCodigo(rs.getInt("id_obra_social"));
				pac = new Paciente(
									rs.getInt("id"),
									rs.getString("nombre"),
									rs.getString("apellido"),
									rs.getString("dni"),
									rs.getString("num_tel"),
									rs.getString("email"),
									obraSocial
									); 

						} // Fin del if
			
			// Cerrar recursos
			if(stmt!=null) {stmt.close();}
			if(rs!=null) {rs.close();}
			DbConnector.getInstancia().releaseConn(); 
											
		} catch(SQLException  ex) {
			// Errores
			System.out.println("SQLException: "+ ex.getMessage());
			System.out.println("SQLState: "+ ex.getSQLState());
			System.out.println("VendorError: "+ ex.getErrorCode());
		}
		return pac;
	} 
	
	
	public LinkedList<Turnos> getTurnosPaciente(Paciente p) throws SQLException{
		LinkedList<Turnos> turnosPaciente = new LinkedList<>();
		PreparedStatement stmt=null;
		ResultSet rs=null;
		ProfesionalController profesionalCtrl = new ProfesionalController();
		String consulta = "select t.numero, t.fecha_turno, t.hora_turno, t.matricula_prof, t.id_paciente \r\n"
				+ "from turnos t \r\n"
				+ "inner join pacientes p \r\n"
				+ "	on t.id_paciente = p.id \r\n"
				+ "where t.id_paciente = ?";
		
		stmt = DbConnector.getInstancia().getConn().prepareStatement(consulta);
		stmt.setInt(1, p.getId());
		rs = stmt.executeQuery();
		
		if (rs!=null) {
			while(rs.next()) {
				Profesional profesional = new Profesional();
				profesional = profesionalCtrl.getByMatricula(rs.getString("matricula_prof"));
				Turnos t = new Turnos(
										rs.getInt("numero"),
										rs.getObject("fecha_turno", LocalDate.class),
										rs.getObject("hora_turno", LocalTime.class),
										profesional
										);
				turnosPaciente.add(t);
					} // Fin del while
		} // Fin del if
		
		if(rs!=null) {rs.close();}
		if(stmt!=null) {stmt.close();}
		DbConnector.getInstancia().releaseConn();
		
		return turnosPaciente;
	}
		
	

}

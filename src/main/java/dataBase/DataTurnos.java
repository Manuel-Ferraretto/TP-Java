package dataBase;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;

import entities.Especialidad;
import entities.Paciente;
import entities.Profesional;
import entities.Turnos;
import logic.PacientesController;
import logic.ProfesionalController;

public class DataTurnos {

	public void deleteByMatricula(String matricula) {
		// TODO Auto-generated method stub
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement("delete from turnos where matricula_prof=? ");
			stmt.setString(1, matricula);
			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}

	public LinkedList<Turnos> getAll() {
		// TODO Auto-generated method stub
		Statement stmt=null;
		ResultSet rs=null;
		LinkedList<Turnos> turnos = new LinkedList<>();
		ProfesionalController profController = new ProfesionalController();
		PacientesController pacController = new PacientesController();
		try {
					
		// Ejecutar la query
		stmt = DbConnector.getInstancia().getConn().createStatement();
		rs = stmt.executeQuery("select * from turnos");
					
		// Mapeao de ResultSet a objeto
		if (rs!=null) {
			while(rs.next()) {
				Paciente paciente = pacController.getPacientById(rs.getInt("id_paciente"));
				Profesional profesional = profController.getByMatricula(rs.getString("matricula_prof"));
				Turnos t = new Turnos(
										rs.getObject("fecha_turno", LocalDate.class),
										rs.getObject("hora_turno", LocalTime.class),
										profesional,
										paciente);
				
				turnos.add(t);
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
		
		return turnos;	
	}
	
	public LinkedList<Turnos> getAllTurnosPacienteActual(int ID) {
		// TODO Auto-generated method stub
		PreparedStatement stmt = null;
		ResultSet rs = null;
		LinkedList<Turnos> turnos = new LinkedList<>();
		ProfesionalController profController = new ProfesionalController();
		PacientesController pacController = new PacientesController();
		
		String consulta = "select t.fecha_turno, t.matricula_prof \r\n"
				+ "from profesionales p \r\n"
				+ "inner join especialidades e\r\n"
				+ "	on p.cod_especialidad = e.codigo_esp\r\n"
				+ "inner join turnos t\r\n"
				+ "	on t.matricula_prof = p.matricula\r\n"
				+ "where t.id_paciente = ?";
		try {
			// Crear la conexión
			stmt = DbConnector.getInstancia().getConn().prepareStatement(consulta);

			// Ejecutar la query
			stmt.setInt(1, ID);
			rs = stmt.executeQuery();

			// Mapeo de ResultSet a objeto
			if (rs!=null) {
				while(rs.next()) {
					Paciente paciente = pacController.getPacientById(rs.getInt("id_paciente"));
					Profesional profesional = profController.getByMatricula(rs.getString("matricula_prof"));
					Turnos t = new Turnos(
											rs.getObject("fecha_turno", LocalDate.class),
											rs.getObject("hora_turno", LocalTime.class),
											profesional,
											paciente);
					
					turnos.add(t);
						} // Fin del while
			} // Fin del if

			// Cerrar recursos
			if (stmt != null) {
				stmt.close();
			}
			if (rs != null) {
				rs.close();
			}
			DbConnector.getInstancia().releaseConn();

		} catch (SQLException ex) {
			// Errores
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		return turnos;
	}
	
	
	public Boolean validateAvailability(Turnos t)throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Boolean availability = true;
		Paciente p = new Paciente();
		
		String consulta = "select id_paciente from turnos where matricula_prof=? and fecha_turno=? and hora_turno=?";
		
		try{
			// Crear la conexión
			stmt = DbConnector.getInstancia().getConn().prepareStatement(consulta);
			
			// Ejecutar la query
			stmt.setString(1, t.getProfesional().getMatricula());
			stmt.setDate(2, Date.valueOf(t.getFecha_turno()));
			stmt.setTime(3, Time.valueOf(t.getHora_turno()));
			rs = stmt.executeQuery();
			
			// Mapeo de ResultSet a objeto
			if(rs!= null && rs.next()) {
				p.setId(rs.getInt("id_paciente"));
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
		if (p.getId() == null) {availability = true;}
		else {availability = false;}
		
		return availability;
	}
}

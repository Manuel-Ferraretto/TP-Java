package dataBase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import entities.ObraSocial;
import entities.Paciente;
import logic.ObrasSocialesController;

public class DataPacientes {

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
		
	

}

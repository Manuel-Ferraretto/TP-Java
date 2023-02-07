package dataBase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;


import entities.Especialidad;
import entities.Especialidad_ObralSocial;
import entities.ObraSocial;
import entities.Paciente;
import entities.Profesional;
import entities.Turnos;

public class DataEspecialidad_ObraSocial {

	public void delete(int codigo_especialidad) {
		// TODO Auto-generated method stub
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement("delete from especialidad_obrasocial where cod_especialidad=? ");
			stmt.setInt(1, codigo_especialidad);
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

	public void delete2(ObraSocial ob) {
		// TODO Auto-generated method stub
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement("delete from especialidad_obrasocial where id_os=? ");
			stmt.setInt(1, ob.getId_obra_social());
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

	public LinkedList<Especialidad_ObralSocial> getEspecialidadesIncluidad(ObraSocial ob) {
		// TODO Auto-generated method stub
		PreparedStatement stmt = null;
		ResultSet rs = null;
		LinkedList<Especialidad_ObralSocial> especialidades = new LinkedList<>();
		String consulta = "select * from especialidad_obrasocial where id_os=?";
		try{
			// Crear la conexión
			stmt = DbConnector.getInstancia().getConn().prepareStatement(consulta);
			
			// Ejecutar la query
			stmt.setInt(1, ob.getId_obra_social());
			rs = stmt.executeQuery();
			
			// Mapeao de ResultSet a objeto
			if (rs!=null) {
				while(rs.next()) {
					Especialidad_ObralSocial esp = new Especialidad_ObralSocial();
					esp.setId_os(rs.getInt("id_os"));
					esp.setCod_especialidad(rs.getInt("cod_especialidad"));
					esp.setProcentaje_cobertura(rs.getFloat("porcentaje_cobertura"));
					
					especialidades.add(esp);
						} // Fin del while
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
		return especialidades;
	}

	public LinkedList<Especialidad> getEspecialidadesNoIncluidad(ObraSocial ob) {
		// TODO Auto-generated method stub
		PreparedStatement stmt = null;
		ResultSet rs = null;
		LinkedList<Especialidad> especialidades = new LinkedList<>();
		String consulta = "select * from especialidades where codigo_esp not in(select cod_especialidad from especialidad_obrasocial where id_os = ? )";
		try{
			// Crear la conexión
			stmt = DbConnector.getInstancia().getConn().prepareStatement(consulta);
			
			// Ejecutar la query
			stmt.setInt(1, ob.getId_obra_social());
			rs = stmt.executeQuery();
			
			// Mapeao de ResultSet a objeto
			if (rs!=null) {
				while(rs.next()) {
					Especialidad esp = new Especialidad();
					esp.setCodigo_esp(rs.getInt("codigo_esp"));
					esp.setNombre(rs.getString("nombre"));
					
					especialidades.add(esp);
						} // Fin del while
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
		return especialidades;
	}

	public void delete3(Especialidad_ObralSocial espob) {
		// TODO Auto-generated method stub
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement("delete from especialidad_obrasocial where id_os=? and cod_especialidad=?");
			stmt.setInt(1, espob.getId_os());
			stmt.setInt(2, espob.getCod_especialidad());
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

	public void add(Especialidad_ObralSocial espob) {
		// TODO Auto-generated method stub
		PreparedStatement stmt= null;
		
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"insert into especialidad_obrasocial(porcentaje_cobertura,id_os,cod_especialidad) values(?,?,?)"
							);
			stmt.setFloat(1, espob.getProcentaje_cobertura());
			stmt.setInt(2, espob.getId_os());
			stmt.setInt(3, espob.getCod_especialidad());
			stmt.executeUpdate();
			
			

			
		}  catch (SQLException e) {
            e.printStackTrace();
		} finally {
            try {
                
                if(stmt!=null)stmt.close();
                DbConnector.getInstancia().releaseConn();
            } catch (SQLException e) {
            	e.printStackTrace();
            }
		}
		
	}
	
	
	

}

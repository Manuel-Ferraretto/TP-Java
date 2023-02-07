package dataBase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalTime;
import java.util.LinkedList;

import entities.Especialidad;
import entities.Paciente;
import entities.Profesional;
import logic.EspecialidadesController;
import logic.ProfesionalController;

public class DataProfesionales {

	public LinkedList<Profesional> getByEspecialidad(int codigo_especialidad) {
		// TODO Auto-generated method stub
		PreparedStatement stmt = null;
		ResultSet rs = null;
		LinkedList<Profesional> profesionales = new LinkedList<>();
		EspecialidadesController especilidadesController = new EspecialidadesController();
		String consulta = "select * from profesionales where cod_especialidad=?";
		try {
			// Crear la conexión
			stmt = DbConnector.getInstancia().getConn().prepareStatement(consulta);

			// Ejecutar la query
			stmt.setInt(1, codigo_especialidad);
			rs = stmt.executeQuery();

			// Mapeo de ResultSet a objeto
			if (rs != null) {
				while (rs.next()) {
					Especialidad especialidadProfActual = new Especialidad();
					especialidadProfActual = especilidadesController.getByCodigo(codigo_especialidad);
					Profesional p = new Profesional(
													rs.getString("matricula"),
													rs.getString("email"),
													rs.getString("nombre"),
													rs.getString("apellido"),
													rs.getInt("estado"),
													rs.getObject("hora_inicio", LocalTime.class),
													rs.getObject("hora_fin", LocalTime.class),
													especialidadProfActual);

					profesionales.add(p);
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
		return profesionales;
	}
	
	
	public LinkedList<Profesional> getDisponiblesByEspecialidad(int codigo_especialidad) {
		// TODO Auto-generated method stub
		PreparedStatement stmt = null;
		ResultSet rs = null;
		LinkedList<Profesional> profesionales = new LinkedList<>();
		EspecialidadesController especilidadesController = new EspecialidadesController();
		String consulta = "select * from profesionales where cod_especialidad=?";
		try {
			// Crear la conexión
			stmt = DbConnector.getInstancia().getConn().prepareStatement(consulta);

			// Ejecutar la query
			stmt.setInt(1, codigo_especialidad);
			rs = stmt.executeQuery();

			// Mapeo de ResultSet a objeto
			if (rs != null) {
				while (rs.next()) {
					Especialidad especialidadProfActual = new Especialidad();
					especialidadProfActual = especilidadesController.getByCodigo(codigo_especialidad);
					Profesional p = new Profesional(
													rs.getString("matricula"),
													rs.getString("email"),
													rs.getString("nombre"),
													rs.getString("apellido"),
													rs.getInt("estado"),
													rs.getObject("hora_inicio", LocalTime.class),
													rs.getObject("hora_fin", LocalTime.class),
													especialidadProfActual);

					profesionales.add(p);
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
		return profesionales;
	}
	
	
	
	

	public LinkedList<Profesional> getAll() {
		// TODO Auto-generated method stub
		Statement stmt = null;
		ResultSet rs = null;
		LinkedList<Profesional> profesionales = new LinkedList<>();
		EspecialidadesController especilidadesController = new EspecialidadesController();
		try {

			// Ejecutar la query
			stmt = DbConnector.getInstancia().getConn().createStatement();
			rs = stmt.executeQuery("select * from profesionales");

			// Mapeao de ResultSet a objeto
			if (rs != null) {
				while (rs.next()) {
					Especialidad especialidadProfActual = new Especialidad();
					especialidadProfActual = especilidadesController.getByCodigo(rs.getInt("cod_especialidad"));
					Profesional p = new Profesional(
													rs.getString("matricula"),
													rs.getString("email"),
													rs.getString("nombre"),
													rs.getString("apellido"),
													rs.getInt("estado"),
													rs.getObject("hora_inicio", LocalTime.class),
													rs.getObject("hora_fin", LocalTime.class),
													especialidadProfActual);
					
					profesionales.add(p);
				} // Fin del while
			} // Fin del if

		} catch (SQLException ex) {
			ex.printStackTrace();

		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}

		return profesionales;
	}

	public Profesional getByEmailMatricula(Profesional p) {
		// TODO Auto-generated method stub
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Profesional profesional = null;
		EspecialidadesController especilidadesController = new EspecialidadesController();
		String consulta = "select * from profesionales where email=? or matricula=?";
		try {
			// Crear la conexión
			stmt = DbConnector.getInstancia().getConn().prepareStatement(consulta);

			// Ejecutar la query
			stmt.setString(1, p.getEmail());
			stmt.setString(2, p.getMatricula());
			rs = stmt.executeQuery();

			// Mapeo de ResultSet a objeto
			if (rs != null && rs.next()) {
				Especialidad especialidadProfActual = new Especialidad();
				especialidadProfActual = especilidadesController.getByCodigo(rs.getInt("cod_especialidad"));
				profesional = new Profesional(
											  rs.getString("matricula"),
											  rs.getString("email"),
											  rs.getString("nombre"),
										      rs.getString("apellido"),
											  rs.getInt("estado"),
											  rs.getObject("hora_inicio", LocalTime.class),
										      rs.getObject("hora_fin", LocalTime.class),
											  especialidadProfActual);
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
		return profesional;
	}

	public void add(Profesional p2) {
		// TODO Auto-generated method stub
		PreparedStatement stmt = null;
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
					"insert into profesionales(matricula,nombre,apellido,email,cod_especialidad,estado) values(?,?,?,?,?,?,?)");
			stmt.setString(1, p2.getMatricula());
			stmt.setString(2, p2.getNombre());
			stmt.setString(3, p2.getApellido());
			stmt.setString(4, p2.getEmail());
			stmt.setInt(6, p2.getEspecialidad().getCodigo_esp());
			stmt.setInt(7, p2.getEstado());
			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public Profesional getByMatricula(String matricula) {
		// TODO Auto-generated method stub
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Profesional profesional = null;
		EspecialidadesController especilidadesController = new EspecialidadesController();
		String consulta = "select * from profesionales where matricula=?";
		try {
			// Crear la conexión
			stmt = DbConnector.getInstancia().getConn().prepareStatement(consulta);

			// Ejecutar la query
			stmt.setString(1, matricula);
			rs = stmt.executeQuery();

			// Mapeo de ResultSet a objeto
			if (rs != null && rs.next()) {
				Especialidad especialidadProfActual = new Especialidad();
				especialidadProfActual = especilidadesController.getByCodigo(rs.getInt("cod_especialidad"));
				profesional = new Profesional(
											  rs.getString("matricula"),
											  rs.getString("email"),
											  rs.getString("nombre"),
										      rs.getString("apellido"),
											  rs.getInt("estado"),
											  rs.getObject("hora_inicio", LocalTime.class),
										      rs.getObject("hora_fin", LocalTime.class),
											  especialidadProfActual);
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
		return profesional;
	}

	public void update(Profesional p2, String matricula) {
		// TODO Auto-generated method stub
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
					"update profesionales set matricula=?,nombre=?,apellido=?,email=?,cod_especialidad=?,estado=? where matricula=? ");
			stmt.setString(1, p2.getMatricula());
			stmt.setString(2, p2.getNombre());
			stmt.setString(3, p2.getApellido());
			stmt.setString(4, p2.getEmail());
			stmt.setInt(5, p2.getEspecialidad().getCodigo_esp());
			stmt.setInt(6, p2.getEstado());
			stmt.setString(7, matricula);

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

	public void delete(Profesional p) {
		// TODO Auto-generated method stub
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = DbConnector.getInstancia().getConn()
					.prepareStatement("delete from profesionales where matricula=? ");
			stmt.setString(1, p.getMatricula());
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
	
	public LinkedList<Profesional> getNombres(Paciente p) {
		// TODO Auto-generated method stub
		PreparedStatement stmt=null;
		ResultSet rs=null;
		LinkedList<Profesional> profesionales = new LinkedList<>();

		String consulta = "select p. matricula, p.nombre, p.apellido \r\n"
				+ "from profesionales p \r\n"
				+ "inner join especialidades e\r\n"
				+ "	on p.cod_especialidad = e.codigo_esp \r\n"
				+ "inner join turnos t \r\n"
				+ "	on t.matricula_prof = p.matricula \r\n"
				+ "where t.id_paciente = ?";
		try {
			// Crear la conexión
			stmt = DbConnector.getInstancia().getConn().prepareStatement(consulta);

			// Ejecutar la query
			stmt.setInt(1, p.getId());
			rs = stmt.executeQuery();

			// Mapeo de ResultSet a objeto
			if (rs != null) {
				while (rs.next()) {
					Profesional profesional = new Profesional(
												  				rs.getString("matricula"),
												  				rs.getString("nombre"),
												  				rs.getString("apellido"));
											      
					profesionales.add(profesional);
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
		return profesionales;
	}
	
	
	public boolean validarMatricula(String matricula) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		boolean matriculaValida = true;
		String nuevaMatricula = null;

		String consulta = "select matricula from profesionales where matricula=?";
		try {
			// Crear la conexión
			stmt = DbConnector.getInstancia().getConn().prepareStatement(consulta);

			// Ejecutar la query
			stmt.setString(1, matricula);
			rs = stmt.executeQuery();

			// Mapeo de ResultSet a objeto
			if (rs != null && rs.next()) {
				nuevaMatricula = rs.getString("matricula");
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
		
		if (nuevaMatricula == null) { matriculaValida = false;}
		
		return matriculaValida;
	}
	
	public boolean validarEmail(String email) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		boolean emailValido = true;
		String nuevoEmail = null;

		String consulta = "select email from profesionales where email=?";
		try {
			// Crear la conexión
			stmt = DbConnector.getInstancia().getConn().prepareStatement(consulta);

			// Ejecutar la query
			stmt.setString(1, email);
			rs = stmt.executeQuery();

			// Mapeo de ResultSet a objeto
			if (rs != null && rs.next()) {
				nuevoEmail = rs.getString("email");
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
		
		if (nuevoEmail == null) { emailValido = false;}
		
		return emailValido;
	}

	public void modificarEstado(String matricula) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Profesional p = new Profesional();
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
					"update profesionales set estado=? where matricula=? ");
			
		p = this.getByMatricula(matricula);
		
		if (p.getEstado() == 1) {
			stmt.setInt(1, 0);
			stmt.setString(2, matricula);
			stmt.executeUpdate();
		}
		else {
			stmt.setInt(1, 1);
			stmt.setString(2, matricula);
			stmt.executeUpdate();
		}
			
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

}

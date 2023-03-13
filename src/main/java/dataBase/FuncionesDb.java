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
import entities.Especialidad_ObralSocial;
import entities.ObraSocial;
import entities.Paciente;
import entities.Profesional;
import entities.Turnos;
import entities.Valor_especialidad;
import logic.EspecialidadesController;
import logic.ObrasSocialesController;
import logic.ProfesionalController;

public class FuncionesDb {
	
	
	public LinkedList<Paciente> getAll() throws SQLException {
		
		Statement stmt=null;
		ResultSet rs=null;
		LinkedList<Paciente> pacientes = new LinkedList<>();
		try {
					
		// Ejecutar la query
		stmt = DbConnector.getInstancia().getConn().createStatement();
		rs = stmt.executeQuery("select * from pacientes");
					
		// Mapeao de ResultSet a objeto
		if (rs!=null) {
			while(rs.next()) {
				Paciente p = new Paciente();
				p.setId(rs.getInt("id"));
				p.setDni(rs.getString("dni"));
				p.setNombre(rs.getString("nombre"));
				p.setApellido(rs.getString("apellido"));
				p.setNum_tel(rs.getString("num_tel"));
				p.setEmail(rs.getString("email"));
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
	} // fin getAll
	
	public LinkedList<Profesional> getByEspecialidad(Especialidad e) throws SQLException {
		PreparedStatement stmt=null;
		ResultSet rs=null;
		LinkedList<Profesional> profesionales = new LinkedList<>();
		String consulta = "select prof.nombre, prof.apellido, prof.matricula \r\n"
				+ "from profesionales prof \r\n"
				+ "where prof.cod_especialidad =? and prof.estado=1";
		
		try {		
		// Ejecutar la query
		stmt = DbConnector.getInstancia().getConn().prepareStatement(consulta);
		stmt.setInt(1, e.getCodigo_esp());
		rs = stmt.executeQuery();
		
		// Mapeo de ResultSet a objeto
		if (rs!=null) {
			while(rs.next()) {
				Profesional prof = new Profesional();
				prof.setNombre(rs.getString("nombre"));
				prof.setApellido(rs.getString("apellido"));
				prof.setMatricula(rs.getString("matricula"));
				profesionales.add(prof);
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
		
		return profesionales;
	} // Fin getByEspecialidad
	
	public Especialidad getEspecialidadByCodigo(Especialidad e) {
		PreparedStatement stmt=null;
		ResultSet rs=null;
		String consulta = "select e.nombre from especialidades e where e.codigo_esp =?";

		try {		
		// Ejecutar la query
		stmt = DbConnector.getInstancia().getConn().prepareStatement(consulta);
		stmt.setInt(1, e.getCodigo_esp());
		rs = stmt.executeQuery();
		
		// Mapeo de ResultSet a objeto
		while(rs.next()) {
			if (rs!=null) {		
				e.setNombre(rs.getString("nombre"));
						} // Fin del if
		}
		
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

		return e;
	} // Fin getEspecialidadByCodigo
	
	public void altaPaciente(Paciente p) {
		PreparedStatement stmt=null;	
		try {		
		// Ejecutar la query
		stmt = DbConnector.getInstancia().getConn().prepareStatement(
				"insert into pacientes (nombre, apellido, dni, email, password, num_tel, id_obra_social) values(?,?,?,?,?,?,?)"
				,PreparedStatement.RETURN_GENERATED_KEYS);
		stmt.setString(1, p.getNombre());
		stmt.setString(2, p.getApellido());
		stmt.setString(3, p.getDni());
		stmt.setString(4, p.getEmail());
		stmt.setString(5, p.getPassword());
		stmt.setString(6, p.getNum_tel());
		stmt.setInt(7, p.getObra_social().getId_obra_social());
		
		stmt.executeUpdate();
		ResultSet Keyrs = stmt.getGeneratedKeys(); 
		
		if (Keyrs != null && Keyrs.next()) {
			int id = Keyrs.getInt(1);
			p.setId(id);		
		}		
		
		} catch(SQLException  ex) {
			ex.printStackTrace();
			
		} finally {
			DbConnector.getInstancia().releaseConn();
		}
	}  // Fin altaPaciente
	
	public LinkedList<Turnos> getTurnos(Profesional p) throws SQLException {
		LinkedList<Turnos> turnos = new LinkedList<>();
		PreparedStatement stmt=null;
		ResultSet rs=null;
		String consulta = "select t.fecha_turno, t.hora_turno, t.numero \r\n"
				+ "from turnos t \r\n"
				+ "inner join profesionales p \r\n"
				+ "	on p.matricula = t.matricula_prof \r\n"
				+ "where p.matricula = ? and t.id_paciente is null \r\n"
				+ "order by t.fecha_turno, t.hora_turno";
			
		stmt = DbConnector.getInstancia().getConn().prepareStatement(consulta);
		stmt.setString(1, p.getMatricula());
		rs = stmt.executeQuery();
		
		if (rs!=null) {
			while(rs.next()) {
				Turnos t = new Turnos();
				t.setFecha_turno(rs.getObject("fecha_turno", LocalDate.class));
				t.setHora_turno(rs.getObject("hora_turno", LocalTime.class));
				t.setNumero(rs.getInt("numero"));
				turnos.add(t);
					} // Fin del while
		} // Fin del if
		
		if(rs!=null) {rs.close();}
		if(stmt!=null) {stmt.close();}
		DbConnector.getInstancia().releaseConn();
		
		return turnos;
	} // Fin getTurnos
	
	public Profesional getProfesional(Profesional p) throws SQLException {
		PreparedStatement stmt=null;
		ResultSet rs=null;
		EspecialidadesController especialidadCtrl = new EspecialidadesController();
		String consulta = "select p.nombre, p.apellido, p.cod_especialidad, p.horaInicio, p.horaFin from profesionales p "
						+	 "where p.matricula = ?";
		
		stmt = DbConnector.getInstancia().getConn().prepareStatement(consulta);
		stmt.setString(1, p.getMatricula());
		rs = stmt.executeQuery();
		
		if(rs!= null && rs.next()) {
				p.setNombre(rs.getString("nombre"));
				p.setApellido(rs.getString("apellido"));
				p.setEspecialidad(especialidadCtrl.getByCodigo(rs.getInt("cod_especialidad")));
				p.setHora_inicio(rs.getObject("horaInicio", LocalTime.class));
				p.setHora_fin(rs.getObject("horaFin", LocalTime.class));
					} // Fin if
		
		if(rs!=null) {rs.close();}
		if(stmt!=null) {stmt.close();}
		DbConnector.getInstancia().releaseConn();
		
		return p;
	} //fin getProfesional
	
	public void asignarTurno(Turnos t) throws SQLException {
		PreparedStatement stmt=null;
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
				"insert into turnos (fecha_turno, hora_turno, matricula_prof, id_paciente) values(?,?,?,?)"
				,PreparedStatement.RETURN_GENERATED_KEYS);
	
		stmt.setDate(1, Date.valueOf(t.getFecha_turno()));
		stmt.setTime(2, Time.valueOf(t.getHora_turno()));
		stmt.setString(3, t.getProfesional().getMatricula());  
		stmt.setInt(4, t.getPaciente().getId());

		stmt.executeUpdate();
		ResultSet Keyrs = stmt.getGeneratedKeys(); 
		
		if (Keyrs != null && Keyrs.next()) {
			int numero = Keyrs.getInt(1);
			t.setNumero(numero);		
		}		
		} catch(SQLException  ex) {
			ex.printStackTrace();
			
		} finally {
			DbConnector.getInstancia().releaseConn();
		}
	} //fin asignarTurno
		
	public Valor_especialidad getValorEspecialidad(Profesional p) throws SQLException {
		PreparedStatement stmt=null;
		ResultSet rs=null;
		Valor_especialidad valor_esp = new Valor_especialidad(); 
		String consulta = "select max(ve.valor) valor \r\n"
				+ "from valor_especialidad ve \r\n"
				+ "inner join especialidades e \r\n"
				+ "	on e.codigo_esp = ve.cod_especialidad \r\n"
				+ "where e.codigo_esp = ?";	
		
		stmt = DbConnector.getInstancia().getConn().prepareStatement(consulta);
		stmt.setInt(1, p.getEspecialidad().getCodigo_esp());
		rs = stmt.executeQuery();
		
		if(rs!= null && rs.next()) {
				valor_esp.setValor(rs.getInt("valor"));
					} // Fin del if
		
		if(rs!=null) {rs.close();}
		if(stmt!=null) {stmt.close();}
		DbConnector.getInstancia().releaseConn();
		
		return valor_esp;
		
	} // fin getValorEspecialidad
	
	
	public Turnos getTurno(Turnos t) throws SQLException {
		PreparedStatement stmt=null;
		ResultSet rs=null;
		ProfesionalController profesionalCtrl = new ProfesionalController();
		Turnos turno = new Turnos();
		String consulta = "select t.numero, t.fecha_turno, t.hora_turno, t.matricula_prof from turnos t \r\n"
				+ "where t.numero = ?";
		
		stmt = DbConnector.getInstancia().getConn().prepareStatement(consulta);
		stmt.setInt(1, t.getNumero());
		rs = stmt.executeQuery();
		
		if(rs!= null && rs.next()) {
			turno.setNumero(rs.getInt("numero"));
			turno.setFecha_turno(rs.getObject("fecha_turno", LocalDate.class));
			turno.setHora_turno(rs.getObject("hora_turno", LocalTime.class));
			turno.setProfesional(profesionalCtrl.getByMatricula(rs.getString("matricula_prof")));
			
		} //fin del if
		
		if(rs!=null) {rs.close();}
		if(stmt!=null) {stmt.close();}
		DbConnector.getInstancia().releaseConn();
		
		return turno;
	}
	
	public ObraSocial getObraSocial(Paciente p) throws SQLException {
		PreparedStatement stmt=null;
		ResultSet rs=null;
		ObraSocial os = new ObraSocial();
		String consulta = "select os.nombre, os.id_obra_social from obras_sociales os \r\n"
				+ "inner join pacientes p \r\n"
				+ "	on os.id_obra_social = p.id_obra_social \r\n"
				+ "where p.id_obra_social = ?";
		
		stmt = DbConnector.getInstancia().getConn().prepareStatement(consulta);
		stmt.setInt(1, p.getObra_social().getId_obra_social());
		rs = stmt.executeQuery();
		
		if(rs!= null && rs.next()) {
			os.setNombre(rs.getString("nombre"));
			os.setId_obra_social(rs.getInt("id_obra_social"));
		} //fin del if
		
		if(rs!=null) {rs.close();}
		if(stmt!=null) {stmt.close();}
		DbConnector.getInstancia().releaseConn();
		
		return os;
	}
	
	public Especialidad_ObralSocial getPorcentajeCobertura(Integer e, Integer os) throws SQLException {
		PreparedStatement stmt=null;
		ResultSet rs=null;
		Especialidad_ObralSocial esp_os = new Especialidad_ObralSocial();
		String consulta = "select e_os.porcentaje_cobertura from especialidad_obrasocial e_os \r\n"
				+ "where e_os.cod_especialidad = ? and e_os.id_os = ?";
		
		stmt = DbConnector.getInstancia().getConn().prepareStatement(consulta);
		stmt.setInt(1, e);
		stmt.setInt(2, os);
		rs = stmt.executeQuery();
		
		if(rs!= null && rs.next()) {
			esp_os.setProcentaje_cobertura(rs.getFloat("porcentaje_cobertura"));
		} //fin del if
		
		if(rs!=null) {rs.close();}
		if(stmt!=null) {stmt.close();}
		DbConnector.getInstancia().releaseConn();
		
		return esp_os;
	}
	
	
	public void cancelarTurno(Integer nro_turno) throws SQLException {
		PreparedStatement stmt=null;
		String consulta = "update turnos set id_paciente = null \r\n"
				+ "where numero = ?";
		
		stmt = DbConnector.getInstancia().getConn().prepareStatement(consulta);
		stmt.setInt(1, nro_turno);
		stmt.executeUpdate();
		
		if(stmt!=null) {stmt.close();}
		DbConnector.getInstancia().releaseConn();
		
	} //fin cancelarTurno
	
	
	public void actualizarDatosPaciente(Paciente p) throws SQLException {
		PreparedStatement stmt=null;
		String consulta = "update pacientes set email=?, password=?, num_tel=? \r\n"
				+ "where id=?";
		
		stmt = DbConnector.getInstancia().getConn().prepareStatement(consulta);
		stmt.setString(1, p.getEmail());
		stmt.setString(2, p.getPassword());
		stmt.setString(3, p.getNum_tel());
		stmt.setInt(4, p.getId());
		stmt.executeUpdate();
		
		if(stmt!=null) {stmt.close();}
		DbConnector.getInstancia().releaseConn();
		
	} //fin cancelarTurno
}

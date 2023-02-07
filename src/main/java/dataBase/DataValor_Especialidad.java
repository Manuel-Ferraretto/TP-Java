package dataBase;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.LinkedList;

import entities.Especialidad;
import entities.Valor_especialidad;
import logic.EspecialidadesController;

public class DataValor_Especialidad {

	public void delete(int codigo_especialidad) {
		// TODO Auto-generated method stub
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement("delete from valor_especialidad where cod_especialidad=? ");
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

	public LinkedList<Valor_especialidad> getValoresActuales() {
		// TODO Auto-generated method stub
		Statement stmt=null;
		ResultSet rs=null;
		LinkedList<Valor_especialidad> valores = new LinkedList<>();
		EspecialidadesController especialidadController = new EspecialidadesController();
		try {
					
		// Ejecutar la query
		stmt = DbConnector.getInstancia().getConn().createStatement();
		rs = stmt.executeQuery("select valor.fecha,valor.cod_especialidad,valor_especialidad.valor from valor_especialidad\r\n"
				+ "inner join (\r\n"
				+ "select max(fecha_desde) fecha, cod_especialidad from valor_especialidad\r\n"
				+ "group by cod_especialidad) valor\r\n"
				+ "on valor.fecha=valor_especialidad.fecha_desde and valor.cod_especialidad=valor_especialidad.cod_especialidad");
					
		// Mapeao de ResultSet a objeto
		if (rs!=null) {
			while(rs.next()) {
				Valor_especialidad val = new Valor_especialidad();
				Especialidad especialidad = especialidadController.getByCodigo(rs.getInt("cod_especialidad"));
				val.setEspecialidad(especialidad);
				val.setValor(rs.getInt("valor"));
				valores.add(val);
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
		
		return valores;
	}

	public void insertarValor(Valor_especialidad ve) {
		// TODO Auto-generated method stub
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"insert into valor_especialidad(fecha_desde,cod_especialidad,valor) values(?,?,?)"
							);
			stmt.setDate(1, Date.valueOf(ve.getFecha_desde()));
			stmt.setInt(2, ve.getEspecialidad().getCodigo_esp());
			stmt.setInt(3, ve.getValor());
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

	public Valor_especialidad getValorPorCodigo(Valor_especialidad ve) {
		// TODO Auto-generated method stub
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Valor_especialidad val = null;
		EspecialidadesController especialidadesController = new EspecialidadesController();
		String consulta = "select valor.fecha,valor.cod_especialidad,valor_especialidad.valor from valor_especialidad\r\n"
				+ "inner join (\r\n"
				+ "select max(fecha_desde) fecha, cod_especialidad from valor_especialidad\r\n"
				+ "group by cod_especialidad) valor\r\n"
				+ "on valor.fecha=valor_especialidad.fecha_desde and valor.cod_especialidad=valor_especialidad.cod_especialidad\r\n"
				+ "where valor.cod_especialidad=?";
		try{
			// Crear la conexión
			stmt = DbConnector.getInstancia().getConn().prepareStatement(consulta);
			
			// Ejecutar la query
			stmt.setInt(1, ve.getEspecialidad().getCodigo_esp());
			rs = stmt.executeQuery();
			
			// Mapeo de ResultSet a objeto
			if(rs!= null && rs.next()) {
				val = new Valor_especialidad();
				Especialidad e = new Especialidad();
				e = especialidadesController.getByCodigo(rs.getInt("cod_especialidad"));
				val.setEspecialidad(e);
				val.setValor(rs.getInt("valor"));
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
		return val;
	}

}

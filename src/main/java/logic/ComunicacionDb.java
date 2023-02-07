package logic;

import java.sql.SQLException;
import java.util.LinkedList;

import dataBase.FuncionesDb;
import entities.Especialidad;
import entities.Especialidad_ObralSocial;
import entities.ObraSocial;
import entities.Paciente;
import entities.Profesional;
import entities.Turnos;
import entities.Valor_especialidad;

public class ComunicacionDb {
	
private FuncionesDb dc;
	
	public ComunicacionDb(){
		dc = new FuncionesDb();
	}
	
	public Paciente validateLogin(Paciente c) throws SQLException {
		
		return dc.getByUser(c);		
	}

	public LinkedList<Paciente> getAll() throws SQLException {

		return dc.getAll();
	}
	
	public LinkedList<Profesional> getByEspecialidad(Especialidad e) throws SQLException {

		return dc.getByEspecialidad(e);
	}
	
	public Especialidad getEspecialidadByCodigo(Especialidad e) throws SQLException {
		
		return dc.getEspecialidadByCodigo(e);	
	}
	
	public void altaPaciente(Paciente c) throws SQLException {
		
			dc.altaPaciente(c);
	}
	
	public LinkedList<Turnos> getTurnos(Profesional p) throws SQLException {
	
		return dc.getTurnos(p);
	}
	
	public Profesional getProfesional(Profesional p) throws SQLException {
		return dc.getProfesional(p);
	}
	
	public void asignarTurno(Turnos t) throws SQLException {
		dc.asignarTurno(t);
	}
	
	public Valor_especialidad getValorEspecialidad(Profesional p) throws SQLException {
		return dc.getValorEspecialidad(p);
	}
	
	public Turnos getTurno(Turnos t) throws SQLException {
		return dc.getTurno(t);
	}
	
	public ObraSocial getObraSocial(Paciente p) throws SQLException {
		return dc.getObraSocial(p);
	}
	
	public Especialidad_ObralSocial getPorcentajeCobertura(Integer e, Integer os) throws SQLException {
		return dc.getPorcentajeCobertura(e, os);
	}
	
	public LinkedList<Turnos> getTurnosPaciente(Paciente p) throws SQLException{
		return dc.getTurnosPaciente(p);
	}
	
	public void cancelarTurno(Integer nro_turno) throws SQLException {
		dc.cancelarTurno(nro_turno);
	}
	
	public Profesional getProfesionalByUser(Profesional p) throws SQLException {
		return dc.getProfesionalByUser(p);
	}
	
	public LinkedList<Turnos> getTurnosProfesional(Profesional p) throws SQLException{
		return dc.getTurnosProfesional(p);
	}
	
	public LinkedList<Paciente> getTurnosPacientesProfActual(Profesional p) throws SQLException{
		return dc.getTurnosPacientesProfActual(p);
	}
	
	public void actualizarDatosPaciente(Paciente p) throws SQLException {
		dc.actualizarDatosPaciente(p);
	}
	
}

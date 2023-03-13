package logic;

import java.sql.SQLException;
import java.util.LinkedList;

import dataBase.DataPacientes;
import entities.Paciente;
import entities.Turnos;

public class PacientesController {
	private DataPacientes dp;
	
	public PacientesController() {
		dp= new DataPacientes();
	}

	public LinkedList<Paciente> getAll() {
		// TODO Auto-generated method stub
		return dp.getAll();
	}
	
	public Paciente getPacientById(int ID) {
		return dp.getPacienteById(ID);
	}
	
	public Paciente existeUsuario(String email, String dni) throws SQLException {
		return dp.existeUsuario(email, dni);
	}
	
	public Paciente validateLogin(Paciente p) throws SQLException {
		return dp.validateLogin(p);
	}
	
	public LinkedList<Turnos> getTurnosPaciente(Paciente p) throws SQLException{
		return dp.getTurnosPaciente(p);
	}

}

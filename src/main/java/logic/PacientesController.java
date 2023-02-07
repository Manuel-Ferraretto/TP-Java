package logic;

import java.util.LinkedList;

import dataBase.DataPacientes;
import entities.Paciente;

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

}

package logic;

import java.util.LinkedList;

import dataBase.DataProfesionales;
import entities.Paciente;
import entities.Profesional;

public class ProfesionalController {
	private DataProfesionales dp;
	
	public ProfesionalController() {
		dp= new DataProfesionales();
	}

	public LinkedList<Profesional> getAll() {
		// TODO Auto-generated method stub
		return dp.getAll();
	}
	
	public LinkedList<Profesional> getDisponiblesByEspecialidad(int codigo_especialidad){
		return dp.getDisponiblesByEspecialidad(codigo_especialidad);
	}

	public Profesional getByEmailMatricula(Profesional p) {
		// TODO Auto-generated method stub
		return dp.getByEmailMatricula(p);
	}

	public void add(Profesional p2) {
		// TODO Auto-generated method stub
		dp.add(p2);
	}

	public Profesional getByMatricula(String matricula) {
		// TODO Auto-generated method stub
		return dp.getByMatricula(matricula);
	}

	public void update(Profesional p2, String matricula) {
		// TODO Auto-generated method stub
		dp.update(p2,matricula);
	}

	public void delete(Profesional p) {
		// TODO Auto-generated method stub
		dp.delete(p);
	}
	
	public LinkedList<Profesional> getNombres(Paciente p) {
		return dp.getNombres(p);
	}
	
	public boolean validarMatricula(String matricula) {
		return dp.validarMatricula(matricula);
	}
	
	public boolean validarEmail(String email) {
		return dp.validarEmail(email);
	}
	
	public void modificarEstado(String matricula) {
		dp.modificarEstado(matricula);
	}
	

}

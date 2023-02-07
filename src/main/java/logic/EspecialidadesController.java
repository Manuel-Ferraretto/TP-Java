package logic;

import java.sql.SQLException;
import java.util.LinkedList;

import dataBase.DataEspecialidad_ObraSocial;
import dataBase.DataEspecialidades;
import dataBase.DataProfesionales;
import dataBase.DataValor_Especialidad;
import entities.Especialidad;
import entities.Paciente;
import entities.Profesional;
import entities.Valor_especialidad;

public class EspecialidadesController {
	private DataEspecialidades de;
	private DataProfesionales dp;
	private DataEspecialidad_ObraSocial deo;
	private DataValor_Especialidad dve;
	
	public EspecialidadesController() {
		de = new DataEspecialidades();
		dp=new DataProfesionales();
		deo= new DataEspecialidad_ObraSocial();
		dve= new DataValor_Especialidad();
		}

	public LinkedList<Especialidad> getAll() throws SQLException {
		// TODO Auto-generated method stub
		return de.getAll();
	}

	public Especialidad getByNombre(String nombre) throws SQLException {
		// TODO Auto-generated method stub
		return  de.getByNombre(nombre);
	}

	public void add(Especialidad nueva_especialidad,Valor_especialidad nuevo_ve) {
		de.add(nueva_especialidad);
		nuevo_ve.setEspecialidad(nueva_especialidad);
		dve.insertarValor(nuevo_ve);
	}

	public Especialidad getByCodigo(int codigo) throws SQLException {
		// TODO Auto-generated method stub
		return de.getByCodigo(codigo);
	}

	public void update(Especialidad esp, Valor_especialidad ve)throws SQLException {
		de.update(esp);
		dve.insertarValor(ve);
	}

	public Integer delete(int codigo_especialidad) throws SQLException {
		LinkedList<Profesional> profesionales= new LinkedList<>();
		profesionales= dp.getByEspecialidad(codigo_especialidad);
		if (profesionales== null || profesionales.isEmpty()) {
		deo.delete(codigo_especialidad);
		dve.delete(codigo_especialidad);
		de.delete(codigo_especialidad);
		return 1;
		}else {
			return 0;
		}
		
	}

	public LinkedList<Valor_especialidad> getValoresActuales() {
		return dve.getValoresActuales();
	}

	public Valor_especialidad getValorPorCodigo(Valor_especialidad ve) {
		return dve.getValorPorCodigo(ve);
	}
	
	public LinkedList<Especialidad> getNombreEspecialidades(Paciente p) throws SQLException{
		return de.getNombres(p);
	}
	
	public boolean validarNombreNuevaEspecialidad(String nombre) throws SQLException {
		return de.validarNombreNuevaEspecialidad(nombre);
	}

}

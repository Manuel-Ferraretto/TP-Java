package entities;

import java.util.LinkedList;
import java.util.List;

public class ObraSocial {
	Integer id_obra_social;
	String nombre;
	List<Paciente> pacientes;
	
	public Integer getId_obra_social() {
		return id_obra_social;
	}
	public void setId_obra_social(Integer id_obra_social) {
		this.id_obra_social = id_obra_social;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public List<Paciente> getPacientes() {
		return pacientes;
	}
	
	public void agregarNuevoPaciente(Paciente p) {
		pacientes.add(p);
	}
	public ObraSocial(String nombre) {
		this.nombre = nombre;
		this.pacientes = new LinkedList<>();;
	}
	
	public ObraSocial() {
		
	}
}

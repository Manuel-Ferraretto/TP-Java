package entities;

import java.util.LinkedList;
import java.util.List;

public class Especialidad {
	Integer codigo_esp;
	String nombre;
	Integer estado;
	List<Profesional> profesionales;
	
	public Integer getCodigo_esp() {
		return codigo_esp;
	}
	public void setCodigo_esp(Integer codigo_esp) {
		this.codigo_esp = codigo_esp;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public List<Profesional> getProfesionales() {
		return profesionales;
	}
	
	public void agregarNuevoProfesional(Profesional p) {
		profesionales.add(p);
	}
	
	public Integer getEstado() {
		return estado;
	}
	
	public void setEstado(Integer estado) {
		this.estado = estado;
	}
	
	public Especialidad(String nombre) {
		this.nombre = nombre;
		this.profesionales = new LinkedList<>();
		this.estado = 1;
	}
	
	public Especialidad() {}
	
}

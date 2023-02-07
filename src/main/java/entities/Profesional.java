package entities;

import java.time.LocalTime;

public class Profesional {
	private String matricula;
	private String email;
	private String nombre;
	private String apellido;
	private Integer estado;
	private LocalTime hora_inicio;
	private LocalTime hora_fin;
	Especialidad especialidad;
	
	public String getMatricula() {
		return matricula;
	}
	
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getApellido() {
		return apellido;
	}
	
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	public Integer getEstado() {
		return estado;
	}
	public void setEstado(Integer estado) {
		this.estado = estado;
	}
	
	public LocalTime getHora_inicio() {
		return hora_inicio;
	}
	
	public void setHora_inicio(LocalTime hora_inicio) {
		this.hora_inicio = hora_inicio;
	}
	
	public LocalTime getHora_fin() {
		return hora_fin;
	}
	
	public void setHora_fin(LocalTime hora_fin) {
		this.hora_fin = hora_fin;
	}

	public Especialidad getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(Especialidad especialidad) {
		this.especialidad = especialidad;
	}

	public Profesional(String matricula, String email, String nombre, String apellido,
			LocalTime hora_inicio, LocalTime hora_fin, Especialidad especialidad) {
		this.matricula = matricula;
		this.email = email;
		this.nombre = nombre;
		this.apellido = apellido;
		this.estado = 1;
		this.hora_inicio = hora_inicio;
		this.hora_fin = hora_fin;
		this.especialidad = especialidad;
	}
	
	public Profesional(String matricula, String email, String nombre, String apellido, int estado,
			LocalTime hora_inicio, LocalTime hora_fin, Especialidad especialidad) {
		this.matricula = matricula;
		this.email = email;
		this.nombre = nombre;
		this.apellido = apellido;
		this.estado = estado;
		this.hora_inicio = hora_inicio;
		this.hora_fin = hora_fin;
		this.especialidad = especialidad;
	}
	
	public Profesional() {
		
	}
	
	public Profesional(String matricula, String nombre, String apellido) {
		this.matricula = matricula;
		this.nombre = nombre;
		this.apellido = apellido;
	}
	
}

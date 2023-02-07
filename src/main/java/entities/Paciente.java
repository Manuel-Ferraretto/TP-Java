package entities;

import java.util.LinkedList;
import java.util.List;

public class Paciente {
	Integer id;
	String nombre;
	String apellido;
	String dni;
	String email;
	String password;
	String num_tel;
	ObraSocial obra_social;
	List<Turnos> turnos;
		
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNum_tel() {
		return num_tel;
	}
	public void setNum_tel(String num_tel) {
		this.num_tel = num_tel;
	}
	public ObraSocial getObra_social() {
		return obra_social;
	}
	public void setObra_social(ObraSocial obra_social) {
		this.obra_social = obra_social;
	}
	
	public Paciente(String nombre, String apellido, String dni, String email, String password,
			String num_tel, ObraSocial obra_social) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.email = email;
		this.password = password;
		this.num_tel = num_tel;
		this.obra_social = obra_social;
		this.turnos = new LinkedList<>();
	}
	
	public Paciente(int id, String nombre, String apellido, String dni, String email, 
			String num_tel, ObraSocial os) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.email = email;
		this.num_tel = num_tel;
		this.obra_social = os;
	}
	
	public Paciente(String email, String password) {
		this.email = email;
		this.password = password;
	}
	
	public Paciente() {
		
	}
	
	
	
	
	
}

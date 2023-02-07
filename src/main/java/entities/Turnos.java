package entities;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

public class Turnos {
	private Integer numero;
	private LocalDate fecha_turno;
	private LocalTime hora_turno;
	Profesional profesional;
	Paciente paciente;
	
	public Integer getNumero() {
		return numero;
	}
	
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	
	public LocalDate getFecha_turno() {
		return fecha_turno;
	}
	
	public void setFecha_turno(LocalDate fecha_turno) {
		this.fecha_turno = fecha_turno;
	}
	
	public LocalTime getHora_turno() {
		return hora_turno;
	}
	
	public void setHora_turno(LocalTime hora_turno) {
		this.hora_turno = hora_turno;
	}
	
	public Profesional getProfesional() {
		return profesional;
	}

	public void setProfesional(Profesional profesional) {
		this.profesional = profesional;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Turnos(LocalDate fecha_turno, LocalTime hora_turno, Profesional profesional,
			Paciente paciente) {
		this.fecha_turno = fecha_turno;
		this.hora_turno = hora_turno;
		this.profesional = profesional;
		this.paciente = paciente;
	}
	
	public Turnos() {
		
	}

	public Turnos(int numero, LocalDate fecha_turno, LocalTime hora_turno, Profesional profesional) {
		this.numero = numero;
		this.fecha_turno = fecha_turno;
		this.hora_turno = hora_turno;
		this.profesional = profesional;
	}
}

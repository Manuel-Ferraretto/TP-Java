package entities;

import java.time.LocalDate;

public class Valor_especialidad {
	
	Especialidad especialidad;
	LocalDate fecha_desde;
	Integer valor;
	
	public Especialidad getEspecialidad() {
		return especialidad;
	}
	
	public void setEspecialidad(Especialidad especialidad) {
		this.especialidad = especialidad;
	}
	
	public LocalDate getFecha_desde() {
		return fecha_desde;
	}
	
	public void setFecha_desde(LocalDate fecha_desde) {
		this.fecha_desde = fecha_desde;
	}
	
	public Integer getValor() {
		return valor;
	}
	
	public void setValor(Integer valor) {
		this.valor = valor;
	}

	public Valor_especialidad(Especialidad especialidad, LocalDate fecha_desde, Integer valor) {
		this.especialidad = especialidad;
		this.fecha_desde = fecha_desde;
		this.valor = valor;
	}

	public Valor_especialidad() {
		// TODO Auto-generated constructor stub
	}
	
}

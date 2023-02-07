package logic;

import java.util.LinkedList;

import dataBase.DataEspecialidad_ObraSocial;
import dataBase.DataObrasSociales;
import dataBase.DataPacientes;
import entities.Especialidad;
import entities.Especialidad_ObralSocial;
import entities.ObraSocial;

public class ObrasSocialesController {
	private DataObrasSociales dos;
	private DataEspecialidad_ObraSocial deo;
	private DataPacientes dp;
	
	public ObrasSocialesController() {
		dos= new DataObrasSociales();
		deo= new DataEspecialidad_ObraSocial();
		dp= new DataPacientes();
	}

	public LinkedList<ObraSocial> getAll() {
		// TODO Auto-generated method stub
		return dos.getAll();
	}

	public ObraSocial getByNombre(String nombre) {
		// TODO Auto-generated method stub
		return dos.getByNombre(nombre);
	}

	public void add(ObraSocial ob2) {
		// TODO Auto-generated method stub
		dos.add(ob2);
		
	}

	public ObraSocial getByCodigo(int ID) {
		// TODO Auto-generated method stub
		return dos.getByCodigo(ID);
	}

	public void update(ObraSocial ob) {
		// TODO Auto-generated method stub
		dos.update(ob);
		
	}

	public void delete(ObraSocial ob) {
		// TODO Auto-generated method stub
		dp.setNullOS(ob);
		deo.delete2(ob);
		dos.delete(ob);
	}

	public LinkedList<Especialidad_ObralSocial> getEspecialidadesIncluidas(ObraSocial ob) {
		// TODO Auto-generated method stub
		return deo.getEspecialidadesIncluidad(ob);
	}

	public LinkedList<Especialidad> getEspecialidadesNoIncluidas(ObraSocial ob) {
		// TODO Auto-generated method stub
		return deo.getEspecialidadesNoIncluidad(ob);
	}

	public void deleteMiEspecialidad(Especialidad_ObralSocial espob) {
		// TODO Auto-generated method stub
		deo.delete3(espob);
	}

	public void addMiEspecialidad(Especialidad_ObralSocial espob) {
		// TODO Auto-generated method stub
		deo.add(espob);
	}
	
	public boolean validarNombreNuevaOS(String nombre) {
		return dos.validarNombreNuevaOS(nombre);
	}

}

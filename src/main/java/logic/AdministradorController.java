package logic;

import java.sql.SQLException;
import java.util.LinkedList;

import dataBase.DataAdministradores;
import entities.Administradores;

public class AdministradorController {
	private DataAdministradores da;
	
	public AdministradorController() {
		da = new DataAdministradores();
	}
	
	public Administradores validate(Administradores a) throws SQLException {
		
		return da.getByUser(a);
	}

	public LinkedList<Administradores> getAll() {
		// TODO Auto-generated method stub
		return da.getAll();
	}

	public Administradores getByUsername(Administradores a) {
		// TODO Auto-generated method stub
		return da.getByUsername(a);
	}

	public void add(Administradores a2) {
		// TODO Auto-generated method stub
		da.add(a2);
	}

	public Administradores getById(Administradores a) {
		// TODO Auto-generated method stub
		return da.getById(a);
	}

	public void update(Administradores a2) {
		// TODO Auto-generated method stub
		da.update(a2);
	}

	public void delete(Administradores a) {
		// TODO Auto-generated method stub
		da.delete(a);
	}



}

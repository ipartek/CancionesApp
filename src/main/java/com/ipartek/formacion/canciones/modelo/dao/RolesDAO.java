package com.ipartek.formacion.canciones.modelo.dao;

import java.util.List;

import com.ipartek.formacion.canciones.modelo.pojo.Cancion;

public class RolesDAO implements Persistable<Cancion> {

	private static RolesDAO INSTANCE = null;

	private RolesDAO() {
		super();
	}

	public synchronized static RolesDAO getInstance() {

		if (INSTANCE == null) {
			INSTANCE = new RolesDAO();
		}
		return INSTANCE;
	}

	@Override
	public boolean create(Cancion rol) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Cancion rol, int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Cancion> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cancion findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}

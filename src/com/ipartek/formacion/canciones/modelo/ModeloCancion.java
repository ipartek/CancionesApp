package com.ipartek.formacion.canciones.modelo;

import java.util.ArrayList;

import com.ipartek.formacion.canciones.pojo.Cancion;

public interface ModeloCancion {
	
	ArrayList<Cancion> getAll();
	
	Cancion getById(long id);
	
	boolean delete(long id);
	
	boolean deleteAll();

	boolean insert(Cancion c);
	
	/**
	 * Modificamos una Cancion
	 * @param c Cancion a modificar
	 * @param id long identificador de la Cancion a modificar
	 * @return true si modifica, false en caso contrario
	 */
	boolean update(Cancion c, long id);
	
}

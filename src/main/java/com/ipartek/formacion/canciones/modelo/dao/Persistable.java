package com.ipartek.formacion.canciones.modelo.dao;

import java.util.List;

public interface Persistable<P> {

	final static int LIMIT = 100;

	/**
	 * Inserta un nuevo <code>Pojo</code> en la bbdd
	 *
	 * @param pojo
	 *            Objeto a insertar
	 * @return true si lo inserta, false en caso contrario
	 */
	boolean create(P pojo);

	/**
	 * Modifica un Pojo pasandole su identificador
	 *
	 * @param pojo
	 *            Objeto a modificar
	 * @param id
	 *            identificador del registro en la bbdd
	 * @return true si insertado correctamente, false en caso contratio o si id < 1
	 */
	boolean update(P pojo, int id);

	/**
	 * Eliminar el Registro de la bbdd correspondiente a su identificador
	 * 
	 * @param id
	 *            int identificador
	 * @return true si elimina, false en caso contrario
	 */
	boolean delete(int id);

	/**
	 * busca los ultimos registros ordenados por id descente y limitado por
	 * <code>LIMIT</code>
	 *
	 * @return List de Pojos
	 */
	List<P> findAll();

	/**
	 * Busca un registro por su identificador
	 *
	 * @param id
	 *            int identificador
	 * @return Pojo si lo encuentra, <code>null</code> en caso contrario
	 */
	P findById(int id);

}

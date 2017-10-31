package com.ipartek.formacion.canciones.modelo.dao;

import java.util.List;

public interface Persistable<P> {

	final static int LIMIT = 100;

	/**
	 * Busca los ultimos registros ordenados por id descendente y limitado por
	 * <code>LIMIT</code>
	 *
	 * @return List Pojos
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

	/**
	 * Inserta un nuevo <code>POJO</code> en la bbdd
	 *
	 * @param pojo
	 *            Objeto a insertar
	 * @return true si lo encuentra, <code>null</code> en caso contrario
	 */
	boolean create(P pojo);

	/**
	 * Modifica un Pojo pasandole su identificador
	 *
	 * @param pojo
	 *            Objeto a modificar
	 * @param id
	 *            identificador del registro en la bbdd, si es < 1 retorna false
	 * @return true si insertado correctamente, false en caso contrario o si id < 1
	 */
	boolean update(P pojo, int id);

	/**
	 * Eliminar el registro de la BBDD correspondiente a su identificador
	 *
	 * @param id
	 *            int identificador
	 * @return true si elimina, false en caso contrario
	 */
	boolean delete(int id);

}

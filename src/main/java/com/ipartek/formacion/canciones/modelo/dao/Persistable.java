package com.ipartek.formacion.canciones.modelo.dao;

import java.util.List;

public interface Persistable<P> {

	static int LIMIT = 100;

	/**
	 * Busca los ultimos registros ordenados por id Descendente y limitado por
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
	 * @return <code>Pojo</code> si lo encuentra, <code>null</code> si no lo
	 *         encuentra
	 */
	P findById(int id);

	/**
	 * Inserta un nuevo <code>pojo</code> en la BD
	 *
	 * @param pojo
	 *            Objeto a insertar
	 * @return true si lo inserta, false si no lo inserta
	 */
	boolean create(P pojo);

	/**
	 * Modifica un pojo pasandole su id
	 *
	 * @param pojo
	 *            Objeto a modificar
	 * @param id
	 *            Identificador del registro en la BD, si es <1 retorna false
	 * @return true si insertado correctamente, false en caso contrario o si id < 1
	 */
	boolean update(P pojo, int id);

	/**
	 * Elimina el registro de la BD correspondiente a su identificador
	 *
	 * @param id
	 *            int identificador
	 * @return true si elimina, false si no elimina
	 */
	boolean delete(int id);

}

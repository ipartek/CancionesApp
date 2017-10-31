package com.ipartek.formacion.canciones.modelo.dao;

import java.util.List;

public interface Persistable<P> {

	final static int LIMIT = 100; // al ser una interface no es necesario poner final y tiene que ser public

	boolean create(P pojo);

	/**
	 * Modifica un Pojo pasandole su identificador
	 *
	 * @param pojo
	 *            Objeto a modificar
	 * @param id
	 *            identificador de registro en la bbdd, si es <1 retorna false
	 * @return true si es insertado correctamente, false en caso contrario o si id <
	 *         1
	 */
	boolean update(P pojo, int id);

	/**
	 * Elimina el registro de la bbdd correspondiente a su identificador
	 *
	 * @param id
	 *            int identificador
	 * @return true si lo elimina, false en caso contrario
	 */
	boolean delete(int id);

	/**
	 * busca los ultimos registros ordenados por id descendente y limitado por
	 * <code> LIMIT </code>
	 *
	 * @return List de pojos
	 */

	List<P> findAll();

	/**
	 * Busca un registro por su identificador
	 *
	 * @param id
	 *            int identificador
	 * @return Pojo si lo encuntra, <code>null</code> en caso contrario
	 */

	P findById(int id);

	/**
	 * Inserta un nuevo <code>Pojo</code> en la bbdd
	 *
	 * @param pojo
	 *            Obejeto a insertar
	 * @return true si lo inseta, false
	 */

}

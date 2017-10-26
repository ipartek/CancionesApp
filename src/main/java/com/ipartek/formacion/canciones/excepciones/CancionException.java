package com.ipartek.formacion.canciones.excepciones;

/**
 * 
 * Exception personalizada para el POJO(Plain old Java Object) de Cancion
 * 
 * @author Administrador
 *
 */
public class CancionException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8144744033103322235L;

	public final static String EXCEPTION_DURACION_INCORRECTA = "Introduce un formato de duracion correcto. Ejemplo:[0]0:00";

	public CancionException(String messageException) {

		super(messageException);

	}

}
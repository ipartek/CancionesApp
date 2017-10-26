package com.ipartek.formacion.canciones.modelo;

import java.util.ArrayList;


import com.ipartek.formacion.canciones.pojo.Cancion;
/**
 * Clase para gestionar las Canciones en memoria mediante un ArrayList, 
 * mas adelante aprenderemos hacer lo mismo con una BBDD.
 * 
 * Vamos a usar un patron <b>singleton</b> para exista una unica instancia de esta clase.
 * @see https://es.wikipedia.org/wiki/Singleton 
 *  
 * @author ur00
 *
 */
public class ModeloCancionImpl implements ModeloCancion {
	
	private static ModeloCancionImpl INSTANCE = null;
	
	private ArrayList<Cancion> canciones;
	
	//simula el indice de una tabla en bbdd
	private long indice = 1;
	
	
	//constructor privado
	private ModeloCancionImpl() {
		super();
		this.canciones = new ArrayList<Cancion>();
		crearMocks();
	}
	
	//acceso para la clase
	public synchronized  static ModeloCancionImpl getInstance() {
		if ( INSTANCE == null ) {
			INSTANCE = new ModeloCancionImpl();
		}
		return INSTANCE;
	}
	
	/**
	 * Crear Juego de datos para probar
	 */
	private void crearMocks() {
		try {
			Cancion c = new Cancion("Dont Speak", "No Doubt", "4:23", "img/defaultalbum.png");
			c.setId(525);		
			this.canciones.add( c );
			
			c = new Cancion("Fiesta", "Celtas Cortos", "4:15", "img/defaultalbum.png");
			c.setId(25);		
			this.canciones.add( c );

			c = new Cancion("Hijos de Caín", "Baron Rojo", "5:18", "img/defaultalbum.png");
			c.setId(69);		
			this.canciones.add( c );
			
			this.indice = 526;
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
	

	@Override
	public ArrayList<Cancion> getAll() {		
		return this.canciones;
	}

	/**
	 * Busca Cancion por id
	 * @param id long identificador de la cancion
	 * @return null si no encuentra, instancia de Cancion si encuentra
	 */
	@Override	
	public Cancion getById(long id) {
		Cancion resul = null;
		Cancion cActual = null;
		for (int i = 0; i < this.canciones.size() ; i++) {
			cActual = this.canciones.get(i);
			if ( id == cActual.getId() ) {
				resul = cActual;
				break;
			}			
		}		
		return resul;
	}

	@Override
	public boolean delete(long id) {
		boolean resul = false;
		
		Cancion c = getById(id);
		if ( c != null ) {
			resul = this.canciones.remove(c);
		}
		
		return resul;
	}

	/**
	 * Al insertar no tenemos ID, buscar el ID mas alto de todas las canciones y le sumamos 1
	 */
	@Override	
	public boolean insert(Cancion c) {
		boolean resul = false;	
		if ( c != null) {
			c.setId(indice);
			resul = this.canciones.add(c);
			if (resul) {
				indice++;
			}
		}	
		return resul;
	}

	@Override
	public boolean update(Cancion c, long id) {
		boolean resul = false;
		if ( c != null) {
			Cancion cActual = null;
			for (int i = 0; i < this.canciones.size() ; i++) {
				cActual = this.canciones.get(i);
				if ( id == cActual.getId() ) {
					this.canciones.set(i, c);
					resul = true;
					break;
				}			
			}	
		}
		return resul;
	}

	@Override
	public boolean deleteAll() {
		boolean resul = false;
		
		if(canciones.removeAll(canciones)) {
			resul = true;
		};
		return resul;
	}

	

}

package com.ipartek.formacion.canciones.modelo.pojo;

import com.ipartek.formacion.canciones.excepciones.CancionException;
import com.ipartek.formacion.canciones.utilidades.Utilidades;

public class Cancion {

	private int id;
	private String nombre;
	private String artista;
	private String duracion;
	private String cover;
	
	public Cancion() {
		super();
		this.id = -1;
		this.nombre = "";
		this.artista = "";
		this.duracion = "0:00";
		this.cover = "";
	}
	
	
	
	public Cancion(String nombre, String artista, String duracion) throws CancionException {
		this();
		this.nombre = nombre;
		this.artista = artista;
		this.setDuracion(duracion);
		this.cover="";
	}
	
	public Cancion(int id, String nombre, String artista, String duracion, String cover) throws CancionException {
		super();
		this.id = id;
		this.nombre = nombre;
		this.artista = artista;
		this.setDuracion(duracion);
		this.cover = cover;
	}
	
	public Cancion(String nombre, String artista, String duracion, String cover) throws CancionException {
		this(nombre, artista, duracion);	
		this.cover = cover;
	}


	public Cancion(int id, String nombre, String artista, String cover) {
		this.id = id;
		this.nombre = nombre;
		this.artista = artista;
		this.cover = cover;
	}
	
	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getArtista() {
		return artista;
	}

	public void setArtista(String artista) {
		this.artista = artista;
	}

	public String getDuracion() {
		return duracion;
	}
	
	public int getDuracionSegundos() {
		int seg = -1;
		try {
			String[] trozos = this.duracion.split(":");
			int minutos = Integer.parseInt(trozos[0])*60;
			int segundos = Integer.parseInt(trozos[1]);		
			seg = minutos + segundos;
		}catch (Exception e) {
			e.printStackTrace();
		}	
		
		return seg;
	}

	/**
	 * La duracion de una Cancion debe tener el siguiente formato: [0]0:00
	 * 
	 * @param duracion
	 *            throws CancionException Formato de duracion no correcto [0]0:00
	 */

	public void setDuracion(String duracion) throws CancionException {

		if (!Utilidades.validarDuracion(duracion)) {

			throw new CancionException(CancionException.EXCEPTION_DURACION_INCORRECTA);
		}

		this.duracion = duracion;
	}

	@Override
	public String toString() {
		return "Cancion [id=" + id + ", nombre=" + nombre + ", artista=" + artista + ", duracion=" + duracion
				+ ", cover=" + cover + "]";
	}
	
}
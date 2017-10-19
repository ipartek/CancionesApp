package com.ipartek.formacion.canciones.pojo;

public class Usuario {
	
	private int id;
	private String nombre;
	private String pass;
	private String email;
	private String avatar;
	
	public Usuario( String nombre, String pass, String email) {
		super();		
		this.nombre = nombre;
		this.pass = pass;
		this.email = email;
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

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", pass=" + pass + ", email=" + email + ", avatar=" + avatar
				+ "]";
	}
	
}

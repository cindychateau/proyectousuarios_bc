package com.codingdojo.cynthia.modelos;

public class UsuarioOld {
	
	private String nombre;
	private String apellido;
	private long id;
	
	public UsuarioOld(String nombre, String apellido, long id) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	
	
}

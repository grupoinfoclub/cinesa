package com.cinesa.modelo;

public class Banco {
	 /* Variables */
	private int idBanco;
	private String nombre;
	
	/**
	 * Contructor vacío para recibir los parámetros que necesitemos
	 */
	public Banco() {
		
	}
	
	/* Getters y Setters */
	public int getIdBanco() {
		return idBanco;
	}

	public void setIdBanco(int idBanco) {
		this.idBanco = idBanco;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Banco [idBanco=" + idBanco + ", nombre=" + nombre + "]";
	}
	
	
}

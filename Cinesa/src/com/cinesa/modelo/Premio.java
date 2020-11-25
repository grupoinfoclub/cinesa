package com.cinesa.modelo;

public class Premio {
	 /* Variables */
	private int id;
	private String fecha;
	private int premiado;

	/* Constructores */
	/**
	 * Constructor que recoge todos los datos del premio
	 * @param id_centro
	 * @param aforo
	 */
	public Premio() {

	}

	/* Getters y Setters */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public int getPremiado() {
		return premiado;
	}

	public void setPremiado(int premiado) {
		this.premiado = premiado;
	}

	@Override
	public String toString() {
		return "Premio [id=" + id + ", fecha=" + fecha + ", premiado=" + premiado + "]";
	}
	
	
}

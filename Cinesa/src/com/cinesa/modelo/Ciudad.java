package com.cinesa.modelo;

public class Ciudad {
	 /* Variables */
	private int id;
	private String nombre;
	private int num_centros;
	private int activo;
	private int borrado;
	
	/* Constructores */
	/**
	 * Constructor que recoge todos los datos de la ciudad
	 * @param nombre
	 * @param num_centros
	 * @param activo
	 * @param borrado
	 */
	public Ciudad(String nombre,int num_centros,int activo,int borrado) {
		super ();
		this.nombre = nombre;
		this.num_centros = num_centros;
		this.activo = activo;
		this.borrado = borrado;
	}
	/**
	 * Contructor vacío para recibir los parámetros que necesitemos
	 */
	public Ciudad() {

	}

	/* Getters y Setters */
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

	public int getNum_centros() {
		return num_centros;
	}

	public void setNum_centros(int num_centros) {
		this.num_centros = num_centros;
	}

	public int getActivo() {
		return activo;
	}

	public void setActivo(int activo) {
		this.activo = activo;
	}

	public int getBorrado() {
		return borrado;
	}

	public void setBorrado(int borrado) {
		this.borrado = borrado;
	}

	@Override
	public String toString() {
		return "Ciudad [id=" + id + ", nombre=" + nombre + ", num_centros=" + num_centros + ", activo=" + activo
				+ ", borrado=" + borrado + "]";
	}
	
	
}

package com.cinesa.modelo;

public class Centro {
	 /* Variables */
	private int id;
	private int id_ciudad;
	private String nombre;
	private int num_salas;
	private int aforo;
	private int activo;
	private int borrado;
	
	/* Constructores */
	/**
	 * Constructor que recoge todos los datos del centro
	 * @param id_ciudad
	 * @param nombre
	 * @param num_salas
	 * @param aforo
	 * @param activo
	 * @param borrado
	 */
	public Centro(int id_ciudad,String nombre,int num_salas,int aforo,int activo,int borrado) {
		super ();
		this.id_ciudad = id_ciudad;
		this.nombre = nombre;
		this.num_salas = num_salas;
		this.aforo = aforo;
		this.activo = activo;
		this.borrado = borrado;
	}
	/**
	 * Contructor vacío para recibir los parámetros que necesitemos
	 */
	public Centro() {
	
	}
	
	/**
	 * Contructor que recoge el id de la ciudad para el listado de centros por ciudad
	 */
	public Centro(int id) {
		this.id=id;
	}
	
	/* Getters y Setters */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_ciudad() {
		return id_ciudad;
	}

	public void setId_ciudad(int id_ciudad) {
		this.id_ciudad = id_ciudad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getNum_salas() {
		return num_salas;
	}

	public void setNum_salas(int num_salas) {
		this.num_salas = num_salas;
	}

	public int getAforo() {
		return aforo;
	}

	public void setAforo(int aforo) {
		this.aforo = aforo;
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
		return "Centro [id=" + id + ", id_ciudad=" + id_ciudad + ", nombre=" + nombre + ", num_salas=" + num_salas
				+ ", aforo=" + aforo + ", activo=" + activo + ", borrado=" + borrado + "]";
	}
	
	
}

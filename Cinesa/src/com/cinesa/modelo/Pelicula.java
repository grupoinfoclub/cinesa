package com.cinesa.modelo;

public class Pelicula {
	 /* Variables */
		private int id;
		private String titulo;
		private int duracion;
		private String idioma;
		private int edad;
		private int id_sala;
		private int id_centro;
		
	/* Constructores */
	/**
	 * Contructor para recibir los parámetros del main
	 * @param titulo
	 * @param duracion
	 * @param idioma
	 * @param edad
	 * @param id_sala
	 * @param id_centro
	 */
	public Pelicula(String titulo, int duracion, String idioma, int edad, int id_sala, int id_centro) {
		super();
		this.titulo = titulo;
		this.duracion = duracion;
		this.idioma = idioma;
		this.edad = edad;
		this.id_sala = id_sala;
		this.id_centro = id_centro;
		
	}
	
	public Pelicula(String titulo) {
		this.titulo = titulo;
	}

	public Pelicula(int id_centro) {
		this.id_centro = id_centro;
	}

	/* Getters y Setters */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public int getId_sala() {
		return id_sala;
	}

	public void setId_sala(int id_sala) {
		this.id_sala = id_sala;
	}

	public int getId_centro() {
		return id_centro;
	}

	public void setId_centro(int id_centro) {
		this.id_centro = id_centro;
	}

	@Override
	public String toString() {
		return "Pelicula [id=" + id + ", titulo=" + titulo + ", duracion=" + duracion + ", idioma=" + idioma + ", edad="
				+ edad + ", id_sala=" + id_sala + ", id_centro=" + id_centro + "]";
	}
	
	
	
}

package com.cinesa.modelo;

public class Asiento {
	 /* Variables */
	private int id;
	private int fila;
	private int columna;
	private int id_sala;
	private int id_centro;
	
	/* Constructores */
	/**
	 * Constructor que recoge todos los datos del asiento
	 * @param fila
	 * @param columna
	 * @param id_sala
	 * @param id_centro
	 */
	public Asiento(int fila, int columna, int id_sala,int id_centro) {
		super();
		this.fila = fila;
		this.columna = columna;
		this.id_sala = id_sala;
		this.id_centro = id_centro;
	}
	/**
	 * Contructor vacío para recibir los parámetros que necesitemos
	 */
	public Asiento() {
		
	}
	/* Getters y Setters */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFila() {
		return fila;
	}

	public void setFila(int fila) {
		this.fila = fila;
	}

	public int getColumna() {
		return columna;
	}

	public void setColumna(int columna) {
		this.columna = columna;
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
		return "asiento [id=" + id + ", fila=" + fila + ", columna=" + columna + ", id_sala=" + id_sala + ", id_centro="
				+ id_centro + "]";
	}
	
	
}

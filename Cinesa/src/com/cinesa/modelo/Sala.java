package com.cinesa.modelo;

public class Sala {
	 /* Variables */
	private int id;
	private int id_centro;
	private int aforo;


	/* Constructores */
	/**
	 * Constructor que recoge todos los datos de la sala
	 * @param id_centro
	 * @param aforo
	 */
	public Sala(int id_centro,int aforo) {
		super ();
		this.id_centro = id_centro;
		this.aforo = aforo;
	}
	/**
	 * Contructor que recibe el id_centro para el filtro por centro
	 */
	public Sala(int id_centro) {
		this.id_centro=id_centro;
	}


	/* Getters y Setters */
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getId_centro() {
		return id_centro;
	}


	public void setId_centro(int id_centro) {
		this.id_centro = id_centro;
	}


	public int getAforo() {
		return aforo;
	}


	public void setAforo(int aforo) {
		this.aforo = aforo;
	}

	@Override
	public String toString() {
		return "sala [id=" + id + ", id_centro=" + id_centro + ", aforo=" + aforo + "]";
	}
	
	
}
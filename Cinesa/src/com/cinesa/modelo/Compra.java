package com.cinesa.modelo;

public class Compra {
	 /* Variables */
	private int id;
	private String fecha;
	private int id_usuario;
	private int importe;
	private int id_sala;
	private int id_centro;
	private String sesion;
	
	/* Constructores */
	/**
	 * Contructor vacío para recibir los parámetros que necesitemos
	 */
	public Compra() {
	
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

	public int getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}

	public int getImporte() {
		return importe;
	}

	public void setImporte(int importe) {
		this.importe = importe;
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

	public String getSesion() {
		return sesion;
	}

	public void setSesion(String sesion) {
		this.sesion = sesion;
	}

	@Override
	public String toString() {
		return "Compra [id=" + id + ", fecha=" + fecha + ", id_usuario=" + id_usuario + ", importe=" + importe
				+ ", id_sala=" + id_sala + ", id_centro=" + id_centro + ", sesion=" + sesion + "]";
	}
	
	
}

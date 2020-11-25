package com.cinesa.modelo;

public class Reserva {
	 /* Variables */
	private int id;
	private int ciudad;
	private int centro;
	private int sala;
	private String fecha;
	private String hora;
	private int fila;
	private int asiento;
	private String estado;
	private int usuario;
	
	/* Constructores */
	/**
	 * Contructor para recibir el filtro de sala y centro para la reserva de butacas
	 */
	public Reserva(int sala) {
		this.sala = sala;
	}
	
	/**
	 * Contructor vacío para recibir los parámetros necesarios
	 */
	public Reserva() {

	}

	/* Getters y Setters */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCiudad() {
		return ciudad;
	}

	public void setCiudad(int ciudad) {
		this.ciudad = ciudad;
	}

	public int getCentro() {
		return centro;
	}

	public void setCentro(int centro) {
		this.centro = centro;
	}

	public int getSala() {
		return sala;
	}

	public void setSala(int sala) {
		this.sala = sala;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public int getFila() {
		return fila;
	}

	public void setFila(int fila) {
		this.fila = fila;
	}

	public int getAsiento() {
		return asiento;
	}

	public void setAsiento(int asiento) {
		this.asiento = asiento;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public int getUsuario() {
		return usuario;
	}

	public void setUsuario(int usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "Reservas [id=" + id + ", ciudad=" + ciudad + ", centro=" + centro + ", sala=" + sala + ", fecha="
				+ fecha + ", hora=" + hora + ", fila=" + fila + ", asiento=" + asiento + ", estado=" + estado + "]";
	}
	
	
	
	
}

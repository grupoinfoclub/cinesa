package com.cinesa.modelo;

public class Cuenta {
	/* Variables */
	private int idCuenta;
	private int usuario;
	private int banco;
	private int numCuenta;
	private int saldo;
	
	/**
	 * Contructor vacío para recibir los parámetros que necesitemos
	 */
	public Cuenta() {
		
	}
	
	/* Getters y Setters */
	public int getIdCuenta() {
		return idCuenta;
	}

	public void setIdCuenta(int idCuenta) {
		this.idCuenta = idCuenta;
	}

	public int getUsuario() {
		return usuario;
	}

	public void setUsuario(int usuario) {
		this.usuario = usuario;
	}

	public int getBanco() {
		return banco;
	}

	public void setBanco(int banco) {
		this.banco = banco;
	}

	public int getNumCuenta() {
		return numCuenta;
	}

	public void setNumCuenta(int numCuenta) {
		this.numCuenta = numCuenta;
	}

	public int getSaldo() {
		return saldo;
	}

	public void setSaldo(int saldo) {
		this.saldo = saldo;
	}

	@Override
	public String toString() {
		return "Cuenta [idCuenta=" + idCuenta + ", usuario=" + usuario + ", banco=" + banco + ", numCuenta=" + numCuenta
				+ ", saldo=" + saldo + "]";
	}

	
}

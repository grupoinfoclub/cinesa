package com.cinesa.modelo;

public class Movimiento {
	/* Variables */
	private int idMovimiento;
	private int cuenta;
	private int saldo;
	
	/**
	 * Contructor vac�o para recibir los par�metros que necesitemos
	 */
	public Movimiento() {
		
	}
	
	/* Getters y Setters */
	public int getIdMovimiento() {
		return idMovimiento;
	}

	public void setIdMovimiento(int idMovimiento) {
		this.idMovimiento = idMovimiento;
	}

	public int getCuenta() {
		return cuenta;
	}

	public void setCuenta(int cuenta) {
		this.cuenta = cuenta;
	}

	public int getSaldo() {
		return saldo;
	}

	public void setSaldo(int saldo) {
		this.saldo = saldo;
	}

	@Override
	public String toString() {
		return "Movimiento [idMovimiento=" + idMovimiento + ", cuenta=" + cuenta + ", saldo=" + saldo + "]";
	}
	
	
}

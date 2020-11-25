package com.cinesa.modelo;

public class Usuario {
	 /* Variables */
	private int id;
	private String dni;
	private String nombre;
	private String apellidos;
	private String CP;
	private String mail;
	private int movil;
	private int rol;
	private String clave;
	private int activo;
	private int borrado;
	
	
	/* Constructores */
	/**
	 * Constructor que recoge todos los datos del usuario
	 * @param dni
	 * @param nombre
	 * @param apellidos
	 * @param CP
	 * @param mail
	 * @param movil
	 * @param rol
	 * @param activo
	 * @param borrado
	 * @param clave
	 */
	public Usuario(String dni, String nombre,String apellidos,String CP, String mail, int movil, int rol, int activo, int borrado, String clave) {
		super ();
		this.dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.CP = CP;
		this.mail = mail;
		this.movil = movil;
		this.rol = rol;
		this.clave = clave;
		this.activo = activo;
		this.borrado = borrado;
	}
	/**
	 * Contructor vacío para recibir los parámetros que necesitemos
	 */
	public Usuario() {
		
	}
	
	/* Getters y Setters */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getCP() {
		return CP;
	}

	public void setCP(String cP) {
		CP = cP;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public int getMovil() {
		return movil;
	}

	public void setMovil(int movil) {
		this.movil = movil;
	}

	public int getRol() {
		return rol;
	}

	public void setRol(int rol) {
		this.rol = rol;
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

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", dni=" + dni + ", nombre=" + nombre + ", apellidos=" + apellidos + ", CP=" + CP
				+ ", mail=" + mail + ", movil=" + movil + ", rol=" + rol + ", clave=" + clave + ", activo=" + activo
				+ ", borrado=" + borrado + "]";
	}
	

	
}

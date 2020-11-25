package com.cinesa.controlador;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.cinesa.modelo.Cuenta;
import com.cinesa.transversal.BaseDeDatos;

public class Cuenta_CRUD {
	public Cuenta cuenta;//recibimos objeto
	/**
	 * Contructor en el que recibimos el objeto instanciado centro
	 */
	public Cuenta_CRUD(Cuenta cuenta) {
		this.cuenta=cuenta;
	}
	/**
	 * funcion para verificar si el usuario tiene importe para realizar la compra de la entrada.
	 * @param cuenta
	 * @return
	 */
	public int obtener(Cuenta cuenta) {
        String query = "";
        int r = 0;
        BaseDeDatos bd = new BaseDeDatos();
        ResultSet resultado = null;
        Statement sentencia = null;
        try {
            query = "select * from cuentas where id_usuario = " + cuenta.getUsuario() + " and saldo >11;";
            Connection conexion = bd.conectar();
            if (conexion != null) {
                sentencia = conexion.createStatement();
	            resultado = sentencia.executeQuery(query);
	            if (resultado.next()) {
	            	 r = 1;
	            }
            }else {
            	System.out.println("Ha habido un problema al conectar con la base de datos");
            }
        
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
        	if (sentencia!=null) {
				try {
					sentencia.close();
					resultado.close();
					bd.conexion.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
        }
		return r ;
    }
	/**
	 * procedimiento para editar el saldo al realizar una compra
	 * @param cuenta
	 */
	 public void editar_saldo(Cuenta cuenta, int saldo) {
	        String query = "";
	        BaseDeDatos bd = new BaseDeDatos();
	        Statement sentencia = null;
	        try {
	            query = "UPDATE cuentas SET saldo = "+ saldo +" WHERE id = " + cuenta.getUsuario() + ";";
	            Connection conexion = bd.conectar();
	            if (conexion != null) {
	            	sentencia = conexion.createStatement();
		            sentencia.executeUpdate(query);
	            }else {
	            	System.out.println("Ha habido un problema al conectar con la base de datos");
	            }
	         
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }finally {
	        	if (sentencia!=null) {
					try {
						sentencia.close();
						bd.conexion.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
	        }
	    }
	 /**
	  * funcion para obtener el saldo de la cuenta filtrando por id usuario
	  * @param cuenta
	  * @return
	  */
	 public int obtenerSaldo(Cuenta cuenta) {
	        String query = "";
	        int r = 0;
	        BaseDeDatos bd = new BaseDeDatos();
	        ResultSet resultado = null;
	        Statement sentencia = null;
	        try {
	            query = "select saldo from cuentas where id_usuario = " + cuenta.getUsuario() + ";";
	            Connection conexion = bd.conectar();
	            if (conexion != null) {
	                sentencia = conexion.createStatement();
		            resultado = sentencia.executeQuery(query);
		            if (resultado.next()) {
		            	 r = resultado.getInt("saldo");
		            }
	            }else {
	            	System.out.println("Ha habido un problema al conectar con la base de datos");
	            }
	        
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }finally {
	        	if (sentencia!=null) {
					try {
						sentencia.close();
						resultado.close();
						bd.conexion.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
	        }
			return r ;
	    }
	 /**
	  * funcion para obtener el numero de cuenta filtrando por id usuario
	  * @param cuenta
	  * @return
	  */
	 public int obtenerIDCuenta(Cuenta cuenta) {
	        String query = "";
	        int r = 0;
	        BaseDeDatos bd = new BaseDeDatos();
	        ResultSet resultado = null;
	        Statement sentencia = null;
	        try {
	            query = "select id from cuentas where id_usuario = " + cuenta.getUsuario() + ";";
	            Connection conexion = bd.conectar();
	            if (conexion != null) {
	                sentencia = conexion.createStatement();
		            resultado = sentencia.executeQuery(query);
		            if (resultado.next()) {
		            	 r = resultado.getInt("id");
		            }
	            }else {
	            	System.out.println("Ha habido un problema al conectar con la base de datos");
	            }
	        
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }finally {
	        	if (sentencia!=null) {
					try {
						sentencia.close();
						resultado.close();
						bd.conexion.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
	        }
			return r ;
	    }
}

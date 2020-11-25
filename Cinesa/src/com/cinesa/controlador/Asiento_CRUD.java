package com.cinesa.controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.cinesa.modelo.Asiento;
import com.cinesa.transversal.*;


public class Asiento_CRUD {
	/**
	 * Contructor vac�o para recibir los par�metros que necesitemos
	 */
	public Asiento_CRUD() {
		
	}
	/**
	 * Procemiento de inserci�n de un asiento en BD. Controla la conexi�n y el registro.
	 * @param asiento instancia de la clase asiento
	 * @throws SQLException
	 */
	 public void agregar(Asiento asiento) throws SQLException {
	        String query = "";
	        BaseDeDatos bd = new BaseDeDatos();
	        Connection conexion = bd.conectar();
	        Statement sentencia = null;
	        if (conexion != null) {
	            sentencia = conexion.createStatement();

		        query = "INSERT INTO asientos(fila, " + "columna, " + "id_sala, "
		                + "id_centro " + ") VALUES (" + asiento.getFila() + ", " + asiento.getColumna() + ", " + asiento.getId_sala() + ", " + asiento.getId_centro() + ");";

		        if (sentencia.executeUpdate(query) > 0) {
		            System.out.println("El registro se insert� exitosamente.");
		        } else {
		            System.out.println("No se pudo insertar el registro.");
		        }

		        System.out.println(query);
	        }else {
	        	System.out.println("Ha habido un problema al conectar con la base de datos");
	        }
	        try {
	        	if (sentencia!=null) {
					try {
						sentencia.close();
						bd.conexion.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
	        }catch (Exception e) {
				e.printStackTrace();
			}
	    }
	 
	 /**
	  * Procedimiento update de la fila del asiento. Se filtrar� por id_centro en la query. Controla errores SQL
	  * @param asiento instancia de la clase Asiento
	  * @param fila
	  */
	    public void editar_fila(Asiento asiento, int fila) {
	        String query = "";
	        BaseDeDatos bd = new BaseDeDatos();
	        PreparedStatement sentenciaP = null;
	        try {
	            query = "UPDATE asientos SET fila = ? WHERE id_centro = ?;";
	            Connection conexion = bd.conectar();
	            if (conexion != null) {
	            	sentenciaP = conexion.prepareStatement(query);
	   	            sentenciaP.setInt(1, fila);
	   	            sentenciaP.setInt(2, asiento.getId_centro());

	   	            sentenciaP.executeUpdate();
	   	            System.out.println("El registro se actualiz� exitosamente.");
	            }else {
	            	System.out.println("Ha habido un problema al conectar con la base de datos");
	            }
	   	        } catch (SQLException e) {
	   	            System.out.println(e.getMessage());
	         } finally {
	         	if (sentenciaP!=null) {
					try {
						sentenciaP.close();
						bd.conexion.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
	        }      
	    }
	    
	    /**
	     * Procedimiento delete de un asiento seg�n su id_centro. Controla errores SQL
	     * @param asiento instancia de la clase Asiento
	     */
	    public void eliminar(Asiento asiento) {
	        String query = "";
	        BaseDeDatos bd = new BaseDeDatos();
	        PreparedStatement sentenciaP = null;
	        try {
	            query = "DELETE FROM asientos WHERE id_centro = ?;";
	            Connection conexion = bd.conectar();
	            if (conexion != null) {
	                sentenciaP = conexion.prepareStatement(query);
		            sentenciaP.setInt(1, asiento.getId_centro());

		            sentenciaP.execute();
		            System.out.println("El registro se elimin� exitosamente.");
	            }else {
	            	System.out.println("Ha habido un problema al conectar con la base de datos");
	            }
	 
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }finally {
	         	if (sentenciaP!=null) {
					try {
						sentenciaP.close();
						bd.conexion.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
	        } 
	    }
	    
	    /**
	     * Funci�n select de todos los asientos.
	     * @param asiento instancia de la clase Asiento
	     */
	    public void obtener(Asiento asiento) {
	        String query = "";
	        BaseDeDatos bd = new BaseDeDatos();
	        Statement sentencia = null;
	        ResultSet resultado = null;
	        try {
	            query = "SELECT * FROM asientos;";
	            Connection conexion = bd.conectar();
	            if (conexion != null) {
	            	sentencia = conexion.createStatement();
	 	            resultado = sentencia.executeQuery(query);

	 	            while (resultado.next()) {
	 	                int id = resultado.getInt("id");
	 	                int fila = resultado.getInt("fila");
	 	                int columna = resultado.getInt("columna");
	 	                int id_sala = resultado.getInt("id_sala");
	 	                int id_centro = resultado.getInt("id_centro");
	 	               

	 	                // Imprimir los resultados.
	 	                System.out.format("%d, %d, %d, %d, %d\n", id, fila, columna, id_sala, id_centro);
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
	    }
}
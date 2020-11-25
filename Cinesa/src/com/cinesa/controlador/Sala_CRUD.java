package com.cinesa.controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.cinesa.modelo.Sala;
import com.cinesa.transversal.*;


public class Sala_CRUD {
	public Sala sala;//recibimos objeto
	/**
	 * Contructor para recibir el objeto de la clase Sala
	 */
	public Sala_CRUD(Sala sala) {
		this.sala=sala;
	}
	/**
	 * Procemiento de inserción de una sala en BD. Controla la conexión y el registro.
	 * @param sala instancia de la clase Sala
	 * @throws SQLException
	 */
	 public void agregar(Sala sala) throws SQLException {
	        String query = "";
	        BaseDeDatos bd = new BaseDeDatos();
	        Connection conexion = bd.conectar();
	        Statement sentencia = null;
	        if (conexion != null)  {
		        sentencia = conexion.createStatement();

		        query = "INSERT INTO salas(id_centro, " + "aforo " + ") VALUES (" + sala.getId_centro() + ", " + sala.getAforo() + ");";

		        if (sentencia.executeUpdate(query) > 0) {
		            System.out.println("El registro se insertó exitosamente.");
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
	 	 * Procedimiento update del aforo de la sala. Se filtrará por ID_centro en la query. Controla errores SQL
	 	 * @param sala instancia de la clase Sala
	 	 * @param aforo
	 	 */
	    public void editar_aforo(Sala sala, int aforo) {
	        String query = "";
	        BaseDeDatos bd = new BaseDeDatos();
	        PreparedStatement sentenciaP = null;
	        try {
	            query = "UPDATE salas SET aforo = ? WHERE id_centro = ?;";
	            Connection conexion = bd.conectar();
	            if (conexion != null) {
	            	sentenciaP = conexion.prepareStatement(query);
		            sentenciaP.setInt(1, aforo);
		            sentenciaP.setInt(2, sala.getId_centro());

		            sentenciaP.executeUpdate();
		            System.out.println("El registro se actualizó exitosamente.");
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
	     * Procedimiento delete de una sala según su id_centro. Controla errores SQL
	     * @param sala instancia de la clase Sala
	     */
	    public void eliminar(Sala sala) {
	        String query = "";
	        BaseDeDatos bd = new BaseDeDatos();
	        PreparedStatement sentenciaP = null;
	        try {
	            query = "DELETE FROM salas WHERE id_centro = ?;";
	            Connection conexion = bd.conectar();
	            if (conexion != null) {
	            	sentenciaP = conexion.prepareStatement(query);
		            sentenciaP.setInt(1, sala.getId_centro());

		            sentenciaP.execute();
		            System.out.println("El registro se eliminó exitosamente.");
		            sentenciaP.close();
		            bd.conexion.close();
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
	     * Función select de todas las salas. Se retorna el resultado de la query
	     * @param sala instancia de la clase Sala
	     */
	    public ResultSet obtener(Sala sala) {
	        String query = "";
	        BaseDeDatos bd = new BaseDeDatos();
	        ResultSet resultado = null;
	        Statement sentencia = null;
	        try {
	            query = "SELECT * FROM salas where id_centro=" + sala.getId_centro() + ";";
	            Connection conexion = bd.conectar();
	            if (conexion != null) {
	                sentencia = conexion.createStatement();
		            resultado = sentencia.executeQuery(query);
					System.out.println("╔═══════════════════════════════════════════════════════════╗");
					System.out.println("║      NÚMERO DE SALA - CENTRO DE PERTENENCIA - AFORO       ║");
					System.out.println("╚═══════════════════════════════════════════════════════════╝");
		            while (resultado.next()) {
		                int id = resultado.getInt("id");
		                int id_centro = resultado.getInt("id_centro");
		                int aforo = resultado.getInt("aforo");
		               

		                // Imprimir los resultados.
		                System.out.format("%d, %d,%d\n", id, id_centro, aforo);
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
	        return resultado ;
	    }
}

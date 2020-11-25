package com.cinesa.controlador;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.cinesa.modelo.Ciudad;
import com.cinesa.transversal.*;

public class Ciudad_CRUD {
	
	public Ciudad ciudad;//recibimos objeto 
	
	/**
	 * Contructor vacío para recibir los parámetros que necesitemos
	 */
	public Ciudad_CRUD(Ciudad ciudad) {
		this.ciudad=ciudad;
	}
	/**
	 * Procemiento de inserción de una ciudad en BD. Controla la conexión y el registro.
	 * @param ciudad instancia de la clase Ciudad
	 * @throws SQLException
	 */
	public void agregar(Ciudad ciudad) throws SQLException {
        String query = "";
        BaseDeDatos bd = new BaseDeDatos();
        Connection conexion = bd.conectar();
        Statement sentencia = null;
        if (conexion != null) {
            sentencia = conexion.createStatement();

            query = "INSERT INTO ciudades(nombre, " + "num_centros, " + "activo, " + "borrado" + ") VALUES ('" + ciudad.getNombre()
                    + "', " + ciudad.getNum_centros() + ", " + ciudad.getActivo() + ", " + ciudad.getBorrado() + ");";

            if (sentencia.executeUpdate(query) > 0) {
                System.out.println("El registro se insertó exitosamente.");
            } else {
                System.out.println("No se pudo insertar el registro.");
            }

            System.out.println(query);
            sentencia.close();
            bd.conexion.close();
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
	 * Procedimiento update del nombre de la ciudad. Se filtrará por nombre en la query. Controla errores SQL
	 * @param ciudad instancia de la clase Ciudad
	 * @param nombre
	 */
    public void editar_nombre(Ciudad ciudad, String nombre) {
        String query = "";
        BaseDeDatos bd = new BaseDeDatos();
        PreparedStatement sentenciaP = null;
        try {
            query = "UPDATE ciudades SET nombre = ? WHERE nombre = ?;";
            Connection conexion = bd.conectar();
            if (conexion != null) {
            	 sentenciaP = conexion.prepareStatement(query);
                 sentenciaP.setString(1, nombre);
                 sentenciaP.setString(2, ciudad.getNombre());

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
     * Procedimiento delete de una ciudad según su nombre. Controla errores SQL
     * @param ciudad instancia de la clase Ciudad
     */ 
    public void eliminar(Ciudad ciudad) {
        String query = "";
        BaseDeDatos bd = new BaseDeDatos();
        PreparedStatement sentenciaP = null;
        try {
            query = "DELETE FROM ciudades WHERE nombre = ?;";
            Connection conexion = bd.conectar();
            if (conexion != null) {
            	  sentenciaP = conexion.prepareStatement(query);
                  sentenciaP.setString(1, ciudad.getNombre());

                  sentenciaP.execute();
                  System.out.println("El registro se eliminó exitosamente.");
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
     * Función select de todas las ciudades.
     * @param ciudad instancia de la clase Ciudad
     */
    public ResultSet obtener(Ciudad ciudad) {
        String query = "";
        BaseDeDatos bd = new BaseDeDatos();
        Statement sentencia = null;
        ResultSet resultado = null;
        try {
            query = "SELECT * FROM ciudades;";
            Connection conexion = bd.conectar();
            if (conexion != null) {
                sentencia = conexion.createStatement();
                resultado = sentencia.executeQuery(query);

                while (resultado.next()) {
                    int id = resultado.getInt("id");
                    String nombre = resultado.getString("nombre");
                    int num_centros = resultado.getInt("num_centros");
                    int activo = resultado.getInt("activo");
                    int borrado = resultado.getInt("borrado");
                   
                    // Imprimir los resultados.
                    System.out.format("%d, %s, %d, %d. %d\n", id, nombre, num_centros, activo, borrado);
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

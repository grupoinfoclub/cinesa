package com.cinesa.controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.cinesa.modelo.Pelicula;
import com.cinesa.transversal.BaseDeDatos;

public class Pelicula_CRUD {
	public Pelicula pelicula;//recibimos objeto
	/**
	 * Contructor que recibe por parámetro el objeto pelicula
	 */
	public Pelicula_CRUD(Pelicula pelicula) {
		this.pelicula = pelicula;
	}
	/**
	 * Procemiento de inserción en BD. Controla la conexión y el registro.
	 * @param pelicula instancia de la clase Pelicula
	 * @throws SQLException
	 */
	 public void agregar(Pelicula pelicula) throws SQLException {
	        String query = "";
	        BaseDeDatos bd = new BaseDeDatos();
	        Connection conexion = bd.conectar();
	        Statement sentencia = null;
	        if (conexion != null)  {
		        sentencia = conexion.createStatement();

		        query = "INSERT INTO peliculas(titulo, " + "duracion, " + "idioma, " + "edad, " + "id_sala, " + "id_centro " + ") VALUES ('" + pelicula.getTitulo() + "', " + pelicula.getDuracion() + ", " + "'" + pelicula.getIdioma() + "', " + pelicula.getEdad() + ", " + pelicula.getId_sala() + ", " + pelicula.getId_centro() + ");";

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
	 	 * Procedimiento update de la edad de la pelicula. Se filtrará por ID en la query. Controla errores SQL
	 	 * @param pelicula instancia de la clase Pelicula
	 	 * @param edad
	 	 */
	    public void editar_edad(Pelicula pelicula, int edad) {
	        String query = "";
	        BaseDeDatos bd = new BaseDeDatos();
	        PreparedStatement sentenciaP = null;
	        try {
	            query = "UPDATE peliculas SET edad = ? WHERE titulo = ?;";
	            Connection conexion = bd.conectar();
	            if (conexion != null) {
	            	sentenciaP = conexion.prepareStatement(query);
		            sentenciaP.setInt(1, edad);
		            sentenciaP.setString(2, pelicula.getTitulo());

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
	     * Procedimiento delete de una pelicula según su id. Controla errores SQL
	     * @param pelicula instancia de la clase Pelicula
	     */
	    public void eliminar(Pelicula pelicula) {
	        String query = "";
	        BaseDeDatos bd = new BaseDeDatos();
	        PreparedStatement sentenciaP = null;
	        try {
	            query = "DELETE FROM peliculas WHERE titulo = ?;";
	            Connection conexion = bd.conectar();
	            if (conexion != null) {
	            	sentenciaP = conexion.prepareStatement(query);
		            sentenciaP.setString(1, pelicula.getTitulo());

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
	     * Función select de todas las peliculas. Se retorna el resultado de la query
	     * @param pelicula instancia de la clase Pelicula
	     */
	    public ResultSet obtener(Pelicula pelicula) {
	        String query = "";
	        BaseDeDatos bd = new BaseDeDatos();
	        ResultSet resultado = null;
	        Statement sentencia = null;
	        try {
	            query = "SELECT * FROM peliculas where id_centro = " + pelicula.getId_centro() + ";";
	            Connection conexion = bd.conectar();
	            if (conexion != null) {
	                sentencia = conexion.createStatement();
		            resultado = sentencia.executeQuery(query);
					System.out.println("╔══════════════════════════════════════════════════════════════════════════╗");
					System.out.println("║  NÚMERO DE PELICULA - TÍTULO - DURACIÓN - IDIOMA - EDAD - SALA - CENTRO  ║");
					System.out.println("╚══════════════════════════════════════════════════════════════════════════╝");
		            while (resultado.next()) {
		                int id = resultado.getInt("id");
		                String titulo = resultado.getString("titulo");
		                int duracion = resultado.getInt("duracion");
		                String idioma = resultado.getString("idioma");
		                int edad = resultado.getInt("edad");
		                int id_sala = resultado.getInt("id_sala");
		                int id_centro = resultado.getInt("id_centro");

		                // Imprimir los resultados.
		                
		                System.out.format("%d, %s, %d, %s, %d, %d, %d\n", id, titulo, duracion, idioma, edad, id_sala, id_centro);
		            }
	            }else {
	            	System.out.println("Ha habido un problema al conectar con la base de datos");
	            }
	      
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        } finally {
	        	if (sentencia!=null) {
					try {
						sentencia.close();
						resultado.close();
						bd.conexion.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
	        }
	        return resultado ;
	    }
	    /**
	     * función para retornar el id_sala y el id_centro de la película escogida
	     * @param pelicula instancia de la clase Pelicula
	     * @return
	     */
		public int filtroPeli(Pelicula pelicula) {
			String query = "";
			int res= 0;
	        BaseDeDatos bd = new BaseDeDatos();
	        ResultSet resultado = null;
	        Statement sentencia = null;
	        System.out.println();
	        try {
	            query = "SELECT id_sala FROM peliculas where titulo = '" + pelicula.getTitulo() + "';";
	            Connection conexion = bd.conectar();
	            if (conexion != null) {
	                sentencia = conexion.createStatement();
		            resultado = sentencia.executeQuery(query);
					System.out.println("╔════════════════════════════╗");
					System.out.println("║       NÚMERO DE SALA       ║");
					System.out.println("╚════════════════════════════╝");
		            while (resultado.next()) {
		                int id_sala = resultado.getInt("id_sala");

		                // Imprimir los resultados.
		                
		                System.out.format("%d\n", id_sala);
		                res = id_sala;
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
						e.printStackTrace();
					}
				}
	        }
	        return res ;
		}
}

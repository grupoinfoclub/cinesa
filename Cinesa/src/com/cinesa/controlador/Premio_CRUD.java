package com.cinesa.controlador;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.cinesa.modelo.Premio;
import com.cinesa.transversal.BaseDeDatos;

public class Premio_CRUD {
	public Premio premio;//recibimos objeto 
	
	/**
	 * constructor que recibe por parámetro la instancia de la clase Usuario
	 * @param usuario
	 */
	public Premio_CRUD(Premio premio) {
		this.premio = premio;
	}
	
	/**
	 * Procemiento de inserción de un premio en BD. Controla la conexión y el registro.
	 * @param premio instancia de la clase Premio
	 * @throws SQLException controla errores SQL
	 */
    public void agregar(Premio premio) throws SQLException {
        String query = "";
        BaseDeDatos bd = new BaseDeDatos();
        Connection conexion = bd.conectar();
        Statement sentencia = null;
        if (conexion != null) {
            sentencia = conexion.createStatement();

            query = "INSERT INTO premios(premiado) VALUES (" + premio.getPremiado() + ");";
            
            if (sentencia.executeUpdate(query) > 0) { 
            	System.out.println("El registro se insertó correctamente.");
            }else {
            	System.out.println("No se pudo insertar el registro.");
            }
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
     * Procedimiento update del premiado. Se filtrará por ID en la query. Controla errores SQL
     * @param premio instancia de la clase Premio
     * @param premiado
     */
    public void editar_premiado(Premio premio, int premiado) {
        String query = "";
        BaseDeDatos bd = new BaseDeDatos();
        PreparedStatement sentenciaP = null;
        try {
            query = "UPDATE premios SET premiado = ? WHERE id = ?;";
            Connection conexion = bd.conectar();
            if (conexion != null) {
            	   sentenciaP = conexion.prepareStatement(query);
                   sentenciaP.setInt(1, premiado);
                   sentenciaP.setInt(2, premio.getId());

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
     * Procedimiento delete de un premio según su id. Controla errores SQL
     * @param premio instancia de la clase Premio
     */
    public void eliminar(Premio premio) {
        String query = "";
        BaseDeDatos bd = new BaseDeDatos();
        PreparedStatement sentenciaP = null;
        try {
            query = "DELETE FROM premios WHERE id = ?;";
            Connection conexion = bd.conectar();
            if (conexion != null) {
            	 sentenciaP = conexion.prepareStatement(query);
                 sentenciaP.setInt(1, premio.getId());

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
     * Función select de los premios filtrando por id_usuario, que sería premiado
     * @param premio
     * @return el resultado de la query para la clase menuAdmin. Controla errores SQL
     */
    @SuppressWarnings("unused")
	public int obtener(Premio premio) {
        String query = "";
        int r = 0;
        BaseDeDatos bd = new BaseDeDatos();
        ResultSet resultado = null;
        Statement sentencia = null;
        try {
            query = "SELECT * FROM premios where premiado= " + premio.getPremiado() + ";";
            Connection conexion = bd.conectar();
            if (conexion != null) {
                sentencia = conexion.createStatement();
                resultado = sentencia.executeQuery(query);

                while (resultado.next()) {
                    int id = resultado.getInt("id");
                    String fecha = resultado.getString("fecha");     
                    int premiado = resultado.getInt("premiado");

                    // Imprimir los resultados.
                    //System.out.format("%d, %s, %d\n", id, fecha, premiado);
                    r = 1;
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
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
        }
        return r;
    }
    
    /**
     * Función que retorna si hay premios ya introducidos en un dia específico
     * @param premio
     * @return el resultado de la query para la clase menuAdmin. Controla errores SQL
     */
    public int obtener2(Premio premio) {
        String query = "";
        int r = 0;
        BaseDeDatos bd = new BaseDeDatos();
        ResultSet resultado = null;
        Statement sentencia = null;
        String cadfecha=premio.getFecha();
        try {
            query = "SELECT * FROM premios where year(fecha)= "+ cadfecha.substring(0,4) + " and month(fecha) = " + cadfecha.substring(5,7) 
            + " and day(fecha) = " + cadfecha.substring(8,10)+ " ;";
            Connection conexion = bd.conectar();
            if (conexion != null) {
                sentencia = conexion.createStatement();
                resultado = sentencia.executeQuery(query);

                while (resultado.next()) {
                    int id = resultado.getInt("id");
                    String fecha = resultado.getString("fecha");     
                    int premiado = resultado.getInt("premiado");

                    // Imprimir los resultados.
                    System.out.format("%d, %s, %d\n", id, fecha, premiado);
                    r = 1;
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
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
        }
        return r;
    }

}

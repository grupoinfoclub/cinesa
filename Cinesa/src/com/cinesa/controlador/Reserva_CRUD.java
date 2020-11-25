package com.cinesa.controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.cinesa.modelo.Reserva;
import com.cinesa.transversal.BaseDeDatos;

public class Reserva_CRUD {
	public Reserva reserva;//recibimos objeto
	
	/**
	 * constructor que recibe por parámetro la instancia de la clase Reserva
	 * @param usuario
	 */
	public Reserva_CRUD(Reserva reserva) {
		this.reserva = reserva;
	}
	
	private String estado = "L"; // L= libre.
	private int usuario = 10000; // esto es para poner un usuario por defecto a la tabla reservas cuando está a null
	/**
	 * Procemiento de inserción de una reserva en BD. Controla la conexión y el registro.
	 * @param reserva
	 * @throws SQLException
	 */
    public void agregar(Reserva reserva) throws SQLException {
        String query = "";
        BaseDeDatos bd = new BaseDeDatos();
        Connection conexion = bd.conectar();
        Statement sentencia = null;
        if (conexion != null) {
            sentencia = conexion.createStatement();

            query = "INSERT INTO reservas(ciudad, " + "centro, " + "sala, "
                    + "fecha, " + "hora, " + "fila, " + "asiento, " + "estado, " + "usuario " + ") VALUES (" + reserva.getCiudad()
                    + ", " + reserva.getCentro() + ", " + reserva.getSala() + ", " + "'" + reserva.getFecha() + "', " + "'" + reserva.getHora() 
                    + "', " + reserva.getFila() + ", " + reserva.getAsiento() + ", " + "'" + estado + "', " + usuario + ");";
            
            sentencia.executeUpdate(query);
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
     * funcion select filtrando por ciudad,sala,centro y estado libre para la opción del menú para elección de butacas.
     * @param reserva
     * @return
     */
    public ResultSet obtener(Reserva reserva) {
        String query = "";
        BaseDeDatos bd = new BaseDeDatos();
        ResultSet resultado = null;
        Statement sentencia = null;
        try {
            query = "SELECT * FROM reservas WHERE ciudad= " + reserva.getCiudad() + " and centro= " + reserva.getCentro() + " and sala= " 
        + reserva.getSala() +" and fecha= '" + reserva.getFecha() + "' and estado = estado;";
            Connection conexion = bd.conectar();
            if (conexion != null) {
                sentencia = conexion.createStatement();
                resultado = sentencia.executeQuery(query);
				System.out.println("╔════════════════════════════════════════════════════════════════════════════════════════════╗");
				System.out.println("║      ID DE RESERVA - CIUDAD - CENTRO - SALA - FECHA - HORA - FILA - ASIENTO - ESTADO       ║");
				System.out.println("╚════════════════════════════════════════════════════════════════════════════════════════════╝");

                while (resultado.next()) {
                    int id = resultado.getInt("id");
                    int ciudad = resultado.getInt("ciudad");
                    int centro = resultado.getInt("centro");
                    int sala = resultado.getInt("sala");
                    String fecha = resultado.getString("fecha");
                    String hora = resultado.getString("hora");     
                    int fila = resultado.getInt("fila");
                    int asiento = resultado.getInt("asiento");
                    String estado = resultado.getString("estado");

                    // Imprimir los resultados.
                    if (estado.equals("R")) {
                    	System.err.println(id+", "+ ciudad+", "+centro+", "+sala+", "+fecha+", "+hora+", "+fila+", "+asiento+", "+estado);
                    }else {
                    	System.out.format("%d, %d, %d, %d, %s, %s, %d, %d, %s\n", id, ciudad,centro,sala,fecha,hora,fila,asiento,estado);
                    }
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
        return resultado;
    }
    
    /**
     * Procedimiento update del estado de la reserva. Se filtrará por fila y asiento en la query. Controla errores SQL
     * @param reserva instancia de la clase Reserva
     * @param estado
     */
    public void editar_estado(Reserva reserva, String estado1, int id) {
        String query = "";
        BaseDeDatos bd = new BaseDeDatos();
        Statement sentencia = null;
        PreparedStatement sentenciaP = null;
        try {
        	query = "UPDATE reservas SET estado = '" + estado1 + "', usuario = " + id + " WHERE fila = " + reserva.getFila() + " and asiento = "
        + reserva.getAsiento() + " and fecha = '" + reserva.getFecha() + "';";
        	Connection conexion = bd.conectar();
            sentencia = conexion.createStatement();
            sentencia.execute(query);
            System.out.println("El registro se actualizó exitosamente.");
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
     * Procedimiento update del estado de la reserva. Se recibe en un array el resultado de la query random y la fecha. Controla errores SQL
     * @param reserva instancia de la clase Reserva
     * @param estado
     */
    public void editar_estado2(int[] x,String fecha, String estado) {
        String query = "";
        BaseDeDatos bd = new BaseDeDatos();
        PreparedStatement sentenciaP = null;
        try {
            query = "UPDATE reservas SET estado = ? WHERE fila = ? and asiento = ? and fecha = ?;";
            Connection conexion = bd.conectar();
            if (conexion != null) {
            	   sentenciaP = conexion.prepareStatement(query);
                   sentenciaP.setString(1, estado);
                   sentenciaP.setInt(2, x[0]);
                   sentenciaP.setInt(3, x[1]);
                   sentenciaP.setString(4, fecha);

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
     * funcion select filtrando por fila,asiento y estado para la opción del menú de comprobar estado de las butacas.
     * @param reserva
     * @return
     */
    @SuppressWarnings("unused")
	public int obtener2(Reserva reserva) {
        String query = "";
        int r = 0;
        BaseDeDatos bd = new BaseDeDatos();
        ResultSet resultado = null;
        Statement sentencia = null;
        try {
            query = "SELECT * FROM reservas WHERE fila= " + reserva.getFila() + " and asiento= " + reserva.getAsiento() + " and fecha = '" 
        + reserva.getFecha() + "' and estado = '" + reserva.getEstado() + "';";
            Connection conexion = bd.conectar();
            if (conexion != null) {
                sentencia = conexion.createStatement();
                resultado = sentencia.executeQuery(query);
				
                while (resultado.next()) {
                    int id = resultado.getInt("id");
                    int ciudad = resultado.getInt("ciudad");
                    int centro = resultado.getInt("centro");
                    int sala = resultado.getInt("sala");
                    String fecha = resultado.getString("fecha");
                    String hora = resultado.getString("hora");     
                    int fila = resultado.getInt("fila");
                    int asiento = resultado.getInt("asiento");
                    String estado = resultado.getString("estado");

                    // Imprimir los resultados.
                    	//System.out.format("%d, %d, %d, %d, %s, %s, %d, %d, %s\n", id, ciudad,centro,sala,fecha,hora,fila,asiento,estado);
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
     * funcion select filtrando por fila,asiento y estado para la opción del menú de comprobar estado de las butacas.
     * @param reserva
     * @return
     */
    public int[] obtener3(Reserva reserva) {
        String query = "";
        int[] r=new int[2];
        BaseDeDatos bd = new BaseDeDatos();
        ResultSet resultado = null;
        Statement sentencia = null;
        try {
            query = "SELECT fila,asiento From reservas where estado = 'L' order by rand() limit 1;";
            Connection conexion = bd.conectar();
            if (conexion != null) {
                sentencia = conexion.createStatement();
                resultado = sentencia.executeQuery(query);
				
                while (resultado.next()) {    
                    int fila = resultado.getInt("fila");
                    int asiento = resultado.getInt("asiento");
                    r[0]=fila;
                    r[1]=asiento;
                    // Imprimir los resultados.
                    	System.out.format("%d, %d\n", fila,asiento);
                    	//r = 1;
                }
                     
            }else {
            	System.out.println("Ha habido un problema al conectar con la base de datos");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } 
        
        return r;
    }
    
   
}

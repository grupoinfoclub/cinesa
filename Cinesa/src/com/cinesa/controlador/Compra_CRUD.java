package com.cinesa.controlador;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.cinesa.modelo.Compra;
import com.cinesa.transversal.BaseDeDatos;

public class Compra_CRUD {
	public Compra compra;//recibimos objeto
	
	/**
	 * constructor que recibe por parámetro la instancia de la clase Compra
	 * @param compra
	 */
	public Compra_CRUD(Compra compra) {
		this.compra = compra;
	}
	
	/**
	 * Procemiento de inserción de una compra en BD. Controla la conexión y el registro.
	 * @param compra
	 * @throws SQLException
	 */
    public void agregar(Compra compra) throws SQLException {
        String query = "";
        BaseDeDatos bd = new BaseDeDatos();
        Connection conexion = bd.conectar();
        Statement sentencia = null;
        if (conexion != null) {
            sentencia = conexion.createStatement();

            query = "INSERT INTO compras(fecha, " + "id_usuario, " + "importe, "
                    + "id_sala, " + "id_centro, " + "sesion " + ") VALUES ('" + compra.getFecha()
                    + "', " + compra.getId_usuario() + ", " + compra.getImporte() + ", " + compra.getId_sala() + ", " 
                    + compra.getId_centro() + ", " + "'" + compra.getSesion() + "');";
            
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
     * Procedimiento update de la compra. Se filtrará por ID en la query. Controla errores SQL
     * @param compra
     */
    public void editarImporte(Compra compra) {
        String query = "";
        BaseDeDatos bd = new BaseDeDatos();
        PreparedStatement sentenciaP = null;
        try {
            query = "UPDATE compras SET importe = ? WHERE id_usuario = ?;";
            Connection conexion = bd.conectar();
            if (conexion != null) {
            	   sentenciaP = conexion.prepareStatement(query);
                   sentenciaP.setInt(1, compra.getImporte());
                   sentenciaP.setInt(2, compra.getId_usuario());

                   sentenciaP.executeUpdate();

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
	 * funcion para obtener el importe por día del mes para la gráfica de ventas. Se filtra por fecha.
	 * @param compra
	 * @return
	 */
    public int obtenerImporte(Compra compra) {
        String query = "";
        BaseDeDatos bd = new BaseDeDatos();
        ResultSet resultado = null;
        Statement sentencia = null;
        int importe = 0;
        try {
            query = "select sum(importe) from compras where fecha = '" + compra.getFecha() + "';";
            Connection conexion = bd.conectar();
            sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(query);
            if (resultado.next()) {
            	importe = resultado.getInt(1);
            }else {
            	importe = 0;
            }
            
        }catch (SQLException e) {
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
		return importe;
    }
}

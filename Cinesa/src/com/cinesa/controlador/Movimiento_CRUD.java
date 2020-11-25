package com.cinesa.controlador;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.cinesa.modelo.Movimiento;
import com.cinesa.transversal.BaseDeDatos;

public class Movimiento_CRUD {
	public Movimiento movimiento;
	
	/**
	 * constructor que recibe por parámetro la instancia de la clase Movimiento
	 * @param movimiento
	 */
	public Movimiento_CRUD(Movimiento movimiento) {
		this.movimiento = movimiento;
	}
	/**
	 * procedimiento para insertar los movimientos de las cuentas al realizar la compra.
	 * @param movimiento
	 * @throws SQLException
	 */
	public void agregar(Movimiento movimiento) throws SQLException {
        String query = "";
        BaseDeDatos bd = new BaseDeDatos();
        Connection conexion = bd.conectar();
        Statement sentencia = null;
        if (conexion != null) {
            sentencia = conexion.createStatement();

            query = "insert into movimientos (cuenta,saldo) values ("+ movimiento.getCuenta() +","+ movimiento.getSaldo()+");";

            if (sentencia.executeUpdate(query) > 0) {
                System.out.println("El registro se insertó exitosamente.");
            } else {
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
}

package com.cinesa.transversal;

import java.sql.Connection;
import java.sql.DriverManager;

public class BaseDeDatos {
	
	//Variables para la conexi�n
	private final String URL = "jdbc:mysql://localhost:3306/";
	private final String BD = "cinesa";
	private final String USER = "root";
	private final String PASSWORD = "admin";
	
	//Inicializamos la variable a null
	public Connection conexion = null;
	
	//Con esta clase de la libreria conectamos con la BD. Controlar� errores y retornar� la conexi�n.
	@SuppressWarnings("finally")
	public Connection conectar(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
            conexion = DriverManager.getConnection(URL + BD, USER, PASSWORD);
//            if (conexion != null) {
//                System.out.println("�Conexi�n Exitosa!");
//            }
		} catch (Exception e) {
            e.printStackTrace();
		  } finally {
			  try {
				  if (conexion != null) {
					  return conexion;
				  }else {
					  return null;
				  }
			  }catch (Exception e) {
				  return null;
			  }     
	      }
	}
}

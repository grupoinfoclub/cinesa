package com.cinesa.principal;

import java.io.IOException;
import java.sql.SQLException;

import org.apache.pdfbox.exceptions.COSVisitorException;

import com.cinesa.transversal.*;
import com.cinesa.vista.Splash;

public class Principal {

	public static void main(String[] args) {

		// Conexión con la BD actual
		BaseDeDatos bd = new BaseDeDatos();
		bd.conectar();
		Splash sp = new Splash();

		try {
			sp.splash2();
			try {
				sp.entrarMenu();
			} catch (COSVisitorException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

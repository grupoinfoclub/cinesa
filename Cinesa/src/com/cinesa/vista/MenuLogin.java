
package com.cinesa.vista;
import java.sql.SQLException;
import java.util.*;

import org.apache.pdfbox.exceptions.COSVisitorException;

import com.cinesa.controlador.Usuario_CRUD;
import com.cinesa.modelo.Usuario;

public class MenuLogin {

	static int opcion;
	int id = 1;
	String dni = "";
	String pass = "";
	Scanner sc = new Scanner(System.in);
	
	/**
	 * Procedimiento para acceder al menú. Mediante un switch accederemos a las distintas opciones del menú.
	 * @param letra  letra de acceso recibida de la clase splash.
	 * splash
	 * @throws InterruptedException
	 * @throws SQLException
	 * @throws COSVisitorException 
	 */
	public void menu(String letra) throws InterruptedException, SQLException, COSVisitorException {
		MenuRegistro mr=new MenuRegistro();
		MenuLog ml2=new MenuLog();
		MenuAdmin ma=new MenuAdmin();
		if (letra.equalsIgnoreCase("E")) {
			System.out.println("╔════════════════════════╗");
			System.out.println("║      M  E  N  Ú        ║");
			System.out.println("║------------------------║");
			System.out.println("║ 1) REGISTRO            ║");
			System.out.println("║ 2) LOGIN               ║");
			System.out.println("║ 3) LOGIN ADMINISTRADOR ║");
			System.out.println("║ 4) SALIR               ║");
			System.out.println("╚════════════════════════╝");
			Thread.sleep(1000);
			System.out.println("Seleccione opción");
			opcion=sc.nextInt();
		}
	
		switch (opcion) {
		case 1:
			mr.registro();break;
		case 2:
			Scanner sc1 = new Scanner(System.in);
			System.out.println("DNI: ");
			dni=sc1.nextLine();
			System.out.println("Password: ");
			pass=sc1.nextLine();
			
			Usuario user2=new Usuario();
			user2.setId(id);
			user2.setDni(dni);
			user2.setClave(pass);
			user2.setRol(3);
			Usuario_CRUD u_crud=new Usuario_CRUD(user2);
			
			int r = 0;
			
			r = u_crud.obtener2(user2);

			if (r==1) {
				ml2.login(dni); 
			}else {
				System.out.println("El usuario no está registrado en el sistema");
			}
			break;
		case 3:
			Scanner sc11 = new Scanner(System.in);
			System.out.println("DNI: ");
			dni=sc11.nextLine();
			System.out.println("Password: ");
			pass=sc11.nextLine();
			Usuario user3=new Usuario();
			user3.setId(id);
			user3.setDni(dni);
			user3.setClave(pass);
			user3.setRol(1);
			Usuario_CRUD u_crud1=new Usuario_CRUD(user3);
			
			int r1 = 0;
			
			r1 = u_crud1.obtener3(user3);
			
			if(r1==1) {
				ma.menu();
			}else {
				System.out.println("El usuario no es administrador");
				menu("e");
			}
			break;	
		case 4:
			System.out.println("¡¡¡¡Hasta Pronto!!!!!");
			break;
		}
	}

}
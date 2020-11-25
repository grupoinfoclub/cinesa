package com.cinesa.vista;

import java.sql.SQLException;
import java.util.Scanner;

import org.apache.pdfbox.exceptions.COSVisitorException;

import com.cinesa.controlador.Usuario_CRUD;
import com.cinesa.modelo.*;

public class MenuRegistro {
	
	private int id;
	private String nombre = "";
	private String dni = "";
	private String pass = "";
	private String confirmPass = "";
	private String letra;
	Scanner sc = new Scanner(System.in);
	
	/**
	 * Procedimiento para tomar los datos de inserción en base de datos de cada usuario.
	 * Se confirmará la contraseña si coinciden pero no el número de intentos.
	 * Por último llamaremos a la función que nos retorna el resultado de la query de la clase 
	 * usuario_CRUD y si no existe el usuario previamente en la BD lo insertará.
	 * @throws SQLException
	 * @throws InterruptedException 
	 * @throws COSVisitorException 
	 */
	public void registro() throws SQLException, InterruptedException, COSVisitorException {
		System.out.println("╔════════════════════════╗");
		System.out.println("║    R E G I S T R O     ║");
		System.out.println("║------------------------║");
		System.out.println("║  Nombre:               ║");
		System.out.println("║  DNI  :                ║");
		System.out.println("║  Password:             ║");
		System.out.println("║  Confirmar Password:   ║");
		System.out.println("╚════════════════════════╝");
		
		
		nombre=sc.nextLine();
		dni=sc.nextLine();
		pass=sc.nextLine();
		confirmPass=sc.nextLine();
		
		while (!pass.equals(confirmPass)) {
			System.out.println("╔════════════════════════╗");
			System.out.println("║    R E G I S T R O     ║");
			System.out.println("║------------------------║");
			System.out.println("║ ERROR, las contraseñas ║");
			System.out.println("║ no coinciden           ║");
			System.out.println("║ Introduzca password:   ║");
			System.out.println("║ Confirmar password:    ║");
			System.out.println("╚════════════════════════╝");
			pass=sc.nextLine();
			confirmPass=sc.nextLine();
		}
		Usuario user1=new Usuario();
		user1.setId(id);
		user1.setNombre(nombre);
		user1.setDni(dni);
		user1.setClave(pass);
		user1.setRol(3);
		Usuario_CRUD u_crud=new Usuario_CRUD(user1);

		int r = 0;
		
		r = u_crud.obtener(user1);

		if (r==0) {
			u_crud.agregar(user1);
			System.out.println("╔════════════════════════════╗");
			System.out.println("║     USUARIO REGISTRADO     ║");
			System.out.println("╚════════════════════════════╝");
			MenuLogin ml=new MenuLogin();
			letra = "e";
			ml.menu(letra);
		}else {
			System.out.println("╔════════════════════════════════════╗");
			System.out.println("║   EL USUARIO YA ESTÁ REGISTRADO    ║");
			System.out.println("╚════════════════════════════════════╝");
		}
	
		
	}
}
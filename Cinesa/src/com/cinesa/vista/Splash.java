package com.cinesa.vista;
import java.sql.SQLException;
import java.util.*;

import org.apache.pdfbox.exceptions.COSVisitorException;
public class Splash {

	static String letra;
	
	Scanner sc = new Scanner(System.in);
	
	public void splash2() throws InterruptedException{
		System.out.println("╔════════════════════════╗");
		System.out.println("║                        ║");
		System.out.println("║                        ║");
		System.out.println("║     C I N E S A        ║");
		System.out.println("║                        ║");
		System.out.println("║                        ║");
		System.out.println("╚════════════════════════╝");
		Thread.sleep(3*1000);
	}
	/**
	 * Procedimiento para acceder al menú
	 * @throws InterruptedException
	 * @throws SQLException
	 * @throws COSVisitorException 
	 */
	public void entrarMenu() throws InterruptedException, SQLException, COSVisitorException {
		MenuLogin ml=new MenuLogin();
		System.out.println("Pulse la tecla E para entrar");
		letra=sc.nextLine();
		
		while (!letra.equalsIgnoreCase("e")){
			System.out.println("Pulse la tecla E para entrar");
			letra=sc.nextLine();
		}
		ml.menu(letra);
		
	}
	
}
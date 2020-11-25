package com.cinesa.vista;

import java.awt.Panel;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JOptionPane;

import org.apache.pdfbox.exceptions.COSVisitorException;
import com.cinesa.controlador.Centro_CRUD;
import com.cinesa.controlador.Ciudad_CRUD;
import com.cinesa.controlador.Compra_CRUD;
import com.cinesa.controlador.Cuenta_CRUD;
import com.cinesa.controlador.Movimiento_CRUD;
import com.cinesa.controlador.Pelicula_CRUD;
import com.cinesa.controlador.Premio_CRUD;
import com.cinesa.controlador.Reserva_CRUD;
import com.cinesa.controlador.Sala_CRUD;
import com.cinesa.controlador.Usuario_CRUD;
import com.cinesa.modelo.Centro;
import com.cinesa.modelo.Ciudad;
import com.cinesa.modelo.Compra;
import com.cinesa.modelo.Cuenta;
import com.cinesa.modelo.Movimiento;
import com.cinesa.modelo.Pelicula;
import com.cinesa.modelo.Premio;
import com.cinesa.modelo.Reserva;
import com.cinesa.modelo.Sala;
import com.cinesa.modelo.Usuario;
import com.cinesa.principal.Ventana;
import com.cinesa.transversal.BaseDeDatos;


public class MenuLog {
	
	static int opcion;
	private int cent;
	private int ciud;
	private String peli;
	private String letra,resp;
	private int dia;
	private int fila,asiento,num;
	private String dnireservas;
	Scanner sc = new Scanner(System.in);
	
	/**
	 * Procedimiento para acceder a las opciones del menú una vez logueado.
	 * @throws InterruptedException
	 * @throws SQLException 
	 * @throws COSVisitorException 
	 */
	@SuppressWarnings("unused")
	public void login(String dnireservas) throws InterruptedException, SQLException, COSVisitorException {
		this.dnireservas=dnireservas;
		System.out.println("╔════════════════════════╗");
		System.out.println("║ M E N Ú   C I N E S A  ║");
		System.out.println("║------------------------║");
		System.out.println("║ 1) LISTADO CENTROS     ║");
		System.out.println("║ 2) LISTADO PELICULAS   ║");
		System.out.println("║ 3) LISTADO SALAS       ║");
		System.out.println("║ 4) COMPRAR ENTRADA     ║");
		System.out.println("║ 5) ATRÁS               ║");
		System.out.println("╚════════════════════════╝");
		Thread.sleep(1000);
		System.out.println("Seleccione opción");
		opcion=sc.nextInt();
		
		Ciudad ci=new Ciudad();
		Ciudad_CRUD ci_crud=new Ciudad_CRUD(ci);
		ResultSet r = null;
		switch (opcion) {
		case 1:
			escogerCentros(r,ci_crud,ci);
			salir();
			break;
		case 2:
			escogerCentros(r,ci_crud,ci);
			escogerPeliSala("peliculas");
			salir();
			break;
		case 3:
			escogerCentros (r,ci_crud,ci);
			escogerPeliSala("salas");
			salir();
			break;
		case 4:
			Usuario user2=new Usuario();
			user2.setDni(dnireservas);
			Usuario_CRUD user2_crud=new Usuario_CRUD(user2);
			int idUser = user2_crud.obtener5(user2);
			Cuenta cu=new Cuenta();
			cu.setUsuario(idUser);
			Cuenta_CRUD cu_crud=new Cuenta_CRUD(cu);
			Premio pr1=new Premio();
			pr1.setPremiado(idUser);
			Premio_CRUD pr1_crud=new Premio_CRUD(pr1);
			int premiado = pr1_crud.obtener(pr1);
			if (cu_crud.obtener(cu)==1 || premiado==1){
				escogerCentros(r,ci_crud,ci);
				escogerPeliSala("peliculas");
				System.out.println("╔═════════════════════════════╗");
				System.out.println("║        ESCOGE TÍTULO        ║");
				System.out.println("╚═════════════════════════════╝");
				peli=sc.nextLine();
				Pelicula p2=new Pelicula(peli);
				p2.setTitulo(peli);
				Pelicula_CRUD p2_crud=new Pelicula_CRUD(p2);
				int r7 = 0;
				r7 = p2_crud.filtroPeli(p2);
				System.out.println("╔═════════════════════════════╗");
				System.out.println("║     ELECCIÓN DE FECHA       ║");
				System.out.println("╚═════════════════════════════╝");
				System.out.println("¿Qué día desea reservar?");
				dia= sc.nextInt();
				sc.nextLine();
				dia=comprobar_dia(dia);
				Calendar fecha = Calendar.getInstance();
				int año = fecha.get(Calendar.YEAR);
			    int mes = fecha.get(Calendar.MONTH) + 1;
				Reserva re=new Reserva();
				re.setCiudad(ciud);
				re.setCentro(cent);
				re.setSala(r7);
				re.setFecha(año+"-"+mes+"-"+dia);
				ResultSet r5 = null;
				Reserva_CRUD re_crud =new Reserva_CRUD(re);
				r5 = re_crud.obtener(re);
				System.out.println("╔═════════════════════════════╗");
				System.out.println("║     ELECCIÓN DE BUTACAS     ║");
				System.out.println("╚═════════════════════════════╝");
				
				System.out.println("Elige el número de butacas:");
				num=sc.nextInt();
				sc.nextLine();
				System.out.println("¿Quieres escoger las butacas?");
				resp=sc.nextLine();
				elegir_butacas(resp,año,mes,dia);
			}else {
				System.out.println("El saldo es insuficiente para realizar la compra de la entrada.");
			}
			salir();
			break;
		case 5:
			MenuLogin ml=new MenuLogin();
			letra = "e";
			ml.menu(letra);
			break;
		default:
			System.out.println("La opción seleccionada no es correcta.");
			login(dnireservas);
		}	
	}
	
	/**
	 * funcion para retornar el día actual para comprobar que no puedan escoger dia anterior
	 * @param dia
	 * @return
	 */
	public int comprobar_dia(int dia) {
		Calendar fecha = Calendar.getInstance();
		int dia2 = fecha.get(Calendar.DAY_OF_MONTH);
		while (dia<dia2) {
			System.out.println("La fecha seleccionada debe ser la de hoy o posterior");
			System.out.println("¿Qué día desea reservar?");
			dia= sc.nextInt();
		}
		return dia;
	}

	/**
	 * procedimiento para salir del menú o programa
	 * @throws InterruptedException
	 * @throws SQLException
	 * @throws COSVisitorException 
	 */
	public void salir() throws InterruptedException, SQLException, COSVisitorException {
		System.out.println("╔═════════════════════════════╗");
		System.out.println("║  PULSA 1 PARA VOLVER ATRÁS  ║");
		System.out.println("║  PULSA 2 PARA SALIR         ║");
		System.out.println("╚═════════════════════════════╝");
		System.out.println("Seleccione opción");
		opcion=sc.nextInt();
		while (opcion!=1 && opcion!=2) {
			System.out.println("Seleccione 1 ó 2 para volver atrás o salir");
			opcion=sc.nextInt();
		}
		if (opcion==1) {
			login(dnireservas);
		}else if (opcion==2){
			System.out.println("¡¡¡¡Hasta Pronto!!!!!");
		}
	}
	
	/**
	 * procedimiento para elegir butacas aleatoriamente o por elección
	 * @param resp
	 * @param anio
	 * @param mes
	 * @param dia
	 * @throws SQLException
	 */
	@SuppressWarnings("resource")
	public void elegir_butacas(String resp, int anio, int mes, int dia) throws SQLException {
		Scanner sc4 = new Scanner(System.in);
		int r14 = 0;
		if (resp.equalsIgnoreCase("si")) {
			for (int i = 0;i<num; i++) {
				int r11;
				do {
					System.out.println("Elige fila:");
					fila=sc4.nextInt();
					System.out.println("Elige asiento:");
					asiento=sc4.nextInt();
					Reserva re2=new Reserva();
					re2.setFila(fila);
					re2.setAsiento(asiento);
					re2.setFecha(anio+"-"+mes+"-"+dia);
					re2.setEstado("R");
					Reserva_CRUD re2_crud =new Reserva_CRUD(re2);
					r11 = re2_crud.obtener2(re2);
					if (r11==1) {
						System.out.println("La butaca ya se encuentra ocupada, por favor, seleccione una butaca libre.");
					}
				}while (r11==1);
				System.out.println("Has escogido la butaca: fila "+fila+" asiento "+asiento);
				Usuario user1=new Usuario();
				user1.setDni(dnireservas);
				Usuario_CRUD user1_crud=new Usuario_CRUD(user1);
				r14 = user1_crud.obtener5(user1);
				realizarReserva(fila,asiento,anio,mes,r14);
			}
			generarQR(r14);
		}else if (resp.equalsIgnoreCase("no")) {
			System.out.println("De acuerdo, el sistema le ofrecerá una butaca aleatoriamente");
			for (int i = 0;i<num; i++) {
				int[] r12;
				Usuario user1=new Usuario();
				user1.setDni(dnireservas);
				Usuario_CRUD user1_crud=new Usuario_CRUD(user1);
				r14 = user1_crud.obtener5(user1);
				Reserva re3=new Reserva();
				Reserva_CRUD re3_crud =new Reserva_CRUD(re3);
				r12 = re3_crud.obtener3(re3);
				realizarReserva(r12[0], r12[1], anio, mes, r14);
			}
			generarQR(r14);
		}else {
			System.out.println("La respuesta no es correcta");
		}
	}
	/**
	 * procedimiento para general el código QR con los datos del comprador. Nombre y fecha
	 * @param r14
	 * @throws SQLException
	 */
	public void generarQR(int r14) throws SQLException {
		String pathname = "C:\\temp\\venta.png";
        String query = "";
        BaseDeDatos bd = new BaseDeDatos();
        ResultSet resultado = null;
        Statement sentencia = null;
        String nombre="",apellidos="",fecha="";
        
        try {
            query = "SELECT nombre, apellidos, fecha FROM usuarios INNER JOIN compras WHERE compras.id_usuario=usuarios.id and usuarios.id= " + r14 + ";";
            Connection conexion = bd.conectar();
            sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(query);
            while (resultado.next()) {
                nombre = resultado.getString("nombre");
                apellidos = resultado.getString("apellidos");
                fecha = resultado.getString("fecha");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
	    
        String textToQr = " "+nombre+" "+apellidos+" "+fecha;

        try {
            Ventana.writeQR(textToQr, pathname);
            System.out.println("Código QR generado con éxito");
        } catch (Exception e) {
            e.printStackTrace();
        }
        enviarMail(r14);
	}
	/**
	 * procedimiento para enviar el código QR generado anteriormente por email al usuario.
	 */
	public void enviarMail(int r14) {
		String email;
		Usuario user6=new Usuario();
		user6.setId(r14);
		Usuario_CRUD user_crud6=new Usuario_CRUD(user6);
		email = user_crud6.obtener6(user6);
		
		String correoEnvia = "infoclub3000@gmail.com";
		String contrasena = ".Borlas69.";
		String receptor = email;
		String asunto = "Le remitimos su entrada de Cine ";
		String mensaje = "Buenos días, le remitimos su entrada de cine recientemente adquirida con nosotros. Un saludo.";

		System.out.println(" Preparando Configuración");
		Properties propiedad = new Properties();
		propiedad.setProperty("mail.smtp.host", "smtp.gmail.com"); 
		propiedad.setProperty("mail.smtp.starttls.enable", "true"); 
		propiedad.setProperty("mail.smtp.port", "465");
		propiedad.setProperty("mail.smtp.auth", "true");
		propiedad.setProperty("mail.smtp.user", correoEnvia);
		propiedad.setProperty("mail.smtp.password", contrasena);
		propiedad.setProperty("mail.transport.protocol", "smtp");
		propiedad.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

		Session sesion = Session.getInstance(propiedad, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(correoEnvia, contrasena);
			}
		});

		System.out.println("Configuración OK");
		System.out.println("Sesión OK");

		MimeMessage mail = new MimeMessage(sesion);
		try {
			mail.setFrom(new InternetAddress(correoEnvia));
			mail.addRecipient(Message.RecipientType.TO, new InternetAddress(receptor));
			mail.setSubject(asunto);
			mail.setText(mensaje);

			System.out.println("adjuntando........................");
			Multipart multipart = new MimeMultipart();
			MimeBodyPart attachPart = new MimeBodyPart();
			String attachFile = "c:\\temp\\venta.png";
			try {
				attachPart.attachFile(attachFile);
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("ERROR");
				return;
			}
			multipart.addBodyPart(attachPart);
			mail.setContent(multipart);
			System.out.println("archivo adjunto preparado........................");

			System.out.println("Enviando");
			Transport transportar = sesion.getTransport("smtp");
			transportar.connect(correoEnvia, contrasena);
			transportar.sendMessage(mail, mail.getRecipients(Message.RecipientType.TO));
			transportar.close();

			JOptionPane.showMessageDialog(null, "Listo, revise su correo");

		} catch (AddressException ex) {
			Logger.getLogger(Panel.class.getName()).log(Level.SEVERE, null, ex);
		} catch (MessagingException ex) {
			Logger.getLogger(Panel.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	/**
	 * procedimiento para finalizar la compra
	 * @param fila
	 * @param asiento
	 * @param anio
	 * @param mes
	 * @param r14
	 */
	public void realizarReserva(int fila, int asiento, int anio, int mes, int r14) {
		Reserva re1=new Reserva();
		re1.setFila(fila);
		re1.setAsiento(asiento);
		re1.setFecha(anio+"-"+mes+"-"+dia);
		re1.setCiudad(1);
		re1.setCentro(1);
		re1.setSala(1);
		Reserva_CRUD re1_crud =new Reserva_CRUD(re1);
		re1_crud.editar_estado(re1,"R",r14);
		Compra com=new Compra();
		com.setFecha(anio+"-"+mes+"-"+dia);
		com.setId_usuario(r14);
		com.setImporte(10);
		com.setId_sala(1);
		com.setId_centro(1);
		com.setSesion("21:00");
		Compra_CRUD com_crud=new Compra_CRUD(com);
		try {
			com_crud.agregar(com);
			Premio pr=new Premio();
			pr.setPremiado(r14);
			Premio_CRUD pr_crud=new Premio_CRUD(pr);
			int r15 = 0;
			r15 = pr_crud.obtener(pr);
			if (r15==1) {
				Compra com1=new Compra();
				com1.setId_usuario(r14);
				com1.setImporte(0);
				com1.setId_sala(1);
				com1.setId_centro(1);
				Compra_CRUD com1_crud=new Compra_CRUD(com1);
				com1_crud.editarImporte(com1);	
			}else {
				Cuenta cu1=new Cuenta();
				cu1.setUsuario(r14);
				Cuenta_CRUD cu1_crud=new Cuenta_CRUD(cu1);
				int idCuenta = cu1_crud.obtenerIDCuenta(cu1);
				int saldo = cu1_crud.obtenerSaldo(cu1);
				saldo -= 10;
				cu1_crud.editar_saldo(cu1,saldo);
				
				Movimiento mo=new Movimiento();
				mo.setSaldo(saldo);
				mo.setCuenta(idCuenta);
				Movimiento_CRUD mo_crud=new Movimiento_CRUD(mo);
				mo_crud.agregar(mo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * procedimiento para la elección de centros
	 * @param r
	 * @param ci_crud
	 * @param ci
	 */
	public void escogerCentros(ResultSet r,Ciudad_CRUD ci_crud, Ciudad ci) {
		System.out.println("╔═════════════════════════════╗");
		System.out.println("║     LISTADO DE CIUDADES     ║");
		System.out.println("╚═════════════════════════════╝");
		r = ci_crud.obtener(ci);
		System.out.println("╔═════════════════════════════╗");
		System.out.println("║        ESCOGE CIUDAD        ║");
		System.out.println("╚═════════════════════════════╝");
		ciud=sc.nextInt();
		sc.nextLine();
		Centro c1=new Centro(ciud);
		c1.setId_ciudad(ciud);
		Centro_CRUD c1_crud=new Centro_CRUD(c1);
		System.out.println("╔═════════════════════════════╗");
		System.out.println("║     LISTADO DE CENTROS      ║");
		System.out.println("╚═════════════════════════════╝");
		c1_crud.obtener(c1);
	}
	/**
	 * procedimiento para escoger listado de peliculas o de salas segén elección
	 * @param opcion
	 */
	@SuppressWarnings("unused")
	public void escogerPeliSala(String opcion) {
		System.out.println("╔═════════════════════════════╗");
		System.out.println("║        ESCOGE CENTRO        ║");
		System.out.println("╚═════════════════════════════╝");
		cent=sc.nextInt();
		sc.nextLine();
		if (opcion.equalsIgnoreCase("peliculas")) {
			Pelicula p=new Pelicula(cent);
			p.setId_centro(cent);
			Pelicula_CRUD p_crud=new Pelicula_CRUD(p);
			ResultSet r2 = null;
			System.out.println("╔═════════════════════════════╗");
			System.out.println("║    LISTADO DE PELÍCULAS     ║");
			System.out.println("╚═════════════════════════════╝");
			r2 = p_crud.obtener(p);
		}else if (opcion.equalsIgnoreCase("salas")) {
			Sala s=new Sala(cent);
			s.setId_centro(cent);
			Sala_CRUD s_crud=new Sala_CRUD(s);
			ResultSet r3;
			System.out.println("╔═════════════════════════════╗");
			System.out.println("║      LISTADO DE SALAS       ║");
			System.out.println("╚═════════════════════════════╝");
			r3 = s_crud.obtener(s);
		}
	}
}

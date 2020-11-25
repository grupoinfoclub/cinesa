package com.cinesa.vista;

import java.awt.Color;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import com.cinesa.controlador.Centro_CRUD;
import com.cinesa.controlador.Compra_CRUD;
import com.cinesa.controlador.Premio_CRUD;
import com.cinesa.controlador.Reserva_CRUD;
import com.cinesa.controlador.Usuario_CRUD;
import com.cinesa.modelo.Centro;
import com.cinesa.modelo.Compra;
import com.cinesa.modelo.Premio;
import com.cinesa.modelo.Reserva;
import com.cinesa.modelo.Usuario;

public class MenuAdmin {
	static int opcion;
	private int mes = 0;
	private int anio = 0;
	private int dias = 0;
	private int id;
	Scanner sc = new Scanner(System.in);
	
	/**
	 * Menú donde están las opciones de administrador como generar el historico de reservas por mes
	 * @throws InterruptedException
	 * @throws SQLException
	 * @throws COSVisitorException 
	 */
	public void menu() throws InterruptedException, SQLException, COSVisitorException {
		MenuLogin ml=new MenuLogin();
		System.out.println("╔════════════════════════╗");
		System.out.println("║      M  E  N  Ú        ║");
		System.out.println("║------------------------║");
		System.out.println("║ 1) GENERAR MES         ║");
		System.out.println("║ 2) PREMIO DIARIO       ║");
		System.out.println("║ 3) LISTADO CENTROS PDF ║");
		System.out.println("║ 4) GUARDAR CENTROS CSV ║");
		System.out.println("║ 5) LEER CENTROS CSV    ║");
		System.out.println("║ 6) GUARDAR CENTROS XML ║");
		System.out.println("║ 7) GRÁFICO VENTAS/MES  ║");
		System.out.println("║ 8) LEER CENTROS XML    ║");
		System.out.println("║ 9) ATRÁS               ║");
		System.out.println("║ 10) SALIR              ║");
		System.out.println("╚════════════════════════╝");
		Thread.sleep(1000);
		System.out.println("Seleccione opción");
		opcion=sc.nextInt();
		
		Centro c=new Centro();
		Centro_CRUD c_crud=new Centro_CRUD(c);
		
		switch (opcion) {
		case 1:
			Scanner sc1 = new Scanner(System.in);
			System.out.println("Seleccione mes");
			mes=sc1.nextInt();
			System.out.println("Seleccione año");
			anio=sc1.nextInt();
			dias = dias_mes(mes,anio);
			rellenarSala(dias,mes,anio);
			menu();
			break;
		case 2:
			int dia2,mes2,año2;
			Calendar fecha = Calendar.getInstance();
			int año = fecha.get(Calendar.YEAR);
		    int mes = fecha.get(Calendar.MONTH) + 1;
		    int dia = fecha.get(Calendar.DAY_OF_MONTH);
		    System.out.println("Introduce día");
		    dia2=sc.nextInt();
		    System.out.println("Introduce mes");
		    mes2=sc.nextInt();
		    System.out.println("Introduce año");
		    año2=sc.nextInt();
		    if (dia==dia2 && mes==mes2 && año==año2) {
		    	Premio pr=new Premio();
		    	pr.setFecha(año2+"-"+mes2+"-"+dia2);
				Premio_CRUD pr_crud=new Premio_CRUD(pr);
				int r1;
				r1 = pr_crud.obtener2(pr);
				if (r1==1) {
					System.out.println("El premio diario ya se ha generado.");
					menu();
				}else {
					Usuario u=new Usuario();
					Usuario_CRUD u_crud =new Usuario_CRUD(u);
					int r6;
					r6 = u_crud.obtenerRnd(u);
					u.setId(r6);
					u_crud.obtener4(u);
					Premio pr1=new Premio();
					Premio_CRUD pr1_crud=new Premio_CRUD(pr1);
					pr1.setPremiado(r6);
					pr1_crud.agregar(pr1);
					salir();
				}
		    }else {
		    	System.out.println("La fecha actual no corresponde con los datos introducidos.");
		    	menu();
		    }
			break;
		case 3:
			int pdfs = 600;
			PDDocument doc = null;
			try {
				doc = new PDDocument();
				PDPage page = new PDPage();
	            doc.addPage( page );
	            PDFont font = PDType1Font.COURIER_BOLD_OBLIQUE;
	            Color color = Color.blue; 
	            PDPageContentStream contentStream = null;
	            try {
					contentStream = new PDPageContentStream(doc, page);
				} catch (IOException e) {
					e.printStackTrace();
				}
	            try {
				contentStream.beginText();
	            contentStream.setFont( font, 16 );
	            contentStream.setNonStrokingColor(color);
	            contentStream.moveTextPositionByAmount( 160, 700 );
	            contentStream.drawString( "------LISTADO DE CENTROS------" );
	            contentStream.endText();
	            
	            contentStream.beginText();
	            contentStream.setFont( font, 12 );
	            contentStream.setNonStrokingColor(Color.GREEN);
	            contentStream.moveTextPositionByAmount( 75, 650 );
	            contentStream.drawString( "ID CENTRO - ID CIUDAD - NOMBRE - NÚMERO DE SALAS - AFORO" );
	            contentStream.endText();
	            
	            List<String> centros = Centro_CRUD.obtenerCentros();
	            for (int i = 0; i < centros.size(); i++) {
	            	contentStream.beginText();
	            	contentStream.setFont( font, 12 );
	            	contentStream.setNonStrokingColor(Color.magenta);
	            	contentStream.moveTextPositionByAmount(75, pdfs);
	                contentStream.drawString(centros.get(i));
	                pdfs=pdfs-30;
	                contentStream.endText();
				}
		        
	            contentStream.close();
	            doc.save( "c:\\temp\\prueba.pdf" );
	            System.out.println("El pdf se ha generado correctamente en la ruta c:\\temp\\prueba.pdf");
	            menu();
		        
	        } catch (IOException e) {
				e.printStackTrace();
	        }
			}finally {
	            if( doc != null )
	            {
	                try {
						doc.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
	            }
	        }
			
			break;
		case 4:
			c_crud.obtenerCSV(c);
			salir();
			break;
		case 5:
			c_crud.leerCSV();
			salir();
			break;
		case 6:
			c_crud.crearXML();
			salir();
			break;
		case 7:
			int mes1 = 0;
			int anio = 0;
			int totalDias = 0;
			XYSeries series = new XYSeries("Ventas");
			
			System.out.println("Introduce mes: ");
			mes1=sc.nextInt();
			System.out.println("Introduce año: ");
			anio=sc.nextInt();
			Compra com=new Compra();
			totalDias = dias_mes(mes1, anio);
	        // Introduccion de datos
			for (int i = 1; i <= totalDias; i++) {
				com.setFecha(anio+"-"+mes1+"-"+i);
				Compra_CRUD com_crud=new Compra_CRUD(com);
				series.add(i, com_crud.obtenerImporte(com));
			}
			
	        DateFormatSymbols dfs = new DateFormatSymbols();
	        String[] months = dfs.getMonths();
			        
	        XYSeriesCollection dataset = new XYSeriesCollection();
	        dataset.addSeries(series);
	        // (String title, String xAxisLabel, String yAxisLabel, XYDataset dataset, PlotOrientation orientation, boolean legend, boolean tooltips, boolean urls)
	        JFreeChart chart = ChartFactory.createXYLineChart(
	                "Ventas CINESA "+months[mes1-1]+ " "+anio, // T�tulo
	                "Dia", // Etiqueta Coordenada X
	                "Cantidad", // Etiqueta Coordenada Y
	                dataset, // Datos
	                PlotOrientation.VERTICAL,
	                true, // Muestra la leyenda de los productos (Producto A)
	                false,
	                false
	        );

	        // Mostramos la grafica en pantalla
	        ChartFrame frame = new ChartFrame("Ejemplo Grafica de tipo Lineal", chart); //titulo del frame
	        frame.pack();
	        frame.setVisible(true);
			break;
		case 8:
			c_crud.cargarXML();
			salir();
			break;
		case 9:
			String letra = "e";
			ml.menu(letra);
			break;
		case 10:
			System.out.println("¡¡¡¡Hasta Pronto!!!!!");
			break;
		}
	}
	
	/**
	 * procedimiento de salida del menú o programa
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
			menu();
		}else if (opcion==2){
			System.out.println("¡¡¡¡Hasta Pronto!!!!!");
		}
	}
	/**
	 * función para retornar el número de días que tendrá el mes elegido
	 * @param mes
	 * @param anio
	 * @return
	 */
	public int dias_mes(int mes, int anio) {
		switch (mes) {
		case 1: case 3: case 5: case 7: case 8: case 10: case 12:
			dias = 31; break;
		case 4: case 6: case 9: case 11:
			dias = 30; break;
		case 2:
			if ((anio % 4 == 0) && ((anio % 100 != 0) || (anio % 400 == 0))) {
				dias = 29;
			}else {
				dias = 28;
			}
			break;
			default:
				System.out.println("Mes incorrecto");
		}
		return dias;
	}
	/**
	 * procedimiento para poner en estado libre cada butaca de la sala
	 * @param dias
	 * @throws SQLException 
	 */
	public void rellenarSala(int dias, int mes, int anio) throws SQLException {
		for (int d=1;d<=dias;d++) {
			for (int f=1;f<=10;f++) {
				for (int a=1;a<=10;a++) {
					Reserva re=new Reserva();
					re.setId(id);
					re.setCiudad(1);
					re.setCentro(1);
					re.setSala(1);
					re.setFecha(anio+"-"+mes+"-"+d);
					re.setHora("21:00");
					re.setFila(f);
					re.setAsiento(a);
					re.setEstado("L");
					Reserva_CRUD re_crud=new Reserva_CRUD(re);
					re_crud.agregar(re);
				}
			}
		}
		System.out.println("╔══════════════════════════════════╗");
		System.out.println("║   -  MES GENERADO CON ÉXITO  -   ║");
		System.out.println("╚══════════════════════════════════╝");
	}
	
}

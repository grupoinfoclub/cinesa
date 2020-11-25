package com.cinesa.controlador;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;
import com.cinesa.modelo.Centro;
import com.cinesa.transversal.*;
import com.cinesa.vista.MenuLogin;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.jdom2.*;
import org.jdom2.input.SAXBuilder;


public class Centro_CRUD {
	public Centro centro;//recibimos objeto
	/**
	 * Contructor en el que recibimos el objeto instanciado centro
	 */
	public Centro_CRUD(Centro centro) {
		this.centro=centro;
	}
	/**
	 * Procemiento de inserción de un centro en BD. Controla la conexión y el registro.
	 * @param centro instancia de la clase Centro
	 * @throws SQLException
	 */
	 public void agregar(Centro centro) throws SQLException {
	        String query = "";
	        BaseDeDatos bd = new BaseDeDatos();
	        Connection conexion = bd.conectar();
	        Statement sentencia = null;
	        if (conexion != null) {
	            sentencia = conexion.createStatement();

		        query = "INSERT INTO centros(id_ciudad, " + "nombre, " + "num_salas, "
		                + "aforo, " + "activo, " + "borrado" + ") VALUES (" + centro.getId_ciudad() + ", " + "'" + centro.getNombre() + "', " + centro.getNum_salas() + ", " + centro.getAforo() + ", " + centro.getActivo() + ", " + centro.getBorrado() + ");";

		        if (sentencia.executeUpdate(query) > 0) {
		            System.out.println("El registro se insertó exitosamente.");
		        } else {
		            System.out.println("No se pudo insertar el registro.");
		        }

		        System.out.println(query);
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
		  * Procedimiento update del nombre del centro. Se filtrará por id_ciudad en la query. Controla errores SQL
		  * @param centro instancia de la clase Centro
		  * @param nombre
		  */
	    public void editar_nombre(Centro centro, String nombre) {
	        String query = "";
	        BaseDeDatos bd = new BaseDeDatos();
	        PreparedStatement sentenciaP = null;
	        try {
	            query = "UPDATE centros SET nombre = ? WHERE id_ciudad = ?;";
	            Connection conexion = bd.conectar();
	            if (conexion != null) {
	            	sentenciaP = conexion.prepareStatement(query);
	 	            sentenciaP.setString(1, nombre);
	 	            sentenciaP.setInt(2, centro.getId_ciudad());

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
	     * Procedimiento delete de un centro según su nombre. Controla errores SQL
	     * @param centro instancia de la clase Centro
	     */
	    public void eliminar(Centro centro) {
	        String query = "";
	        BaseDeDatos bd = new BaseDeDatos();
	        PreparedStatement sentenciaP = null;
	        try {
	            query = "DELETE FROM centros WHERE nombre = ?;";
	            Connection conexion = bd.conectar();
	            if (conexion != null) {
	            	sentenciaP = conexion.prepareStatement(query);
		            sentenciaP.setString(1, centro.getNombre());

		            sentenciaP.execute();
		            System.out.println("El registro se eliminó exitosamente.");
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
	     * Función select de todos los centros. Se retorna el resultado de la query
	     * @param centro instancia de la clase Centro
	     */
	    public ResultSet obtener(Centro centro) {
	        String query = "";
	        BaseDeDatos bd = new BaseDeDatos();
	        ResultSet resultado = null;
	        Statement sentencia = null;
	        try {
	            query = "SELECT * FROM centros where id_ciudad = " + centro.getId_ciudad() + ";";
	            Connection conexion = bd.conectar();
	            if (conexion != null) {
	                sentencia = conexion.createStatement();
		            resultado = sentencia.executeQuery(query);
		            System.out.println("╔════════════════════════════════════════════════════════════════════╗");
					System.out.println("║ NÚMERO DE CENTRO - ID DE CIUDAD - NOMBRE - NÚMERO DE SALAS - AFORO ║");
					System.out.println("╚════════════════════════════════════════════════════════════════════╝");
		            while (resultado.next()) {
		                int id = resultado.getInt("id");
		                int id_ciudad = resultado.getInt("id_ciudad");
		                String nombre = resultado.getString("nombre");
		                int num_salas = resultado.getInt("num_salas");
		                int aforo = resultado.getInt("aforo");
		                int activo = resultado.getInt("activo");
		                int borrado = resultado.getInt("borrado");
		               

		                // Imprimir los resultados.
		                System.out.format("%d, %d, %s, %d, %d, %d, %d\n", id, id_ciudad, nombre, num_salas, aforo, activo, borrado);
		            }
	            }else {
	            	System.out.println("Ha habido un problema al conectar con la base de datos");
	            }
	        
	        } catch (SQLException e) {
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
			return resultado ;
	    }
	    
	    /**
	     * Con este método obtenemos los datos de la BD y los guardamos en el fichero CSV.
	     * @param centro Instancia de la clase Centro
	     */
	    public void obtenerCSV(Centro centro) {
	        String query = "";
	        BaseDeDatos bd = new BaseDeDatos();
	        ResultSet resultado = null;
	        Statement sentencia = null;
	        String fichero="centros.csv";
	    	boolean existe = new File(fichero).exists();
	    	
	        try {
	        	CsvWriter t2 = new CsvWriter(new FileWriter(fichero, true),',');
	            query = "SELECT * FROM centros;";
	            Connection conexion = bd.conectar();
	            if (conexion != null) {
	                sentencia = conexion.createStatement();
		            resultado = sentencia.executeQuery(query);
		            try {
		            	if (!existe){
		            		t2.write("Id");
		         	        t2.write("Id_ciudad");
		         	        t2.write("Nombre");
		         	        t2.write("Num_salas");
		         	        t2.write("Aforo");
		         	      	t2.write("Activo");
		         	      	t2.write("Borrado");
		         	        t2.endRecord();
			            }
		            }catch (IOException e) {
		            	e.printStackTrace();
		            }
		            
		            while (resultado.next()) {
		                try {
		                	t2.write("" + resultado.getInt("id"));
		        	    	t2.write("" + resultado.getInt("id_ciudad")); 
		        	    	t2.write(resultado.getString("nombre")); 
		        	    	t2.write("" + resultado.getInt("num_salas"));
		        	    	t2.write("" + resultado.getInt("aforo"));
		        	    	t2.write("" + resultado.getInt("activo"));
		        	    	t2.write("" + resultado.getInt("borrado"));
		        	    	t2.endRecord();
		                }catch (IOException e) {
			            	e.printStackTrace();
			            }
		                
		            }
		            t2.close();
	            }else {
	            	System.out.println("Ha habido un problema al conectar con la base de datos");
	            }
	        
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }catch (IOException e1) {
	        	e1.printStackTrace();
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
	    }
	    /**
	     * Con este método leemos el fichero CSV guardado con el procedimiento anterior obtenerCSV
	     * @throws COSVisitorException 
	     */
	 public void leerCSV() throws COSVisitorException {
		 String fichero="centros.csv";
		 try {
			 boolean existe = new File(fichero).exists();
			 if (!existe) {
				 System.out.println("El archivo no existe");
				 MenuLogin ml=new MenuLogin();
				 String letra = "e";
				 try {
					ml.menu(letra);
				 } catch (InterruptedException e) {
					e.printStackTrace();
				 } catch (SQLException e) {
					e.printStackTrace();
				 }
			 }
			 CsvReader t1 =  new CsvReader(fichero);  
			 t1.readHeaders();
			 while(t1.readRecord()) {
				 String Id = t1.get("Id");
				 String Id_ciudad = t1.get("Id_ciudad");
				 String Nombre = t1.get("Nombre");
				 String Num_salas = t1.get("Num_salas");
				 String Aforo = t1.get("Aforo");
				 String Activo = t1.get("Activo");
				 String Borrado = t1.get("Borrado");
				 System.out.println("Id - "+Id+" Id_ciudad - "+Id_ciudad+" Nombre - "+Nombre+" Num_salas - "+Num_salas+" Aforo - "+Aforo+" Activo - "+Activo+" Borrado - "+Borrado);   
			 }
			 t1.close();
		 }catch (FileNotFoundException e) {
			    e.printStackTrace();
		 }catch (IOException e1) {
			    e1.printStackTrace();
		 }
	 }
	 /**
	  * procedimiento para crear el archivo xml del listado de centros. Lee del archivo de origen csv.
	  */
	 public void crearXML() {
		 String origen="centros.csv";
		 try {
			 boolean existe = new File(origen).exists();
			 if (!existe) {
				 System.out.println("Fin del proceso - El archivo no existe");
					return;
			 }
			 String destino="centros.xml";
			 existe = new File(destino).exists();
			 if (!existe) {
					System.out.println("Es la primera vez que se genera el archivo centros.xml");
			 }else {
				 System.out.println("Añadiendo centros al archivo xml");
			 }
			 
			//lectura
			CsvReader t1 =  new CsvReader(origen);  
			t1.readHeaders();
			
			FileWriter fp = null;
			PrintWriter pw = null;
			fp = new FileWriter(destino);
			pw = new PrintWriter(fp);
			pw.println("<?xml version=\"1.0\"?>");
			pw.println("<centros>");
			while(t1.readRecord()) { // mientras pueda leer datos en la fila
			   //leemos en cada iteracción 
			   String Id = t1.get("Id");
			   String Id_ciudad = t1.get("Id_ciudad");
			   String Nombre = t1.get("Nombre");
			   String Num_salas = t1.get("Num_salas");
			   String Aforo = t1.get("Aforo");
			   String Activo = t1.get("Activo");
			   String Borrado = t1.get("Borrado");
			   System.out.println(" Id--"+Id+" Id_ciudad--"+Id_ciudad+" Nombre--"+Nombre+" Num_salas--"+Num_salas+" Aforo--"+Aforo+" Activo--"+Activo+" Borrado--"+Borrado); 
			   //escritura
			   pw.println("<centro>");
			   pw.println("<id>"+Id+"</id>");
			   pw.println("<id_ciudad>"+Id_ciudad+"</id_ciudad>");
			   pw.println("<nombre>"+Nombre+"</nombre>");
			   pw.println("<num_salas>"+Num_salas+"</num_salas>");
			   pw.println("<aforo>"+Aforo+"</aforo>");
			   pw.println("<activo>"+Activo+"</activo>");
			   pw.println("<borrado>"+Borrado+"</borrado>");
			   pw.println("</centro>");
			}
			pw.println("</centros>");
			t1.close();
			pw.close();
			fp.close();
		 }catch (FileNotFoundException e) {
			 e.printStackTrace();
		 }catch (IOException e1) {
			 e1.printStackTrace();
		 }
	 }
	 /**
	  * procedimiento para cargar el archivo Xml del listado de centros
	  */
	 @SuppressWarnings({ "rawtypes", "unused" })
	public void cargarXML() {
		  //Se crea un SAXBuilder para poder parsear el archivo
		    SAXBuilder builder = new SAXBuilder();
		    File xmlFile = new File( "centros.xml" );
		    try{
		    	//Se crea el documento a traves del archivo
		        Document document = (Document) builder.build( xmlFile );
		        //Se obtiene la raiz 'tables'  centros
		        Element rootNode = document.getRootElement();
		        //Se obtiene la lista de hijos de la raiz 'tables'
		        List list = rootNode.getChildren( "centro" );  //tabla ahora centro
		        
		        System.out.println( "\tId\tId_ciudad\tNombre\tNum_salas\tAforo\tActivo\tBorrado");
		        
		        for ( int i = 0; i < list.size(); i++ ){
		        	//Se obtiene el elemento 'tabla' ahora centro
		            Element tabla = (Element) list.get(i);
		            //Se obtiene la lista de hijos del tag 'tabla' ahora centro
		            List lista_campos = tabla.getChildren();
		            String Id=tabla.getChildText("id");
		            String Id_ciudad=tabla.getChildText("id_ciudad");
		            String Nombre=tabla.getChildText("nombre");
		            String Num_salas=tabla.getChildText("num_salas");
		            String Aforo=tabla.getChildText("aforo");
		            String Activo=tabla.getChildText("activo");
		            String Borrado=tabla.getChildText("borrado");
		            
		            System.out.println("\t"+Id+"\t"+Id_ciudad+"\t\t"+Nombre+"\t\t"+Num_salas+"\t"+Aforo+"\t"+Activo+"\t"+Borrado);
		        }
		    }catch ( IOException io ) {
		        System.out.println( io.getMessage() );
		    }catch ( JDOMException jdomex ) {
		        System.out.println( jdomex.getMessage() );
		    }
	 }
	 /**
	  * método para obtener los centros para el pdf de listado de centros.
	  * @return
	  */
	 public static List<String> obtenerCentros() {
	        String query = "";
	        BaseDeDatos bd = new BaseDeDatos();
	        ResultSet resultado = null;
	        Statement sentencia = null;
	        List<String> centros = new ArrayList<>();
	        try {
	            query = "SELECT * FROM centros;";
	            Connection conexion = bd.conectar();
                sentencia = conexion.createStatement();
	            resultado = sentencia.executeQuery(query);
	            while (resultado.next()) {
	                int id = resultado.getInt("id");
	                int id_ciudad = resultado.getInt("id_ciudad");
	                String nombre = resultado.getString("nombre");
	                int num_salas = resultado.getInt("num_salas");
	                int aforo = resultado.getInt("aforo");

		                // Imprimir los resultados.
		                 centros.add("   " + id + "           " + id_ciudad + "       " + nombre + "          " + num_salas + "          " + aforo);
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
			return centros;
	    }

}

package com.cinesa.principal;

import javax.swing.JFrame;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Reader;
import com.google.zxing.Result;
import com.google.zxing.Writer;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeWriter;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

//indica a Java que no muestre una advertencia del campo omitido serialVersionUID para serializar
//JFrame es una clase utilizada en Swing para generar ventanas sobre las cuales a�adir distintos objetos 
//gr�ficos para escritorio con los que podr� interactuar el usuario
@SuppressWarnings("serial")
public class Ventana extends JFrame{
	/**
	 * Contructor para establecer los par�metros de Ventana
	 * @throws WriterException
	 */
	public Ventana() throws WriterException {
		int x=0;
        BufferedImage imagen = crearQR("pruebecilla", 300, 300);
        ImageIcon icono = new ImageIcon(imagen);
        JLabel etiqueta = new JLabel("");
         
        etiqueta.setIcon(icono);
         
        this.setIconImage(imagen);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Ejemplo de codigo QR");
        this.getContentPane().add(etiqueta);
        //empaqueta la dimensi�n del marco de forma autom�tica sin tener que usar setSize o setBounds
        //se adapta a la plataforma
        this.pack();        
    }
	/**
	 * funci�n para crear el QR y retornar la imagen al constructor
	 * @param datos
	 * @param ancho
	 * @param altura
	 * @return
	 * @throws WriterException
	 */
	 //la clase BufferedImage se usa para mantener una representaci�n de una imagen en memoria 
	// dentro de una aplicaci�n Java, de modo que la puedes modificar o guardar en cualquiera 
	//de los formatos est�ndar propios de im�genes.
	public BufferedImage crearQR(String datos, int ancho, int altura) throws WriterException {
        BitMatrix matrix; //Representa una matriz de bits 2D.
        Writer escritor = new QRCodeWriter();//Este objeto representa un c�digo QR como una matriz BitMatrix 2D de valores de escala de grises.
        //Encode=codifica
        matrix = escritor.encode(datos, BarcodeFormat.QR_CODE, ancho, altura); //EAN_8, QR_CODE, PDF_417, AZTEC
         
        BufferedImage imagen = new BufferedImage(ancho, altura, BufferedImage.TYPE_INT_RGB);
        //itera a trav�s de la matriz y dibuja los p�xeles a la imagen
        for(int y = 0; y < altura; y++) {
            for(int x = 0; x < ancho; x++) {
                int grayValue = (matrix.get(x, y) ? 0 : 1) & 0xff;
                imagen.setRGB(x, y, (grayValue == 0 ? 0 : 0xFFFFFF));
            }
        }
        return imagen;        
    }
	/**
	 * Procedimiento para escribir el QR con los datos y tama�o
	 * @param text
	 * @param pathname
	 * @throws WriterException
	 * @throws IOException
	 */
	 public static void writeQR(String text, String pathname) throws WriterException, IOException {

	        int width = 600;
	        int height = 400;

	        String imageFormat = "png"; // "jpeg" "gif" "tiff"
	        //Representa una matriz de bits 2D.  Encode=codifica
	        BitMatrix bitMatrix = new QRCodeWriter().encode(text, BarcodeFormat.QR_CODE, width, height);
	        //Se usa para escribir en archivos binarios de java
	        FileOutputStream outputStream = new FileOutputStream(new File(pathname));
	        //Escribe una BitMatrix en el BufferedImage, archivo o flujo. 
	        //depende de la librer�a zxing 
	        MatrixToImageWriter.writeToStream(bitMatrix, imageFormat, outputStream);

    }
	 /**
	  * Funci�n para leer el QR y retornar los datos 
	  * @param pathname
	  * @return
	  * @throws FormatException
	  * @throws ChecksumException
	  * @throws NotFoundException
	  * @throws IOException
	  */
	 //Suma de comprobaci�n=Checksum para archivos
    public static String readQR(String pathname) throws FormatException, ChecksumException, NotFoundException, IOException {

        InputStream qrInputStream = new FileInputStream(pathname);
        BufferedImage qrBufferedImage = ImageIO.read(qrInputStream);
        //Luminancia= es el brillo fotom�trico a la luz procedente de los objetos   Iluminancia=nivel de iluminaci�n sobre una superficie
        //jerarqu�a de clases para abstraer diferentes implementaciones de mapas de bits entre plataformas 
        // en una interfaz est�ndar para solicitar valores de luminancia en escala de grises. 
        LuminanceSource source = new BufferedImageLuminanceSource(qrBufferedImage);
        //algoritmo de umbral local algo m�s lento que los histogramas eficiente para im�genes de alta frecuencia de c�digos de barras
        //con datos negros sobre fondos blancos
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source)); 
        Reader reader = new MultiFormatReader(); //decodifica los formatos de los c�digos de barras admitidos por la librer�a zxing
        Result stringBarCode = reader.decode(bitmap);

        return stringBarCode.getText();
    }
}

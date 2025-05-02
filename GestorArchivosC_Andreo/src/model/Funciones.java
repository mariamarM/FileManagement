/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

/**
 *
 * @author andre
 */
public class Funciones {

    /**
     *
     * @param fileName crea una carpeta con el nombre del fileName
     */
    public static void createFolder(String fileName) {
        //  String path = System.getProperty("user.dir");
        String path = "data";

        String rutaCarpeta = path + File.separator + fileName;
        File carpeta = new File(rutaCarpeta);
        if (!carpeta.exists()) {
            carpeta.mkdir();
            System.out.println("Carpeta creada con exito.");
        }
    }

    /**
     *
     * @param path es la ruta del archivo donde se encuentra
     * @param fileName es el nombre del archivo que se crea en el Folder
     * @param content es el texto introducido, si no hay se creara el contenido
     */
    public static void createFile(String path, String fileName, String content) throws IOException {
        File file = new File(path + File.separator + fileName);
        file.getParentFile().mkdirs();
        //al ponerle true estas añadiendo el archvo q has creado si has puesto el condicional
        //preguntas para ver si existe pero no tiene sentido porq lo estas adjuntando aqui para ser modificado
        FileWriter wr = new FileWriter(file, true);
        wr.write(content + System.lineSeparator());
        wr.close();
       
            System.out.println("Archivo creado/modificado: " + file.getAbsolutePath());

      

    }

    /**
     *
     * @param path la ruta de los archivos
     * @return se devuelve el listado de los archivos
     */
    public static String[] showListFiles(String path) {
        File carpetaFormacion = new File(path);
        String[] listaDocumentos = carpetaFormacion.list();
//        String textoTeclado = br.readLine();
//        int numeroDocumento = Integer.parseInt(textoTeclado);
//        String rutaDocumento = path + File.separator+listaDocumentos[numeroDocumento];
        if (listaDocumentos == null || listaDocumentos.length == 0) {
            System.out.println("ERROR al listar los archivos porque la carpeta esta vacia.");
            return new String[0];
        }
        return listaDocumentos;
    }

    /**
     *
     * @param path la ruta del archivo
     * @param fileName junto con el fileName del archivo
     * @return el contenido del archivo
     */
    public static String showFile(String path, String fileName) throws IOException {
        File file = new File(path + "/" + fileName);
        return new String(Files.readAllBytes(file.toPath()));
    }

    /**
     *
     * @param path es la ruta seleccionada del archivo
     * @param fileName el nombre del archivo
     * @param newContent el contenido nuevo que pondra el usuario
     * @return si el archivo no existe devolvera false
     */
    public static boolean overWriteFile(String path, String fileName, String newContent) throws IOException {
        File archivo = new File(path + "/" + fileName);
        if (!archivo.exists()) {
            return false;
        } else {
            FileWriter nuevoContenido = new FileWriter(archivo, false);
            nuevoContenido.write(newContent);

            nuevoContenido.close();
            return true;

        }
    }

    /**
     *
     * @param path se borrara el path del archivo
     * @param fileName se borrara el nombre del archivo
     */
    public static void deleteFile(String path, String fileName) {
        File fichero = new File(path + File.separator + fileName);
        fichero.delete();
    }

    /**
     *
     * @param path la ruta del archivo
     * @param fileName el nombre del archivo
     * @return devolvera la cantidad de caracteres que tiene el archivo
     */
    public static int countChars(String path, String fileName) throws IOException {
        String contenidoArchivo = showFile(path, fileName);
        return contenidoArchivo.length();
    }

    /**
     *
     * @param path la ruta del archivo
     * @param fileName el nombre del archivo
     * @return devolvera la cantidad de palabras que contiene el archivo
     */
    public static int countWords(String path, String fileName) throws IOException {
        String contenido = showFile(path, fileName);

        String[] palabras = contenido.split("\\s+");
        return palabras.length;
    }

    /**
     *
     * @param path la ruta del archivo
     * @param fileName el nombre del archivo
     * @param oldWord palabra dentro del contenido a ser reemplazada
     * @param newWord palabra escrita por el usuario para ser reemplazada por la
     * OldWord
     * @return devuelve el nuevo contenido con las palabras intercambiadas.
     */
    public static String swapWords(String path, String fileName, String oldWord, String newWord) throws IOException {
        String archivoNormal = showFile(path, fileName);
        String archivoActualizado = archivoNormal.replace(oldWord, newWord);

        overWriteFile(path, fileName, archivoActualizado);
        return archivoActualizado;
    }

    /**
     *
     * @param path la ruta del archivo
     * @param fileName el nombre del archivo a ser conversionado
     * @throws java.io.IOException
     */
    public static void printPDF(String path, String fileName) throws IOException {
        String archivoNormal = showFile(path, fileName);
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        PDPageContentStream contentStream = new PDPageContentStream(document, page);
        contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA, 12);
        contentStream.newLineAtOffset(50, 700);

        String[] lines = archivoNormal.split("\n");
        for (String line : lines) {
            contentStream.showText(line);
            contentStream.newLineAtOffset(0, -15);
        }

        String pdfOutput = path + File.separator + fileName.replace(".txt", ".pdf");
        document.save(pdfOutput);
        document.close();

    }

}

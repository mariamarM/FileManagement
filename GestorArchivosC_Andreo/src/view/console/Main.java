/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.console;

import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import model.Funciones;

/**
 *
 * @author andre
 */
public class Main {

    static Scanner scan = new Scanner(System.in);
    public static final String COLOR = "\u001B[43m";

    public static void main(String[] args) throws IOException {
        String option;
        do {
            System.out.printf(COLOR + "Maria Andreo\n");
            System.out.println("1.- Crear una carpeta ");
            System.out.println("2.-Crear archivo");
            System.out.println("3.- Ver listado de archivos y/o carpetas");
            System.out.println("4.- Eliminar archivos y/o carpetas");
            System.out.println("5.- Sobreescribir archivos");
            System.out.println("6.- Cantidad palabras del archivo ");
            System.out.println("7.- Cantidad letras del archivo ");
            System.out.println("8.- Conversion a PDF");
            System.out.println("9.- Ver un archivo ");
            System.out.println("10.- Intercambiar palabras del archivo");
            System.out.println("11.- Salir\n");

            option = scan.nextLine();

            switch (option) {
                case "1":
                    crearCarpeta();
                    break;
                case "2":
                    crearArchivo();
                    break;
                case "3":
                    listaArchivos();
                    break;
                case "4":
                    eliminarArchivos();
                    break;
                case "5":
                    editarArchivos();
                    break;
                case "6":
                    contarPalabras();
                    break;
                case "7":
                    contarLetras();
                    break;
                case "8":
                    conversionPDF();
                    break;
                case "9":
                    verArchivo();
                    break;
                case "10":
                    intercambiarPalabras();
                    break;

            }

        } while (!option.equals("11"));
    }

    public static void crearCarpeta() {
        System.out.println("Como quieres llamar a la carpeta?");
        String nameFolder = scan.nextLine();
        Funciones.createFolder(nameFolder);
    }

    public static void crearArchivo() throws IOException {
        System.out.println("Cual es la ruta de la carpeta donde se va  guardar? ");
        String path = scan.nextLine();
        System.out.println("Nombre del archivo: ");
        String fileName = scan.nextLine();
        System.out.println("Contenido/mensaje del archivo: ");
        String contenido = scan.nextLine();
        Funciones.createFile(path, fileName, contenido);
         if (!fileName.endsWith(".txt")) {
            fileName += ".txt";
             System.out.println(fileName);
         }
    }

    public static void listaArchivos() {
        String path = scan.nextLine();
        String[] files = Funciones.showListFiles(path);
        if (files.length == 0) {
            System.out.println("No hay archivos o ruta no valida.");
        } else {
            for (String f : files) {
                System.out.println(f);
            }
        }
    }

    public static void eliminarArchivos() {
        System.out.println("Ruta de la carpeta: ");
        String path = scan.nextLine();

        System.out.println("Nombre del archivo: ");
        String fileName = scan.nextLine();

        Funciones.deleteFile(path, fileName);
    }

    public static void editarArchivos() throws IOException {
        System.out.print("Ruta de la carpeta:  ");
        String path = scan.nextLine();
        System.out.print("Nombre del archivo: ");
        String fileName = scan.nextLine();
        System.out.print("Contenido para añadir:  ");
        String content = scan.nextLine();
        boolean res = Funciones.overWriteFile(path, fileName, content);
        if (!res) {
            System.out.println("Archivo no existe.");
        }
    }

    public static void contarPalabras() throws IOException {
        System.out.print("Ruta de la carpeta:  ");
        String path = scan.nextLine();
        System.out.print("Nombre del archivo: ");
        String fileName = scan.nextLine();
        System.out.println("Numero de letras en el archivo: " + Funciones.countWords(path, fileName));

    }

    public static void contarLetras() throws IOException {
        System.out.print("Ruta de la carpeta:  ");
        String path = scan.nextLine();
        System.out.print("Nombre del archivo: ");
        String fileName = scan.nextLine();
        System.out.println("Numero de palabras en el archivo: " + Funciones.countChars(path, fileName));
    }

    public static void conversionPDF() throws IOException {
        System.out.print("Ruta de la carpeta:  ");
        String path = scan.nextLine();
        System.out.print("Nombre del archivo: ");
        String fileName = scan.nextLine();
        if (fileName.contains(".txt")) {
            Funciones.printPDF(path, fileName);
            System.out.println("PDF ha sido creado con exito.");
            System.out.println("Archivo nuevo: " + Funciones.showFile(path, fileName));
        }
    }

    public static void verArchivo() throws IOException {
        System.out.print("Ruta de la carpeta:  ");
        String path = scan.nextLine();
        System.out.print("Nombre del archivo: ");
        String fileName = scan.nextLine();
        System.out.println("Contenido del archivo:  " + Funciones.showFile(path, fileName));
    }

    public static void intercambiarPalabras() throws IOException {
        System.out.print("Ruta de la carpeta:  ");
        String path = scan.nextLine();
        System.out.print("Nombre del archivo: ");
        String fileName = scan.nextLine();
        System.out.println("Palabra que quieras reemplazar del archivo: ");
        String oW = scan.next();
        System.out.println("Palabra nueva que sera reemplazada por la anterior: ");
        String nW = scan.next();
        System.out.printf("Resultado:\n" + Funciones.swapWords(path, fileName, oW, nW));

    }
}

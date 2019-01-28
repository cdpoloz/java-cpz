package Util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Scanner;
import javax.imageio.ImageIO;

/**
 * @author CPZ
 */
public class Archivo {

    public static boolean existe(String ruta) {
        File f = new File(ruta);
        return f.exists();
    }

    public static ArrayList<String> obtenerLineas(StringBuilder ruta) {
        return obtenerLineas(ruta.toString());
    }

    public static ArrayList<String> obtenerLineas(String ruta) {
        ArrayList<String> lineas = new ArrayList();
        try {
            File file = new File(ruta);
            Scanner data = new Scanner(file);
            while (data.hasNext()) {
                String item = data.nextLine();
                lineas.add(item);
            }
        } catch (FileNotFoundException e) {
            Mensaje.archivoNoEncontrado(ruta);
            System.out.println(e.getMessage());
        }
        return lineas;
    }

    public static boolean grabarLinea(String data, StringBuilder ruta) {
        return grabarLinea(data, ruta.toString());
    }

    public static boolean grabarLinea(String data, String ruta) {
        try (PrintWriter pw = new PrintWriter(new FileOutputStream(ruta))) {
            pw.println(data);
        } catch (Exception e) {
            Mensaje.errorGrabarArchivo(ruta);
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public static boolean grabarLineas(ArrayList<String> data, String ruta) {
        try (PrintWriter pw = new PrintWriter(new FileOutputStream(ruta))) {
            data.forEach(d -> pw.println(d));
        } catch (Exception e) {
            Mensaje.errorGrabarArchivo(ruta);
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public static boolean grabarCadena(String data, String ruta) {
        try (PrintWriter pw = new PrintWriter(new FileOutputStream(ruta))) {
            pw.println(data);
        } catch (Exception e) {
            Mensaje.errorGrabarArchivo(ruta);
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public static String obtenerCadena(StringBuilder ruta) {
        return obtenerCadena(ruta.toString());
    }

    public static String obtenerCadena(String ruta) {
        StringBuilder cadena = new StringBuilder();
        try {
            File file = new File(ruta);
            Scanner data = new Scanner(file);
            while (data.hasNext()) {
                cadena.append(data.nextLine()).append("\n");
            }
        } catch (FileNotFoundException e) {
            Mensaje.archivoNoEncontrado(ruta);
            System.out.println(e.getMessage());
        }
        return cadena.toString();
    }

    public static int obtenerCantidadArchivos(String ruta) {
        int cantidad = 0;
        File f = new File(ruta);
        for (File file : f.listFiles()) {
            if (file.isFile()) {
                cantidad++;
            }
        }
        return cantidad;
    }

    public static boolean existeDirectorio(String ruta) {
        File f = new File(ruta);
        return f.exists() && f.isDirectory();
    }

    public static void crearDirectorio(String ruta) {
        new File(ruta).mkdirs();
    }

    public static ArrayList<String> obtenerNombreArchivos(String ruta) {
        ArrayList<String> nombres = new ArrayList();
        File carpeta = new File(ruta);
        File[] lstArchivos = carpeta.listFiles();
        for (File n : lstArchivos) {
            nombres.add(n.getName());
        }
        return nombres;
    }

    public static ArrayList<String> obtenerNombreArchivosEmpiezaCon(String ruta, String cadenaIni) {
        ArrayList<String> nombres = new ArrayList();
        File carpeta = new File(ruta);
        File[] lstArchivos = carpeta.listFiles();
        for (File n : lstArchivos) {
            if (n.getName().startsWith(cadenaIni)) {
                nombres.add(n.getName());
            }
        }
        return nombres;
    }

    public static ArrayList<String> obtenerNombreArchivosTerminaCon(String ruta, String cadenaFin) {
        ArrayList<String> nombres = new ArrayList();
        File carpeta = new File(ruta);
        File[] lstArchivos = carpeta.listFiles();
        for (File n : lstArchivos) {
            if (n.getName().endsWith(cadenaFin)) {
                nombres.add(n.getName());
            }
        }
        return nombres;
    }

    public static ArrayList<String> obtenerNombreArchivosContiene(String ruta, String cadena) {
        ArrayList<String> nombres = new ArrayList();
        File carpeta = new File(ruta);
        File[] lstArchivos = carpeta.listFiles();
        for (File n : lstArchivos) {
            if (n.getName().contains(cadena)) {
                nombres.add(n.getName());
            }
        }
        return nombres;
    }

    public static String imageToString(BufferedImage image, String extension) {
        String imageString = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, extension, bos);
            byte[] imageBytes = bos.toByteArray();
            imageString = Base64.getEncoder().encodeToString(imageBytes);
            bos.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return imageString;
    }

    public static BufferedImage stringToImage(String imageString) {
        BufferedImage image = null;
        byte[] imageByte = Base64.getDecoder().decode(imageString);
        try (ByteArrayInputStream bis = new ByteArrayInputStream(imageByte)) {
            image = ImageIO.read(bis);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return image;
    }

    public static boolean bimageToFile(BufferedImage bi, String ruta, String ext) {
        try {
            File imgOut = new File(ruta);
            ImageIO.write(bi, ext, imgOut);
        } catch (IOException e) {
            Mensaje.errorGrabarArchivo(ruta);
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public static boolean borrar(String ruta) {
//        System.out.println("Tratando de borrar: " + ruta);
        File f = new File(ruta);
        if (f.isDirectory()) {
            for (File ff : f.listFiles()) {
                borrar(ff);
            }
        }
        return f.delete();
    }
    
    public static boolean borrar(File f) {
//        System.out.println("Tratando de borrar: " + f.getPath());
        if (f.isDirectory()) {
            for (File ff : f.listFiles()) {
                borrar(ff);
            }
        }
        return f.delete();
    }
    
    public static String obtenerRutaUltimoArchivoModificado(String rutaDirectorio) {
        File directorio = new File(rutaDirectorio);
        File[] archivos = directorio.listFiles();
        if (archivos == null || archivos.length == 0) {
            return null;
        }
        File ultimoArchivoModificado = archivos[0];
        for (int i = 1; i < archivos.length; i++) {
            if (ultimoArchivoModificado.lastModified() < archivos[i].lastModified()) {
                ultimoArchivoModificado = archivos[i];
            }
        }
        return ultimoArchivoModificado.getAbsolutePath();
    }
    
    public static File obtenerUltimoArchivoModificado(String rutaDirectorio) {
        File directorio = new File(rutaDirectorio);
        File[] archivos = directorio.listFiles();
        if (archivos == null || archivos.length == 0) {
            return null;
        }
        File ultimoArchivoModificado = archivos[0];
        for (int i = 1; i < archivos.length; i++) {
            if (ultimoArchivoModificado.lastModified() < archivos[i].lastModified()) {
                ultimoArchivoModificado = archivos[i];
            }
        }
        return ultimoArchivoModificado;
    }
    
}

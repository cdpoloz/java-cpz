package Util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author CPZ
 */
public class Varios {

    public static void printHashmap(HashMap datos) {
        datos.keySet().forEach(llave -> System.out.println(llave + ": " + datos.get(llave)));
        System.out.println("");
    }

    public static ArrayList<String> comandoConsola(ArrayList<String> comando) {
        String s = null;
        ArrayList<String> resultado = new ArrayList();
        try {
            ProcessBuilder pb = new ProcessBuilder(comando);
            Process process = pb.start();
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
            BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            // read the output from the command
            //System.out.println("Here is the standard output of the command:\n");
            while ((s = stdInput.readLine()) != null) {
                if (s.equals("")) {
                    continue;
                }
                resultado.add(s);
            }
            // read any errors from the attempted command
            //System.out.println("Here is the standard error of the command (if any):\n");
            while ((s = stdError.readLine()) != null) {
                if (s.equals("")) {
                    continue;
                }
                resultado.add(s);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return resultado;
    }

    public static ArrayList<String> comandoConsola(String comando) {
        String s = null;
        ArrayList<String> resultado = new ArrayList();
        try {
            ProcessBuilder pb = new ProcessBuilder(comando);
            Process process = pb.start();
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
            BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            // read the output from the command
            //System.out.println("Here is the standard output of the command:\n");
            while ((s = stdInput.readLine()) != null) {
                if (s.equals("")) {
                    continue;
                }
                resultado.add(s);
            }
            // read any errors from the attempted command
            //System.out.println("Here is the standard error of the command (if any):\n");
            while ((s = stdError.readLine()) != null) {
                if (s.equals("")) {
                    continue;
                }
                resultado.add(s);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return resultado;
    }
    
    public static void detenerProceso(int pid) {
    }

    public static String os() {
        return System.getProperty("os.name").toLowerCase();
    }
    
    public static void nop() {
        System.out.print("");
    }

}

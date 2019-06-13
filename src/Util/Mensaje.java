package Util;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * @author CPZ
 */
public class Mensaje {

    public static void error() {
        JOptionPane.showMessageDialog(null, "", "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void error(Object mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void errorConsultaDB(String query) {
        StringBuilder mensaje = new StringBuilder();
        mensaje.append("Error al consultar la base de datos:");
        mensaje.append("\n");
        mensaje.append(query);
        mensaje.append("\n");
        JOptionPane.showMessageDialog(null, mensaje.toString(), "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void errorRegistroDB() {
        JOptionPane.showMessageDialog(null, "Error al registrar en la base de datos.", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void busquedaVaciaDB() {
        JOptionPane.showMessageDialog(null, "No se encontraron elementos para los criterios de búsqueda seleccionados.", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
    }

    public static boolean confirmarOperacion(String operacion) {
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea " + operacion + "?", "Advertencia", dialogButton);
        return dialogResult == 0;
    }

    public static void resultadoOperacion(boolean operacionRealizada) {
        if (operacionRealizada) {
            JOptionPane.showMessageDialog(null, "La operación se realizó correctamente.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "La operación no se pudo realizar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void archivoNoEncontrado(String ruta) {
        StringBuilder mensaje = new StringBuilder();
        mensaje.append("No se encontró el archivo: '").append(ruta).append("'");
        JOptionPane.showMessageDialog(null, mensaje.toString(), "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void archivosNoEncontrados(String ruta) {
        StringBuilder mensaje = new StringBuilder();
        mensaje.append("No se encontraron archivos: '").append(ruta).append("'");
        JOptionPane.showMessageDialog(null, mensaje.toString(), "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void errorCargarArchivo(String ruta) {
        StringBuilder mensaje = new StringBuilder();
        mensaje.append("Se produjo un error al cargar el archivo: '").append(ruta).append("'");
        JOptionPane.showMessageDialog(null, mensaje.toString(), "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void errorGrabarArchivo(String ruta) {
        StringBuilder mensaje = new StringBuilder();
        mensaje.append("Se produjo un error al grabar el archivo: '").append(ruta).append("'");
        JOptionPane.showMessageDialog(null, mensaje.toString(), "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void valorNoPermitido(String valor) {
        StringBuilder mensaje = new StringBuilder();
        mensaje.append("El valor ingresado no está permitido: '").append(valor).append("'");
        JFrame fr = new JFrame();
        fr.setAlwaysOnTop(true);
        JOptionPane.showMessageDialog(fr, mensaje.toString(), "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void valorFaltante(String valor) {
        StringBuilder mensaje = new StringBuilder();
        mensaje.append("Falta ingresar: '").append(valor).append("'.");
        JOptionPane.showMessageDialog(null, mensaje.toString(), "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void fechasErroneas() {
        JOptionPane.showMessageDialog(null, "La fecha final no puede ser anterior a la inicial.", "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void seleccionRegistro() {
        JOptionPane.showMessageDialog(null, "Debe seleccionar un registro.", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
    }

}

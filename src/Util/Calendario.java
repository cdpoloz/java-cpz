package Util;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author CPZ
 */
public class Calendario {

    public static Date agregarDias(Date fechaOrigen, int cantidadDias) {
        Calendar c = Calendar.getInstance();
        c.setTime(fechaOrigen);
        c.add(Calendar.DATE, cantidadDias);
        return c.getTime();
    }
    
    public static Date agregarMeses(Date fechaOrigen, int cantidadMeses) {
        Calendar c = Calendar.getInstance();
        c.setTime(fechaOrigen);
        c.add(Calendar.MONTH, cantidadMeses);
        return c.getTime();
    }
    
    public static Date agregarAnios(Date fechaOrigen, int cantidadAnios) {
        Calendar c = Calendar.getInstance();
        c.setTime(fechaOrigen);
        c.add(Calendar.YEAR, cantidadAnios);
        return c.getTime();
    }
    
    public static Date agregarSegundos(Date fechaOrigen, int cantidadSegundos) {
        Calendar c = Calendar.getInstance();
        c.setTime(fechaOrigen);
        c.add(Calendar.SECOND, cantidadSegundos);
        return c.getTime();
    }

    public static Date inicioMes(Date fecha) {
        Calendar c = Calendar.getInstance();
        c.setTime(fecha);
        c.set(Calendar.DAY_OF_MONTH, 1);
        return c.getTime();
    }

    public static boolean rangoFechasValido(Date fechaInicio, Date fechaFin) {
        if (fechaInicio != null && fechaFin != null) {
            if (fechaFin.before(fechaInicio)) {
                return false;
            }
        }
        return true;
    }

    public static int diasDiferencia(Date desde, Date hasta) {
        long duration = hasta.getTime() - desde.getTime();
        long horas = TimeUnit.MILLISECONDS.toHours(duration);
        return (int) horas / 24;
    }

    public static int minutosDiferencia(Date desde, Date hasta) {
        long duration = hasta.getTime() - desde.getTime();
        long segundos = TimeUnit.MILLISECONDS.toMinutes(duration);
        return (int) segundos;
    }

    public static int segundosDiferencia(Date desde, Date hasta) {
        long duration = hasta.getTime() - desde.getTime();
        long segundos = TimeUnit.MILLISECONDS.toSeconds(duration);
        return (int) segundos;
    }

    public static Date asignarHora(Date fecha, int hora, int minuto, int segundo) {
        Calendar c = Calendar.getInstance();
        c.setTime(fecha);
        c.set(Calendar.HOUR_OF_DAY, hora);
        c.set(Calendar.MINUTE, minuto);
        c.set(Calendar.SECOND, segundo);
        fecha = c.getTime();
        return fecha;
    }

    public static int obtenerHora(Date fecha) {
        Calendar c = Calendar.getInstance();
        c.setTime(fecha);
        return c.get(Calendar.HOUR_OF_DAY);
    }
    
    public static int obtenerAnio(Date fecha) {
        Calendar c = Calendar.getInstance();
        c.setTime(fecha);
        return c.get(Calendar.YEAR);
    }
    
    public static int obtenerMes(Date fecha) {
        Calendar c = Calendar.getInstance();
        c.setTime(fecha);
        return c.get(Calendar.MONTH) + 1;
    }
    
    public static int obtenerDia(Date fecha) {
        Calendar c = Calendar.getInstance();
        c.setTime(fecha);
        return c.get(Calendar.DAY_OF_MONTH);
    }

    public static int obtenerMinuto(Date fecha) {
        Calendar c = Calendar.getInstance();
        c.setTime(fecha);
        return c.get(Calendar.MINUTE);
    }

    public static int obtenerSegundo(Date fecha) {
        Calendar c = Calendar.getInstance();
        c.setTime(fecha);
        return c.get(Calendar.SECOND);
    }

}

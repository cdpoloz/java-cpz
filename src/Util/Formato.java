package Util;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

/**
 * @author CPZ
 */
public class Formato {

    public static Date stringToDate(String fecha, boolean orden) {
        if (fecha == null || fecha.equals("")) {
            return null;
        }
        Date f = null;
        SimpleDateFormat formatter = null;
        try {
            if (orden) {
                formatter = new SimpleDateFormat("dd-MM-yyyy");
            } else {
                formatter = new SimpleDateFormat("yyyy-MM-dd");
            }
            f = (Date) formatter.parse(fecha);
        } catch (ParseException e) {
            Mensaje.error(e.getMessage());
        }
        return f;
    }

    public static String dateToString(Date fecha, boolean orden, String sep) {
        if (fecha == null) {
            return "";
        }
        if (orden) {
            return new SimpleDateFormat("dd" + sep + "MM" + sep + "yyyy").format(fecha);
        } else {
            return new SimpleDateFormat("yyyy" + sep + "MM" + sep + "dd").format(fecha);
        }
    }

    public static String dateToString(Date fecha, boolean orden, String sep, boolean hora) {
        if (fecha == null) {
            return "";
        }
        if (orden) {
            if (hora) {
                return new SimpleDateFormat("dd" + sep + "MM" + sep + "yyyy HH:mm:ss").format(fecha);
            } else {
                return null;
            }
        } else if (hora) {
            return new SimpleDateFormat("yyyy" + sep + "MM" + sep + "dd HH:mm:ss").format(fecha);
        } else {
            return null;
        }
    }

    public static String invertirFechaString(String fecha) {
        String[] datos = null;
        String separador = "";
        if (fecha.contains("-")) {
            datos = fecha.split("-");
            separador = "-";
        } else if (fecha.contains("/")) {
            datos = fecha.split("/");
            separador = "/";
        }
        return datos[2] + separador + datos[1] + separador + datos[0];
    }

    public static String bigDecimalToString(BigDecimal numero, String tipo, int CANTIDAD_DECIMALES) {
        if (numero == null) {
            return floatToString(0f, tipo, CANTIDAD_DECIMALES);
        }
        return floatToString(numero.floatValue(), tipo, CANTIDAD_DECIMALES);
    }

    public static String floatToString(float numero, String tipo, int CANTIDAD_DECIMALES) {
        StringBuilder formato = new StringBuilder();
        DecimalFormat df;
        if (tipo.equals("sepMiles")) {
            formato.append("#,##0.");
        } else if (tipo.equals("dec")) {
            formato.append("#0.");
        }
        if (CANTIDAD_DECIMALES == 0) {
            formato.setLength(formato.length() - 1);
        } else {
            for (int i = 0; i < CANTIDAD_DECIMALES; i++) {
                formato.append("0");
            }
        }
        df = new DecimalFormat(formato.toString());
        return df.format(numero);
    }

    public static float stringToFloat(String numero) {
        float resultado = 0f;
        try {
            if (numero == null || numero.equals("")) {
                return 0f;
            }
            numero = numero.replace(",", "");
            resultado = Float.parseFloat(numero);
        } catch (NumberFormatException e) {
            Mensaje.error(e.getMessage());
        }
        return resultado;
    }

    public static float redondearFloat(float numero, int numeroDecimales) {
        BigDecimal bd = new BigDecimal(Float.toString(numero));
        bd = bd.setScale(numeroDecimales, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }

    public static Date quitarHora(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static String getTime(String formato) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        String hora = String.format("%02d", cal.get(Calendar.HOUR_OF_DAY));
        String minuto = String.format("%02d", cal.get(Calendar.MINUTE));
        String segundo = String.format("%02d", cal.get(Calendar.SECOND));
        StringBuilder time = new StringBuilder();
        if (formato.equals("hh:mm")) {
            time.append(hora).append(":").append(minuto);
        } else if (formato.equals("hh:mm:ss")) {
            time.append(hora).append(":").append(minuto).append(":").append(segundo);
        }
        return time.toString();
    }

    public static Timestamp getTimestamp(Date fecha) {
        return new Timestamp(fecha.getTime());
    }

    public static String listToString(ArrayList<String> lst) {
        StringBuilder str = new StringBuilder();
        lst.forEach(s -> str.append(s).append("\n"));
        return str.toString();
    }

    public static ArrayList<String> stringToList(String str) {
        return arrayToList(str.split("\\r?\\n|\\r"));
    }

    public static ArrayList arrayToList(Object[] array) {
        return new ArrayList(Arrays.asList(array));
    }

}

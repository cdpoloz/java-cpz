package Conn;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;

/**
 * @author CPZ
 */
public class Web {

    public static boolean webIsAvailable(String webUrl) {
        if (webUrl.startsWith("www.")) {
            webUrl = "http://" + webUrl;
        }
        try {
            final URL url = new URL(webUrl);
            final URLConnection conn = url.openConnection();
            conn.connect();
            return true;
        } catch (MalformedURLException e) {
            return false;
        } catch (IOException e) {
            return false;
        }
    }

    public static int postToPHP(String phpUrl, String parametros) {
        int codigoRespuesta = 0;
        // se intenta la conexión
        try {
            URL url = new URL(phpUrl);
            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
            urlConn.setRequestMethod("POST");
            urlConn.setDoOutput(true);
            try (DataOutputStream wr = new DataOutputStream(urlConn.getOutputStream())) {
                wr.writeBytes(parametros);
                wr.flush();
            }
            // se lee la respuesta
            codigoRespuesta = urlConn.getResponseCode();
//            StringBuilder msj = new StringBuilder();
//            msj.append("Solicitud enviada a: ").append(url.getHost()).append(url.getPath()).append("\n");
//            msj.append("Parámetros         : ").append(parametros).append("\n");
//            msj.append("Código de respuesta: ").append(codigoRespuesta).append("\n");
//            System.out.println(msj + "\n");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return codigoRespuesta;
    }

    public static String getFromPHP(String phpUrl) {
        StringBuilder respuesta = new StringBuilder();
        // se intenta la conexión
        try {
            URL url = new URL(phpUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            int codigoRespuesta = conn.getResponseCode();
            // se lee la respuesta
            try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                String output;
                while ((output = in.readLine()) != null) {
                    respuesta.append(output);
                }
            }
            conn.disconnect();
            StringBuilder msj = new StringBuilder();
            msj.append("Solicitud enviada a: ").append(url.getHost()).append(url.getPath()).append("\n");
            msj.append("Código de respuesta: ").append(codigoRespuesta).append("\n");
            msj.append("Respuesta          : ").append(respuesta).append("\n");
//            System.out.println(msj + "\n");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return respuesta.toString();
    }

    public static String getFromPHP(String phpUrl, HashMap<String, String> parametrosMap) {
        StringBuilder respuesta = new StringBuilder();
        // se intenta la conexión
        try {
            String parametros = formatearParametros(parametrosMap);
            URI uriBase = new URI(phpUrl);
            URI uri = new URI(uriBase.getScheme(), uriBase.getAuthority(), uriBase.getPath(), parametros, null);
            HttpURLConnection conn = (HttpURLConnection) uri.toURL().openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(false);
            conn.setRequestMethod("GET");
            conn.connect();
            int codigoRespuesta = conn.getResponseCode();
            // se lee la respuesta
            try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                String output;
                while ((output = in.readLine()) != null) {
                    respuesta.append(output);
                }
            }
            conn.disconnect();
            StringBuilder msj = new StringBuilder();
            msj.append("Solicitud enviada a: ").append(uri.getHost()).append(uri.getPath()).append("\n");
            msj.append("Código de respuesta: ").append(codigoRespuesta).append("\n");
            msj.append("Respuesta          : ").append(respuesta).append("\n");
//            System.out.println(msj + "\n");
        } catch (IOException | URISyntaxException e) {
            System.out.println(e.getMessage());
        }
        return respuesta.toString();
    }

    private static String formatearParametros(HashMap<String, String> parametrosMap) {
        StringBuilder parametros = new StringBuilder();
        try {
            for (HashMap.Entry<String, String> entry : parametrosMap.entrySet()) {
                parametros.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
                parametros.append("=");
                parametros.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
                parametros.append("&");
            }
            if (parametros.length() > 0) {
                parametros.setLength(parametros.length() - 1);
            }
        } catch (UnsupportedEncodingException e) {
            System.out.println(e.getMessage());
        }
        return parametros.toString();
    }

}

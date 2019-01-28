package Net;

import Util.Varios;
import java.util.ArrayList;

/**
 * @author CPZ
 */
public class Net {

    public static String getMacAddress(String ip) {
        ArrayList<String> comando = new ArrayList();
        comando.add("arp");
        comando.add("-a");
        ArrayList<String> resultado = Varios.comandoConsola(comando);
        String r = "";
        for (String s : resultado) {
            if (!s.contains(ip)) {
                continue;
            }
            r = s.trim();
            break;
        }
        return r.split("\\s+")[1];
    }

    public static String getIpAddress(String mac) {
        mac = mac.toLowerCase();
        ArrayList<String> comando = new ArrayList();
        comando.add("arp");
        comando.add("-a");
        ArrayList<String> resultado = Varios.comandoConsola(comando);
        String r = "";
        for (String s : resultado) {
            if (!s.contains(mac)) {
                continue;
            }
            r = s.trim();
            break;
        }
        return r.split("\\s+")[0];
    }
}

package Bean.Net;

import Util.Varios;
import java.util.ArrayList;
import lombok.Getter;

/**
 * @author CPZ
 */
public class Ping implements Runnable {

    private @Getter final String ip;
    private @Getter boolean ok;

    public Ping(String ip) {
        this.ip = ip;
    }

    @Override
    public void run() {
        ArrayList<String> s = new ArrayList();
        s.add("ping");
        s.add(ip);
        Varios.comandoConsola(s);
        ok = true;
    }

}

package Bean.Net;

import processing.core.PApplet;
import processing.net.Client;
import processing.net.Server;

/**
 * @author CPZ
 */
public class Servidor {

    private int puerto;
    private Server servidor;
    private Client cliente;
    private final PApplet pa;

    public Servidor(PApplet pa, int puerto) {
        this.pa = pa;
        this.puerto = puerto;
        servidor = new Server(pa, puerto);
    }

    public int getPuerto() {
        return puerto;
    }

    public void setPuerto(int puerto) {
        servidor.stop();
        this.puerto = puerto;
        servidor = new Server(pa, puerto);
    }

    public String get() {
        cliente = servidor.available();
        String mensaje = null;
        if (cliente != null) {
            mensaje = cliente.readString();
        }
        return mensaje;
    }

    public void send(String mensaje) {
        servidor.write(mensaje);
    }

    public String getIpCliente() {
        String ip = null;
        if (cliente != null) {
            ip = cliente.ip();
        }
        return ip;
    }

    public void closeCliente(String ip) {
        if (cliente != null && cliente.ip().equals(ip)) {
            servidor.disconnect(cliente);
        }
    }

    public void stop() {
        servidor.stop();
    }

}

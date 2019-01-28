package Bean.Net;

import processing.core.PApplet;
import processing.net.Client;

/**
 * @author CPZ
 */
public class Cliente {

    private final PApplet pa;
    private String host;
    private int puerto;
    private Client cliente;
    private String ip;

    public Cliente(PApplet pa, String host, int puerto) {
        this.pa = pa;
        this.host = host;
        this.puerto = puerto;
        cliente = new Client(pa, host, puerto);
        ip = cliente.ip();
    }

    public Client getCliente() {
        return cliente;
    }

    public int getPuerto() {
        return puerto;
    }

    public String getHost() {
        return host;
    }

    public void setPuerto(int puerto) {
        cliente.stop();
        this.puerto = puerto;
        cliente = new Client(pa, host, puerto);
    }

    public void setHost(String host) {
        cliente.stop();
        this.host = host;
        cliente = new Client(pa, host, puerto);
    }

    public void send(String mensaje) {
        cliente.write(mensaje);
    }

    public String get() {
        String mensaje = null;
        if (cliente.available() > 0) {
            mensaje = cliente.readString();
        }
        return mensaje;
    }

    public String getIp() {
        return ip;
    }

    public void clear() {
        cliente.clear();
    }

    public void stop() {
        cliente.stop();
    }

}

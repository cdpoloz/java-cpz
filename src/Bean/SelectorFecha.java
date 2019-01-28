package Bean;

import Util.Calendario;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import processing.core.PApplet;
import processing.core.PShape;
import processing.core.PVector;

/**
 * @author CPZ
 */
public class SelectorFecha {

    private final PApplet sk;
    private final PVector pos;
    private final Texto txtDia, txtMes, txtAnio;
    private final Boton btDiaMenos, btDiaMas, btMesMas, btMesMenos, btAnioMenos, btAnioMas;
    private @Setter @Getter boolean boolDisplay;
    private @Getter Date fecha;
    private Date fechaMin, fechaPrev;

    public SelectorFecha(PApplet sk, PVector pos, float escala) {
        this.sk = sk;
        this.pos = pos;
        boolDisplay = true;
        fecha = new Date();
        fechaPrev = fecha;
        // Textos
        txtDia = new Texto(sk);
        txtDia.setPos(new PVector(pos.x - sk.width * escala * 0.82f, pos.y));
        txtDia.setAlineaX(PApplet.CENTER);
        txtDia.setAlineaY(PApplet.CENTER);
        txtDia.setColorTexto(sk.color(128));
        txtDia.setFuente(sk.createFont("Consolas", sk.height * escala));
        txtDia.setTextSize(txtDia.getFuente().getSize());
        txtDia.setTexto(String.format("%02d", Calendario.obtenerDia(fecha)));
        txtMes = new Texto(sk);
        txtMes.setPos(new PVector(pos.x, pos.y));
        txtMes.setAlineaX(PApplet.CENTER);
        txtMes.setAlineaY(PApplet.CENTER);
        txtMes.setColorTexto(sk.color(128));
        txtMes.setFuente(sk.createFont("Consolas", sk.height * escala));
        txtMes.setTextSize(txtMes.getFuente().getSize());
        txtMes.setTexto(String.format("%02d", Calendario.obtenerMes(fecha)));
        txtAnio = new Texto(sk);
        txtAnio.setPos(new PVector(pos.x + sk.width * escala * 1.05f, pos.y));
        txtAnio.setAlineaX(PApplet.CENTER);
        txtAnio.setAlineaY(PApplet.CENTER);
        txtAnio.setColorTexto(sk.color(128));
        txtAnio.setFuente(sk.createFont("Consolas", sk.height * escala));
        txtAnio.setTextSize(txtAnio.getFuente().getSize());
        txtAnio.setTexto(String.format("%02d", Calendario.obtenerAnio(fecha)));
        // Botones
        btDiaMas = new Boton(sk);
        btDiaMas.setPos(new PVector(txtDia.getPos().x, txtDia.getPos().y - sk.height * escala * 0.75f));
        btDiaMas.setTipo("cuadrado");
        btDiaMas.setAncho(sk.height * escala * 0.9f);
        btDiaMas.setAlto(sk.height * escala * 0.45f);
        btDiaMenos = new Boton(sk);
        btDiaMenos.setPos(new PVector(txtDia.getPos().x, txtDia.getPos().y + sk.height * escala * 1f));
        btDiaMenos.setTipo("cuadrado");
        btDiaMenos.setAncho(sk.height * escala * 0.9f);
        btDiaMenos.setAlto(sk.height * escala * 0.45f);
        btMesMas = new Boton(sk);
        btMesMas.setPos(new PVector(txtMes.getPos().x, txtMes.getPos().y - sk.height * escala * 0.75f));
        btMesMas.setTipo("cuadrado");
        btMesMas.setAncho(sk.height * escala * 0.9f);
        btMesMas.setAlto(sk.height * escala * 0.45f);
        btMesMenos = new Boton(sk);
        btMesMenos.setPos(new PVector(txtMes.getPos().x, txtMes.getPos().y + sk.height * escala * 1f));
        btMesMenos.setTipo("cuadrado");
        btMesMenos.setAncho(sk.height * escala * 0.9f);
        btMesMenos.setAlto(sk.height * escala * 0.45f);
        btAnioMas = new Boton(sk);
        btAnioMas.setPos(new PVector(txtAnio.getPos().x, txtAnio.getPos().y - sk.height * escala * 0.75f));
        btAnioMas.setTipo("cuadrado");
        btAnioMas.setAncho(sk.height * escala * 0.9f);
        btAnioMas.setAlto(sk.height * escala * 0.45f);
        btAnioMenos = new Boton(sk);
        btAnioMenos.setPos(new PVector(txtAnio.getPos().x, txtAnio.getPos().y + sk.height * escala * 1f));
        btAnioMenos.setTipo("cuadrado");
        btAnioMenos.setAncho(sk.height * escala * 0.9f);
        btAnioMenos.setAlto(sk.height * escala * 0.45f);
    }

    public void cargarImagenes(String rutaImagenes) {
        btDiaMas.setImgOff(sk.loadShape(rutaImagenes + "masOff.svg"));
        btDiaMas.setImgOn(sk.loadShape(rutaImagenes + "masOn.svg"));
        btDiaMas.setImgOverOff(sk.loadShape(rutaImagenes + "masOverOff.svg"));
        btDiaMas.setImgOverOn(sk.loadShape(rutaImagenes + "masOverOn.svg"));
        btMesMas.setImgOff(sk.loadShape(rutaImagenes + "masOff.svg"));
        btMesMas.setImgOn(sk.loadShape(rutaImagenes + "masOn.svg"));
        btMesMas.setImgOverOff(sk.loadShape(rutaImagenes + "masOverOff.svg"));
        btMesMas.setImgOverOn(sk.loadShape(rutaImagenes + "masOverOn.svg"));
        btAnioMas.setImgOff(sk.loadShape(rutaImagenes + "masOff.svg"));
        btAnioMas.setImgOn(sk.loadShape(rutaImagenes + "masOn.svg"));
        btAnioMas.setImgOverOff(sk.loadShape(rutaImagenes + "masOverOff.svg"));
        btAnioMas.setImgOverOn(sk.loadShape(rutaImagenes + "masOverOn.svg"));
        btDiaMenos.setImgOff(sk.loadShape(rutaImagenes + "menosOff.svg"));
        btDiaMenos.setImgOn(sk.loadShape(rutaImagenes + "menosOn.svg"));
        btDiaMenos.setImgOverOff(sk.loadShape(rutaImagenes + "menosOverOff.svg"));
        btDiaMenos.setImgOverOn(sk.loadShape(rutaImagenes + "menosOverOn.svg"));
        btMesMenos.setImgOff(sk.loadShape(rutaImagenes + "menosOff.svg"));
        btMesMenos.setImgOn(sk.loadShape(rutaImagenes + "menosOn.svg"));
        btMesMenos.setImgOverOff(sk.loadShape(rutaImagenes + "menosOverOff.svg"));
        btMesMenos.setImgOverOn(sk.loadShape(rutaImagenes + "menosOverOn.svg"));
        btAnioMenos.setImgOff(sk.loadShape(rutaImagenes + "menosOff.svg"));
        btAnioMenos.setImgOn(sk.loadShape(rutaImagenes + "menosOn.svg"));
        btAnioMenos.setImgOverOff(sk.loadShape(rutaImagenes + "menosOverOff.svg"));
        btAnioMenos.setImgOverOn(sk.loadShape(rutaImagenes + "menosOverOn.svg"));
    }

    public void setImagenesBotonMas(PShape masOn, PShape masOff, PShape masOverOn, PShape masOverOff) {
        btDiaMas.setImgOn(masOn);
        btDiaMas.setImgOff(masOff);
        btDiaMas.setImgOverOn(masOverOn);
        btDiaMas.setImgOverOff(masOverOff);
        btMesMas.setImgOn(masOn);
        btMesMas.setImgOff(masOff);
        btMesMas.setImgOverOn(masOverOn);
        btMesMas.setImgOverOff(masOverOff);
        btAnioMas.setImgOn(masOn);
        btAnioMas.setImgOff(masOff);
        btAnioMas.setImgOverOn(masOverOn);
        btAnioMas.setImgOverOff(masOverOff);
    }

    public void setColorTexto(int c) {
        txtDia.setColorTexto(c);
        txtMes.setColorTexto(c);
        txtAnio.setColorTexto(c);
    }

    public void update() {
        fechaPrev = fecha;
        btDiaMas.update();
        btDiaMenos.update();
        btMesMas.update();
        btMesMenos.update();
        btAnioMas.update();
        btAnioMenos.update();
        if (btDiaMas.isOn()) {
            fecha = Calendario.agregarDias(fecha, 1);
            btDiaMas.setOn(false);
        }
        if (btDiaMenos.isOn()) {
            fecha = Calendario.agregarDias(fecha, -1);
            btDiaMenos.setOn(false);
        }
        if (btMesMas.isOn()) {
            fecha = Calendario.agregarMeses(fecha, 1);
            btMesMas.setOn(false);
        }
        if (btMesMenos.isOn()) {
            fecha = Calendario.agregarMeses(fecha, -1);
            btMesMenos.setOn(false);
        }
        if (btAnioMas.isOn()) {
            fecha = Calendario.agregarAnios(fecha, 1);
            btAnioMas.setOn(false);
        }
        if (btAnioMenos.isOn()) {
            fecha = Calendario.agregarAnios(fecha, -1);
            btAnioMenos.setOn(false);
        }
        if (cambio() && fecha.before(fechaMin)) {
            fecha = fechaMin;
        }
        updateTextos();
    }

    public void display() {
        if (!boolDisplay) {
            return;
        }
        // display
        txtMes.display();
        btMesMas.display();
        btMesMenos.display();
        txtDia.display();
        btDiaMas.display();
        btDiaMenos.display();
        txtAnio.display();
        btAnioMas.display();
        btAnioMenos.display();
    }

    public void mouseReleased() {
        btDiaMas.mouseReleased();
        btDiaMenos.mouseReleased();
        btMesMas.mouseReleased();
        btMesMenos.mouseReleased();
        btAnioMas.mouseReleased();
        btAnioMenos.mouseReleased();
    }

    public boolean cambio() {
        return fechaPrev.before(fecha) || fechaPrev.after(fecha);
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
        updateTextos();
    }

    public void setFechaMin(Date fechaMin) {
        this.fechaMin = fechaMin;
        if (fecha.before(fechaMin)) {
            setFecha(fechaMin);
        }
        updateTextos();
    }

    public void updateTextos() {
        txtDia.setTexto(String.format("%02d", Calendario.obtenerDia(fecha)));
        txtMes.setTexto(String.format("%02d", Calendario.obtenerMes(fecha)));
        txtAnio.setTexto(String.format("%02d", Calendario.obtenerAnio(fecha)));
    }

    public void recalcular(float escala) {
        // Textos
        txtDia.setPos(new PVector(pos.x - sk.width * escala * 0.82f, pos.y));
        txtDia.setFuente(sk.createFont("Consolas", sk.height * escala));
        txtDia.setTextSize(txtDia.getFuente().getSize());
        txtMes.setFuente(sk.createFont("Consolas", sk.height * escala));
        txtMes.setTextSize(txtMes.getFuente().getSize());
        txtAnio.setPos(new PVector(pos.x + sk.width * escala * 1.05f, pos.y));
        txtAnio.setFuente(sk.createFont("Consolas", sk.height * escala));
        txtAnio.setTextSize(txtAnio.getFuente().getSize());
        // Botones
        btDiaMas.setPos(new PVector(txtDia.getPos().x, txtDia.getPos().y - sk.height * escala * 0.75f));
        btDiaMas.setAncho(sk.height * escala * 0.9f);
        btDiaMas.setAlto(sk.height * escala * 0.45f);
        btDiaMenos.setPos(new PVector(txtDia.getPos().x, txtDia.getPos().y + sk.height * escala * 1f));
        btDiaMenos.setAncho(sk.height * escala * 0.9f);
        btDiaMenos.setAlto(sk.height * escala * 0.45f);
        btMesMas.setPos(new PVector(txtMes.getPos().x, txtMes.getPos().y - sk.height * escala * 0.75f));
        btMesMas.setAncho(sk.height * escala * 0.9f);
        btMesMas.setAlto(sk.height * escala * 0.45f);
        btMesMenos.setPos(new PVector(txtMes.getPos().x, txtMes.getPos().y + sk.height * escala * 1f));
        btMesMenos.setAncho(sk.height * escala * 0.9f);
        btMesMenos.setAlto(sk.height * escala * 0.45f);
        btAnioMas.setPos(new PVector(txtAnio.getPos().x, txtAnio.getPos().y - sk.height * escala * 0.75f));
        btAnioMas.setAncho(sk.height * escala * 0.9f);
        btAnioMas.setAlto(sk.height * escala * 0.45f);
        btAnioMenos.setPos(new PVector(txtAnio.getPos().x, txtAnio.getPos().y + sk.height * escala * 1f));
        btAnioMenos.setAncho(sk.height * escala * 0.9f);
        btAnioMenos.setAlto(sk.height * escala * 0.45f);
    }

}

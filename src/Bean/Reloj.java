package Bean;

import lombok.Getter;
import lombok.Setter;
import processing.core.PApplet;
import processing.core.PVector;

/**
 * @author CPZ
 */
public class Reloj {

    private final PApplet sk;
    private @Getter final Texto texto;
    private int seg, segPrev;
    private @Setter @Getter boolean boolDisplay;

    public Reloj(PApplet sk, PVector pos, float escala) {
        this.sk = sk;
        texto = new Texto(sk);
        texto.setPos(new PVector(pos.x, pos.y));
        texto.setAlineaX(PApplet.CENTER);
        texto.setAlineaY(PApplet.CENTER);
        texto.setColorTexto(sk.color(255));
        texto.setFuente(sk.createFont("Consolas", sk.height * escala));
        texto.setTextSize(texto.getFuente().getSize());
        StringBuilder hora = new StringBuilder();
        hora.append(String.format("%02d", PApplet.hour()));
        hora.append(":");
        hora.append(String.format("%02d", PApplet.minute()));
        hora.append(":");
        hora.append(String.format("%02d", PApplet.second()));
        texto.setTexto(hora.toString());
        boolDisplay = true;
        seg = PApplet.second();        
    }

    public void setColorTexto(int c) {
        texto.setColorTexto(c);
    }
    
    public void alineaX(int alinea) {
        texto.setAlineaX(alinea);
    }
    
    public void alineaY(int alinea) {
        texto.setAlineaY(alinea);
    }
    
    public void update() {
        segPrev = seg;
        seg = PApplet.second();
        if (seg == segPrev) {
            return;
        }
        StringBuilder hora = new StringBuilder();
        hora.append(String.format("%02d", PApplet.hour()));
        hora.append(":");
        hora.append(String.format("%02d", PApplet.minute()));
        hora.append(":");
        hora.append(String.format("%02d", PApplet.second()));
        texto.setTexto(hora.toString());
    }

    public void display() {
        if (!boolDisplay) {
            return;
        }
        texto.display();
    }

    public void recalcular(float escala) {
        texto.setFuente(sk.createFont("Consolas", sk.height * escala));
        texto.setTextSize(texto.getFuente().getSize());
    }
    
    public String getHora() {
        return texto.getTexto();
    }

}

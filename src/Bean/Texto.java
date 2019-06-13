package Bean;

import Util.VariablesSketch;
import java.util.HashMap;
import lombok.Getter;
import lombok.Setter;
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PVector;

/**
 * @author CPZ
 */
public class Texto {

    private final PApplet sk;
    private final VariablesSketch vsk;
    private @Setter @Getter PVector pos;
    private @Setter @Getter int colorTexto;
    private @Setter @Getter PFont fuente;
    private @Setter @Getter float textSize;
    private @Setter int alineaX, alineaY;
    private @Getter String texto;
    private @Setter @Getter boolean boolDisplay, boolPosUnit;

    public Texto(PApplet sk) {
        this.sk = sk;
        vsk = new VariablesSketch(sk);
        texto = " ";
        boolDisplay = true;
    }

    public void setup(HashMap h) {
//        if (h.get("pos") != null) {
//            pos = (PVector) h.get("pos");
//        }
//        if (h.get("colorTexto") != null) {
//            cTexto = (int) h.get("colorTexto");
//        }
//        if (h.get("fuente") != null) {
//            fuente = (PFont) h.get("fuente");
//        }
//        if (h.get("alineaX") != null) {
//            alineaX = (int) h.get("alineaX");
//        }
//        if (h.get("alineaY") != null) {
//            alineaY = (int) h.get("alineaY");
//        }
//        if (h.get("textSize") != null) {
//            textSize = (float) h.get("textSize");
//        }
//        if (h.get("texto") != null) {
//            texto = (String) h.get("texto");
//        }
    }

    public void display(Object... o) {
        if (!boolDisplay) {
            return;
        }
        if (o.length > 0 && o[0] instanceof String) {
            texto = (String) o[0];
        }
        vsk.save();
        sk.fill(colorTexto);
        sk.textFont(fuente);
        sk.textSize(textSize);
        sk.textAlign(alineaX, alineaY);
        float x = pos.x;
        float y = pos.y;
        if (boolPosUnit) {
            x *= sk.width;
            y *= sk.height;
        }
        sk.text(texto, x, y);
        vsk.restore();
    }

    public void setTexto(Object o) {
        if (o instanceof String) {
            texto = (String) o;
        } else if (o instanceof StringBuilder) {
            texto = ((StringBuilder) o).toString();
        }
    }
    
    public void update(String texto) {
        this.texto = texto;
    }

    @Override
    public String toString() {
        return texto;
    }
}
   
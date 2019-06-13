package Bean;

import Util.VariablesSketch;
import lombok.Getter;
import lombok.Setter;
import processing.core.PApplet;
import processing.core.PVector;

/**
 * @author CPZ
 */
public class Barra {
    
    private final PApplet sk;
    private final VariablesSketch vsk;
    private @Setter @Getter PVector pos;
    private @Setter @Getter float ancho, alto;
    private @Setter int colorFill, colorStroke;
    private @Setter boolean boolFill, boolStroke, boolDisplay;
    private @Setter int modo;
    
    public Barra(PApplet sk) {
        this.sk = sk;
        this.vsk = new VariablesSketch(sk);
        boolDisplay = true;
    }
    
    public void display() {
        if (!boolDisplay) {
            return;
        }
        vsk.save();
        if (boolFill) {
            sk.fill(colorFill);
        } else {
            sk.noFill();
        }
        if (boolStroke) {
            sk.stroke(colorStroke);
        } else {
            sk.noStroke();
        }
        sk.rectMode(modo);
        sk.rect(pos.x, pos.y, ancho, alto);
        vsk.restore();
    }
}

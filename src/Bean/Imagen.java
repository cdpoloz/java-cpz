package Bean;

import Util.VariablesSketch;
import lombok.Getter;
import lombok.Setter;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PShape;
import processing.core.PVector;

/**
 * @author CPZ
 */
public class Imagen {

    private final PApplet sk;
    private final VariablesSketch vsk;
    private @Setter @Getter PVector pos;
    private @Setter boolean boolPosUnit;
    private @Setter Object img;
    private @Setter @Getter float ancho, alto;
    private @Setter int modo;

    public Imagen(PApplet sk) {
        this.sk = sk;
        vsk = new VariablesSketch(sk);
    }

    public void display(boolean b) {
        if (!b) {
            return;
        }
        display();
    }
    
    public void display() {
        vsk.save();
        float x = pos.x;
        float y = pos.y;
        if (boolPosUnit) {
            x *= sk.width;
            y *= sk.height;
        }
        if (img instanceof PImage) {
            sk.imageMode(modo);
            sk.image((PImage) img, x, y, ancho, alto);
        } else if (img instanceof PShape) {
            sk.shapeMode(modo);
            sk.shape((PShape) img, x, y, ancho, alto);
        }
        vsk.restore();
    }

}

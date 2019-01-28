package Bean;

import Util.VariablesSketch;
import lombok.Setter;
import processing.core.PApplet;
import static processing.core.PApplet.radians;
import static processing.core.PConstants.CENTER;
import processing.core.PShape;
import processing.core.PVector;

/**
 * @author CPZ
 */
public class Spinner {

    private final PApplet sk;
    private final VariablesSketch vsk;
    private @Setter PShape shape;
    private @Setter float ang, d;
    private @Setter PVector pos;
    private @Setter boolean boolDisplay, boolPosUnit, boolUpdate;
    private @Setter float escala;

    public Spinner(PApplet sk) {
        this.sk = sk;
        vsk = new VariablesSketch(sk);
        boolUpdate = true;
    }

    public void update() {
        if (!boolUpdate) {
            return;
        }
        ang += d;
    }

    public void display() {
        if (!boolDisplay) {
            return;
        }
        vsk.save();
        sk.pushMatrix();
        if (boolPosUnit) {
            sk.translate(pos.x * sk.width, pos.y * sk.height);
        } else {
            sk.translate(pos.x, pos.y);
        }        
        sk.rotate(radians(ang));
        sk.shapeMode(CENTER);
        sk.shape(shape, 0, 0, sk.height * escala, sk.height * escala);
        sk.popMatrix();
        vsk.restore();
    }

}

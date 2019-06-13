package Bean;

import Util.VariablesSketch;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

/**
 * @author CPZ
 */
public class Pixel {

    private final PApplet sk;
    private final VariablesSketch vsk;
    private final float ancho, alto;
    private int c, c1, c2;
    private final PImage pixel;
    private final PVector pos;
    private float indColor, dIndColor;
    private boolean finCambioColor;

    public Pixel(PApplet sk, PVector pos, float ancho, float alto, PImage pixel) {
        this.sk = sk;
        vsk = new VariablesSketch(sk);
        this.pos = pos;
        this.ancho = ancho;
        this.alto = alto;
        this.pixel = pixel;
    }

    public void update() {
        if (finCambioColor) {
            return;
        }
        if (indColor < 1) {
            indColor += dIndColor;
        } else {
            finCambioColor = true;
        }
        c = sk.lerpColor(c1, c2, indColor);
    }

    public void display() {
        vsk.save();
        sk.tint(c);
        sk.image(pixel, pos.x, pos.y, ancho, alto);
        vsk.restore();
    }

    public void setVel(float dIndColor) {
        this.dIndColor = dIndColor;
    }

    public void setColorIni(int c1) {
        this.c1 = c1;
    }

    public void setColorFin(int c2) {
        this.c2 = c2;
    }

    public int getColorIni() {
        return c1;
    }

    public int getColorFin() {
        return c2;
    }

    public int getColor() {
        return c;
    }

    public boolean finCambioColor() {
        return finCambioColor;
    }

    public void setFinCambioColor(boolean finCambioColor) {
        this.finCambioColor = finCambioColor;
    }

    public float getIndColor() {
        return indColor;
    }

    public void setIndColor(float indColor) {
        this.indColor = indColor;
    }

}

package Bean;

import Util.VariablesSketch;
import lombok.Getter;
import lombok.Setter;
import processing.core.PApplet;

/**
 * @author CPZ
 */
public class Guia {

    private final PApplet sk;
    private final VariablesSketch vsk;
    private @Setter @Getter boolean boolDisplay;
    private @Setter int c;
    private final int dx, dy;

    public Guia(PApplet sk) {
        this.sk = sk;
        vsk = new VariablesSketch(sk);
        dx = 35;
        dy = 10;
    }

    public void display() {
        if (!boolDisplay) {
            return;
        }
        vsk.save();
        sk.noCursor();
        sk.stroke(c);
        sk.strokeWeight(1);
        sk.line(0, sk.mouseY, sk.width, sk.mouseY);
        sk.line(sk.mouseX, 0, sk.mouseX, sk.height);
        String xStr = String.format("%.3f", (float) sk.mouseX / (float) sk.width);
        String yStr = String.format("%.3f", (float) sk.mouseY / (float) sk.height);
        sk.fill(c);
        sk.textSize(10);
        sk.textAlign(PApplet.CENTER, PApplet.CENTER);
        int x = sk.mouseX;
        if (x > sk.width - 2 * dx) {
            x -= dx;
        } else {
            x += dx;
        }
        int y = sk.mouseY;
        if (y > sk.height - 2 * dy) {
            y -= dy;
        } else {
            y += dy;
        }
        sk.text("(" + xStr + ", " + yStr + ")", x, y);
        vsk.restore();
    }

    public void display(boolean b) {
        if (!b) {
            return;
        }
        vsk.save();
        sk.stroke(c);
        sk.strokeWeight(1);
        sk.line(0, sk.mouseY, sk.width, sk.mouseY);
        sk.line(sk.mouseX, 0, sk.mouseX, sk.height);
        String xStr = String.format("%.3f", (float) sk.mouseX / (float) sk.width);
        String yStr = String.format("%.3f", (float) sk.mouseY / (float) sk.height);
        sk.fill(c);
        sk.textSize(10);
        sk.textAlign(PApplet.CENTER, PApplet.CENTER);
        int x = sk.mouseX;
        if (x > sk.width - 2 * dx) {
            x -= dx;
        } else {
            x += dx;
        }
        int y = sk.mouseY;
        if (y > sk.height - 2 * dy) {
            y -= dy;
        } else {
            y += dy;
        }
        sk.text("(" + xStr + ", " + yStr + ")", x, y);
        vsk.restore();
    }

}

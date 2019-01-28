package Bean;

import Util.VariablesSketch;
import java.util.ArrayList;
import lombok.Getter;
import lombok.Setter;
import processing.core.PApplet;
import processing.core.PVector;

/**
 * @author CPZ
 */
public final class Bezier {

    private final PApplet sk;
    private final VariablesSketch vsk;
    private @Getter final PVector[] pto;
    private @Getter final ArrayList<PVector> trayectoria;
    private @Getter boolean boolDisplay;
    private @Getter @Setter boolean boolEdit;
    private @Getter @Setter boolean boolDisplayP;
    private @Getter float longitud;
    private final float radioEdit;
    private final int detalleBezier;
    private @Setter int c;

    public Bezier(PApplet sk, float x0, float y0, float x1, float y1, float x2, float y2, float x3, float y3) {
        this.sk = sk;
        vsk = new VariablesSketch(sk);
        pto = new PVector[4];
        pto[0] = new PVector(x0 * sk.width, y0 * sk.height, 0);
        pto[1] = new PVector(x1 * sk.width, y1 * sk.height, 0);
        pto[2] = new PVector(x2 * sk.width, y2 * sk.height, 0);
        pto[3] = new PVector(x3 * sk.width, y3 * sk.height, 0);
        trayectoria = new ArrayList();
        calcularTrayectoria();
        radioEdit = 20;
        detalleBezier = 100;
        c = sk.color(64, 192, 32);
    }

    public void display() {
        if (!boolDisplay) {
            return;
        }
        vsk.save();
        sk.stroke(c);
        sk.strokeWeight(2);
        sk.noFill();
        sk.bezierDetail(detalleBezier);
        sk.bezier(pto[0].x, pto[0].y, pto[1].x, pto[1].y, pto[2].x, pto[2].y, pto[3].x, pto[3].y);
        if (boolDisplayP) {
            for (int i = 0; i < pto.length; i++) {
                sk.strokeWeight(5);
                sk.stroke(0, 255, 0);
                sk.point(pto[i].x, pto[i].y);
                if (boolEdit) {
                    sk.strokeWeight(1);
                    sk.noFill();
                    sk.stroke(0);
                    sk.ellipse(pto[i].x + 1, pto[i].y + 1, radioEdit, radioEdit);
                    sk.strokeWeight(2);
                    sk.stroke(0, 255, 0);
                    sk.ellipse(pto[i].x, pto[i].y, radioEdit, radioEdit);
                }
                sk.fill(255, 0, 255);
                sk.text(i, pto[i].x + 10, pto[i].y - 10);
            }
            if (boolEdit) {
                for (PVector p : pto) {
                    if (PApplet.dist(sk.mouseX, sk.mouseY, p.x, p.y) < radioEdit) {
                        sk.stroke(255, 0, 0);
                        sk.noFill();
                        sk.ellipse(p.x, p.y, radioEdit, radioEdit);
                    }
                }
            }
        }
        vsk.restore();
    }

    public void flipHor() {
        for (PVector p : pto) {
            p.set(PApplet.abs(sk.width - p.x), p.y);
        }
    }

    public void flipVer() {
        for (PVector p : pto) {
            p.set(p.x, PApplet.abs(sk.height - p.y));
        }
    }

    public void edit() {
        if (!boolEdit) {
            return;
        }
        for (PVector p : pto) {
            if (PApplet.dist(sk.mouseX, sk.mouseY, p.x, p.y) < radioEdit) {
                p.set(sk.mouseX, sk.mouseY);
            }
        }
    }

    public void calcularTrayectoria() {
        int n = 1000;
        trayectoria.clear();
        for (int i = 0; i < n; i++) {
            float x = sk.bezierPoint(pto[0].x, pto[1].x, pto[2].x, pto[3].x, (float) i / (float) n);
            float y = sk.bezierPoint(pto[0].y, pto[1].y, pto[2].y, pto[3].y, (float) i / (float) n);
            trayectoria.add(new PVector(x, y));
        }
        longitud = 0;
        for (int i = 1; i < trayectoria.size(); i++) {
            PVector pos = trayectoria.get(i);
            PVector posP = trayectoria.get(i - 1);
            longitud += PVector.dist(pos, posP);
        }
    }

    public PVector getPtoTrayectoria(int i) {
        return trayectoria.get(i);
    }

    public void setBoolDisplay(boolean boolDisplay) {
        this.boolDisplay = boolDisplay;
        boolEdit = false;
    }

    public PVector getPto(int i) {
        return pto[i];
    }

}

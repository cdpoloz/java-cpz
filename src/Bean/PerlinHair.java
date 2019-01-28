package Bean;

import Util.VariablesSketch;
import java.util.ArrayList;
import processing.core.PApplet;
import processing.core.PVector;

public class PerlinHair {

    private final PApplet sk;
    private final VariablesSketch vsk;
    private final ArrayList<PerlinWalker> hair;
    private int steps, numSteps, dSteps;
    private float alfaHair, radio;
    private int c;
    private boolean sentido;

    public PerlinHair(PApplet sk) {
        this.sk = sk;
        vsk = new VariablesSketch(sk);
        hair = new ArrayList();
        reset();
    }

    public void update() {
        for (PerlinWalker t : hair) {
            t.update();
        }
        if (!sentido) {
            if (steps > 0) {
                steps -= dSteps;
            } else {
                sentido = true;
            }
        } else if (steps < numSteps) {
            steps += dSteps;
        } else {
            sentido = false;
        }
    }

    public void display(PVector pos) {
        vsk.save();
        if (steps > 0) {
            for (int i = 0; i < steps; i++) {
                float t = (float) i / (float) (steps);
                float x = sk.bezierPoint(pos.x, hair.get(0).getPos().x, hair.get(1).getPos().x, hair.get(2).getPos().x, t);
                float y = sk.bezierPoint(pos.y, hair.get(0).getPos().y, hair.get(1).getPos().y, hair.get(2).getPos().y, t);
                sk.stroke(sk.red(c), sk.green(c), sk.blue(c), alfaHair);
                sk.strokeWeight(sk.random(radio));
                sk.point(x, y);
            }
        }
        vsk.restore();
    }

    public void reset() {
        hair.clear();
        for (int i = 0; i < 3; i++) {
            float xtmin = sk.random(-sk.width * 0.25f, 1.25f * sk.width);
            float xtmax = sk.random(-sk.width * 0.25f, 1.25f * sk.width);
            float ytmin = sk.random(-sk.height * 0.25f, 1.25f * sk.height);
            float ytmax = sk.random(-sk.height * 0.25f, 1.25f * sk.height);
            hair.add(new PerlinWalker(sk, xtmin, xtmax, ytmin, ytmax));
        }
        float i = sk.random(1);
        int c1 = sk.color(sk.random(255), sk.random(255), sk.random(255));
        int c2 = sk.color(sk.random(255), sk.random(255), sk.random(255));
        c = sk.lerpColor(c1, c2, i);
        numSteps = 200;
        steps = numSteps;
        dSteps = 1;
        alfaHair = 16;
        radio = 3;
    }
}

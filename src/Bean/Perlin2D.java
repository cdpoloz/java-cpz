package Bean;

import lombok.Setter;
import processing.core.PApplet;
import processing.core.PVector;

public class Perlin2D {

    private final PApplet sk;
    private float x, y;
    private @Setter float dx, dy;

    public Perlin2D(PApplet sk, float d) {
        this.sk = sk;
        this.dx = d;
        this.dy = d;
        x = sk.random(5000);
        y = sk.random(5000);
    }
    
    public Perlin2D(PApplet sk, float dx, float dy) {
        this.sk = sk;
        this.dx = dx;
        this.dy = dy;
        x = sk.random(5000);
        y = sk.random(5000);
    }

    public PVector get() {
        return new PVector(sk.noise(x), sk.noise(y));
    }

    public void update() {
        x += dx;
        y += dy;
    }

    public void reset() {
        x = sk.random(5000);
        y = sk.random(5000);
    }
}

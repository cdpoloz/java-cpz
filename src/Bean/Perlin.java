package Bean;

import lombok.Setter;
import processing.core.PApplet;

public class Perlin {

    private final PApplet sk;
    private float val;
    private @Setter float vel;

    public Perlin(PApplet sk, float vel) {
        this.sk = sk;
        this.vel = vel;
        val = sk.random(5000);
    }

    public float get() {
        return sk.noise(val);
    }

    public void update() {
        val += vel;
    }

    public void reset() {
        val = sk.random(5000);
    }
}

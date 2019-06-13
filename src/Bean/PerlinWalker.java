package Bean;

import lombok.Getter;
import lombok.Setter;
import processing.core.PApplet;
import processing.core.PVector;

/**
 * @author CPZ
 */
public class PerlinWalker {

    private final PApplet sk;
    private float x, y, z;
    private @Setter @Getter PVector pos;
    private @Setter @Getter float xmin, xmax, ymin, ymax, zmin, zmax;
    private float tx, ty, tz, dtx, dty, dtz;
    private float vel = 0.0075f;  // 0.0120

    public PerlinWalker(PApplet sk) {
        this.sk = sk;
        this.xmin = 0;
        this.xmax = sk.width;
        this.ymin = 0;
        this.ymax = sk.height;
        this.zmin = 0;
        this.zmax = 0;
        tx = sk.random(xmin, xmax);
        ty = sk.random(ymin, ymax);
        tz = sk.random(zmin, zmax);
        dtx = sk.random(0.8f * vel, 1.2f * vel);
        dty = sk.random(0.8f * vel, 1.2f * vel);
        dtz = sk.random(0.8f * vel, 1.2f * vel);
        pos = new PVector();
    }

    public PerlinWalker(PApplet sk, float xmin, float xmax, float ymin, float ymax) {
        this.sk = sk;
        this.xmin = xmin;
        this.xmax = xmax;
        this.ymin = ymin;
        this.ymax = ymax;
        this.zmin = 0;
        this.zmax = 0;
        tx = sk.random(xmin, xmax);
        ty = sk.random(ymin, ymax);
        tz = sk.random(zmin, zmax);
        dtx = sk.random(0.8f * vel, 1.2f * vel);
        dty = sk.random(0.8f * vel, 1.2f * vel);
        dtz = sk.random(0.8f * vel, 1.2f * vel);
        pos = new PVector();
    }

    public void update() {
        tx += dtx;
        ty += dty;
        tz += dtz;
        x = sk.noise(tx) * (xmax - xmin) + xmin;
        y = sk.noise(ty) * (ymax - ymin) + ymin;
        z = sk.noise(tz) * (zmax - zmin) + zmin;
        pos.set(x, y, z);
    }

    public void setVel(float vel) {
        this.vel = vel;
        dtx = sk.random(0.8f * vel, 1.2f * vel);
        dty = sk.random(0.8f * vel, 1.2f * vel);
        dtz = sk.random(0.8f * vel, 1.2f * vel);
    }
    
}

package Bean;

import Util.VariablesSketch;
import lombok.Getter;
import lombok.Setter;
import processing.core.PApplet;
import static processing.core.PConstants.CENTER;
import processing.core.PImage;
import processing.core.PShape;
import processing.core.PVector;

/**
 * @author CPZ
 */
public class Boton {

    private final PApplet sk;
    private final VariablesSketch vsk;
    private @Setter @Getter PVector pos;
    private @Setter boolean boolPosUnit;
    private @Setter @Getter boolean over, on, boolUpdate;
    private @Setter Object imgOff, imgOverOn, imgOverOff, imgOn;    
    private @Setter @Getter float ancho, alto;
    private @Setter String tipo;
    private @Setter Texto texto;
    private @Setter @Getter String aux;

    public Boton(PApplet sk) {
        this.sk = sk;
        vsk = new VariablesSketch(sk);
        boolUpdate = true;
    }

    public void update() {
        if(!boolUpdate) {
            return;
        }
        float x = sk.mouseX;
        float y = sk.mouseY;
        switch (tipo) {
            case "redondo":
                if (boolPosUnit) {
                    over = PApplet.dist(x, y, pos.x * sk.width, pos.y * sk.height) <= ancho * 0.5f;
                } else {
                    over = PApplet.dist(x, y, pos.x, pos.y) <= ancho * 0.5f;
                }                
                break;
            case "cuadrado":
                boolean overX, overY;
                if (boolPosUnit) {
                    overX = x >= pos.x * sk.width - ancho * 0.5f && x <= pos.x * sk.width + ancho * 0.5f;
                    overY = y >= pos.y * sk.height - alto * 0.5f && y <= pos.y * sk.height + alto * 0.5f;
                } else {
                    overX = x >= pos.x - ancho * 0.5f && x <= pos.x + ancho * 0.5f;
                    overY = y >= pos.y - alto * 0.5f && y <= pos.y + alto * 0.5f;
                }                
                over = overX && overY;
                break;
            default:
                break;
        }
    }
    
    public void update(float x, float y) {
        if(!boolUpdate) {
            return;
        }
        switch (tipo) {
            case "redondo":
                if (boolPosUnit) {
                    over = PApplet.dist(x, y, pos.x * sk.width, pos.y * sk.height) <= ancho * 0.5f;
                } else {
                    over = PApplet.dist(x, y, pos.x, pos.y) <= ancho * 0.5f;
                }
                break;
            case "cuadrado":
                boolean overX, overY;
                if (boolPosUnit) {
                    overX = x >= pos.x * sk.width - ancho * 0.5f && x <= pos.x * sk.width + ancho * 0.5f;
                    overY = y >= pos.y * sk.height - alto * 0.5f && y <= pos.y * sk.height + alto * 0.5f;
                } else {
                    overX = x >= pos.x - ancho * 0.5f && x <= pos.x + ancho * 0.5f;
                    overY = y >= pos.y - alto * 0.5f && y <= pos.y + alto * 0.5f;
                }                
                over = overX && overY;
                break;
            default:
                break;
        }
    }

    public void display() {
        vsk.save();
        sk.shapeMode(CENTER);
        float x = pos.x;
        float y = pos.y;
        if (boolPosUnit) {
            x *= sk.width;
            y *= sk.height;
        }
        if (on) {
            if (over) {
                if (imgOverOn instanceof PShape) {
                    sk.shape((PShape) imgOverOn, x, y, ancho, alto);
                } else if (imgOverOn instanceof PImage) {
                    sk.image((PImage) imgOverOn, x, y, ancho, alto);
                }
            } else {
                if (imgOn instanceof PShape) {
                    sk.shape((PShape) imgOn, x, y, ancho, alto);
                } else if (imgOn instanceof PImage) {
                    sk.image((PImage) imgOn, x, y, ancho, alto);
                }
            }
        } else if (over) {
            if (imgOverOff instanceof PShape) {
                sk.shape((PShape) imgOverOff, x, y, ancho, alto);
            } else if (imgOverOff instanceof PImage) {
                sk.image((PImage) imgOverOff, x, y, ancho, alto);
            }
        } else {
            if (imgOff instanceof PShape) {
                sk.shape((PShape) imgOff, x, y, ancho, alto);
            } else if (imgOff instanceof PImage) {
                sk.image((PImage) imgOff, x, y, ancho, alto);
            }
        }
        if (texto != null) {
            texto.display();
        }
        vsk.restore();
    }

    public void mouseReleased() {
        if (!over) {
            return;
        }
        on = !on;
    }

}

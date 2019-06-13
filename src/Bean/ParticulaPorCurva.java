package Bean;

import java.util.ArrayList;
import processing.core.PApplet;
import static processing.core.PApplet.lerp;
import static processing.core.PApplet.map;
import static processing.core.PApplet.radians;
import processing.core.PImage;
import processing.core.PVector;

public class ParticulaPorCurva {

    private final PApplet sk;
    private int indPos;
    private int dIndPos, dIndPosMin, dIndPosMax;
    private final int indPosMax;
    private int indFinAlfa;
    private float indFinAlfaMin, indFinAlfaMax;
    private final Bezier b;
    private float x, y;
    private PVector p;
    private final PVector pos, posPrev, dir, dirNormal;
    private float alfaMin, alfaMax;
    private float alfa;
    private boolean boolSentidoNormal, boolSinModPerlin;
    private float dNormal;
    private float dNormalMax;
    private float modPerlin, perlin, velPerlin;
    private final float velPerlinMin, velPerlinMax;
    private int c, c0, c1, c2, c3;
    private float indCC1, indCC2, indCC1Min, indCC1Max, indCC2Min, indCC2Max;
    private final PImage aura;
    private float diametroMin, diametroMax;
    private float diametro;
    private float indCA1, indCA2, indCA1Min, indCA1Max, indCA2Min, indCA2Max;

    public ParticulaPorCurva(PApplet sk, Bezier b, PImage aura) {
        this.sk = sk;
        this.b = b;
        this.aura = aura;
        dIndPosMin = 10;
        dIndPosMax = 15;
        indPosMax = b.getTrayectoria().size();
        p = b.getPtoTrayectoria(0);
        posPrev = new PVector(p.x, p.y);
        pos = new PVector(p.x, p.y);
        dir = new PVector();
        dirNormal = new PVector();
        dNormal = 0f;
        dNormalMax = sk.height * 0.25f;
        velPerlinMin = 0.050f;
        velPerlinMax = 0.075f;
        diametroMin = 10;
        diametroMax = 50;
    }
    
    public void setVelMinMax(int dIndPosMin, int dIndPosMax) {
        if (dIndPosMin <= 0) {
            dIndPosMin = 1;
        }
        this.dIndPosMin = dIndPosMin;
        this.dIndPosMax = dIndPosMax;
    }

    public void setupCambios(float indCA1, float indCA2, float dCA, float indCC1, float indCC2, float dCC) {
        indFinAlfaMin = 0.75f;
        indFinAlfaMax = 1;
        this.indCA1 = indCA1;
        this.indCA2 = indCA2;
        indCA1Min = indCA1 * (1 - dCA);
        indCA1Max = indCA1 * (1 + dCA);
        indCA2Min = indCA2 * (1 - dCA);
        indCA2Max = indCA2 * (1 + dCA);
        this.indCC1 = indCC1;
        this.indCC2 = indCC2;
        indCC1Min = indCC1 * (1 - dCC);
        indCC1Max = indCC1 * (1 + dCC);
        indCC2Min = indCC2 * (1 - dCC);
        indCC2Max = indCC2 * (1 + dCC);
    }

    public void setColores(ArrayList<Integer> c) {
        if (c == null || c.size() != 4) {
            c0 = sk.color(255, 0, 255);
            c1 = sk.color(255, 255, 0);
            c2 = sk.color(255, 255, 0);
            c3 = sk.color(255, 255, 0);
        } else {
            this.c0 = c.get(0);
            this.c1 = c.get(1);
            this.c2 = c.get(2);
            this.c3 = c.get(3);
        }
    }
    
    void setAlfaMax(float alfaMax) {
        this.alfaMax = alfaMax;
    }

    public void setAnchoMax(float dNormalMax) {
        this.dNormalMax = dNormalMax;
    }
    
    public void setDiametroMinMax(float diametroMin, float diametroMax) {
        this.diametroMin = diametroMin;
        this.diametroMax = diametroMax;
    }

    public void reset() {
        indPos = 0;
        dIndPos = (int) sk.random(dIndPosMin, dIndPosMax);
        indFinAlfa = (int) (indPosMax * sk.random(indFinAlfaMin, indFinAlfaMax));
        boolSentidoNormal = sk.random(1) > 0.5;
        boolSinModPerlin = sk.random(1) > 0.75;
        perlin = sk.random(1000);
        velPerlin = sk.random(velPerlinMin, velPerlinMax);
        indCA1 = sk.random(indCA1Min, indCA1Max);
        indCA2 = sk.random(indCA2Min, indCA2Max);
        indCC1 = sk.random(indCC1Min, indCC1Max);
        indCC2 = sk.random(indCC2Min, indCC2Max);
    }

    public void update() {
        // se actualiza el índice de la posición
        indPos += dIndPos;
        // si se llega al final se reinician los parámetros
        if (indPos >= indFinAlfa) {
            reset();
        }
        // se actualiza la posición actual y la previa
        if (indPos == 0) {
            p = b.getPtoTrayectoria(0);
        } else {
            p = b.getPtoTrayectoria(indPos - 1);
        }
        posPrev.set(p.x, p.y);
        p = b.getPtoTrayectoria(indPos);
        pos.set(p.x, p.y);
        // se actualiza la dirección
        p = PVector.sub(pos, posPrev);
        dir.set(p.x, p.y);
        // se calcula la dirección normal
        float anguloRotacion = 90;
        if (boolSentidoNormal) {
            anguloRotacion *= -1;
        }
        dirNormal.set(dir.x, dir.y);
        dirNormal.rotate(radians(anguloRotacion));
        dirNormal.normalize();
        // se calcula la desviación (magnitud de la normal) respecto de la posición
        dNormal = map(indPos, 0, indPosMax, 0, dNormalMax);
        perlin += velPerlin;
        modPerlin = sk.noise(perlin);
        if (!boolSinModPerlin) {
            dirNormal.mult(dNormal * modPerlin);
        } else {
            dirNormal.mult(dNormal * modPerlin * 0.25f);
        }
        // se calcula la posición final de la partícula
        x = pos.x + dirNormal.x;
        y = pos.y + dirNormal.y;
        // se calcula el índice del alfa        
        float indAlfa = map(indPos, 0, indFinAlfa, 0, 1);
        float aMin = 0f;
        float aMax = 0f;
        if (indAlfa >= 0 && indAlfa < indCA1) {
            indAlfa = map(indAlfa, 0, indCA1, 0, 1);
            aMin = alfaMin;
            aMax = alfaMax;
        } else if (indAlfa >= indCA1 && indAlfa < indCA2) {
            indAlfa = map(indAlfa, indCA1, indCA2, 0, 1);
            aMin = alfaMax;
            aMax = alfaMax;
        } else if (indAlfa >= indCA2) {
            indAlfa = map(indAlfa, indCA2, 1, 0, 1);
            aMin = alfaMax;
            aMax = alfaMin;
        }
        alfa = lerp(aMin, aMax, indAlfa);
        // se calcula el índice del color
        float indColor = map(indPos, 0, indFinAlfa, 0, 1);
        int cMin = 0;
        int cMax = 0;
        if (indColor >= 0 && indColor < indCC1) {
            indColor = map(indColor, 0, indCC1, 0, 1);
            cMin = c0;
            cMax = c1;
        } else if (indColor >= indCC1 && indColor < indCC2) {
            indColor = map(indColor, indCC1, indCC2, 0, 1);
            cMin = c1;
            cMax = c2;
        } else if (indColor >= indCC2) {
            indColor = map(indColor, indCC2, 1, 0, 1);
            cMin = c2;
            cMax = c3;
        }
        // se calcula el color
        c = sk.lerpColor(cMin, cMax, indColor);
        c = sk.color(sk.red(c), sk.green(c), sk.blue(c), alfa);
        // se calcula el diámetro de la partícula
        diametro = map(indPos, 0, indFinAlfa, diametroMin, diametroMax);
    }

    public void display() {
        sk.imageMode(sk.CENTER);
        sk.tint(c);
        sk.image(aura, x, y, diametro, diametro);
    }

    public int getIndPos() {
        return indPos;
    }

    public int getIndFinAlfa() {
        return indFinAlfa;
    }

    public int getdIndPos() {
        return dIndPos;
    }

}

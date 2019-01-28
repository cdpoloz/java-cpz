package Util;

import processing.core.PApplet;

/**
 * @author CPZ
 */
public class VariablesSketch {

    private final PApplet sk;
    private float strokeWeightPrev;
    private int colorFillPrev;
    private int colorStrokePrev;
    private int colorTintPrev;
    private int imageModePrev;
    private int rectModePrev;
    private int colorModePrev;
    private float rMaxPrev, gMaxPrev, bMaxPrev, aMaxPrev;
    private int textAlignPrev;
    private float textSizePrev;
    private boolean fillPrev, strokePrev, tintPrev, cursorPrev;

    public VariablesSketch(PApplet sk) {
        this.sk = sk;
    }

    public void save() {
        strokeWeightPrev = sk.g.strokeWeight;
        fillPrev = sk.g.fill;
        strokePrev = sk.g.stroke;
        tintPrev = sk.g.tint;
        colorFillPrev = sk.g.fillColor;
        colorStrokePrev = sk.g.strokeColor;
        colorTintPrev = sk.g.tintColor;
        imageModePrev = sk.g.imageMode;
        rectModePrev = sk.g.rectMode;
        colorModePrev = sk.g.colorMode;
        rMaxPrev = sk.g.colorModeX;
        gMaxPrev = sk.g.colorModeY;
        bMaxPrev = sk.g.colorModeZ;
        aMaxPrev = sk.g.colorModeA;
        textAlignPrev = sk.g.textAlign;
        textSizePrev = sk.g.textSize;
    }

    public void restore() {
        if (strokePrev) {
            sk.stroke(colorStrokePrev);
        }
        if (fillPrev) {
            sk.fill(colorFillPrev);
        }
        if (tintPrev) {
            sk.tint(colorTintPrev);
        }
        sk.strokeWeight(strokeWeightPrev);
        sk.imageMode(imageModePrev);
        sk.rectMode(rectModePrev);
        sk.colorMode(colorModePrev, rMaxPrev, gMaxPrev, bMaxPrev, aMaxPrev);
        sk.textAlign(textAlignPrev);
        sk.textSize(textSizePrev);
    }
}

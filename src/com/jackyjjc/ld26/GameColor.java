package com.jackyjjc.ld26;

import org.newdawn.slick.Color;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class GameColor {

    public float c;
    public float m;
    public float y;
    public float k;

    public GameColor(float c, float m, float y, float k) {
        this.c = c;
        this.m = m;
        this.y = y;
        this.k = k;
    }

    public static GameColor fromRGB(Color color) {

        float r1 = (float) (color.getRed() / 255.0);
        float g1 = (float) (color.getGreen() / 255.0);
        float b1 = (float) (color.getBlue() / 255.0);

        float k = 1 - Math.max(Math.max(r1, g1), b1);
        float c = (1- k) == 0 ? 0 : (1 - r1 - k) / (1 - k);
        float m = (1- k) == 0 ? 0 : (1 - g1 - k) / (1 - k);
        float y = (1- k) == 0 ? 0 : (1 - b1 - k) / (1 - k);

        return new GameColor(c, m, y, k);
    }

    public Color toRGB() {

        int r = (int) (255 * (1 - c) * (1 - k));
        int g = (int) (255 * (1 - m) * (1 - k));
        int b = (int) (255 * (1 - y) * (1 - k));

        return new Color(r, g, b);
    }
}

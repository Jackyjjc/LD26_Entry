package com.jackyjjc.ld26;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Polygon;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class Hexagon {

    private GameColor fillGameColor;
    private GameColor lineGameColor;
    private int lineThickness;

    private Polygon shape;

    public Hexagon(int x, int y, int sideLength) {

        this.fillGameColor = GameColor.fromRGB(Color.white);
        this.lineGameColor = GameColor.fromRGB(Color.black);
        this.lineThickness = 3;

        float c = sideLength;
        float a = c / 2;
        float b = (float) (Math.sin(Math.toRadians(60)) * c);

        float[] points = new float[] {0 + x, b + y,
                                      a + x, 0 + y,
                                      a + c + x, y,
                                      2 * c + x, b + y,
                                      a + c + x, 2 * b + y,
                                      a + x, 2 * b + y};

        shape = new Polygon(points);
    }

    public void draw(Graphics g) {

        g.setColor(fillGameColor.toRGB());
        g.fill(shape);

        g.setLineWidth(lineThickness);
        g.setColor(lineGameColor.toRGB());
        g.draw(shape);
    }

    public boolean contains(int x, int y) {
        return shape.contains(x, y);
    }

    /* Boring Setters */
    public void setFillColor(GameColor fillGameColor) {
        this.fillGameColor = fillGameColor;
    }

    public void setLineColor(GameColor lineGameColor) {
        this.lineGameColor = lineGameColor;
    }

    public void setLineThickness(int lineThickness) {
        this.lineThickness = lineThickness;
    }
}

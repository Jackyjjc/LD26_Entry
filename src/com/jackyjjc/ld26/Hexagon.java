package com.jackyjjc.ld26;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Polygon;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class Hexagon {

    private Color fillColor;
    private Color lineColor;
    private int lineThickness;

    private Polygon shape;

    public Hexagon(int x, int y, int sideLength) {

        this.fillColor = Color.white;
        this.lineColor = Color.black;
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

        g.setColor(fillColor);
        g.fill(shape);

        g.setLineWidth(lineThickness);
        g.setColor(lineColor);
        g.draw(shape);
    }

    public boolean contains(int x, int y) {
        return shape.contains(x, y);
    }

    /* Boring Setters */
    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }

    public void setLineColor(Color lineColor) {
        this.lineColor = lineColor;
    }

    public void setLineThickness(int lineThickness) {
        this.lineThickness = lineThickness;
    }
}

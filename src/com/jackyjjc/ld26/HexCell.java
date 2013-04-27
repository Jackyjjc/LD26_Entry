package com.jackyjjc.ld26;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Polygon;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class HexCell {

    public static final int SIDE_LENGTH = 30;
    public static final int HEIGHT = 52;

    private int x;
    private int y;

    private Polygon shape;
    private double[] proportionMap;

    public HexCell(int x, int y) {

        this.x = x;
        this.y = y;

        float c = SIDE_LENGTH;
        float a = c / 2;
        float b = (float) (Math.sin(Math.toRadians(60)) * c);

        float[] points = new float[] {0 + x, b + y,
                                      a + x, 0 + y,
                                      a + c + x, y,
                                      2 * c + x, b + y,
                                      a + c + x, 2 * b + y,
                                      a + x, 2 * b + y};

        shape = new Polygon(points);
        proportionMap = new double[Element.values().length];
        for(Element element : Element.values()) {
            proportionMap[element.getId()] = 0;
        }
    }

    public void draw(Graphics g) {
        g.setLineWidth(3);
        g.setColor(getColor());
        g.fill(shape);
        g.setColor(Color.black);
        g.draw(shape);
    }

    public Color getColor() {

        float red = 0;
        float green = 0;
        float blue = 0;

        for(Element element : Element.values()) {
            double portion = proportionMap[element.getId()];
            red += element.getColor().getRed() * portion;
            green += element.getColor().getGreen() * portion;
            blue += element.getColor().getBlue() * portion;
        }

        if(red + green + blue == 0) {
            return Color.white;
        }

        return new Color(red, green, blue);
    }

    public boolean contains(int x, int y) {
        return shape.contains(x, y);
    }

    public void setElement(Element newElement) {

        for(Element element : Element.values()) {
            proportionMap[element.getId()] = 0;
        }

        proportionMap[newElement.getId()] = 100;
    }

}

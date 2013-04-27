package com.jackyjjc.ld26;

import org.newdawn.slick.Color;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public enum Element {

    GOLD(Color.yellow, 0),
    WOOD(new Color(127, 51, 0), 1),
    WATER(Color.blue, 2),
    FIRE(Color.red, 3),
    EARTH(Color.green, 4);

    private Color color;
    private int id;

    private Element(Color color, int id) {
        this.color = color;
        this.id = id;
    }

    public Color getColor() {
        return color;
    }

    public int getId() {
        return id;
    }
}

package com.jackyjjc.ld26;

import org.newdawn.slick.Color;

/**
 * The five basic elements that forms everything based on ancient chinese mythology
 *
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public enum Element {

    METAL(GameColor.fromRGB(new Color(255, 205, 5)), 0),
    WOOD(GameColor.fromRGB(new Color(178, 118, 15)), 1),
    WATER(GameColor.fromRGB(new Color(15, 67, 227)), 2),
    FIRE(GameColor.fromRGB(new Color(255, 5, 5)), 3),
    EARTH(GameColor.fromRGB(new Color(9, 154, 5)), 4);

    private GameColor color;
    private int id;

    private Element(GameColor GameColor, int id) {
        this.color = GameColor;
        this.id = id;
    }

    public GameColor getColor() {
        return color;
    }

    public int getId() {
        return id;
    }

    public Element getMutualOvercomeElement() {

        Element mcElement = null;

        switch (this) {
            case METAL:
                mcElement = FIRE;
                break;
            case WOOD:
                mcElement = METAL;
                break;
            case WATER:
                mcElement = EARTH;
                break;
            case FIRE:
                mcElement = WATER;
                break;
            case EARTH:
                mcElement = WOOD;
                break;
        }

        return mcElement;
    }

    public Element getMutualGeneratingElement() {

        Element mgElement = null;

        switch (this) {

            case METAL:
                mgElement = EARTH;
                break;
            case WOOD:
                mgElement = WATER;
                break;
            case WATER:
                mgElement = METAL;
                break;
            case FIRE:
                mgElement = WOOD;
                break;
            case EARTH:
                mgElement = FIRE;
                break;
        }

        return mgElement;
    }
}

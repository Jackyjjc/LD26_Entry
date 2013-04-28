package com.jackyjjc.ld26;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import java.util.List;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class HexCell {

    public static final int SIDE_LENGTH = 30;
    public static final int WIDTH = 49;
    public static final int HEIGHT = 56;

    private int x;
    private int y;

    private Hexagon hexagon;

    private Player owner;
    private int[] proportionMap;

    public HexCell(int x, int y) {

        this.x = x;
        this.y = y;

        Point mousePos = Utils.mapPosToMousePos(x, y);

        this.hexagon = new Hexagon(mousePos.x, mousePos.y, SIDE_LENGTH);

        this.owner = null;

        proportionMap = new int[Element.values().length];
        for(Element element : Element.values()) {
            proportionMap[element.getId()] = 0;
        }
    }

    public void draw(Graphics g) {
        hexagon.setFillColor(getColor());
        if(owner != null) {
            hexagon.setLineColor(owner.getColor());
        }
        hexagon.draw(g);
    }

    public Color getColor() {

        float red = 0;
        float green = 0;
        float blue = 0;

        double total = 0;
        for(Element element : Element.values()) {
            total += proportionMap[element.getId()];
        }

        if(total == 0) {
            return Color.white;
        }

        for(Element element : Element.values()) {
            double portion = proportionMap[element.getId()] / total;
            red += element.getColor().getRed() * portion;
            green += element.getColor().getGreen() * portion;
            blue += element.getColor().getBlue() * portion;
        }

        return new Color(red, green, blue);
    }

    public void addElement(Element element) {
        proportionMap[element.getId()]++;
    }

    public boolean contains(int x, int y) {
        return hexagon.contains(x, y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player player) {
        this.owner = player;
        player.addCell(this);
    }
}

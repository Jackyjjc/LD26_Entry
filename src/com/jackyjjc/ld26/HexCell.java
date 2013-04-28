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

    public GameColor getColor() {

        float c = 0;
        float m = 0;
        float y = 0;
        float k = 0;

        double total = 0;
        for(Element element : Element.values()) {
            total += proportionMap[element.getId()];
        }

        for(Element element : Element.values()) {
            double portion = (total == 0) ? 0 : proportionMap[element.getId()] / total;
            c += element.getColor().c * portion;
            m += element.getColor().m * portion;
            y += element.getColor().y * portion;
            k += element.getColor().k * portion;
        }

        return new GameColor(c, m, y, k);
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

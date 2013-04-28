package com.jackyjjc.ld26;

import org.newdawn.slick.Graphics;

import java.util.ArrayList;
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

        double total = getTotalElements();

        for(Element element : Element.values()) {
            double portion = (total == 0) ? 0 : proportionMap[element.getId()] / total;
            c += element.getColor().c * portion;
            m += element.getColor().m * portion;
            y += element.getColor().y * portion;
            k += element.getColor().k * portion;
        }

        return new GameColor(c, m, y, k);
    }

    public double getElementStrength(Element element) {
        int strength = proportionMap[element.getId()];

        if(strength > 0) {
            strength += proportionMap[element.getMutualGeneratingElement().getId()] * 0.5;
        }
        return strength;
    }

    public void interact(HexCell other) {

        List<Element> elementsToRemoveOnThis = new ArrayList<Element>();
        List<Element> elementsToRemoveOnOther = new ArrayList<Element>();

        for (Element element : Element.values()) {
            Element mcElement = element.getMutualOvercomeElement();
            if(this.getElementStrength(element) > other.getElementStrength(mcElement)) {
                elementsToRemoveOnOther.add(mcElement);
            } else {
                elementsToRemoveOnThis.add(element);
            }
        }

        this.removeElements(elementsToRemoveOnThis);
        other.removeElements(elementsToRemoveOnOther);
    }

    public int getTotalElements() {
        int total = 0;
        for(Element element : Element.values()) {
            total += proportionMap[element.getId()];
        }
        return total;
    }

    public void addElement(Element element) {
        proportionMap[element.getId()]++;
    }

    public void reduceElement(Element element) {
        if(proportionMap[element.getId()] > 0) {
            proportionMap[element.getId()]--;
        }
    }

    public void removeElements(List<Element> elements) {
        for (Element element : elements) {
            removeElement(element);
        }
    }

    public void removeElement(Element element) {
        proportionMap[element.getId()] = 0;
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

        if(player == null && owner != null) {
            owner.removeCell(this);
        }

        this.owner = player;

        if(player != null) {
            player.addCell(this);
        }
    }
}

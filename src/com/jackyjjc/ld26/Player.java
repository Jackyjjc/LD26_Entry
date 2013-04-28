package com.jackyjjc.ld26;

import org.newdawn.slick.Color;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class Player {

    private Color color;
    private List<HexCell> cells;

    private Element currentElement;

    public Player(Color color) {
        this.color = color;
        this.cells = new ArrayList<HexCell>();
        getNewElement();
    }

    public Color getColor() {
        return color;
    }

    public void addCell(HexCell cell) {
        cells.add(cell);
    }

    public void removeCell(HexCell cell) {
        cells.remove(cell);
    }

    public List<HexCell> getOwnedCells() {
        return cells;
    }

    public Element getCurrentElement() {
        return currentElement;
    }

    public void getNewElement() {
        //TODO: generate element based on some sort of rule
        currentElement = Element.values()[0];
    }
}

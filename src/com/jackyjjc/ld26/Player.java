package com.jackyjjc.ld26;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class Player {

    private GameColor color;
    private List<HexCell> cells;

    private Element currentElement;

    public Player(GameColor color) {
        this.color = color;
        this.cells = new ArrayList<HexCell>();
        getNewElement();
    }

    public GameColor getColor() {
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
        currentElement = Element.values()[((int) (Math.random() * 5))];
    }
}

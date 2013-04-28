package com.jackyjjc.ld26;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class GameMap {

    private static final int WIDTH = 16;
    private static final int HEIGHT = 10;

    private static final int BG_WIDTH = WIDTH + 2;
    private static final int BG_HEIGHT = HEIGHT + 3;
    private static final int BG_XOFFSET = - 49;
    private static final int BG_YOFFSET = - 84;

    private Hexagon[][] background;

    private HexCell[][] map;

    private Player player;

    public GameMap() {

        background = new Hexagon[BG_HEIGHT][BG_WIDTH];
        for (int row = 0; row < BG_HEIGHT; row++) {
            for(int col = 0; col < BG_WIDTH; col++) {
                Point mousePos = Utils.mapPosToMousePos(col, row);
                background[row][col] = new Hexagon(mousePos.x + BG_XOFFSET, mousePos.y + BG_YOFFSET, HexCell.SIDE_LENGTH);
            }
        }


        map = new HexCell[HEIGHT][WIDTH];

        for (int row = 0; row < HEIGHT; row++) {
            for(int col = 0; col < WIDTH; col++) {
                map[row][col] = new HexCell(col, row);
            }
        }

        player = new Player(Color.orange);
    }

    public List<HexCell> getSurroundingCell(int col, int row) {

        List<HexCell> cells = new ArrayList<HexCell>();

        if(withinMap(col - 1, row)) cells.add(map[row][col]);
        if(withinMap(col, row - 1)) cells.add(map[row][col]);
        if(withinMap(col + 1, row)) cells.add(map[row][col]);
        if(withinMap(col, row + 1)) cells.add(map[row][col]);

        if(col % 2 == 0) {
            if(withinMap(col + 1, row + 1)) cells.add(map[row][col]);
            if(withinMap(col - 1, row + 1)) cells.add(map[row][col]);
        } else {
            if(withinMap(col + 1, row - 1)) cells.add(map[row][col]);
            if(withinMap(col - 1, row - 1)) cells.add(map[row][col]);
        }

        return cells;
    }

    public boolean withinMap(int col, int row) {
        return col >= 0 && col < WIDTH && row >= 0 && row < HEIGHT;
    }

    public void draw(Graphics g) {
        for (int row = 0; row < BG_HEIGHT; row++) {
            for(int col = 0; col < BG_WIDTH; col++) {
                background[row][col].draw(g);
            }
        }
    }
}

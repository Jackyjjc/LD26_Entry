package com.jackyjjc.ld26;

import org.newdawn.slick.Graphics;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class GameMap {

    private int WIDTH = 17;
    private int HEIGHT = 11;

    private HexCell[][] map;

    public GameMap() {
        map = new HexCell[HEIGHT][WIDTH];

        for (int row = 0; row < HEIGHT; row++) {
            for(int col = 0; col < WIDTH; col++) {
                if(col % 2 != 0) {
                    map[row][col] = new HexCell(col * 45 + 10, row * HexCell.HEIGHT);
                } else {
                    map[row][col] = new HexCell(col * 45 + 10, row * HexCell.HEIGHT + 26);
                }
            }
        }
    }

    public HexCell getCell(int col, int row) {
        return map[row][col];
    }

    public void draw(Graphics g) {
        for (int row = 0; row < HEIGHT; row++) {
            for(int col = 0; col < WIDTH; col++) {
                map[row][col].draw(g);
            }
        }
    }

    public void press(int x, int y) {
        for (int row = 0; row < HEIGHT; row++) {
            for(int col = 0; col < WIDTH; col++) {
                if(map[row][col].contains(x, y)) {
                    map[row][col].setElement(Element.values()[((int) (Math.random() * 5))]);
                }
            }
        }
    }
}

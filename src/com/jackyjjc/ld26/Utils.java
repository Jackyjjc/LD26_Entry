package com.jackyjjc.ld26;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class Utils {

    private static final int X_OFFSET = 2;
    private static final int Y_OFFSET = 8;

    public static Point mousePosToMapPos(int mouseX, int mouseY) {

        int col = (mouseX - X_OFFSET) / HexCell.WIDTH;
        int row;

        if(col % 2 == 0) {
            row = (mouseY - 28 - Y_OFFSET) / HexCell.HEIGHT;
        } else {
            row = (mouseY - Y_OFFSET) / HexCell.HEIGHT;
        }

        return Point.createPoint(col, row);
    }

    public static Point mapPosToMousePos(int col, int row) {

        int x, y;

        x = col * HexCell.WIDTH + X_OFFSET;

        if(col % 2 == 0) {
            y = row * HexCell.HEIGHT + 28 + Y_OFFSET;
        } else {
            y = row * HexCell.HEIGHT + Y_OFFSET;
        }

        return Point.createPoint(x, y);
    }

}

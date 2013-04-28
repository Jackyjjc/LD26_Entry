package com.jackyjjc.ld26;

import org.newdawn.slick.*;

import java.util.List;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class LDGame extends BasicGame {

    private static final int NUM_PLAYERS = 2;

    private GameMap map;
    private GameController logic;
    private Player[] players;

    public LDGame() {
        super("#LD26 Game - Place Holder");
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {

        players = new Player[NUM_PLAYERS];
        players[0] = new Player(GameColor.fromRGB(Color.magenta));
        players[1] = new Player(GameColor.fromRGB(Color.cyan));

        this.map = new GameMap();
        this.logic = new GameController(players, map);

        gameContainer.getInput().addMouseListener(this);
    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {

        int mouseX = gameContainer.getInput().getMouseX();
        int mouseY = gameContainer.getInput().getMouseY();

    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {

        graphics.setAntiAlias(true);
        graphics.setColor(Color.white);
        graphics.fillRect(0, 0, gameContainer.getWidth(), gameContainer.getHeight());

        map.draw(graphics);

        for (Player player : players) {
            for(HexCell cell : player.getOwnedCells()) {
                cell.draw(graphics);
            }
        }

        //display the next element the user can use
        Hexagon hex = new Hexagon(5, 5, 20);
        hex.setFillColor(logic.getCurrentPlayer().getCurrentElement().getColor());
        hex.draw(graphics);
    }

    @Override
    public void mouseReleased(int button, int x, int y) {

        if(button == Input.MOUSE_LEFT_BUTTON) {

            Point mapPos = Utils.mousePosToMapPos(x, y);
            if(!map.withinMap(mapPos.x, mapPos.y)) {
                System.out.println(mapPos.x + "," + mapPos.y + " not within valid range");
                return;
            }

            HexCell clickedCell = null;

            HexCell currentCell = map.getCell(mapPos.x, mapPos.y);

            List<HexCell> cells = map.getSurroundingCell(mapPos.x, mapPos.y);
            if(currentCell != null) {
                cells.add(currentCell);
            }

            for (HexCell cell : cells) {
                if(cell.contains(x, y)) {
                    clickedCell = cell;
                }
            }
            if(clickedCell != null) {
                System.out.println("Player clicked cell " + clickedCell.getX() + "," + clickedCell.getY());
                if(logic.isLegalMove(clickedCell)) {
                    logic.PlaceElement(clickedCell);
                    logic.nextTurn();
                }
            } else {
                System.out.println("Clicked Cell is null");
            }
        }
    }
}

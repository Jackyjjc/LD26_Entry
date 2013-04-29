package com.jackyjjc.ld26;

import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.util.List;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class GamePlayState extends BasicGameState {

    private int id;

    private static final int NUM_PLAYERS = 2;

    private GameMap map;
    private GameController logic;
    private Player[] players;

    private int tutorialSlide = 0;
    private Image[] tutorial;

    private boolean listenerAdded;

    public GamePlayState(int id) {
        this.id = id;
    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {

        players = new Player[NUM_PLAYERS];
        players[0] = new Player(GameColor.fromRGB(Color.magenta));
        players[1] = new Player(GameColor.fromRGB(Color.cyan));

        this.map = new GameMap();
        this.logic = new GameController(players, map);

        gameContainer.getInput().removeMouseListener(this);
        this.listenerAdded = false;

        this.tutorial = new Image[2];
        this.tutorial[0] = new Image("assets/helpScreen1.png");
        this.tutorial[1] = new Image("assets/helpScreen.png");
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {

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

        if(tutorialSlide < tutorial.length) {
            graphics.drawImage(tutorial[tutorialSlide], 101.5f, 119.5f);
        }
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        if(tutorialSlide >= tutorial.length && !listenerAdded) {
            gameContainer.getInput().addMouseListener(this);
        } else if(tutorialSlide < tutorial.length) {
            if(gameContainer.getInput().isKeyPressed(Input.KEY_ENTER) || gameContainer.getInput().isKeyPressed(Input.KEY_SPACE)) {
                tutorialSlide++;
            }
        } else if(tutorialSlide >= tutorial.length && listenerAdded) {

        }
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

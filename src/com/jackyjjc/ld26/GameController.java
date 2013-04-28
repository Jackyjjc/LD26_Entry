package com.jackyjjc.ld26;

import java.util.List;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class GameController {

    private int numTurns;
    private Player[] players;
    Player currentPlayer;

    private GameMap map;

    public GameController(Player[] players, GameMap map) {
        this.players = players;
        this.map = map;

        this.numTurns = 0;
        currentPlayer = players[0];
    }

    public void PlaceElement(HexCell cell) {

        System.out.println("User Place Element");

        //get all the adjacent cells
        List<HexCell> cells = map.getSurroundingCell(cell.getX(), cell.getY());

        //check if there is an opposite element



        if(cell.getOwner() == null) {
            cell.setOwner(currentPlayer);
        }
        cell.addElement(currentPlayer.getCurrentElement());

    }

    /**
     *
     * @return If it has successfully advanced to the next turn
     */
    public boolean nextTurn() {

        boolean succeed = false;

        if(!isFinished()) {

            numTurns++;

            currentPlayer = players[numTurns % players.length];

            //player gets a new element
            currentPlayer.getNewElement();

            succeed = true;
        }

        return succeed;
    }

    public boolean isLegalMove(HexCell cell) {

        boolean legal = false;

        if(cell.getOwner() == null || cell.getOwner() == currentPlayer) {
            legal = true;
        } else {
            System.out.println("It is not a legal move because owner is " + cell.getOwner());
        }

        return legal;
    }

    public boolean isFinished() {
        return false;
    }

}

package com.jackyjjc.ld26;

import org.newdawn.slick.*;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class LDGame extends BasicGame {

    private GameMap map;

    public LDGame() {
        super("#LD26 Game - Place Holder");
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {
        map = new GameMap();
    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {
        int mouseX = gameContainer.getInput().getMouseX();
        int mouseY = gameContainer.getInput().getMouseY();
        if(gameContainer.getInput().isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
            map.press(mouseX, mouseY);
        }
    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        graphics.setColor(Color.white);
        graphics.fillRect(0, 0, gameContainer.getWidth(), gameContainer.getHeight());
        map.draw(graphics);
    }
}

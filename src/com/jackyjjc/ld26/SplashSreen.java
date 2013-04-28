package com.jackyjjc.ld26;

import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class SplashSreen extends BasicGameState {

    private static final int Y_OFFSET = 370;
    private static final long WAIT_TIME = 1000;

    private Image splashImage;
    private SpriteSheet buttons;
    private int id;

    private long timePassed;

    private int selection;

    private boolean doneRendering;

    public SplashSreen(int id) {
        this.id = id;
        this.selection = 0;
        this.doneRendering = false;
    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        splashImage = new Image("assets/splashScreen.png");
        buttons = new SpriteSheet("assets/buttons.png", 250, 50);
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {

        graphics.drawImage(splashImage, 0, 0);

        if(timePassed > WAIT_TIME) {

            float alpha = (float) ((timePassed - WAIT_TIME) / 2000.0);

            if(alpha > 1) alpha = 1;

            Image subImage = buttons.getSubImage(0,0);
            subImage.setAlpha(alpha);
            graphics.drawImage(subImage, 275, Y_OFFSET);

            subImage = buttons.getSubImage(0,1);
            subImage.setAlpha(alpha);
            graphics.drawImage(subImage, 275, Y_OFFSET + 65);

            subImage = buttons.getSubImage(0,2);
            subImage.setAlpha(alpha);
            graphics.drawImage(subImage, 275, Y_OFFSET + 2 * 65);

            if((1.0 - alpha) < 0.001) {
                doneRendering = true;
                graphics.setColor(new Color(1f, 1f, 1f, 0.3f));
                graphics.fillRect(275, Y_OFFSET + selection * 65, 250, 50);
            }
        }
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {

        timePassed += i;

        if(doneRendering) {
            if(gameContainer.getInput().isKeyPressed(Input.KEY_DOWN)) {
                selection += 1;
                selection %= 3;
            } else if (gameContainer.getInput().isKeyPressed(Input.KEY_UP)) {
                selection--;
                if(selection < 0) {
                    selection += 3;
                }
            }

            if(gameContainer.getInput().isKeyPressed(Input.KEY_SPACE) || gameContainer.getInput().isKeyPressed(Input.KEY_ENTER)) {
                if(selection == 0) {
                    stateBasedGame.enterState(ElementalWar.GAMEPLAY_STATE);
                } else if(selection == 2) {
                    System.exit(0);
                }
            }
        }

    }
}

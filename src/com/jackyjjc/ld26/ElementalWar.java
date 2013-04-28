package com.jackyjjc.ld26;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class ElementalWar extends StateBasedGame {

    public static final int SPLASH_STATE = 0;
    public static final int GAMEPLAY_STATE = 1;
    private static final int FINISH_STATE = 2;

    public ElementalWar() {
        super("#LD26 Entry - Elemental War");
    }

    @Override
    public void initStatesList(GameContainer gameContainer) throws SlickException {
        this.addState(new SplashSreen(SPLASH_STATE));
        this.addState(new GamePlayState(GAMEPLAY_STATE));
    }

    public static void main(String[] args) throws SlickException {

        AppGameContainer app = new AppGameContainer(new ElementalWar());
        app.setDisplayMode(800, 600, false);
        app.setShowFPS(false);
        app.start();

    }
}

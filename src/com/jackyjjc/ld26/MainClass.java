package com.jackyjjc.ld26;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class MainClass {

    public static void main(String[] args) throws SlickException {

        AppGameContainer app = new AppGameContainer(new LDGame());
        app.setDisplayMode(800, 600, false);
        app.setShowFPS(false);
        app.start();

    }

}

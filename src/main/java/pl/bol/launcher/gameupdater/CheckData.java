package pl.bol.launcher.gameupdater;

import pl.bol.engine.core.CoreEngine;
import pl.bol.game.TestGame;

/**
 * Created by Bolo on 2014-10-28.
 */
public class CheckData {
    private static boolean startGame;

    public CheckData() {
        startGame = false;
    }

    public static boolean getStartGame() {
        return startGame;
    }

    public static void setStartGame(boolean x) {
        startGame = x;
    }

    public static void check() {
        if(CheckData.getStartGame()) {
            CoreEngine engine = new CoreEngine(800, 600, new TestGame());
            engine.createWindow("Battle Of Legends");
            engine.startGame();
        }
    }
}

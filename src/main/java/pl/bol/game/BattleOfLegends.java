package pl.bol.game;

import pl.bol.launcher.GUI.Window;

public class BattleOfLegends {
    private static Window window;
    public static void main(String[] args) {
        window = new Window(800, 600, "Launcher", true, false);
        window.setupFrame();
    }
}

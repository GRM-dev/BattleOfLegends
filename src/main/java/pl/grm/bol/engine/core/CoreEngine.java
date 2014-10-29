package pl.grm.bol.engine.core;

import org.lwjgl.opengl.Display;

import pl.grm.bol.engine.render.RenderingEngine;
import pl.grm.bol.engine.render.Window;
import pl.tm24.patrykp.biblioteki.patryklib.Czas;

public class CoreEngine {
    private int width;
    private int height;
    private boolean isGameRunning;
    private Game game;
    private RenderingEngine renderingEngine;
    Czas time = new Czas("Czas", 5);

    public CoreEngine(int width, int height, Game game) {
        this.isGameRunning = false;
        this.game = game;
        this.height = height;
        this.width = width;
    }

    public void createWindow(String gameName) {
        Window.createWindow(width, height, gameName);
        this.renderingEngine = new RenderingEngine();
    }

    public void startGame() {
        if (isGameRunning)
            return;
        runGame();
    }

    public void stopGame() {
        if (!isGameRunning)
            return;
        isGameRunning = false;
    }

    private void runGame() {
        isGameRunning = true;
        game.init();

        while (isGameRunning) {
            if (Window.isCloseRequested())
                stopGame();
            time.aktualizuj();

            game.input((float)time.delta());
            Input.update();
            game.update((float)time.delta());

            renderingEngine.render(game.getRootObject());
            Window.render();

            Display.setTitle("Battle Of Legends FPS: " + time.fps());

            try {
                Thread.sleep(17);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
        cleanUp();
    }

    public void cleanUp() {
        Window.dispose();
    }
}
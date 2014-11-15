package pl.bol.game;

import java.util.logging.Level;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import pl.bol.engine.inputs.keyboard.KeyboardInput;
import pl.bol.engine.inputs.mouse.MouseInput;
import pl.bol.game.filehandler.BLog;
import pl.tm24.patrykp.biblioteki.patryklib.Czas;

public class Window {
	private boolean isGameRuning = false;
	private Czas time = new Czas("Czas", 1);
	private Game game;
	private BLog bLog;
	private static int WIDTH_GAME_WINDOW = 800;
	private static int HEIGHT_GAME_WINDOW = 600;
	private static String TITLE_GAME_WINDOW = "Battle of Legends";

	public Window(Game game) {
		this.game = game;
		bLog = new BLog();
	}

	public void createWindow(String title) {
		Display.setTitle(title);
		try {
			Display.setDisplayMode(new DisplayMode(getWidthDevWindow(),
					getHeightGameWindow()));
			Display.create();
		} catch (LWJGLException e) {
			bLog.log(Level.SEVERE, e.toString(), e);
		}
	}

	public void startGame() {
		if (!isGameRuning)
			isGameRuning = true;
		loopGame();
	}

	public void stopGame() {
		if (isGameRuning)
			isGameRuning = false;
	}

	private void destroyWindow() {
		Display.destroy();
		System.exit(0);
	}

	private void loopGame() {
		while (!Display.isCloseRequested() && isGameRuning) {
			time.aktualizuj();
			KeyboardInput.update();
			MouseInput.update();

			game.input();
			game.render();

			Display.update();
		}

		destroyWindow();
	}

	public int getWidthDevWindow() {
		return WIDTH_GAME_WINDOW;
	}

	public int getHeightGameWindow() {
		return HEIGHT_GAME_WINDOW;
	}

	public String getTitleGameWindow() {
		return TITLE_GAME_WINDOW;
	}
}

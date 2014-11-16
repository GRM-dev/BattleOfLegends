package pl.grm.bol.game;

import java.util.logging.Level;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import pl.grm.bol.engine.inputs.keyboard.KeyboardInput;
import pl.grm.bol.engine.inputs.mouse.MouseInput;
import pl.tm24.patrykp.biblioteki.patryklib.Czas;

public class GameWindow {
	private boolean isGameRuning = false;
	private Czas time;
	private Presenter presenter;
	private int WIDTH_GAME_WINDOW = 800;
	private int HEIGHT_GAME_WINDOW = 600;
	private String TITLE_GAME_WINDOW = "Battle of Legends";

	public GameWindow(Presenter presenter) {
		this.presenter = presenter;
		time = new Czas("Czas", 1);
	}

	public void createWindow(String title) {
		Display.setTitle(title);
		try {
			Display.setDisplayMode(new DisplayMode(getWidthDevWindow(),
					getHeightGameWindow()));
			Display.create();
		} catch (LWJGLException e) {
			presenter.getbLog().log(Level.SEVERE, e.toString(), e);
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

			presenter.input();
			presenter.render();

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

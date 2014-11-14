package pl.bol.game;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import pl.bol.engine.inputs.keyboard.KeyboardInput;
import pl.tm24.patrykp.biblioteki.patryklib.Czas;

public class Window {
	private int height;
	private int width;
	private boolean isGameRuning = false;
	private Czas time = new Czas("Czas", 1);
	private Game game;

	/**
	 * Constructor of window
	 * 
	 * @param width
	 * @param height
	 */
	public Window(int width, int height) {
		this.width = width;
		this.height = height;
		game = new Game();
	}

	/**
	 * Create new window with param of constructor and title
	 * 
	 * @param title
	 */
	public void createWindow(String title) {
		Display.setTitle(title);
		try {
			Display.setDisplayMode(new DisplayMode(width, height));
			Display.create();
		} catch (LWJGLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Start the game
	 */
	public void startGame() {
		if (!isGameRuning)
			isGameRuning = true;
		loopGame();
	}

	/**
	 * Stop the game
	 */
	public void stopGame() {
		if (isGameRuning)
			isGameRuning = false;
	}

	/**
	 * Destroy the game window
	 */
	private void destroyWindow() {
		Display.destroy();
		System.exit(0);
	}

	/**
	 * Game loop
	 */
	private void loopGame() {
		while (!Display.isCloseRequested() && isGameRuning) {
			time.aktualizuj();
			KeyboardInput.update();

			game.input();
			game.render();

			Display.update();
		}

		destroyWindow();
	}
}

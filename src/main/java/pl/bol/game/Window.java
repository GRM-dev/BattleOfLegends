package pl.bol.game;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import pl.bol.engine.inputs.keyboard.KeyboardInput;
import pl.bol.engine.inputs.mouse.MouseInput;
import pl.tm24.patrykp.biblioteki.patryklib.Czas;

public class Window {
	private static boolean isGameRuning = false;
	private static Czas time = new Czas("Czas", 1);
	private static Game game;
	private final static int WIDTH_GAME_WINDOW = 800;
	private final static int HEIGHT_GAME_WINDOW = 600;
	private final static String TITLE_GAME_WINDOW = "Battle of Legends";

	public Window(Game game) {
		this.game = game;
	}

	public static void createWindow(String title) {
		Display.setTitle(title);
		try {
			Display.setDisplayMode(new DisplayMode(getWidthDevWindow(),
					getHeightGameWindow()));
			Display.create();
		} catch (LWJGLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void startGame() {
		if (!isGameRuning)
			isGameRuning = true;
		loopGame();
	}

	public static void stopGame() {
		if (isGameRuning)
			isGameRuning = false;
	}

	private static void destroyWindow() {
		Display.destroy();
		System.exit(0);
	}

	private static void loopGame() {
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

	public static int getWidthDevWindow() {
		return WIDTH_GAME_WINDOW;
	}

	public static int getHeightGameWindow() {
		return HEIGHT_GAME_WINDOW;
	}

	public static String getTitleGameWindow() {
		return TITLE_GAME_WINDOW;
	}
}

package pl.bol.game;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import pl.bol.engine.inputs.keyboard.KeyboardInput;
import pl.bol.engine.inputs.mouse.MouseInput;
import pl.tm24.patrykp.biblioteki.patryklib.Czas;

public class Window {
	private static int height;
	private static int width;
	private static boolean isGameRuning = false;
	private static Czas time = new Czas("Czas", 1);
	private static Game game;

	public Window(Game game) {
		this.game = game;
		this.height = game.getHeightGameWindow();
		this.width = game.getWidthGameWindow();
	}

	public static void createWindow(String title) {
		Display.setTitle(title);
		try {
			Display.setDisplayMode(new DisplayMode(width, height));
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
}

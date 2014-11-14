package pl.bol.game;

import org.lwjgl.input.Keyboard;

import pl.bol.engine.inputs.keyboard.KeyboardInput;

public class Game {
	public static final int WIDTH_GAME_WINDOW = 800;
	public static final int HEIGHT_GAME_WINDOW = 600;
	public static final String TITLE_GAME_WINDOW = "Battle of Legends";

	public Game() {

	}

	/**
	 * Getting the keys
	 */
	public void input() {
		if (KeyboardInput.getKeyDown(Keyboard.KEY_W)) {
			System.out.println("W");
		}

		if (KeyboardInput.getKeyUp(Keyboard.KEY_W)) {
			System.out.println("S");
		}
	}

	/**
	 * Render components
	 */
	public void render() {

	}

	public static int getWidthGameWindow() {
		return WIDTH_GAME_WINDOW;
	}

	public static int getHeightGameWindow() {
		return HEIGHT_GAME_WINDOW;
	}

	public static String getTitleGameWindow() {
		return TITLE_GAME_WINDOW;
	}
}

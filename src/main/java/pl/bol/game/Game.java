package pl.bol.game;

import org.lwjgl.input.Keyboard;

import pl.bol.engine.graphic.RenderUtil;
import pl.bol.engine.inputs.keyboard.KeyboardInput;
import pl.bol.engine.inputs.mouse.MouseInput;

public class Game {
	private final int WIDTH_GAME_WINDOW = 800;
	private final int HEIGHT_GAME_WINDOW = 600;
	private final String TITLE_GAME_WINDOW = "Battle of Legends";

	public void input() {
		if (KeyboardInput.getKeyDown(Keyboard.KEY_W))
			System.out.println("W");
		if (KeyboardInput.getKeyDown(Keyboard.KEY_S))
			System.out.println("S");
		if (KeyboardInput.getKeyDown(Keyboard.KEY_A))
			System.out.println("A");
		if (KeyboardInput.getKeyDown(Keyboard.KEY_D))
			System.out.println("D");

		if (MouseInput.getButtonDown(0))
			System.out.println("Selected at: " + MouseInput.getMousePosition());
		if (MouseInput.getButtonDown(1))
			Window.stopGame();
	}

	public void render() {
		RenderUtil.clearScreen();
	}

	public int getWidthGameWindow() {
		return WIDTH_GAME_WINDOW;
	}

	public int getHeightGameWindow() {
		return HEIGHT_GAME_WINDOW;
	}

	public String getTitleGameWindow() {
		return TITLE_GAME_WINDOW;
	}
}

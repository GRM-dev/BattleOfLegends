package pl.bol.game;

import org.lwjgl.input.Keyboard;

import pl.bol.devwindow.DevWindow;
import pl.bol.engine.graphic.RenderUtil;
import pl.bol.engine.inputs.keyboard.KeyboardInput;
import pl.bol.engine.inputs.mouse.MouseInput;
import pl.bol.game.filehandler.ResourcesLoader;

public class Game {
	private DevWindow devWindow;
	private Window window;

	public void initGame() {
		new ResourcesLoader(this);
		ResourcesLoader.getConfig();
		ResourcesLoader.setGameIcon();
	}

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

	public DevWindow getDevWindow() {
		return devWindow;
	}

	public void setDevWindow(DevWindow devWindow) {
		this.devWindow = devWindow;
	}

	public Window getWindow() {
		return window;
	}

	public void setWindow(Window window) {
		this.window = window;
	}
}

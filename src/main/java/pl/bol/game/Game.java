package pl.bol.game;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.logging.Level;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

import pl.bol.devwindow.DevWindow;
import pl.bol.engine.graphic.RenderUtil;
import pl.bol.engine.inputs.keyboard.KeyboardInput;
import pl.bol.engine.inputs.mouse.MouseInput;
import pl.bol.game.filehandler.BLog;
import pl.bol.game.filehandler.ResourcesLoader;
import pl.grm.bol.lib.FileOperation;

public class Game {
	private DevWindow devWindow;
	private Window window;
	private BLog bLog;

	public Game() {
		window = new Window(this);
		devWindow = new DevWindow(this);
		bLog = new BLog();
	}

	public void initGame() {
		try {
			FileOperation.readConfigFile(ResourcesLoader.class);
		} catch (IllegalArgumentException e) {
			bLog.log(Level.SEVERE, e.toString(), e);
		} catch (IllegalAccessException e) {
			bLog.log(Level.SEVERE, e.toString(), e);
		} catch (NoSuchFieldException e) {
			bLog.log(Level.SEVERE, e.toString(), e);
		} catch (SecurityException e) {
			bLog.log(Level.SEVERE, e.toString(), e);
		}
		try {
			ByteBuffer[] icons = new ByteBuffer[2];
			icons[0] = ResourcesLoader.loadIcon("gameIcon_16.png", 16, 16);
			icons[1] = ResourcesLoader.loadIcon("gameIcon_32.png", 32, 32);
			Display.setIcon(icons);
		} catch (IOException ex) {
			bLog.log(Level.SEVERE, ex.toString(), ex);
		}
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
			window.stopGame();
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

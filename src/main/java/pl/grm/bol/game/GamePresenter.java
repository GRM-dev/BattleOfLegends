package pl.grm.bol.game;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.logging.Level;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

import pl.grm.bol.engine.graphic.RenderUtil;
import pl.grm.bol.engine.inputs.keyboard.KeyboardInput;
import pl.grm.bol.engine.inputs.mouse.MouseInput;
import pl.grm.bol.filehandler.ResourcesLoader;
import pl.grm.bol.lib.BLog;
import pl.grm.bol.lib.FileOperation;

public class GamePresenter {
	private BLog		bLog;
	private GameWindow	gameWindow;
	
	public GamePresenter() {
		bLog = new BLog("game.ini");
		gameWindow = new GameWindow(this);
	}
	
	public void initGame() {
		try {
			FileOperation.readConfigFile("");
		}
		catch (IllegalArgumentException e) {
			bLog.log(Level.SEVERE, e.toString(), e);
		}
		catch (SecurityException e) {
			bLog.log(Level.SEVERE, e.toString(), e);
		}
		try {
			ByteBuffer[] icons = new ByteBuffer[2];
			icons[0] = ResourcesLoader.loadIcon("gameIcon_16.png", 16, 16);
			icons[1] = ResourcesLoader.loadIcon("gameIcon_32.png", 32, 32);
			Display.setIcon(icons);
		}
		catch (IOException ex) {
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
			gameWindow.stopGame();
	}
	
	public void render() {
		RenderUtil.clearScreen();
	}
	
	public GameWindow getGameWindow() {
		return gameWindow;
	}
	
	public void setGameWindow(GameWindow gameWindow) {
		this.gameWindow = gameWindow;
	}
	
	public BLog getbLog() {
		return bLog;
	}
	
	public void setbLog(BLog bLog) {
		this.bLog = bLog;
	}
}

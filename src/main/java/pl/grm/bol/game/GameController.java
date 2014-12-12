package pl.grm.bol.game;

import java.io.*;
import java.nio.*;
import java.util.logging.*;

import org.lwjgl.input.*;
import org.lwjgl.opengl.*;

import pl.grm.bol.engine.graphic.rendering.states.*;
import pl.grm.bol.lib.*;
import pl.tm24.patrykp.biblioteki.patryklib.*;

public class GameController implements GameUtil {

	private boolean isGameRuning = false;
	private GamePresenter gamePresenter;
	private Input input;
	private Czas time;
	private BLog logger;
	private static final long REFRESH_INTERVAL_TIME = 17;

	@Override
	public void render() {
		// TODO Auto-generated method stub

	}

	@Override
	public void input() {
		if (input.getKeyDown(Keyboard.KEY_W)) {
			System.out.println("W");
			setStateOfGame(StateOfGame.GAME_MENU);
		}
		if (input.getKeyDown(Keyboard.KEY_S)) {
			System.out.println("S");
			setStateOfGame(StateOfGame.GAME_LOADING);
		}
		if (input.getKeyDown(Keyboard.KEY_A)) {
			System.out.println("A");
			setStateOfGame(StateOfGame.GAME_RUNNING);
		}
		if (input.getKeyDown(Keyboard.KEY_D)) {
			System.out.println("D");
			setStateOfGame(StateOfGame.GAME_STOPPED);
		}

		if (input.getButtonDown(0))
			System.out.println("Selected at: " + input.getMousePosition());
		if (input.getButtonDown(1))
			stopGame();
		input.update();
	}

	@Override
	public void init() {
		try {
			FileOperation.readConfigFile("");
		}
		catch (IllegalArgumentException e) {
			logger.log(Level.SEVERE, e.toString(), e);
		}
		catch (SecurityException e) {
			logger.log(Level.SEVERE, e.toString(), e);
		}

		try {
			ByteBuffer[] icons = new ByteBuffer[2];
			icons[0] = ResourcesLoader.loadIcon("gameIcon_16.png", 16, 16);
			icons[1] = ResourcesLoader.loadIcon("gameIcon_32.png", 32, 32);
			Display.setIcon(icons);
		}
		catch (IOException ex) {
			logger.log(Level.SEVERE, ex.toString(), ex);
		}
	}

	public void startGame() {
		if (!isGameRuning()) {
			setGameRuning(true);
			Thread loopGame = new Thread(new Runnable() {

				@Override
				public void run() {
					gamePresenter.createWindow();
					loopGame();
				}
			});
			loopGame.start();
		} else {
			logger.info("Game is running!!");
		}
	}

	public void stopGame() {
		if (isGameRuning())
			setGameRuning(false);
		else
			logger.info("Game is not running!!");
	}

	private void destroyWindow() {
		Display.destroy();
		System.exit(0);
	}

	private synchronized void loopGame() {
		while (!Display.isCloseRequested() && isGameRuning()) {
			time.aktualizuj();
			input();
			gamePresenter.render();
			// gamePresenter.getGameWindow().getRenderUtil().clearScreen();
			Display.update();
			try {
				Thread.sleep(REFRESH_INTERVAL_TIME);
			}
			catch (InterruptedException e) {
				logger.log(Level.SEVERE, e.toString(), e);
			}
		}
		destroyWindow();
	}

	public boolean isGameRuning() {
		return isGameRuning;
	}

	public void setGameRuning(boolean isGameRuning) {
		this.isGameRuning = isGameRuning;
	}

	public GamePresenter getGamePresenter() {
		return gamePresenter;
	}

	public void setGamePresenter(GamePresenter gamePresenter) {
		this.gamePresenter = gamePresenter;
	}

	public Input getInput() {
		return input;
	}

	public void setInput(Input input) {
		this.input = input;
	}

	public Czas getTime() {
		return time;
	}

	public void setTime(Czas time) {
		this.time = time;
	}

	public BLog getLogger() {
		return logger;
	}

	public void setLogger(BLog logger) {
		this.logger = logger;
	}

	private void setStateOfGame(StateOfGame state) {
		gamePresenter.setStateOfGame(state);
	}
}

package pl.grm.bol.game;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.logging.Level;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

import pl.grm.bol.engine.graphic.rendering.states.StateOfGame;
import pl.grm.bol.engine.inputs.Input;
import pl.grm.bol.filehandler.ResourcesLoader;
import pl.grm.bol.lib.BLog;
import pl.grm.bol.lib.FileOperation;
import pl.tm24.patrykp.biblioteki.patryklib.Czas;

public class GameController {
	private boolean isGameRuning = false;
	private GamePresenter gamePresenter;
	private Input input;
	private Czas time;
	private StateOfGame stateOfGame;
	private BLog logger;
	private static final long REFRESH_INTERVAL_TIME = 17;

	public void initGame() {
		try {
			FileOperation.readConfigFile("");
		} catch (IllegalArgumentException e) {
			logger.log(Level.SEVERE, e.toString(), e);
		} catch (SecurityException e) {
			logger.log(Level.SEVERE, e.toString(), e);
		}

		try {
			ByteBuffer[] icons = new ByteBuffer[2];
			icons[0] = ResourcesLoader.loadIcon("gameIcon_16.png", 16, 16);
			icons[1] = ResourcesLoader.loadIcon("gameIcon_32.png", 32, 32);
			Display.setIcon(icons);
		} catch (IOException ex) {
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

			inputUpdate();
			gamePresenter.renderState(stateOfGame);
			// gamePresenter.getGameWindow().getMesh().draw();
			// gamePresenter.getGameWindow().getRenderUtil().clearScreen();
			Display.update();

			try {
				Thread.sleep(17);
			} catch (InterruptedException e) {
				logger.log(Level.SEVERE, e.toString(), e);
			}
		}
		destroyWindow();
	}

	public void inputUpdate() {
		if (input.getKeyDown(Keyboard.KEY_W)) {
			System.out.println("W");
			stateOfGame = StateOfGame.GAME_MENU;
		}
		if (input.getKeyDown(Keyboard.KEY_S))
			System.out.println("S");
		if (input.getKeyDown(Keyboard.KEY_A))
			System.out.println("A");
		if (input.getKeyDown(Keyboard.KEY_D))
			System.out.println("D");

		if (input.getButtonDown(0))
			System.out.println("Selected at: " + input.getMousePosition());
		if (input.getButtonDown(1))
			stopGame();
		input.update();
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

	public StateOfGame getStateOfGame() {
		return stateOfGame;
	}

	public void setStateOfGame(StateOfGame stateOfGame) {
		this.stateOfGame = stateOfGame;
	}

	public BLog getLogger() {
		return logger;
	}

	public void setLogger(BLog logger) {
		this.logger = logger;
	}
}

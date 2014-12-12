package pl.grm.bol.game;

import java.util.logging.*;

import org.lwjgl.*;
import org.lwjgl.opengl.*;

import pl.grm.bol.engine.graphic.*;
import pl.grm.bol.engine.graphic.rendering.states.*;
import pl.grm.bol.lib.*;

public class GamePresenter implements GameUtil {

	private GameController gameController;
	private BLog logger;
	private static int WIDTH_GAME_WINDOW = 1900;
	private static int HEIGHT_GAME_WINDOW = 1080;
	private static String TITLE_GAME_WINDOW = "Battle of Legends";
	private StateUtil stateUtil;
	private RenderUtil renderUtil;
	private StateOfGame stateOfGame;

	public GamePresenter(BLog bLog, GameController gameController) {
		this.logger = bLog;
		this.gameController = gameController;
	}

	@Override
	public void render() {
		stateOfGame.getState().render();
	}

	@Override
	public void input() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init() {
		stateUtil = new StateUtil(this);
		stateUtil.init();
	}

	public void createWindow() {
		Display.setTitle(TITLE_GAME_WINDOW);
		try {
			Display.setDisplayMode(new DisplayMode(WIDTH_GAME_WINDOW,
					HEIGHT_GAME_WINDOW));
			Display.create();
		}
		catch (LWJGLException e) {
			logger.log(Level.SEVERE, e.toString(), e);
		}
	}

	public void setBLog(BLog bLog) {
		this.logger = bLog;
	}

	public BLog getBLog() {
		return logger;
	}

	public void setGameController(GameController gameController) {
		this.gameController = gameController;
	}

	public void stopGame() {
		gameController.stopGame();
	}

	public StateUtil getStateUtil() {
		return stateUtil;
	}

	public void setStateUtil(StateUtil stateUtil) {
		this.stateUtil = stateUtil;
	}

	public RenderUtil getRenderUtil() {
		return renderUtil;
	}

	public void setRenderUtil(RenderUtil renderUtil) {
		this.renderUtil = renderUtil;
	}

	public StateOfGame getStateOfGame() {
		return stateOfGame;
	}

	public void setStateOfGame(StateOfGame stateOfGame) {
		this.stateOfGame = stateOfGame;
	}
}

package pl.grm.bol.game;

import pl.grm.bol.engine.graphic.rendering.states.*;
import pl.grm.bol.lib.*;
import pl.tm24.patrykp.biblioteki.patryklib.*;

public class GameFactory {

	private GamePresenter gamePresenter;
	private GameController gameController;
	private BLog logger;

	private StateOfGame stateOfGame;
	private Input input;
	private Czas time;

	public GameFactory(boolean server) {
		logger = new BLog("game.log");
	}

	public void createGame() {
		gameController = new GameController();
		gamePresenter = new GamePresenter(logger, gameController);
		stateOfGame = StateOfGame.GAME_LOADING;
		input = new Input();
		time = new Czas("Czas", 1);
		setupController();
		setupPresenter();
		gameController.init();
		gamePresenter.init();
		System.gc();
	}

	private void setupController() {
		gameController.setLogger(logger);
		gameController.setTime(time);
		gameController.setInput(input);
		gameController.setGamePresenter(gamePresenter);
	}

	private void setupPresenter() {
		gamePresenter.setStateOfGame(stateOfGame);
	}

	public GameController getGameController() {
		return gameController;
	}
}

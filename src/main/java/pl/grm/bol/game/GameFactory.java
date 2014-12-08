package pl.grm.bol.game;

import pl.grm.bol.engine.graphic.rendering.states.StateOfGame;
import pl.grm.bol.engine.inputs.Input;
import pl.grm.bol.lib.BLog;
import pl.tm24.patrykp.biblioteki.patryklib.Czas;

public class GameFactory {
	private GamePresenter gamePresenter;
	private BLog logger;
	private GameController gameController;
	private StateOfGame stateOfGame;
	private Input input;
	private Czas time;

	public GameFactory(boolean server) {
		logger = new BLog("game.log");
	}

	public void createGame() {
		gameController = new GameController();
		gamePresenter = new GamePresenter(logger, gameController);
		stateOfGame = StateOfGame.LOADING_GAME;
		input = new Input();
		time = new Czas("Czas", 1);
		setupController();
	}

	private void setupController() {
		gameController.setLogger(logger);
		gameController.setTime(time);
		gameController.setInput(input);
		gameController.setStateOfGame(stateOfGame);
		gameController.setGamePresenter(gamePresenter);
	}

	public GameController getGameController() {
		return gameController;
	}
}

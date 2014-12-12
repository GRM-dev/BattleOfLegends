package pl.grm.bol.game;

import pl.grm.bol.engine.graphic.rendering.states.*;
import pl.grm.bol.lib.*;
import pl.tm24.patrykp.biblioteki.patryklib.*;
import scala.collection.mutable.*;

public class GameFactory {

	private GamePresenter gamePresenter;
	private GameController gameController;
	private BLog logger;
	private HashMap<String, IStateOfGame> states;

	private StateOfGame stateOfGame;
	private Input input;
	private Czas time;
	private StateUtil stateUtil;

	public GameFactory(boolean server) {
		logger = new BLog("game.log");
	}

	public void createGame() {
		gameController = new GameController();
		gamePresenter = new GamePresenter(logger, gameController);
		input = new Input();
		time = new Czas("Czas", 1);
		stateUtil = new StateUtil(gamePresenter);
		states = new HashMap<String, IStateOfGame>();
		setupStates();
		setupController();
		setupPresenter();
		gameController.init();
		gamePresenter.init();
	}

	private void setupController() {
		gameController.setLogger(logger);
		gameController.setTime(time);
		gameController.setInput(input);
		gameController.setGamePresenter(gamePresenter);
		GameController.setStates(states);
		gameController.setStateOfGame(stateOfGame);
	}

	private void setupPresenter() {
		gamePresenter.setStateUtil(stateUtil);
	}

	private void setupStates() {
		stateOfGame = StateOfGame.GAME_LOADING;
		for (StateOfGame state : StateOfGame.values()) {
			IStateOfGame iStateOfGame = state.createState();
			iStateOfGame.setPresenter(gamePresenter);
			iStateOfGame.setUtils(gamePresenter.getStateUtil());
			states.put(state.getName(), iStateOfGame);
		}
	}

	public GameController getGameController() {
		return gameController;
	}
}

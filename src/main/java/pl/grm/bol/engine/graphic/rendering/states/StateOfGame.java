package pl.grm.bol.engine.graphic.rendering.states;

import pl.grm.bol.game.*;
import scala.collection.mutable.*;

public enum StateOfGame {
	GAME_LOADING(0, "Loading game", GameLoading.class),
	GAME_RUNNING(1, "Running game", GameRunning.class),
	GAME_STOPPED(2, "Stopped game", GamePaused.class),
	GAME_MENU(3, "Menu game", GameMenu.class);

	private int ID;
	private String name;
	private Class<? extends IStateOfGame> state;

	StateOfGame(int ID, String name, Class<? extends IStateOfGame> state) {
		this.ID = ID;
		this.name = name;
		this.state = state;
	}

	public IStateOfGame createState() {
		try {
			return state.newInstance();
		}
		catch (InstantiationException e) {
			e.printStackTrace();
		}
		catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	public int getID() {
		return ID;
	}

	public String getName() {
		return name;
	}

	public static IStateOfGame getIState(StateOfGame stateOfGame) {
		HashMap<String, IStateOfGame> states = GameController.getStates();
		if (states.contains(stateOfGame.getName())) {
			IStateOfGame iStateOfGame = (IStateOfGame) states.get(stateOfGame
					.getName());
			return iStateOfGame;
		} else
			return null;

	}
}

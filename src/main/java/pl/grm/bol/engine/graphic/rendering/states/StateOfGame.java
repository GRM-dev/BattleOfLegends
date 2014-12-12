package pl.grm.bol.engine.graphic.rendering.states;

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

	public IStateOfGame getState() {
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
}

package pl.grm.bol.engine.graphic.rendering.states;

import static org.lwjgl.opengl.GL11.*;
import pl.grm.bol.game.*;

public class GamePaused implements IStateOfGame {

	private StateUtil stateUtil;
	private GamePresenter gamePresenter;

	@Override
	public void render() {
		glRectf(-600, -800, 600, 800);
	}

	@Override
	public void setPresenter(GamePresenter gamePresenter) {
		this.gamePresenter = gamePresenter;
	}

	@Override
	public void setUtils(StateUtil stateUtil) {
		this.stateUtil = stateUtil;
	}

	@Override
	public void input() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}
}

package pl.grm.bol.engine.graphic.rendering.states;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.util.vector.*;

import pl.grm.bol.game.*;

public class StateUtil implements GameUtil {

	private GamePresenter gamePresenter;

	public StateUtil(GamePresenter gamePresenter) {
		this.gamePresenter = gamePresenter;
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub

	}

	@Override
	public void input() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init() {

	}

	public void setColor3f(ColorLevel level, Vector3f color) {
		switch (level) {
			case BACKGROUND :
				glColor3f(color.getX(), color.getY(), color.getZ());
				break;
			default :
				gamePresenter.getBLog().info("ERROR: GameRunning, setColor");
				break;
		}
	}

	public void setColor4f(ColorLevel level, Vector3f color, float alfa) {
		switch (level) {
			case BACKGROUND :
				glColor4f(color.getX(), color.getY(), color.getZ(), alfa);
				break;
			default :
				gamePresenter.getBLog().info("ERROR: GameRunning, setColor");
				break;
		}
	}
}

package pl.grm.bol.engine.graphic.rendering.states;

import pl.grm.bol.game.*;

public interface IStateOfGame extends GameUtil {

	public void setPresenter(GamePresenter gamePresenter);
	public void setUtils(StateUtil stateUtil);
}

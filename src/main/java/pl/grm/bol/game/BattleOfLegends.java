package pl.grm.bol.game;

import pl.grm.bol.engine.graphic.RenderUtil;

public class BattleOfLegends {
	private static GamePresenter gamePresenter;

	public static void main(String[] args) {
		gamePresenter = new GamePresenter();

		gamePresenter.initGame();
		gamePresenter.getGameWindow().createWindow(
				gamePresenter.getGameWindow().getTitleGameWindow());

		RenderUtil.initGraphic();
		gamePresenter.getGameWindow().startGame();
	}
}

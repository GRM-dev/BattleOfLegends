package pl.grm.bol.game;

import pl.grm.bol.devwindow.WindowDev;
import pl.grm.bol.engine.graphic.RenderUtil;

public class BattleOfLegends {
	private static Presenter presenter;
	private static WindowDev windowDev;

	public static void main(String[] args) {
		presenter = new Presenter();
		windowDev = new WindowDev();

		presenter.initGame();
		presenter.getGameWindow().createWindow(
				presenter.getGameWindow().getTitleGameWindow());

		windowDev.main(args);

		RenderUtil.initGraphic();
		presenter.getGameWindow().startGame();
	}
}

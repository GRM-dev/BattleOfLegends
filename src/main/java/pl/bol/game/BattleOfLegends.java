package pl.bol.game;

import pl.bol.devwindow.DevWindow;
import pl.bol.engine.graphic.RenderUtil;

public class BattleOfLegends {
	private static Game game;

	public static void main(String[] args) {
		game = new Game();

		Window window = new Window(game);
		DevWindow devWindow = new DevWindow(game);

		game.initGame();

		window.createWindow(game.getWindow().getTitleGameWindow());
		RenderUtil.initGraphic();
		DevWindow.createDevWindow(game.getDevWindow().getTitleDevWindow());
		window.startGame();
	}
}

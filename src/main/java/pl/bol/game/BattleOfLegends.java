package pl.bol.game;

import pl.bol.devwindow.DevWindow;
import pl.bol.engine.graphic.RenderUtil;

public class BattleOfLegends {
	private static Game game;
	private static Window window;
	private static DevWindow devWindow;

	public static void main(String[] args) {
		game = new Game();
		window = new Window(game);
		devWindow = new DevWindow(game);

		game.initGame();
		// devWindow.createDevWindow(game.getDevWindow().getTitleDevWindow());
		devWindow.createDevWindow();
		window.createWindow(game.getWindow().getTitleGameWindow());

		RenderUtil.initGraphic();
		window.startGame();
	}
}

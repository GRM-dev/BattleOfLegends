package pl.bol.game;

import pl.bol.engine.graphic.RenderUtil;

public class BattleOfLegends {
	private static Game game;

	public static void main(String[] args) {
		game = new Game();

		Window window = new Window(game);

		window.createWindow(game.getTitleGameWindow());
		RenderUtil.initGraphic();
		window.startGame();
	}
}

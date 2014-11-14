package pl.bol.game;

public class BattleOfLegends {
	public static void main(String[] args) {
		Window window = new Window(Game.WIDTH_GAME_WINDOW,
				Game.HEIGHT_GAME_WINDOW);
		window.createWindow(Game.TITLE_GAME_WINDOW);
		window.startGame();
	}
}

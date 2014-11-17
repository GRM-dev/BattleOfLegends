package pl.grm.bol.game;

import pl.grm.bol.devwindow.BattleOfLegendsDev;
import pl.grm.bol.engine.graphic.RenderUtil;

public class BattleOfLegends {
	private static GamePresenter	gamePresenter;
	
	public static void main(String[] args) {
		if (args.length == 1) {
			gamePresenter = new GamePresenter();
			
			gamePresenter.initGame();
			gamePresenter.getGameWindow().createWindow();
			
			RenderUtil.initGraphic();
			gamePresenter.getGameWindow().startGame();
		} else if (args.length == 2) {
			BattleOfLegendsDev dev = new BattleOfLegendsDev();
			dev.start();
		} else {
			System.out.println("Bad params!");
		}
	}
}

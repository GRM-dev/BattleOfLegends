package pl.grm.bol.game;

import javax.swing.JOptionPane;

import pl.grm.bol.devwindow.BattleOfLegendsDev;
import pl.grm.bol.engine.graphic.RenderUtil;

public class BattleOfLegends {
	private static GamePresenter	gamePresenter;
	
	public static void main(String[] args) {
		boolean server = false;
		if (args.length == 1) {
			if (args[0] == "runWithServerToConnect") {
				server = true;
			}
			gamePresenter = new GamePresenter(server);
			
			gamePresenter.initGame();
			gamePresenter.getGameWindow().createWindow();
			RenderUtil.initGraphic();
			gamePresenter.getGameWindow().startGame();
		} else if (args.length == 2) {
			BattleOfLegendsDev dev = new BattleOfLegendsDev();
			dev.start();
		} else {
			JOptionPane.showMessageDialog(null, "The program has been started the wrong way!",
					"Error!", JOptionPane.ERROR_MESSAGE);
			System.out.println("Bad params!");
		}
	}
}

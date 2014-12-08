package pl.grm.bol.game;

import javax.swing.JOptionPane;

import pl.grm.bol.devwindow.BattleOfLegendsDev;

public class BattleOfLegends {
	private static GameFactory gameFactory;
	private static GameController gameController;

	public static void main(String[] args) {
		boolean server = false;
		if (args.length == 1) {
			if (args[0] == "runWithServerToConnect") {
				server = true;
			}
			gameFactory = new GameFactory(server);
			// gamePresenter.getGameWindow().getRenderUtil().initGraphic();
			// gamePresenter.createMesh();
			gameFactory.createGame();
			gameController = gameFactory.getGameController();
			gameController.startGame();
		} else if (args.length == 2) {
			BattleOfLegendsDev dev = new BattleOfLegendsDev();
			dev.start();
		} else {
			JOptionPane.showMessageDialog(null,
					"The program has been started the wrong way!", "Error!",
					JOptionPane.ERROR_MESSAGE);
			System.out.println("Bad params!");
		}
	}
}

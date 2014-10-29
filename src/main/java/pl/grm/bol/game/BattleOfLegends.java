package pl.grm.bol.game;

import pl.grm.bol.engine.core.CoreEngine;

public class BattleOfLegends {
	public static void main(String[] args) {
		CoreEngine coreEngine = new CoreEngine(800, 600, new TestGame());
		coreEngine.createWindow("BOL");
		coreEngine.startGame();
	}
}

package pl.bol.devwindow.panels;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;

import pl.bol.game.Game;

public class LeftPanel extends JComponent {
	private Game game;

	public LeftPanel(Game game) {
		this.game = game;
		setLayout(null);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.setColor(Color.blue);
		g.fillRect(0, 0, game.getDevWindow().getWidthDevWindow(), game
				.getDevWindow().getHeightDevWindow());

		g.setColor(Color.red);
		g.fillOval(2, 10, 100, 100);

		g.setColor(Color.white);
		g.fillOval(50, 10, 100, 100);
	}
}

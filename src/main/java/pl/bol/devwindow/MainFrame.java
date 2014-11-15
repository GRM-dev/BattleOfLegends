package pl.bol.devwindow;

import javax.swing.JFrame;
import javax.swing.JSplitPane;

import pl.bol.devwindow.panels.LeftPanel;
import pl.bol.devwindow.panels.RightPanel;
import pl.bol.game.Game;

public class MainFrame extends JFrame {
	private Game game;
	private RightPanel rightPanel;
	private LeftPanel leftPanel;
	private JSplitPane jSplitPane;

	public MainFrame(Game game) {
		this.game = game;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		setTitle(game.getDevWindow().getTitleDevWindow());
		getContentPane().setLayout(null);

		rightPanel = new RightPanel(game);
		leftPanel = new LeftPanel(game);
		jSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel,
				rightPanel);
		setContentPane(jSplitPane);
	}
}

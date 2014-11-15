package pl.bol.devwindow;

import javax.swing.SwingUtilities;

import pl.bol.game.Game;

public class DevWindow {
	private static Game game;
	private static MainFrame jFrame;
	private final static String TITLE_DEV_WINDOW = "Dev window";
	private final static int WIDTH_DEV_WINDOW = 800;
	private final static int HEIGHT_DEV_WINDOW = 600;

	public DevWindow(Game game) {
		this.game = game;
	}

	public static void createDevWindow(final String title) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				jFrame = new MainFrame(game);
				jFrame.setVisible(true);
			}
		});
	}

	public static String getTitleDevWindow() {
		return TITLE_DEV_WINDOW;
	}

	public static int getWidthDevWindow() {
		return WIDTH_DEV_WINDOW;
	}

	public static int getHeightDevWindow() {
		return HEIGHT_DEV_WINDOW;
	}
}

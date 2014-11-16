package pl.grm.bol.devwindow;

import java.awt.EventQueue;

public class BattleOfLegendsDev {
	private static DevPresenter devPresenter;

	public static void main(String[] args) {
		devPresenter = new DevPresenter();

		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				try {
					DevWindow devWindow = new DevWindow(devPresenter);
					devWindow.getFrame().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}

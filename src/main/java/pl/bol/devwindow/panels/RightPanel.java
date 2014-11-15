package pl.bol.devwindow.panels;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import pl.bol.game.Game;

public class RightPanel extends JPanel {
	private Game game;

	public RightPanel(Game game) {
		this.game = game;
		setBackground(SystemColor.inactiveCaption);
		setLayout(null);

		JButton btnKliknij = new JButton("Kliknij!!");
		btnKliknij.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("lol");
			}
		});
		btnKliknij.setBackground(SystemColor.textHighlight);
		btnKliknij.setForeground(SystemColor.controlText);
		btnKliknij.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnKliknij.setBounds(10, 359, 89, 23);
		add(btnKliknij);
	}
}

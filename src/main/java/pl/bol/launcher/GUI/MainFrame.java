package pl.bol.launcher.GUI;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private Container container;
    private JPanel buttons;
    private JPanel textAreas;
    private JPanel mainPanel;
    private Window presenter;

    public MainFrame(Window presenter) {
        this.presenter = presenter;
        container = getContentPane();
        mainPanel = new JPanel();
        container.add(mainPanel);
        mainPanel.setLayout(new BorderLayout());

        buttons = new Buttons(mainPanel);
        mainPanel.add(buttons, BorderLayout.SOUTH);

        textAreas = new TextAreas(mainPanel);
    }
}

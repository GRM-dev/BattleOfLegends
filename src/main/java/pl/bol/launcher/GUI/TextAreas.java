package pl.bol.launcher.GUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class TextAreas extends JPanel {
    private final ArrayList<JTextArea> textAreas;
    private JPanel jPanel;

    public TextAreas(JPanel jPanel) {
        this.jPanel = jPanel;
        textAreas = new ArrayList<JTextArea>();
    }

    public void draw() {
        jPanel.add(textAreas.get(0), BorderLayout.EAST);
    }

    public void initTextAreas() {
        textAreas.add(new JTextArea());
    }

    public JTextArea getTextAreas(int i) {
        return textAreas.get(i);
    }
}

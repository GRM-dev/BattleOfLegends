package pl.bol.launcher.GUI;

import pl.bol.engine.core.CoreEngine;
import pl.bol.engine.core.Game;
import pl.bol.game.TestGame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Buttons extends JPanel {
    private ArrayList<JButton> buttons;
    private JPanel jPanel;
    private CoreEngine coreEngine;

    public Buttons(JPanel jPanel) {
        buttons = new ArrayList<JButton>();

        this.jPanel = jPanel;
        setupButtons();
        for(JButton button : buttons) {
            this.add(button);
        }
    }

    private JButton addButton(String name) {
        JButton button = new JButton(name);
        buttons.add(button);
        return button;
    }

    private void setupButtons() {
        addButton("Play!").addActionListener(onButtonPress(1));
        addButton("Cancel").addActionListener(onButtonPress(2));
    }

    public ActionListener onButtonPress(int name) {
        switch(name) {
            case 1: return onOKPress();
            case 2: return onCANCELPress();
            default: return null;
        }
    }

    public ActionListener onOKPress() {
        ActionListener aListener = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                coreEngine = new CoreEngine(800, 600, new TestGame());
                coreEngine.createWindow("BOL");
                coreEngine.startGame();
            }
        };
        return aListener;
    }

    public ActionListener onCANCELPress() {
        ActionListener aListener = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        };
        return aListener;
    }
}

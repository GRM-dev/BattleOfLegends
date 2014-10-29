package pl.bol.launcher.GUI;

import javax.swing.*;
import java.awt.*;

public class Window {
    private int widthScreen;
    private int heightScreen;
    private int widthFrame;
    private int heightFrame;
    private Dimension screenSize;
    private MainFrame frame;
    private String name;
    private int width;
    private int height;
    private boolean proportionsWindow;
    private boolean visibleWindow;

    public Window(int width, int height, String name, boolean visible, boolean proportions) {
        this.name = name;
        this.proportionsWindow = proportions;
        this.visibleWindow = visible;
        this.width = width;
        this.height = height;
    }

    public void setupFrame() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                    frame = new MainFrame(Window.this);
                    if(proportionsWindow)
                        setupBounds();
                    if(!proportionsWindow)
                        frame.setSize(width, height);
                    frame.setTitle(name);
                    frame.pack();
                    frame.setResizable(true);
                    frame.setVisible(visibleWindow);
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            private void setupBounds() {
                heightScreen = (int)screenSize.getHeight();
                widthScreen = (int)screenSize.getWidth();
                widthFrame = widthScreen / 2 - widthScreen / 30;
                heightFrame = widthFrame * 3 / 4;
                frame.setBounds(widthScreen / 30, heightScreen / 2
                        - heightFrame / 2, 0, 0);
            }
        });
    }
}

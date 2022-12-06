package view;

import controllers.ControllerSnakeGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ViewMenuGame{
    public ViewMenuGame() {
        JFrame frame = new JFrame();
        frame.setTitle("Snake Game Menu");
        frame.setSize(new Dimension(700, 500));

        Dimension windowSize = frame.getSize();
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Point centerPoint = ge.getCenterPoint();
        int dx = centerPoint.x - windowSize.width / 2;
        int dy = centerPoint.y - windowSize.height / 2;
        frame.setLocation(dx,dy);

        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("A is closing");
                try {
                    ControllerSnakeGame controller = new ControllerSnakeGame(50);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }

            @Override
            public void windowClosed(WindowEvent e) {
                System.out.println("A has closed");
            }
        });
    }
}

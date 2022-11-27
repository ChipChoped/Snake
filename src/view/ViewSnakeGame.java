package view;

import controllers.AbstractController;
import games.SnakeGame;
import view.PanelSnakeGame;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class ViewSnakeGame implements Observer {
    protected AbstractController controller;
    protected PanelSnakeGame panelSnakeGame;
    JFrame frame = new JFrame();

    public ViewSnakeGame(Observable obs, AbstractController controller, PanelSnakeGame panelSnakeGame) {
        obs.addObserver(this);
        this.controller = controller;
        this.panelSnakeGame = panelSnakeGame;

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Snake");
        frame.setSize(new Dimension(panelSnakeGame.getSizeX() * 40, panelSnakeGame.getSizeY() * 40));
        frame.setVisible(true);

        Dimension windowSize = frame.getSize();
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Point centerPoint = ge.getCenterPoint();
        int dx = centerPoint.x - windowSize.width / 2;
        int dy = centerPoint.y - windowSize.width / 2;
        frame.setLocation(dx,dy);

        try {
            frame.add(this.panelSnakeGame);
        }
        catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
    
    public void update(Observable observable, Object o) {
        SnakeGame game = (SnakeGame) observable;
        this.panelSnakeGame.updateInfoGame(game.getFeaturesSnakes(), game.getItems());
        frame.repaint();
    }
}

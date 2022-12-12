package view;

import controllers.ControllerSnakeGame;
import games.SnakeGame;
import utils.AgentAction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;
import java.util.Observer;

public class ViewSnakeGame implements Observer, KeyListener {
    protected ControllerSnakeGame controller;
    protected PanelSnakeGame panelSnakeGame;
    JFrame frame = new JFrame();

    public ViewSnakeGame(Observable obs, ControllerSnakeGame controller, PanelSnakeGame panelSnakeGame) {
        obs.addObserver(this);
        this.controller = controller;
        this.panelSnakeGame = panelSnakeGame;

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Snake");
        frame.setSize(new Dimension(panelSnakeGame.getSizeX() * 40, panelSnakeGame.getSizeY() * 40));
        frame.setVisible(true);
        frame.addKeyListener(this);
        frame.requestFocus();

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

        this.controller.getGame().initializeGame();
    }

    public void keyTyped(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch(keyCode) {
            case KeyEvent.VK_UP:
                System.out.println("UP!");
                    controller.getGame().setP1NextMove(AgentAction.MOVE_UP);
                break;
            case KeyEvent.VK_DOWN:
                System.out.println("DOWN!");
                controller.getGame().setP1NextMove(AgentAction.MOVE_DOWN);
                break;
            case KeyEvent.VK_LEFT:
                System.out.println("LEFT!");
                controller.getGame().setP1NextMove(AgentAction.MOVE_LEFT);
                break;
            case KeyEvent.VK_RIGHT:
                System.out.println("RIGHT!");
                controller.getGame().setP1NextMove(AgentAction.MOVE_RIGHT);
                break;
            case KeyEvent.VK_Z:
                System.out.println("UP!");
                controller.getGame().setP2NextMove(AgentAction.MOVE_UP);
                break;
            case KeyEvent.VK_S:
                System.out.println("DOWN!");
                controller.getGame().setP2NextMove(AgentAction.MOVE_DOWN);
                break;
            case KeyEvent.VK_Q:
                System.out.println("LEFT!");
                controller.getGame().setP2NextMove(AgentAction.MOVE_LEFT);
                break;
            case KeyEvent.VK_D:
                System.out.println("RIGHT!");
                controller.getGame().setP2NextMove(AgentAction.MOVE_RIGHT);
                break;
        }
    }

    public void keyPressed(KeyEvent keyEvent) {}
    public void keyReleased(KeyEvent keyEvent) {}

    public void update(Observable observable, Object o) {
        SnakeGame game = (SnakeGame) observable;
        this.panelSnakeGame.updateInfoGame(game.getFeaturesSnakes(), game.getFeaturesItems());
        frame.repaint();
        frame.requestFocus();
    }
}

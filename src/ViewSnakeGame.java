import model.InputMap;
import view.PanelSnakeGame;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class ViewSnakeGame implements Observer {
    protected AbstractController controller;

    public ViewSnakeGame(Observable obs, AbstractController controller) {
        obs.addObserver(this);
        this.controller = controller;

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Snake");
        frame.setSize(new Dimension(700, 700));
        frame.setVisible(true);

        Dimension windowSize = frame.getSize();
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Point centerPoint = ge.getCenterPoint();
        int dx = centerPoint.x - windowSize.width /2;
        int dy = centerPoint.y - windowSize.height / 2 - 350;
        frame.setLocation(dx,dy);

        try {
            InputMap map = new InputMap("layout/arena.lay");
            PanelSnakeGame panelSnakeGame = new PanelSnakeGame(map.getSizeX(), map.getSizeY(), map.get_walls(), map.getStart_snakes(), map.getStart_items());
            frame.add(panelSnakeGame);
        }
        catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    public void update(Observable observable, Object o) {
        SimpleGame game = (SimpleGame) o;
    }
}

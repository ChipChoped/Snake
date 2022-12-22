package view;

import controllers.AbstractController;
import controllers.ControllerSnakeGame;
import games.SnakeGame;
import states.EndState;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class ViewCommand implements Observer {
    protected ControllerSnakeGame controller;
    protected JLabel turnNumberLabel = new JLabel("Turn : 0", JLabel.CENTER);

    public ViewCommand(Observable obs, ControllerSnakeGame controller) {
        obs.addObserver(this);
        this.controller = controller;

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Snake Command Panel");
        frame.setSize(new Dimension(700, 330));

        Dimension windowSize = frame.getSize();
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Point centerPoint = ge.getCenterPoint();
        int dx = centerPoint.x - windowSize.width /2;
        int dy = centerPoint.y - windowSize.height / 2 + 350;
        frame.setLocation(dx,dy);

        JPanel mainPanel = new JPanel();
        JPanel gameCommandsPanel = new JPanel();
        JPanel turnCommandPanel = new JPanel();
        JPanel turnSliderPanel = new JPanel();
        JPanel gameInfoPanel = new JPanel();
        JPanel playersPanel = new JPanel();
        JPanel player1Panel = new JPanel();
        JPanel lives1Panel = new JPanel();

        mainPanel.setLayout(new GridLayout(2, 1));
        gameCommandsPanel.setLayout(new GridLayout(1, 4));
        turnCommandPanel.setLayout(new GridLayout(1, 2));
        turnSliderPanel.setLayout(new GridLayout(2, 1));
        gameInfoPanel.setLayout(new GridLayout(2, 1));
        playersPanel.setLayout(new GridLayout(1, 2));
        player1Panel.setLayout(new GridLayout(2, 1));

        Icon restartIcon = new ImageIcon("icons/icon_restart.png");
        Icon playIcon = new ImageIcon("icons/icon_play.png");
        Icon stepIcon = new ImageIcon("icons/icon_step.png");
        Icon pauseIcon = new ImageIcon("icons/icon_pause.png");

        JButton restartButton = new JButton(restartIcon);
        JButton playButton = new JButton(playIcon);
        JButton stepButton = new JButton(stepIcon);
        JButton pauseButton = new JButton(pauseIcon);

        restartButton.setEnabled(false);
        pauseButton.setEnabled(false);

        restartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                restartButton.setEnabled(false);
                pauseButton.setEnabled(false);
                playButton.setEnabled(true);
                stepButton.setEnabled(true);

                controller.restart();
            }
        });

        playButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                playButton.setEnabled(false);
                stepButton.setEnabled(false);
                restartButton.setEnabled(true);
                pauseButton.setEnabled(true);

                controller.play();
            }
        });

        stepButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                restartButton.setEnabled(true);
                controller.step();

                if (controller.getState() instanceof EndState) {
                    playButton.setEnabled(false);
                    stepButton.setEnabled(false);
                }
            }
        });

        pauseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                restartButton.setEnabled(true);
                pauseButton.setEnabled(false);
                playButton.setEnabled(true);
                stepButton.setEnabled(true);

                controller.pause();
            }
        });

        gameCommandsPanel.add(restartButton);
        gameCommandsPanel.add(playButton);
        gameCommandsPanel.add(stepButton);
        gameCommandsPanel.add(pauseButton);

        JLabel turnSliderLabel = new JLabel("Number of turns per second", JLabel.CENTER);

        JSlider turnSlider = new JSlider();
        turnSlider.setMinimum(1);
        turnSlider.setMaximum(10);
        turnSlider.setMajorTickSpacing(1);
        turnSlider.setPaintTicks(true);
        turnSlider.setPaintLabels(true);

        turnSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent event) {
                controller.setSpeed(turnSlider.getValue());
            }
        });

        ImageIcon heartIcon = new ImageIcon("images/heart.png");
        Image heartImage = heartIcon.getImage();
        Image resizedHeartImage = heartImage.getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
        heartIcon = new ImageIcon(resizedHeartImage);

        JLabel player1Label = new JLabel("Player 1", JLabel.CENTER);
        JLabel heartLabel1 = new JLabel(heartIcon);
        JLabel heartLabel2 = new JLabel(heartIcon);
        JLabel heartLabel3 = new JLabel(heartIcon);

        lives1Panel.add(heartLabel1);
        lives1Panel.add(heartLabel2);
        lives1Panel.add(heartLabel3);

        player1Panel.add(player1Label);
        player1Panel.add(lives1Panel);

        playersPanel.add(player1Panel);

        if (this.controller.getGame().getInitialSnakes().size() >= 2) {
            JPanel player2Panel = new JPanel();
            JPanel lives2Panel = new JPanel();

            player2Panel.setLayout(new GridLayout(2, 1));

            JLabel player2Label = new JLabel("Player 2", JLabel.CENTER);
            JLabel heartLabel4 = new JLabel(heartIcon);
            JLabel heartLabel5 = new JLabel(heartIcon);
            JLabel heartLabel6 = new JLabel(heartIcon);

            lives2Panel.add(heartLabel4);
            lives2Panel.add(heartLabel5);
            lives2Panel.add(heartLabel6);

            player2Panel.add(player2Label);
            player2Panel.add(lives2Panel);

            playersPanel.add(player2Panel);
        }
        else
            playersPanel.setLayout(new GridLayout(2, 1));

        gameInfoPanel.add(turnNumberLabel);
        gameInfoPanel.add(playersPanel);

        turnSliderPanel.add(turnSliderLabel);
        turnSliderPanel.add(turnSlider);

        turnCommandPanel.add(turnSliderPanel);
        turnCommandPanel.add(gameInfoPanel);

        mainPanel.add(gameCommandsPanel);
        mainPanel.add(turnCommandPanel);
        frame.add(mainPanel);

        frame.setVisible(true);
    }

    public void update(Observable observable, Object o) {
        SnakeGame game = (SnakeGame) observable;
        turnNumberLabel.setText("Turn : " + game.getTurn());
    }
}

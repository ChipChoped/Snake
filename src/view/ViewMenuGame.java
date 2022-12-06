package view;

import controllers.ControllerSnakeGame;
import strategies.InteractiveStrategy;
import strategies.RandomStrategy;
import strategies.Strategy;
import utils.GameMode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ViewMenuGame{
    private GameMode chosenMode = GameMode.MANUAL;
    private String chosenMap;

    public ViewMenuGame() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Snake Game Menu");
        frame.setSize(new Dimension(700, 300));

        Dimension windowSize = frame.getSize();
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Point centerPoint = ge.getCenterPoint();
        int dx = centerPoint.x - windowSize.width / 2;
        int dy = centerPoint.y - windowSize.height / 2;
        frame.setLocation(dx,dy);

        JPanel mainPanel = new JPanel();
        JPanel gameModePanel = new JPanel();
        JPanel radioButtonsPanel = new JPanel();
        JPanel filePanel = new JPanel();

        mainPanel.setLayout(new GridLayout(1, 2));
        gameModePanel.setLayout(new GridLayout(2, 1));
        radioButtonsPanel.setLayout(new GridLayout(1, 2));
        filePanel.setLayout(new GridLayout(3, 1));

        JLabel gameModeLabel = new JLabel("Choose which game mode you want to play in :", JLabel.CENTER);
        JLabel fileChooserLabel = new JLabel("Choose which map you want to play in :", JLabel.CENTER);

        JRadioButton manualModeRadio = new JRadioButton("Manual Mode");
        JRadioButton randomModeRadio = new JRadioButton("Random Mode");

        manualModeRadio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                chosenMode = GameMode.MANUAL;
            }
        });

        randomModeRadio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                chosenMode = GameMode.RANDOM;
            }
        });

        manualModeRadio.setSelected(true);
        ButtonGroup gameModeRadioGroup = new ButtonGroup();

        JButton playButton = new JButton("Play!");

        playButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                Strategy chosenStrategy = null;

                switch (chosenMode) {
                    case MANUAL:
                        chosenStrategy = new InteractiveStrategy();
                        break;
                    case RANDOM:
                        chosenStrategy = new RandomStrategy();
                }

                frame.dispose();
                
                try {
                    ControllerSnakeGame controller = new ControllerSnakeGame(50, chosenStrategy);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

        gameModeRadioGroup.add(manualModeRadio);
        gameModeRadioGroup.add(randomModeRadio);

        radioButtonsPanel.add(manualModeRadio);
        radioButtonsPanel.add(randomModeRadio);
        radioButtonsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        gameModePanel.add(gameModeLabel);
        gameModePanel.add(radioButtonsPanel);
        gameModePanel.setBorder(BorderFactory.createTitledBorder("Game Mode"));

        filePanel.add(playButton);

        mainPanel.add(gameModePanel);
        mainPanel.add(filePanel);

        frame.add(mainPanel);
        frame.setVisible(true);
    }
}

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

public class ViewGameMenu {
    private GameMode chosenMode = GameMode.MANUAL;
    private String chosenMapPath = System.getProperty("user.dir") + "/layout/arena.lay";
    private String chosenMapName = "arena";

    public ViewGameMenu() {
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
        JPanel rightPanel = new JPanel();

        mainPanel.setLayout(new GridLayout(1, 2));
        gameModePanel.setLayout(new GridLayout(2, 1));
        radioButtonsPanel.setLayout(new GridLayout(1, 2));
        filePanel.setLayout(new GridLayout(4, 1));
        rightPanel.setLayout(new GridLayout(2, 1));

        JLabel gameModeLabel = new JLabel("Choose which game mode you want to play in :", JLabel.CENTER);

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

        JLabel fileChooserLabel = new JLabel("Choose which map you want to play in :", JLabel.CENTER);
        JFileChooser fileChooser = new JFileChooser(System.getProperty("user.dir") + "/layout");
        JButton fileButton = new JButton("Choose file");
        JLabel currentMapLabel = new JLabel("Current map : " + chosenMapName, JLabel.CENTER);

        fileButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                int state = fileChooser.showOpenDialog(null);

                if (state == JFileChooser.APPROVE_OPTION) {
                    chosenMapPath = fileChooser.getSelectedFile().getAbsolutePath();
                    chosenMapName = fileChooser.getSelectedFile().getName().substring(0, fileChooser.getSelectedFile().getName().length() - 4);
                    currentMapLabel.setText("Current map : " + chosenMapName);
                }
            }
        });

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
                    ControllerSnakeGame controller = new ControllerSnakeGame(500, chosenStrategy, chosenMapPath);
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

        filePanel.add(fileChooserLabel);
        filePanel.add(fileButton);
        filePanel.add(currentMapLabel);
        filePanel.setBorder(BorderFactory.createTitledBorder("Map Selection"));

        rightPanel.add(filePanel);
        rightPanel.add(playButton);

        mainPanel.add(gameModePanel);
        mainPanel.add(rightPanel);

        frame.add(mainPanel);
        frame.setVisible(true);
    }
}

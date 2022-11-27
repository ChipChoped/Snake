package controllers;

import games.SimpleGame;
import view.ViewCommand;
import view.ViewSimpleGame;

public class ControllerSimpleGame extends AbstractController {
    public ControllerSimpleGame(int maxTurn) {
        this.game = new SimpleGame(maxTurn);
        ViewCommand viewCommand = new ViewCommand(this.game, this);
        ViewSimpleGame viewSimpleGame = new ViewSimpleGame(this.game, this);
    }

    public void restart() {
        this.game.initializeGame();
    }

    public void step() {
        this.game.step();
    }

    public void play() {
        this.game.launch();
    }

    public void pause() {
        this.game.pause();
    }

    public void setSpeed(int speed) {
        this.game.setSpeed(speed);
    }
}

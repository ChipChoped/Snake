public class ControllerSimpleGame extends AbstractController {
    public ControllerSimpleGame(int maxTurn) {
        this.game = new SimpleGame(maxTurn);
        ViewCommand viewCommand = new ViewCommand(this.game, this);
        ViewSimpleGame viewSimpleGame = new ViewSimpleGame(this.game, this);
    }

    void restart() {
        this.game.initializeGame();
    }

    void step() {
        this.game.step();
    }

    protected void play() {
        this.game.launch();
    }

    void pause() {
        this.game.pause();
    }

    void setSpeed(int speed) {
        this.game.setSpeed(speed);
    }
}

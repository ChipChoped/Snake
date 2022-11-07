import model.InputMap;
import view.PanelSnakeGame;

public class ControllerSnakeGame extends AbstractController {
    InputMap map;
    PanelSnakeGame panelSnakeGame;

    public ControllerSnakeGame(int maxTurn) throws Exception {
        this.game = new SnakeGame(maxTurn);
        ViewCommand viewCommand = new ViewCommand(this.game, this);
        ViewSnakeGame viewSnakeGame = new ViewSnakeGame(this.game, this);
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

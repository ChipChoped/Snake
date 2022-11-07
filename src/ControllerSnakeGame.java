import model.InputMap;
import view.PanelSnakeGame;

public class ControllerSnakeGame extends AbstractController {
    public ControllerSnakeGame(int maxTurn) throws Exception {
        InputMap map = new InputMap("layout/arena.lay");
        PanelSnakeGame panelSnakeGame = new PanelSnakeGame(map.getSizeX(), map.getSizeY(), map.get_walls(), map.getStart_snakes(), map.getStart_items());

        this.game = new SnakeGame(maxTurn, map.getStart_snakes(), map.getStart_items());
        ViewCommand viewCommand = new ViewCommand(this.game, this);
        ViewSnakeGame viewSnakeGame = new ViewSnakeGame(this.game, this, panelSnakeGame);
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

import utils.FeaturesItem;
import utils.FeaturesSnake;
import utils.Item;
import utils.Snake;

import java.util.ArrayList;

public class SnakeGame extends Game {
    private ArrayList<Snake> snakes;
    private ArrayList<Item> items;

    public SnakeGame(int maxTurn, ArrayList<FeaturesSnake> snakes, ArrayList<FeaturesItem> items) {
        super(maxTurn);
        this.snakes = new ArrayList<>();
        this.items = new ArrayList<>();

        for (FeaturesSnake snake : snakes) {
            assert false;
            this.snakes.add(new Snake(snake));
        }

        for (FeaturesItem item : items) {
            assert false;
            this.items.add(new Item(item));
        }
}

    protected void initializeGame() {
        this.turn = 0;
        this.isRunning = false;
        this.time = 100;

        setChanged();
        notifyObservers();
    }

    protected void takeTurn() {

    }

    protected boolean gameContinue() {
        return turn != maxturn;
    }

    protected void gameOver() {
        System.out.println("Game Over!");

        setChanged();
        notifyObservers();
    }
}

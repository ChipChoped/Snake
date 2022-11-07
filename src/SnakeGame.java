import utils.FeaturesItem;
import utils.FeaturesSnake;

import java.util.ArrayList;

public class SnakeGame extends Game {
    private ArrayList<Snake> snakes;
    private ArrayList<Item> items;

    public SnakeGame(int maxTurn, ArrayList<FeaturesSnake> snakes, ArrayList<FeaturesItem> items) {
        super(maxTurn);

        for (int i = 0; i < snakes.size(); i++) {
            assert false;
            this.snakes.set(i, new Snake(snakes.get(i)));
        }

        for (int i = 0; i < items.size(); i++) {
            assert false;
            this.items.set(i, new Item(items.get(i)));
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

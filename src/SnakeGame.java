import utils.*;

import java.util.ArrayList;
import java.util.Random;

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

    public ArrayList<FeaturesSnake> getFeaturesSnakes() {
        ArrayList<FeaturesSnake> snakes = new ArrayList<>();

        for (Snake snake : this.snakes)
            snakes.add(new FeaturesSnake(snake.getPositions(), snake.getLastAction(), snake.getColorSnake(), snake.isInvincible(), snake.isSick()));

        return snakes;
    }

    public ArrayList<FeaturesItem> getItems() {
        ArrayList<FeaturesItem> items = new ArrayList<>();

        for (Item item : this.items)
            items.add(new FeaturesItem(item.getX(), item.getY(), item.getItemType()));

        return items;
    }

    protected void initializeGame() {
        this.turn = 0;
        this.isRunning = false;
        this.time = 100;

        setChanged();
        notifyObservers();
    }

    protected void takeTurn() {
        Random rand = new Random();
        for (Snake snake : snakes)
            switch (rand.nextInt(4)) {
                case 0:
                    snake.getBehavior().moveAgent(snake, AgentAction.MOVE_UP);
                    break;
                case 1:
                    snake.getBehavior().moveAgent(snake, AgentAction.MOVE_DOWN);
                    break;
                case 2:
                    snake.getBehavior().moveAgent(snake, AgentAction.MOVE_LEFT);
                    break;
                case 3:
                    snake.getBehavior().moveAgent(snake, AgentAction.MOVE_RIGHT);
                    break;
            }
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

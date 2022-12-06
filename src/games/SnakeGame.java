package games;

import strategies.InteractiveStrategy;
import strategies.RandomStrategy;
import strategies.Strategy;
import utils.*;

import java.util.ArrayList;
import java.util.Random;

public class SnakeGame extends Game {
    private final ArrayList<Snake> initialSnakes;
    private final ArrayList<Item> initialItems;

    private ArrayList<Snake> snakes;
    private ArrayList<Item> items;

    private Strategy strategy;

    private boolean allSnakesEliminated;
    private boolean withWalls;
    private int sizeX;
    private int sizeY;

    public SnakeGame(int maxTurn, ArrayList<FeaturesSnake> snakes, ArrayList<FeaturesItem> items, boolean withWalls, int sizeX, int sizeY) {
        super(maxTurn);
        this.initialSnakes = new ArrayList<>();
        this.initialItems = new ArrayList<>();
        this.snakes = new ArrayList<>();
        this.items = new ArrayList<>();
        this.strategy = new InteractiveStrategy();
        this.withWalls = withWalls;
        this.sizeX = sizeX;
        this.sizeY = sizeY;

        for (FeaturesSnake snake : snakes) {
            assert false;
            this.initialSnakes.add(new Snake(snake));
        }

        for (FeaturesItem item : items) {
            assert false;
            this.initialItems.add(new Item(item));
        }
}

    public boolean getWithWalls() { return this.withWalls; }
    public ArrayList<Snake> getSnakes() { return this.snakes; }
    public ArrayList<Item> getItems() { return  this.items; }
    public int getSizeX() { return this.sizeX; }
    public int getSizeY() { return this.sizeY; }
    public void setAllSnakesEliminated(boolean eliminated) { this.allSnakesEliminated = eliminated; }

    public ArrayList<FeaturesSnake> getFeaturesSnakes() {
        ArrayList<FeaturesSnake> snakes = new ArrayList<>();

        for (Snake snake : this.snakes)
            snakes.add(new FeaturesSnake(snake.getPositions(), snake.getLastAction(), snake.getColorSnake(), snake.isInvincible(), snake.isSick()));

        return snakes;
    }

    public ArrayList<FeaturesItem> getFeaturesItems() {
        ArrayList<FeaturesItem> items = new ArrayList<>();

        for (Item item : this.items)
            items.add(new FeaturesItem(item.getX(), item.getY(), item.getItemType()));

        return items;
    }

    @SuppressWarnings("unchecked")
    public void initializeGame() {
        this.turn = 0;
        this.time = 100;
        this.isRunning = false;
        this.allSnakesEliminated = false;

        this.snakes = new ArrayList<Snake>();
        this.items = new ArrayList<Item>();

        for (Snake snake : initialSnakes)
            this.snakes.add(new Snake(snake));

        for (Item item : initialItems)
            this.items.add(new Item(item));

        setChanged();
        notifyObservers();
    }

    @SuppressWarnings("unchecked")
    protected void takeTurn() {
        this.strategy.move(this);
    }

    public boolean gameContinue() {
        return turn != maxturn && !allSnakesEliminated;
    }

    protected void gameOver() {
        System.out.println("Game Over!");

        setChanged();
        notifyObservers();
    }
}

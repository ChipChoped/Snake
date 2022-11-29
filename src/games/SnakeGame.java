package games;

import utils.*;

import java.util.ArrayList;
import java.util.Random;

public class SnakeGame extends Game {
    private final ArrayList<Snake> initialSnakes;
    private final ArrayList<Item> initialItems;

    private ArrayList<Snake> snakes;
    private ArrayList<Item> items;

    private boolean withWalls;
    private int sizeX;
    private int sizeY;

    public SnakeGame(int maxTurn, ArrayList<FeaturesSnake> snakes, ArrayList<FeaturesItem> items, boolean withWalls, int sizeX, int sizeY) {
        super(maxTurn);
        this.initialSnakes = new ArrayList<>();
        this.initialItems = new ArrayList<>();
        this.snakes = new ArrayList<>();
        this.items = new ArrayList<>();
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

    @SuppressWarnings("unchecked")
    public void initializeGame() {
        this.turn = 0;
        this.isRunning = false;
        this.time = 100;

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
        Random rand = new Random();
        ArrayList<Snake> otherSnakes = new ArrayList<>();
        ArrayList<Snake> eleminatedSnakes = new ArrayList<>();
        boolean eleminated = false;

        for (int i = 0; i < this.snakes.size(); i++) {
            otherSnakes = (ArrayList<Snake>) this.snakes.clone();
            otherSnakes.remove(i);
            otherSnakes.removeAll(eleminatedSnakes);

            if (eleminatedSnakes.contains(this.snakes.get(i)))
                i++;
            else {
                switch (rand.nextInt(4)) {
                    case 0:
                        eleminated = !this.snakes.get(i).getBehavior().moveAgent(this.snakes.get(i), AgentAction.MOVE_UP, otherSnakes, this.items, this.sizeX, this.sizeY, this.withWalls);
                        break;
                    case 1:
                        eleminated = !this.snakes.get(i).getBehavior().moveAgent(this.snakes.get(i), AgentAction.MOVE_DOWN, otherSnakes, this.items, this.sizeX, this.sizeY, this.withWalls);
                        break;
                    case 2:
                        eleminated = !this.snakes.get(i).getBehavior().moveAgent(this.snakes.get(i), AgentAction.MOVE_LEFT, otherSnakes, this.items, this.sizeX, this.sizeY, this.withWalls);
                        break;
                    case 3:
                        eleminated = !this.snakes.get(i).getBehavior().moveAgent(this.snakes.get(i), AgentAction.MOVE_RIGHT, otherSnakes, this.items, this.sizeX, this.sizeY, this.withWalls);
                        break;
                }

                ArrayList<Snake> difference = new ArrayList<>(this.snakes);
                difference.removeAll(otherSnakes);

                if (!eleminated) {
                    difference.remove(this.snakes.get(i));
                }

                eleminatedSnakes.addAll(difference);
            }
        }

        this.snakes.removeAll(eleminatedSnakes);
    }

    public boolean gameContinue() {
        return turn != maxturn;
    }


    protected void gameOver() {
        System.out.println("Game Over!");

        setChanged();
        notifyObservers();
    }
}

package behaviors;

import utils.*;

import java.util.ArrayList;
import java.util.Random;

public abstract class Behavior {
    public boolean isLegalMove(Snake snake, AgentAction action) {
        return (action == AgentAction.MOVE_UP && snake.getLastAction() != AgentAction.MOVE_DOWN) ||
                (action == AgentAction.MOVE_DOWN && snake.getLastAction() != AgentAction.MOVE_UP) ||
                (action == AgentAction.MOVE_LEFT && snake.getLastAction() != AgentAction.MOVE_RIGHT ||
                (action == AgentAction.MOVE_RIGHT && snake.getLastAction() != AgentAction.MOVE_LEFT));
    }

    public boolean isEliminated(Snake snake, Position position, ArrayList<Snake> otherSnakes, int sizeX, int sizeY, boolean withWalls) {
        if (withWalls && (position.getX() == 0 || position.getY() == 0 ||
                position.getX() == sizeX - 1 || position.getY() == sizeY - 1)) {
            return true;
        }

        for (int i = 2; i < snake.getPositions().size() - 1; i++)
            if (Snake.collision(position, snake.getPositions().get(i)))
                return true;

        for (Snake otherSnake : otherSnakes)
            if (otherSnake.getPositions().contains(position)) {
                if (Snake.collision(position, otherSnake.getPositions().get(0)) &&
                        snake.getPositions().size() == otherSnake.getPositions().size()) {
                    otherSnakes.remove(otherSnake);
                    return true;
                } else if (snake.getPositions().size() >= otherSnake.getPositions().size()) {
                    otherSnakes.remove(otherSnake);
                    return false;
                } else {
                    return true;
                }
            }

        return false;
    }

    public Item eatApple(Snake snake, ArrayList<Item> items, int pItem, int sizeX, int sizeY, boolean withWalls) {
        Random randApple = new Random();
        if (randApple.nextInt(101) <= pItem) {
            int border = 1;
            ItemType type;

            if (withWalls)
                border = 0;

            int randItem = randApple.nextInt(3);

            if (randItem == 0)
                type = ItemType.SICK_BALL;
            else if (randItem == 1)
                type = ItemType.INVINCIBILITY_BALL;
            else
                type = ItemType.BOX;

            snake.getPositions().add(new Position(snake.getPositions().get(snake.getPositions().size() - 1)));

            int x;
            int y;
            int cap = 0;
            ArrayList<Position> itemsPositions = new ArrayList<>();

            for (Item item : items)
                itemsPositions.add(new Position(item.getX(), item.getY()));

            do {
                x = new Random().nextInt(sizeX) + border;
                y = new Random().nextInt(sizeY) + border;
                cap++;
            } while (itemsPositions.contains(new Position(x, y)) && cap < 10);

            return new Item(x, y, type);
        }

        return null;
    }

    public boolean onItem(Snake snake, Position position, ArrayList<Item> items, int pItem, int sizeX, int sizeY, boolean withWalls) {
        for (Item item : items)
            if (item.getX() == position.getX() && item.getY() == position.getY()) {
                switch (item.getItemType()) {
                    case APPLE:
                        Item itemGenerated = eatApple(snake, items,pItem, sizeX, sizeY, withWalls);

                        if (itemGenerated != null)
                            items.add(itemGenerated);

                        items.remove(item);
                        return true;
                    case SICK_BALL:
                        snake.setBehavior(new SickBehavior());
                        items.remove(item);
                        return true;
                    case INVINCIBILITY_BALL:
                        snake.setBehavior(new InvincibleBehavior());
                        items.remove(item);
                        return true;
                    case BOX:
                        Random randBox = new Random();
                        if (randBox.nextBoolean())
                            snake.setBehavior(new SickBehavior());
                        else
                            snake.setBehavior(new InvincibleBehavior());

                        items.remove(item);
                        return true;
                }
            }

        return false;
    }

    public boolean moveAgent(Snake snake, AgentAction action, ArrayList<Snake> otherSnakes, ArrayList<Item> items, int sizeX, int sizeY, boolean withWalls) {
        if (!isLegalMove(snake, action))
            action = snake.getLastAction();

        AgentAction lastAction = snake.getLastAction();
        Position position = new Position(snake.getPositions().get(0));

        int move = 1;

        switch (action) {
            case MOVE_UP:
                if (!withWalls && position.getY() == 0)
                    move = -sizeY + 1;
                position.setY(position.getY() - move);
                lastAction = AgentAction.MOVE_UP;
                break;
            case MOVE_DOWN:
                if (!withWalls && position.getY() == sizeY - 1)
                    move = -sizeY + 1;
                position.setY(position.getY() + move);
                lastAction = AgentAction.MOVE_DOWN;
                break;
            case MOVE_LEFT:
                if (!withWalls && position.getX() == 0)
                    move = -sizeX + 1;
                position.setX(position.getX() - move);
                lastAction = AgentAction.MOVE_LEFT;
                break;
            case MOVE_RIGHT:
                if (!withWalls && position.getX() == sizeX - 1)
                    move = -sizeX + 1;
                position.setX(position.getX() + move);
                lastAction = AgentAction.MOVE_RIGHT;
                break;
        }

        return moveIfNotEliminated(snake, position, lastAction, otherSnakes, items, sizeX, sizeY, withWalls);
    }

    protected abstract boolean moveIfNotEliminated(Snake snake, Position position, AgentAction lastAction, ArrayList<Snake> otherSnakes, ArrayList<Item> items, int sizeX, int sizeY, boolean withWalls);
}

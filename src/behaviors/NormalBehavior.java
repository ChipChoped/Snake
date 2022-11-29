package behaviors;

import utils.*;

import java.util.ArrayList;
import java.util.Random;

public class NormalBehavior implements Behavior {
    public boolean isLegalMove(Snake snake, AgentAction action) {
        return (action == AgentAction.MOVE_UP && snake.getLastAction() != AgentAction.MOVE_DOWN) ||
                (action == AgentAction.MOVE_DOWN && snake.getLastAction() != AgentAction.MOVE_UP) ||
                (action == AgentAction.MOVE_LEFT && snake.getLastAction() != AgentAction.MOVE_RIGHT ||
                (action == AgentAction.MOVE_RIGHT && snake.getLastAction() != AgentAction.MOVE_LEFT));
    }

    public boolean isEliminated(Snake snake, Position position, ArrayList<Snake> otherSnakes, int sizeX, int sizeY, boolean withWalls) {
        if (withWalls && (position.getX() == 0 || position.getY() == 0 ||
            position.getX() == sizeX - 1 || position.getY() == sizeY - 1)) {
            System.out.println("Wall");
            return true;
        }

        for (Snake otherSnake : otherSnakes)
            if (otherSnake.getPositions().contains(position)) {
                System.out.println("Collision");
                return true;
            }

        return false;
    }

    public Item eatApple(Snake snake, int pItem, int sizeX, int sizeY, boolean withWalls) {
        Random randApple = new Random();
        if (randApple.nextInt(101) <= pItem) {
            int border = 1;
            ItemType type;

            if (withWalls)
                border = 0;

            if (randApple.nextBoolean())
                type = ItemType.SICK_BALL;
            else
                type = ItemType.INVINCIBILITY_BALL;

            snake.getPositions().add(snake.getPositions().get(snake.getPositions().size() - 1));

            return new Item(new Random().nextInt(sizeX) + border,
                    new Random().nextInt(sizeY) + border, type);
        }

        return null;
    }

    public boolean onItem(Snake snake, Position position, ArrayList<Item> items, int pItem, int sizeX, int sizeY, boolean withWalls) {
        for (Item item : items)
            if (item.getX() == position.getX() && item.getY() == position.getY()) {
                switch (item.getItemType()) {
                    case APPLE:
                        Item itemGenerated = eatApple(snake, pItem, sizeX, sizeY, withWalls);

                        if (itemGenerated != null)
                            items.add(itemGenerated);
                        break;
                    case SICK_BALL:
                        snake.setBehavior(new SickBehavior());
                        break;
                    case INVINCIBILITY_BALL:
                        snake.setBehavior(new InvincibleBehavior());
                        break;
                    case BOX:
                        Random randBox = new Random();
                        if (randBox.nextBoolean())
                            snake.setBehavior(new SickBehavior());
                        else
                            snake.setBehavior(new InvincibleBehavior());
                }

                return true;
            }

        return false;
    }

    public boolean moveAgent(Snake snake, AgentAction action, ArrayList<Snake> otherSnakes, ArrayList<Item> items, int sizeX, int sizeY, boolean withWalls) {
        if (isLegalMove(snake, action)) {
            AgentAction lastAction = snake.getLastAction();
            ArrayList<Position> positions = new ArrayList<Position>();

            for (Position position : snake.getPositions())
                positions.add(new Position(position));

            int move = 1;

            switch (action) {
                case MOVE_UP:
                    if (!withWalls && positions.get(0).getY() == 0)
                        move = -sizeY + 1;
                    positions.get(0).setY(positions.get(0).getY() - move);
                    lastAction = AgentAction.MOVE_UP;
                    break;
                case MOVE_DOWN:
                    if (!withWalls && positions.get(0).getY() == sizeY - 1)
                        move = -sizeY + 1;
                    positions.get(0).setY(positions.get(0).getY() + move);
                    lastAction = AgentAction.MOVE_DOWN;
                    break;
                case MOVE_LEFT:
                    if (!withWalls && positions.get(0).getX() == 0)
                        move = -sizeX + 1;
                    positions.get(0).setX(positions.get(0).getX() - move);
                    lastAction = AgentAction.MOVE_LEFT;
                    break;
                case MOVE_RIGHT:
                    if (!withWalls && positions.get(0).getX() == sizeX - 1)
                        move = -sizeX + 1;
                    positions.get(0).setX(positions.get(0).getX() + move);
                    lastAction = AgentAction.MOVE_RIGHT;
                    break;
            }

            if (!isEliminated(snake, positions.get(0), otherSnakes, sizeX, sizeY, withWalls)) {
                onItem(snake, positions.get(0), items, 100, sizeX, sizeY, withWalls);

                for (int i = 1; i < positions.size(); i++)
                    positions.set(i, positions.get(i - 1));

                snake.setPositions(positions);
                snake.setLastAction(lastAction);

                return true;
            }
            else
                return false;
        }

        return true;
    }
}

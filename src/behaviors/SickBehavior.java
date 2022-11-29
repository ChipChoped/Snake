package behaviors;

import utils.AgentAction;
import utils.Item;
import utils.Position;
import utils.Snake;

import java.util.ArrayList;

public class SickBehavior implements Behavior {
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

        for (Snake otherSnake : otherSnakes)
            if (otherSnake.getPositions().contains(position)) {
                return true;
            }

        return false;
    }

    public Item eatApple(Snake snake, int pItem, int sizeX, int sizeY, boolean withWalls) {
        // Can't eat apples :C
        return null;
    }

    public boolean onItem(Snake snake, Position position, ArrayList<Item> items, int pItem, int sizeX, int sizeY, boolean withWall) {
        // Can't take items anymore :C
        return false;
    }

    public boolean moveAgent(Snake snake, AgentAction action, ArrayList<Snake> otherSnakes, ArrayList<Item> items, int sizeX, int sizeY, boolean withWalls) {
        if (isLegalMove(snake, action)) {
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

            if (!isEliminated(snake, position, otherSnakes, sizeX, sizeY, withWalls)) {
                for (int i = 1; i < snake.getPositions().size(); i++)
                    snake.getPositions().set(i, snake.getPositions().get(i - 1));

                snake.getPositions().set(0, position);
                snake.setLastAction(lastAction);

                return true;
            }
            else
                return false;
        }

        return true;
    }
}

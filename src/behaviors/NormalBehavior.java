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
        if (withWalls)
            if (position.getX() == 0 || position.getY() == 0 ||
                position.getX() == sizeX - 1 || position.getY() == sizeY - 1) {
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

    public boolean onItem(Snake snake, Position position, ArrayList<Item> items, int pItem, int sizeX, int sizeY, boolean withWall) {
        for (Item item : items)
            if (item.getX() == position.getX() && item.getY() == position.getY()) {
                switch (item.getItemType()) {
                    case APPLE:
                        Random randApple = new Random();
                        if (pItem <= randApple.nextInt(101)) {
                            int border = 1;
                            ItemType type;

                            if (withWall)
                                border = 0;

                            if (randApple.nextBoolean())
                                type = ItemType.SICK_BALL;
                            else
                                type = ItemType.INVINCIBILITY_BALL;

                            items.add(new Item(new Random().nextInt(sizeX) + border,
                                    new Random().nextInt(sizeY) + border, type));
                        }
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

    public void moveAgent(Snake snake, AgentAction action, ArrayList<Snake> otherSnakes, int sizeX, int sizeY, boolean withWalls) {
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

            // isEliminated(snake, positions.get(0), otherSnakes, sizeX, sizeY, withWalls);

            snake.setPositions(positions);
            snake.setLastAction(lastAction);
        }
    }
}

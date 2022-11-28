package behaviors;

import utils.AgentAction;
import utils.Position;
import utils.Snake;

import java.util.ArrayList;

public class InvincibleBehavior implements Behavior {
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

    public void moveAgent(Snake snake, AgentAction action, ArrayList<Snake> otherSnakes, int sizeX, int sizeY, boolean withWalls) {
        if (isLegalMove(snake, action)) {
            ArrayList<Position> positions = new ArrayList<Position>();

            for (Position position : snake.getPositions())
                positions.add(new Position(position));

            int move = 1;

            switch (action) {
                case MOVE_UP:
                    if (!withWalls && positions.get(0).getY() == 0)
                        move = -sizeY + 1;
                    positions.get(0).setY(positions.get(0).getY() - move);
                    break;
                case MOVE_DOWN:
                    if (!withWalls && positions.get(0).getY() == sizeY - 1)
                        move = -sizeY + 1;
                    positions.get(0).setY(positions.get(0).getY() + move);
                    break;
                case MOVE_LEFT:
                    if (!withWalls && positions.get(0).getX() == 0)
                        move = -sizeX + 1;
                    positions.get(0).setX(positions.get(0).getX() - move);
                    break;
                case MOVE_RIGHT:
                    if (!withWalls && positions.get(0).getX() == sizeX - 1)
                        move = -sizeX + 1;
                    positions.get(0).setX(positions.get(0).getX() + move);
                    break;
            }

            // isEliminated(snake, positions.get(0), otherSnakes, sizeX, sizeY, withWalls);

            snake.setPositions(positions);
        }
    }
}

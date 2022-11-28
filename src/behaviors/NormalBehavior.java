package behaviors;

import utils.AgentAction;
import utils.Position;
import utils.Snake;

import java.util.ArrayList;

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

    public boolean onBorder(Position position, int sizeX, int sizeY) {
        return position.getX() == 0 || position.getY() == 0 ||
                position.getX() == sizeX - 1 || position.getY() == sizeY - 1;
    }

    public void moveAgent(Snake snake, AgentAction action, ArrayList<Snake> otherSnakes, int sizeX, int sizeY, boolean withWalls) {
        if (isLegalMove(snake, action)) {
            ArrayList<Position> positions = new ArrayList<Position>();

            for (Position position : snake.getPositions())
                positions.add(new Position(position));

            int move[];

            if (onBorder(positions.get(0), sizeX, sizeY))
                move = new int[]{sizeX - 1, sizeY - 1};
            else
                move = new int[]{1, 1};

            switch (action) {
                case MOVE_UP:
                    positions.get(0).setY(positions.get(0).getY() - move[1]);
                    break;
                case MOVE_DOWN:
                    positions.get(0).setY(positions.get(0).getY() + move[1]);
                    break;
                case MOVE_LEFT:
                    positions.get(0).setX(positions.get(0).getX() - move[0]);
                    break;
                case MOVE_RIGHT:
                    positions.get(0).setX(positions.get(0).getX() + move[0]);
                    break;
            }

            // isEliminated(snake, positions.get(0), otherSnakes, sizeX, sizeY, withWalls);

            snake.setPositions(positions);
        }
    }
}

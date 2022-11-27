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

    public boolean isEliminated(Snake snake, AgentAction action, ArrayList<Snake> otherSnakes, int sizeX, int sizeY, boolean withWalls) {
        return false;

    }

    public void moveAgent(Snake snake, AgentAction action, ArrayList<Snake> otherSnakes, int sizeX, int sizeY, boolean withWalls) {
        if (isLegalMove(snake, action)) {
            ArrayList<Position> positions = new ArrayList<Position>();

            for (Position position : snake.getPositions())
                positions.add(new Position(position));

            switch (action) {
                case MOVE_UP:
                    positions.get(0).setY(positions.get(0).getY() - 1);
                    break;
                case MOVE_DOWN:
                    positions.get(0).setY(positions.get(0).getY() + 1);
                    break;
                case MOVE_LEFT:
                    positions.get(0).setX(positions.get(0).getX() - 1);
                    break;
                case MOVE_RIGHT:
                    positions.get(0).setX(positions.get(0).getX() + 1);
                    break;
            }

            snake.setPositions(positions);
        }
    }
}

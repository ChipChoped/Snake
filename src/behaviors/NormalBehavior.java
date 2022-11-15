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

    public void moveAgent(Snake snake, AgentAction action) {
        if (isLegalMove(snake, action)) {
            ArrayList<Position> positions = snake.getPositions();

            switch (action) {
                case MOVE_UP -> positions.get(0).setY(positions.get(0).getY() - 1);
                case MOVE_DOWN -> positions.get(0).setY(positions.get(0).getY() + 1);
                case MOVE_LEFT -> positions.get(0).setX(positions.get(0).getX() - 1);
                case MOVE_RIGHT -> positions.get(0).setX(positions.get(0).getX() + 1);
            }

            snake.setPositions(positions);
        }
    }
}

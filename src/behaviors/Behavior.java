package behaviors;

import utils.AgentAction;
import utils.Position;
import utils.Snake;

import java.util.ArrayList;

public interface Behavior {
    public boolean isLegalMove(Snake snake, AgentAction action);
    public boolean isEliminated(Snake snake, Position position, ArrayList<Snake> otherSnakes, int sizeX, int sizeY, boolean withWalls);
    public boolean onBorder(Position position, int sizeX, int sizeY);
    public void moveAgent(Snake snake, AgentAction action, ArrayList<Snake> otherSnakes, int sizeX, int sizeY, boolean withWalls);
}

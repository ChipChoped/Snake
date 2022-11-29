package behaviors;

import utils.AgentAction;
import utils.Item;
import utils.Position;
import utils.Snake;

import java.util.ArrayList;

public interface Behavior {
    public boolean isLegalMove(Snake snake, AgentAction action);
    public boolean isEliminated(Snake snake, Position position, ArrayList<Snake> otherSnakes, int sizeX, int sizeY, boolean withWalls);
    public boolean onItem(Snake snake, Position position, ArrayList<Item> items, int pItem, int sizeX, int sizeY, boolean withWall);
    public boolean moveAgent(Snake snake, AgentAction action, ArrayList<Snake> otherSnakes, int sizeX, int sizeY, boolean withWalls);
}

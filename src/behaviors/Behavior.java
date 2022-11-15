package behaviors;

import utils.Position;
import java.util.ArrayList;
import utils.Snake;

public interface Behavior {
    public boolean isLegalMove(Snake snake, ArrayList<Position> positions);
    public void moveAgent(Snake snake, ArrayList<Position> positions);
}

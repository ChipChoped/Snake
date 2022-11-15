package behaviors;

import utils.AgentAction;
import utils.Snake;

public class NormalBehavior implements Behavior {
    public boolean isLegalMove(Snake snake, AgentAction action) {
        return false;
    }

    public void moveAgent(Snake snake, AgentAction action) {

    }
}

package behaviors;

import utils.AgentAction;
import utils.Snake;

public interface Behavior {
    public boolean isLegalMove(Snake snake, AgentAction action);
    public void moveAgent(Snake snake, AgentAction action);
}

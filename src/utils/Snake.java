package utils;

import utils.AgentAction;
import utils.ColorSnake;
import utils.FeaturesSnake;
import utils.Position;

import java.util.ArrayList;

public class Snake extends FeaturesSnake {
    public Snake(ArrayList<Position> positions, AgentAction lastAction, ColorSnake colorSnake, boolean isInvincible, boolean isSick) {
        super(positions, lastAction, colorSnake, isInvincible, isSick);
    }

    public Snake(FeaturesSnake snake) {
        super(snake.getPositions(), snake.getLastAction(), snake.getColorSnake(), snake.isInvincible(), snake.isSick());
    }
}

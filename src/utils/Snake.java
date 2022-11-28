package utils;

import behaviors.Behavior;
import behaviors.NormalBehavior;

import java.util.ArrayList;

public class Snake extends FeaturesSnake {
    Behavior behavior;

    public Snake(ArrayList<Position> positions, AgentAction lastAction, ColorSnake colorSnake, boolean isInvincible, boolean isSick) {
        super(positions, lastAction, colorSnake, isInvincible, isSick);
        behavior = new NormalBehavior();
    }

    public Snake(FeaturesSnake snake) {
        super(snake.getPositions(), snake.getLastAction(), snake.getColorSnake(), snake.isInvincible(), snake.isSick());
        behavior = new NormalBehavior();
    }

    public Snake(Snake snake) {
        super(new ArrayList<Position>(), snake.getLastAction(), snake.colorSnake, snake.isInvincible, snake.isSick);
        for (Position position : snake.positions)
            this.positions.add(new Position(position));
        behavior = new NormalBehavior();
    }

    public Behavior getBehavior() { return this.behavior; }
    public void setBehavior(Behavior behavior) { this.behavior = behavior; }
}

package strategies;

import games.SnakeGame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InteractiveStrategy implements Strategy{
    public void move(SnakeGame game) {
        new MoveKeyListener();
    }
}

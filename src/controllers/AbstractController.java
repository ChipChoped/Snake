package controllers;

import games.Game;

abstract public class AbstractController {
    protected Game game;

    public Game getGame() { return this.game; }
    public abstract void restart();
    public abstract void step();
    public abstract void play();
    public abstract void pause();
    public abstract void setSpeed(int source);
}

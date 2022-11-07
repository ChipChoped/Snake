abstract public class AbstractController {
    protected Game game;

    public Game getGame() { return this.game; }
    abstract void restart();
    abstract void step();
    protected abstract void play();
    abstract void pause();
    abstract void setSpeed(int source);
}

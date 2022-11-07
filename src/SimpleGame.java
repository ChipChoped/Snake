public class SimpleGame extends Game {
    public SimpleGame(int maxturn) {
        super(maxturn);
    }

    protected void initializeGame() {
        this.turn = 0;
        this.isRunning = false;
        this.time = 100;

        setChanged();
        notifyObservers();
    }

    protected void takeTurn() {

    }

    protected boolean gameContinue() {
        return turn != maxturn;
    }

    protected void gameOver() {
        System.out.println("Game Over!");

        setChanged();
        notifyObservers();
    }
}

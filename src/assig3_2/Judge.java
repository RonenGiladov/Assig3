package assig3_2;

/**
 * this class represents a judge in a coin flipping game.
 * it implements the Runnable interface, so it can be used to create a thread.
 */
public class Judge implements Runnable{
    private GamePlay game;

    /**
     * constructor initializes the variables.
     * @param game a GamePlay object representing the game play.
     */
    public Judge (GamePlay game) {
        this.game = game;
    }

    /**
     * function to run the game play in a new thread.
     * the judge makes the coin unavailable for flipping, waits for 1 second,
     * then makes the coin available for flipping and waits for 0.5 seconds.
     * this cycle is repeated until the number of rounds exceeds 10.
     */
    public void run () {
        try {
            while (game.getNumOfRounds() <= 10) {
                    game.makeCoinAvail(false);
                    Thread.sleep(1000);
                    game.makeCoinAvail(true);
                    Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

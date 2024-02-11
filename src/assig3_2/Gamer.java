// Shoham Avraham 318232469; Ronen Giladov 209506757; Assignment3_part2
package assig3_2;

/**
 * this class represents a gamer in a coin flipping game.
 * it implements the Runnable interface, so it can be used to create a thread
 */
public class Gamer implements Runnable{
    private int goodFlipsCounter;
    private GamePlay game;

    /**
     * constructor initializes the variables.
     * @param game a GamePlay object representing the game play.
     */
    public Gamer (GamePlay game) {
        goodFlipsCounter = 0;
        this.game = game;
    }

    /**
     * function for the gamer to play the game.
     * the gamer flips the coin until the number of rounds exceeds 10.
     * if the coin flip is successful, the counter for successful coin flips is incremented.
     */
    public synchronized void play () {
        try {
            while (game.getNumOfRounds() <= 10) {
                if (game.flipCoin() == true) {
                    goodFlipsCounter++;
                }
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
            }
    }

    /**
     *  getter method to get the score of the gamer.
     *  the score is the number of successful coin flips.
     * @return int representing the score.
     */
    public int getScore () {
        return goodFlipsCounter;
    }

    /**
     * method to run the game play in a new thread.
     */
    public void run () {
        play();
    }
}

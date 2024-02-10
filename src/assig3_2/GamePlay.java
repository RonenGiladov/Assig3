package assig3_2;

import java.util.Random;

/**
 *  this class represents a game where a coin is flipped
 */
public class GamePlay {
    private boolean coin_available;
    private int rounds_counter;

    /**
     *  constructor initializes the variables
     */
    public GamePlay (){
        coin_available = true;
        rounds_counter = 1;
    }

    /**
     * function to make the coin available or unavailable for flipping
     * @param val boolean value to change if the coin is available
     */
    public synchronized void makeCoinAvail (boolean val) {
        if (val) { // make the coin available for flipping
            coin_available = true;
            notifyAll();
        } else { // make the coin unavailable for flipping
            coin_available = false;
        }
    }

    /**
     * function to flip the coin, checking if the coin is available if not then wait for the coin to become available
     * @return boolean value true if the random value is 1, false if 0
     */
    public synchronized boolean flipCoin() {
        Random rand = new Random();
        int play; // variable to store the result of the coin flip
        // if the coin is unavailable, wait until it becomes available
        while (coin_available == false){
            try {
                System.out.println(Thread.currentThread().getName() + " is waiting for coin");
                wait();
            } catch (InterruptedException e){
                throw new RuntimeException(e);
            }
        }
        if (rounds_counter > 10) {
            return false; // If the rounds counter is greater than 10, return false
        }
        // if the coin is available and the rounds counter is less than or equal to 10, flip the coin
        System.out.println(Thread.currentThread().getName() + " is flipping coin");
        coin_available = false;
        rounds_counter++;
        play = rand.nextInt(2);
        // if the coin flip results in heads, make the coin available for flipping and notify all waiting threads
        if (play == 1){
            coin_available = true;
            notifyAll();
            return true;
        }
        // if the coin flip results in tails, make the coin available for flipping and notify all waiting threads
        coin_available = true;
        notifyAll();
        return false;
    }

    /**
     * getter method to get the number of rounds
     * @return int of number of rounds the game has gone through
     */
    public synchronized int getNumOfRounds () {
        return rounds_counter;
    }
}

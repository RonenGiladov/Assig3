// Shoham Avraham 318232469; Ronen Giladov 209506757; Assignment3_part2
package assig3_2;

/**
 * this is the main class that runs the coin flipping game.
 */
public class Main {
    public static void main (String args[]){
        // create a new GamePlay object
        GamePlay game = new GamePlay();
        // create two gamers and a judge, passing the GamePlay object to their constructors
        Gamer g1 = new Gamer(game);
        Gamer g2 = new Gamer(game);
        Judge judge = new Judge(game);
        // create threads for the two gamers and the judge
        Thread threadG1 = new Thread(g1);
        Thread threadG2 = new Thread(g2);
        Thread threadJudge = new Thread(judge);
        // start the threads
        threadG1.start();
        threadG2.start();
        threadJudge.start();

        // variables to store the scores of the two players
        int player1 = 0;
        int player2 = 0;

        try {
            // wait for all threads to finish
            threadG1.join();
            threadG2.join();
            threadJudge.join();

            // get the scores of the two players
            player1 = g1.getScore();
            player2 = g2.getScore();
        } catch (InterruptedException e){
            throw new RuntimeException(e);
        }
        // determine the winner based on the scores of the two players
        if (player1 > player2) {
            System.out.println("player 1 wins");
        } else if (player1 < player2) {
            System.out.println("player 2 wins");
        } else {
            System.out.println("tie");
        }
    }
}

// Shoham Avraham 318232469; Ronen Giladov 209506757; Assignment3_part3
package assig3_3;

import java.util.Scanner;

/**
 * the main class responsible for starting the salad preparation process.
 */
public class Main {


	/**
	 * the main method where the salad preparation process is initiated.
	 * @param args the command line arguments (not used)
	 */
	public static void main(String[] args) {
		System.out.println("Please Type How Many Salads To Prepare:");
		Scanner scan = new Scanner(System.in);
		final int numOfSaladsToPrepare = scan.nextInt();
		System.out.println("Preparing " + numOfSaladsToPrepare + " Salads...");

		
		// YOUR CODE HERE: use threads to prepare N salads (as the user requested)
		// create a SlicerMachine instance to prepare salads
		SlicerMachine slicerMachine = new SlicerMachine(numOfSaladsToPrepare);
		// create threads to add cucumbers, tomatoes, and slice vegetables
		CucumbersThread cucumbersThread = new CucumbersThread(slicerMachine);
		TomatoesThread tomatoesThread = new TomatoesThread(slicerMachine);
		SlicerThread slicerThread = new SlicerThread(slicerMachine);
		// start the threads
		cucumbersThread.start();
		tomatoesThread.start();
		slicerThread.start();

		try {  // wait for all threads to complete
			cucumbersThread.join();
			tomatoesThread.join();
			slicerThread.join();
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		
		System.out.println("Done");
		scan.close();
	}

}

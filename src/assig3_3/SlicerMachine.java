// Shoham Avraham 318232469; Ronen Giladov 209506757; Assignment3_part3
package assig3_3;

/**
 *	this class represents a salad-making machine.
 *  it keeps track of the number of cucumbers, tomatoes, and prepared salads.
 *  it also controls the addition of vegetables and the preparation of salads.
 */
public class SlicerMachine {

	private int numOfCucumbers = 0;
	private int numOfTomatoes = 0;
	private int numOfPreparedSalads = 0;

	private final int numOfNeededSalads;    // The number of salads needed


	private final int cucumbersNeededForOneSalad = 3;
	private final int tomatoesNeededForOneSalad = 2;

	// objects used for synchronization
	private final Object lock1 = new Object();
	private final Object lock2 = new Object();
	private final Object lock3 = new Object();


	/**
	 * constructor initializes the number of needed salads.
	 * @param numOfNeededSalads the number of salads needed.
	 */
	public SlicerMachine (int numOfNeededSalads) {
		this.numOfNeededSalads = numOfNeededSalads;
	}

	/**
	 * function to add one cucumber into the slicer chamber.
	 * if the number of cucumbers is already enough for one salad, it waits until some cucumbers are used.
	 */
	void addOneCucumber() {
		synchronized (lock1) {
			try {
				while (numOfCucumbers >= cucumbersNeededForOneSalad) {
					lock1.wait();
				}
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			System.out.println("adding one cucumber to the machine");
			numOfCucumbers++;
		}
		synchronized (lock3) {
			lock3.notifyAll();
		}


	}

	/**
	 * function to add one tomato into the slicer chamber.
	 * if the number of tomatoes is already enough for one salad, it waits until some tomatoes are used.
	 */
	void addOneTomato() {
		synchronized (lock2) {
			try {
				while (numOfTomatoes >= tomatoesNeededForOneSalad) {
					lock2.wait();
				}
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			System.out.println("adding one tomato to the machine");
			numOfTomatoes++;
		}
		synchronized (lock3) {
				lock3.notifyAll();
		}
	}

	// if there are enough vegetables in the slicer
	// chamber, make another salad

	/**
	 * function to slice the vegetables and make a salad.
	 * if there are not enough vegetables for one salad, it waits until enough vegetables are added.
	 */
	void sliceVegetables() {
		synchronized (lock3) {
			try {
				while (numOfCucumbers < cucumbersNeededForOneSalad || numOfTomatoes < tomatoesNeededForOneSalad) {
					lock3.wait();
				}
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			makeNewSalad();
			synchronized (lock1){
				lock1.notifyAll();
			}
			synchronized (lock2) {
				lock2.notifyAll();
			}
		}
	}


	/**
	 * private method to make a new salad.
	 * it increments the number of prepared salads and decrements the number of cucumbers and tomatoes.
	 */
	private void makeNewSalad() {
		System.out.println("== preparing one more salad ==");
		numOfPreparedSalads++;
		// update stock
		numOfTomatoes = numOfTomatoes - tomatoesNeededForOneSalad;
		numOfCucumbers = numOfCucumbers - cucumbersNeededForOneSalad;
	}

	/**
	 * getter method to get the number of prepared salads.
	 * @return the number of prepared salads.
	 */
	int getNumOfPreparedSalads() {
		return numOfPreparedSalads;
	}

	/**
	 * getter method to get the number of needed salads.
	 * @return the number of needed salads.
	 */
	int getNumOfNeededSalads() {return numOfNeededSalads; }
}
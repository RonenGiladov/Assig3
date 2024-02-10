package assig3_3;
/*
public class SlicerMachine {
	
	private int numOfCucumbers = 0;
	private int numOfTomatoes = 0;
	private int numOfPreparedSalads = 0;

	private final int numOfNeededSalads;

	private final int cucumbersNeededForOneSalad = 3;
	private final int tomatoesNeededForOneSalad = 2;

	private final Object lock1 = new Object();
	private final Object lock2 = new Object();
	private final Object lock3 = new Object();


	// constructor
	public SlicerMachine (int numOfNeededSalads) {
		this.numOfNeededSalads = numOfNeededSalads;
	}

	// add one cucumber into the slicer chamber
	 synchronized void addOneCucumber() {
		try {
			while (numOfCucumbers >= cucumbersNeededForOneSalad) {
				wait();
			}
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		System.out.println("adding one cucumber to the machine");
		numOfCucumbers++;
		notifyAll();
	}

	// add one tomato into the slicer chamber
	synchronized void addOneTomato() {
		try {
			while (numOfTomatoes >= tomatoesNeededForOneSalad) {
				wait();
			}
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		System.out.println("adding one tomato to the machine");
		numOfTomatoes++;
		notifyAll();
	}

	
	// if there are enough vegetables in the slicer
	// chamber, make another salad
	synchronized void sliceVegetables() {
		try {
			while (numOfCucumbers < cucumbersNeededForOneSalad || numOfTomatoes < tomatoesNeededForOneSalad) {
				wait();
			}
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		makeNewSalad();
		notifyAll();
	}



	private void makeNewSalad() {
		System.out.println("== preparing one more salad ==");
		numOfPreparedSalads++; 
		// update stock
		numOfTomatoes = numOfTomatoes - tomatoesNeededForOneSalad;
		numOfCucumbers = numOfCucumbers - cucumbersNeededForOneSalad;
	}
	
	int getNumOfPreparedSalads() {
		return numOfPreparedSalads;
	}

	int getNumOfNeededSalads() {return numOfNeededSalads; }
}*/

public class SlicerMachine {

	private int numOfCucumbers = 0;
	private int numOfTomatoes = 0;
	private int numOfPreparedSalads = 0;

	private final int numOfNeededSalads;

	private final int cucumbersNeededForOneSalad = 3;
	private final int tomatoesNeededForOneSalad = 2;

	private final Object lock1 = new Object();
	private final Object lock2 = new Object();
	private final Object lock3 = new Object();


	// constructor
	public SlicerMachine (int numOfNeededSalads) {
		this.numOfNeededSalads = numOfNeededSalads;
	}

	// add one cucumber into the slicer chamber
	void addOneCucumber() {
		synchronized (lock1) {
			try {
				while (numOfCucumbers >= cucumbersNeededForOneSalad) {
					lock1.wait();
				}
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}

			if (numOfCucumbers < cucumbersNeededForOneSalad) {
				System.out.println("adding one cucumber to the machine");
				numOfCucumbers++;
				//lock1.notifyAll();
			}
		}
		synchronized (lock3) {
			lock3.notifyAll();
		}


	}

	// add one tomato into the slicer chamber
	void addOneTomato() {
		synchronized (lock2) {
			try {
				while (numOfTomatoes >= tomatoesNeededForOneSalad) {
					lock2.wait();
				}
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}

			if (numOfTomatoes < tomatoesNeededForOneSalad) {
				System.out.println("adding one tomato to the machine");
				numOfTomatoes++;
				//lock2.notifyAll();
			}
		}
		synchronized (lock3) {
				lock3.notifyAll();
		}
	}

	// if there are enough vegetables in the slicer
	// chamber, make another salad
	void sliceVegetables() {
		synchronized (lock3) {
			try {
				while (numOfCucumbers < cucumbersNeededForOneSalad || numOfTomatoes < tomatoesNeededForOneSalad) {
					lock3.wait();
				}
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			if ((numOfCucumbers >= cucumbersNeededForOneSalad) && (numOfTomatoes >= tomatoesNeededForOneSalad)) {
				makeNewSalad();
				synchronized (lock1){
					lock1.notifyAll();
				}
				synchronized (lock2) {
					lock2.notifyAll();
				}
			}
		}
	}



	private void makeNewSalad() {
		System.out.println("== preparing one more salad ==");
		numOfPreparedSalads++;
		// update stock
		numOfTomatoes = numOfTomatoes - tomatoesNeededForOneSalad;
		numOfCucumbers = numOfCucumbers - cucumbersNeededForOneSalad;
	}

	int getNumOfPreparedSalads() {
		return numOfPreparedSalads;
	}

	int getNumOfNeededSalads() {return numOfNeededSalads; }
}
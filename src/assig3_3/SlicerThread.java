// Shoham Avraham 318232469; Ronen Giladov 209506757; Assignment3_part3
package assig3_3;

/**
 * represents a thread responsible for slicing vegetables in a slicer machine.
 * this thread continuously slices vegetables in the slicer machine until the number of prepared salads matches the number of needed salads.
 */
public class SlicerThread extends Thread {
	private SlicerMachine slicerMachine;

    /**
     * constructs a new instance of SlicerThread
     * @param slicerMachine the slicer machine to which vegetables will be sliced
     */
    public SlicerThread (SlicerMachine slicerMachine) {
        this.slicerMachine = slicerMachine;
    }

    /**
     * continuously slices vegetables in the slicer machine until the number of prepared salads matches the number of needed salads.
     */
    public void slice () {
        while (slicerMachine.getNumOfNeededSalads() != slicerMachine.getNumOfPreparedSalads()) {
            slicerMachine.sliceVegetables();
        }
    }

    @Override
    public void run () {
        slice();
    }

}

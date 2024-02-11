// Shoham Avraham 318232469; Ronen Giladov 209506757; Assignment3_part1
package assig3_3;

/**
 * represents a thread responsible for adding tomatoes to a slicer machine.
 * this thread continuously adds tomatoes to the slicer machine until the number of prepared salads matches the number of needed salads.
 */
public class TomatoesThread extends Thread {
    private SlicerMachine slicerMachine;

    /**
     * constructs a new instance of TomatoesThread
     * @param slicerMachine the slicer machine to which tomatoes will be added
     */
    public TomatoesThread (SlicerMachine slicerMachine) {
        this.slicerMachine = slicerMachine;
    }

    /**
     * continuously adds tomatoes to the slicer machine until the number of prepared salads matches the number of needed salads.
     */
    public void addTomato () {
        while (slicerMachine.getNumOfPreparedSalads() != slicerMachine.getNumOfNeededSalads()) {
            slicerMachine.addOneTomato();
        }
    }

    @Override
    public void run () {
        addTomato();
    }
}

// Shoham Avraham 318232469; Ronen Giladov 209506757; Assignment3_part1
package assig3_3;

/**
 * represents a thread responsible for adding cucumbers to a slicer machine.
 * this thread continuously adds cucumbers to the slicer machine until the number of prepared salads matches the number of needed salads.
 */
public class CucumbersThread extends Thread {
    private SlicerMachine slicerMachine;

    /**
     * constructs a new instance of CucumbersThread
     * @param slicerMachine the slicer machine to which cucumbers will be added
     */
    public CucumbersThread (SlicerMachine slicerMachine) {
        this.slicerMachine = slicerMachine;
    }

    /**
     * continuously adds cucumbers to the slicer machine until the number of prepared salads matches the number of needed salads.
     */
    public void addCucumber () {
        while (slicerMachine.getNumOfNeededSalads() != slicerMachine.getNumOfPreparedSalads()) {
            slicerMachine.addOneCucumber();
        }
    }

    @Override
    public void run () {
        addCucumber();
    }
}

package assig3_3;

public class SlicerThread extends Thread {
	private SlicerMachine slicerMachine;

    public SlicerThread (SlicerMachine slicerMachine) {
        this.slicerMachine = slicerMachine;
    }

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

package assig3_3;

public class CucumbersThread extends Thread {
    private SlicerMachine slicerMachine;

    public CucumbersThread (SlicerMachine slicerMachine) {
        this.slicerMachine = slicerMachine;
    }

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

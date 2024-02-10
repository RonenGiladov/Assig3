package assig3_3;

public class TomatoesThread extends Thread {
    private SlicerMachine slicerMachine;

    public TomatoesThread (SlicerMachine slicerMachine) {
        this.slicerMachine = slicerMachine;
    }

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

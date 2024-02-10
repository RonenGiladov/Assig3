package assig3_1;
// Class to control the order of execution and the running state of the threads
class OrderController {
    private int orderNumber;
    boolean isRunning;

    public OrderController() {
        orderNumber = 1;
        isRunning = false;
    }

    // Method to change the orderNumber
    public void changeOrder() {
        if (orderNumber < 3) {
            orderNumber++;
        } else {
            orderNumber = 1;
        }
    }

    public void setRunning(boolean flag) {
        isRunning = flag;
    }

    public int getOrderNumber() {
        return orderNumber;
    }
}

public class Main {
    public static void main(String args[]) {

        Object lock = new Object(); // Lock object for synchronization
        OrderController orderController = new OrderController();

        Thread t1 = new Thread(() -> {
            while (true) {
                synchronized (lock) {
                    while (orderController.getOrderNumber() != 1) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    // Code block A
                    System.out.println("1");
                    // Code block A

                    orderController.changeOrder();
                    lock.notifyAll();
                }
            }
        });

        Thread t2 = new Thread(() -> {
            while (true) {
                synchronized (lock) {
                    while (orderController.getOrderNumber() != 2) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    // Code block B
                    System.out.println("2");
                    // Code block B

                    lock.notifyAll();
                    if (orderController.isRunning) {
                        orderController.setRunning(false);
                        continue;
                    }
                    orderController.changeOrder();
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread t3 = new Thread(() -> {
            while (true) {
                synchronized (lock) {
                    while (orderController.getOrderNumber() != 3) {
                        try {
                            orderController.setRunning(true);
                            lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    // Code block C
                    System.out.println("3\n_");
                    // Code block C
                    orderController.changeOrder();
                    orderController.setRunning(false);
                    lock.notifyAll();
                }
            }
        });

        t1.start();
        t2.start();
        t3.start();
    }
}

// Shoham Avraham 318232469; Ronen Giladov 209506757; Assignment3_part1
package assig3_1;
/**
 * Class to control the order of execution and the running state of the threads
 */
class OrderController {
    boolean t1 = true;
    boolean t2 = false;
    boolean t3 = false;
}

/**
 *  class that syncs 3 Threads t1 t2 t3
 *  in a specific order first t1 from start to finish, then t2 from start to finish a number of times depending on the cpu
 *  lastly t3 from start to finish back to t1, an endless cycle
 */
public class Main {
    public static void main(String args[]) {

        Object lock = new Object(); // Lock object for synchronization
        OrderController orderController = new OrderController();

        Thread t1 = new Thread(() -> {
            while (true) {
                synchronized (lock) {
                    while (!orderController.t1) { // wait until its t1 turn
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    // Code block A
                    System.out.println("A");
                    // Code block A

                    orderController.t1 = false;
                    orderController.t2 = true;
                    lock.notifyAll();
                }
            }
        });

        Thread t2 = new Thread(() -> {
            while (true) {
                synchronized (lock) {
                    while (!orderController.t2) { // wait until its t2 turn
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    // Code block B
                    System.out.println("B");
                    // Code block B

                    orderController.t3 = true;
                    lock.notifyAll();

                }

            }
        });

        Thread t3 = new Thread(() -> {
            while (true) {
                synchronized (lock) {
                    while (!orderController.t3) { // wait until its t3 turn
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    orderController.t2 = false;
                    // Code block C
                    System.out.println("C\n_");
                    // Code block C
                    orderController.t3 = false;
                    orderController.t1 = true;
                    lock.notifyAll();
                }
            }
        });

        t1.start();
        t2.start();
        t3.start();
    }
}

/**
 *  class that syncs 3 Threads t1 t2 t3
 *  in a specific order first t1 from start to finish, then t2 from start to finish a number of times depending on the cpu
 *  lastly t3 from start to finish back to t1, an endless cycle
 */
class main{

    static final Object lock = new Object(); // lock
    static boolean isT1 = true;
    static boolean isT2 = false;
    static boolean isT3 = false;


    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    synchronized (lock) {
                        while (!isT1) { // wait until its t1 turn
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                    // code block A
                    System.out.println("A");
                    // code block A
                    isT1 = false;
                    isT2 = true;
                    lock.notifyAll();
                    }
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    synchronized (lock) {
                        while (!isT2) { // wait until its t2 turn
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                Thread.currentThread().interrupt();
                            }
                        }
                        // code block B
                        System.out.println("B");
                        // code block B
                        isT3 = true;
                        lock.notifyAll();
                    }
                }
            }
        });
        Thread t3 = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    synchronized (lock) {
                        while (!isT3) { // wait until its t3 turn
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                Thread.currentThread().interrupt();
                            }
                        }
                        isT2 = false;
                        // code block C
                        System.out.println("C");
                        // code block C

                        isT3 = false;
                        isT1 = true;
                        lock.notifyAll();
                    }
                }
            }
        });
        t1.start();
        t2.start();
        t3.start();
    }
}

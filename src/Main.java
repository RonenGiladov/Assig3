class main{
     static final Object lock = new Object();
     static boolean isT2 = false;
     static boolean isT3 = false;
     static boolean isT1 = true;

    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    synchronized (lock) {
                        while (!isT1) {
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
                        while (!isT2) {
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
                        while (!isT3) {
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

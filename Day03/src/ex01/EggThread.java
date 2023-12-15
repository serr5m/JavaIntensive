package ex01;

class EggThread extends Thread {
    private final int count;
    private final Object lock;

    public EggThread(int count, Object lock) {
        this.count = count;
        this.lock = lock;
    }

    @Override
    public void run() {
        synchronized (lock) {
            for (int i = 0; i < count; i++) {
                System.out.println("Egg");
                lock.notify(); // Notify the waiting thread (HenThread)
                try {
                    if (i < count - 1) {
                        lock.wait(); // Wait for the notification from HenThread
                    }
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
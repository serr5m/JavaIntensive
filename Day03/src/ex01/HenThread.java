package ex01;

class HenThread extends Thread {
    private final int count;
    private final Object lock;

    public HenThread(int count, Object lock) {
        this.count = count;
        this.lock = lock;
    }

    @Override
    public void run() {
        synchronized (lock) {
            for (int i = 0; i < count; i++) {
                System.out.println("Hen");
                lock.notify();
                try {
                    if (i < count - 1) {
                        lock.wait();
                    }
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
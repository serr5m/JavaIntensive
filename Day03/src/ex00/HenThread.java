package ex00;

class HenThread implements Runnable {
    private final int count;

    public HenThread(int count) {
        this.count = count;
    }

    @Override
    public void run() {
        for (int i = 0; i < count; i++) {
            System.out.println("Hen");
        }
    }
}
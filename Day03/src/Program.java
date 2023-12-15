import static java.lang.Thread.sleep;

public class Program {
    public static class PrinterThread extends Thread {
        final protected int count;

        PrinterThread(int count) {
            this.count = count;
        }
    }

    public class EggThread extends PrinterThread {
        EggThread(int count) {
            super(count);
        }

        @Override
        public void run() {
            for (int i = 0; i < count; ++i) {
                System.out.println("Egg" + getName());
                try {
                    sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static class HenThread extends PrinterThread {

        HenThread(int count) {
            super(count);
        }

        @Override
        public void run() {
            for (int i = 0; i < count; ++i) {

                System.out.println("Hen" + getName());
                try {
                    sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static void main(String[] args) {
        Thread henThread = new HenThread(50);
        Thread eggThread = new Program.HenThread(50);
        eggThread.start();
        henThread.start();
        for (int i = 0; i < 50; ++i) {
            System.out.println("Human");
            try {
                sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            eggThread.join();
            henThread.join();
        } catch (InterruptedException err) {
            System.out.println(err.getMessage());
        }
    }
}

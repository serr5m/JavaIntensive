package ex02;

public class ThreadedAdder extends Thread {
    private final int[] array;
    private final int start;
    private final int end;
    final int[] totalSum;

    public ThreadedAdder(int[] array, int start, int end, int[] totalSum) {
        this.array = array;
        this.start = start;
        this.end = end;
        this.totalSum = totalSum;
    }

    @Override
    public void run() {
        int result = 0;
        for (int i = start; i < end; i++) {
            result += array[i];
        }

        synchronized (totalSum) {
            totalSum[0] += result;
        }

        System.out.println(getName() + ": from " + start + " to " + end + " sum is " + result);
    }
}

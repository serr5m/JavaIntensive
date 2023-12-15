package ex02;

import java.util.ArrayList;

public class ThreadDistributor {
    private final int[] array;
    private final int countThreads;
    private final int arraySize;
    private final ArrayList<ThreadedAdder> threads = new ArrayList<>();
    private final int[] totalSum;

    public ThreadDistributor(int[] array, int arraySize, int countThreads) {
        this.array = array;
        this.countThreads = countThreads;
        this.arraySize = arraySize;
        this.totalSum = new int[1];
    }

    void distributeArrayAcrossThreads() {
        int elementsInAThread = arraySize / countThreads;
        int remainder = arraySize % countThreads;

        int currentPosition = 0;
        int start = 0, end = 0;

        for (int i = 0; i < countThreads; i++) {
            if (i == countThreads - 1 && remainder != 0) {
                start = currentPosition;
                end = start + elementsInAThread + remainder;
            } else {
                start = currentPosition;
                end = start + elementsInAThread;
            }
            threads.add(new ThreadedAdder(array, start, end, totalSum));
            currentPosition = end;
        }
    }

    void startThread() {
        for (ThreadedAdder thread : threads) {
            thread.start();
        }

        for (ThreadedAdder thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println("Sum by threads: " + totalSum[0]);
    }
}

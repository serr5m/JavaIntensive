package ex02;

import java.util.Random;

public class ArrayGenerator {
    int[] array;
    Random random = new Random();

    public ArrayGenerator(int arraySize) {
        array = new int[arraySize];
    }

    void fillArray() {
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(-1000, 1000);
        }
    }

    public int[] getArray() {
        return array;
    }
}

package ex04;

import java.util.Scanner;

public class Program {

    private static int maxCount(int[] counts) {
        int max = 0;
        for (int count : counts) {
            if (count > max) {
                max = count;
            }
        }
        return max;
    }

    private static int maxIndex(int[] counts, int maxCount) {
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] == maxCount) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] storageCounts = new int[Character.MAX_VALUE];
        int[] resultCount = new int[10];
        char[] resultCharacters = new char[10];

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        char[] characters = input.toCharArray();
        for (int i = 0; i < characters.length; i++) {
            storageCounts[characters[i]]++;
        }

        for (int i = 0; i < 10; i++) {
            int maxCount = maxCount(storageCounts);
            int maxIndex = maxIndex(storageCounts, maxCount);
            resultCount[i] = storageCounts[maxIndex];
            resultCharacters[i] = (char) maxIndex;
            System.out.println(maxIndex);
        }

//        for (int i = 0; i < storageCounts.length; i++) {
//            if (storageCounts[i] != 0) {
//                System.out.println(storageCounts[i]);
//            }
//        }

//        System.out.println("asdasd " + maxCount(storageCounts));
        scanner.close();
    }
}

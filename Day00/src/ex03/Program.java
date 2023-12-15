package ex03;

import java.util.Scanner;
import java.lang.String;

public class Program {

    static final int MAX_WEEKS = 18;
    static final int MIN_DAYS = 5;
    static final int MAX_GRADE = 9;
    static final int MIN_GRADE = 1;

    private static int readGrade(Scanner scanner) {
        int minGrade = scanner.nextInt();
        isIllegalGrade(minGrade, scanner);
        for (int j = 0; j < MIN_DAYS - 1; j++) {
            int currentGrade = scanner.nextInt();
            isIllegalGrade(currentGrade, scanner);
            if (minGrade > currentGrade) {
                minGrade = currentGrade;
            }
        }
        return minGrade;
    }

    private static void isIllegalGrade(int grade, Scanner scanner) {
        if (grade < MIN_GRADE || grade > MAX_GRADE) {
            exitTheProgram(scanner);
        }
    }

    private static long fillStorageGrades(int grade, long storage) {
        if (storage == 0) {
            storage = grade;
        } else {
            storage *= 10;
            storage += grade;
        }
        return storage;
    }

    private static void printGraph(long storageOfGrades, Scanner scanner) {
        storageOfGrades = reverseStorageGrades(storageOfGrades);
        int countWeek = 1;
        while (storageOfGrades != 0) {
            System.out.print("Week " + countWeek + " ");
            long currentGrade = storageOfGrades % 10;
            storageOfGrades /= 10;
            for (int i = 0; i < currentGrade; i++) {
                System.out.print("=");
            }
            System.out.println(">");
            ++countWeek;
        }
        scanner.close();
    }

    private static long reverseStorageGrades(long storageGrades) {
        long newStorageGrades = 0;
        while (storageGrades != 0) {
            long tmp = storageGrades % 10;
            if (newStorageGrades == 0) {
                newStorageGrades = tmp;
            } else {
                newStorageGrades *= 10;
                newStorageGrades += tmp;
            }
            storageGrades /= 10;
        }
        return newStorageGrades;
    }

    private static void exitTheProgram(Scanner scanner) {
        System.err.print("Illegal argument");
        scanner.close();
        System.exit(-1);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long storageOfGrades = 0;
        for (int i = 1; i <= MAX_WEEKS; i++) {
            String isWeek = scanner.next();
            if (isWeek.equals("Week")) {
                int day = scanner.nextInt();
                if (day != i) {
                    exitTheProgram(scanner);
                }
                int minGradeOfWeek = readGrade(scanner);
                storageOfGrades = fillStorageGrades(minGradeOfWeek, storageOfGrades);


            } else if (isWeek.equals("42")) {
                break;
            } else {
                exitTheProgram(scanner);
            }
        }
        printGraph(storageOfGrades, scanner);
    }
}


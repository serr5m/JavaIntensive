package Day00.ex01;

import java.util.Scanner;

import static java.lang.Math.sqrt;

public class Program {

    private static void prime(int n) {
        boolean result = true;
        int count_iterations = 0;
        for (int i = 2; i <= sqrt(n); ++i) {
            if (n % i == 0) {
                result = false;
                break;
            }
            count_iterations++;
        }
        count_iterations++;
        System.out.println(result + " " + count_iterations);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        if (number < 2) {
            System.out.println("Illegal Argument");
        } else {
            prime(number);
        }
        scanner.close();
        System.exit(-1);
    }
}

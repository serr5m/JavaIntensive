package Day00;

import java.util.Scanner;

import static java.lang.Math.sqrt;

public class ex01 {
    public static boolean prime(int n) {
        for (int i = 2; i <= sqrt(n); ++i) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
//        System.out.println(number + 2);
        boolean result = prime(number);
        System.out.println(result);
    }


}
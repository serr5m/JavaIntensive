package Day00.ex02;

import java.util.*;

import static java.lang.Math.sqrt;

public class Program {
    private static int sumDigitsOfNumbers (int number) {

        int result = 0;
        while (number != 0) {
           int remainder = number % 10;
            number /= 10;
            result += remainder;
        }
        return result;
    }

    private static boolean isPrime(int num) {
        for (int i = 2; i <= sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int count_coffee_request = 0;
        while (scanner.hasNextInt()) {
           int tmp = scanner.nextInt();
            if (tmp == 42) {
                break;
            }

            if(isPrime(sumDigitsOfNumbers(tmp))) {
                count_coffee_request++;
            }
        }
        System.out.println("Count of coffee-request – " + count_coffee_request);
        scanner.close();
    }
}

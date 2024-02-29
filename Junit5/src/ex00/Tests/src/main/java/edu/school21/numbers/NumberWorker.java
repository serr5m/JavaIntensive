package edu.school21.numbers;

public class NumberWorker {
    public boolean isPrime(int number) {
        if (number < 2) {
            throw new IllegalNumberException("Number can't be less than 2");
        }
        for (int i = 2; i <= Math.sqrt(number); ++i) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    public int digitsSum(int number) {
        int result = 0;
        while (number != 0) {
            int remainder = number % 10;
            result += remainder;
            number /= 10;
        }
        return result;
    }

    class IllegalNumberException extends RuntimeException {
        public IllegalNumberException(String message) {
            super(message);
        }
    }


}

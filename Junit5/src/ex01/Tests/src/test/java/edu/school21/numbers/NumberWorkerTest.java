package edu.school21.numbers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

class NumberWorkerTest {

    private NumberWorker numberWorker;

    @BeforeEach
    void setUp() {
        numberWorker = new NumberWorker();
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 3, 5, 7, 11, 13, 17})
    void isPrimeForPrimes(int argument) {
        Assertions.assertTrue(numberWorker.isPrime(argument), "Test isPrimeNumber is failed");
    }

    @ParameterizedTest
    @ValueSource(ints = {4, 6, 8, 12, 14, 300, 400})
    void isPrimeForNotPrimes(int argument) {
        Assertions.assertFalse(numberWorker.isPrime(argument), "Test isPrimeForNotPrimes is failed");
    }

    @ParameterizedTest
    @ValueSource(ints = {-2, 0, -1, 1})
    void isPrimeForIncorrectNumbers(int argument) {
        Assertions.assertThrows(NumberWorker.IllegalNumberException.class, () -> numberWorker.isPrime(argument), "Test isPrimeForIncorrectNumbers is failed");
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/data.csv")
    void checkDigitSum(int number, int result) {
        Assertions.assertEquals(numberWorker.digitsSum(number), result);
    }

}

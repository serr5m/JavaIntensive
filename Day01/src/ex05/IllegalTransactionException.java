package ex05;

public class IllegalTransactionException extends RuntimeException {
    public IllegalTransactionException() {
        super("The sender does not have enough money");
    }
}

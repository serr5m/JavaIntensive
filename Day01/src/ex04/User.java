package ex04;

public class User {
    private final int identifier;
    private String name;
    private double balance;
    private TransactionList transactionList;


    public User(int identifier, String name, double balance) {
        this.identifier = identifier;
        this.name = name;
        if (balance < 0) {
            System.err.println("Balance cannot be negative");
            System.exit(-1);
        }
        this.balance = balance;
        transactionList = new TransactionsLinkedList();
    }

    public int getIdentifier() {
        return identifier;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public void addBalance(double amount) {
        this.balance += amount;
    }

    public TransactionList getTransactionList() {
        return transactionList;
    }

}


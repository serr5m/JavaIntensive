package ex05;

import java.util.UUID;

public class User {
    private final int identifier;
    private String name;
    private double balance;
    private TransactionList transactionList;


    public User(String name, double balance) {
        this.identifier = UserIdsGenerator.getInstance().generateId();
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

    public double getTransferAmount(UUID uuid) {
        Transaction[] transactions = transactionList.toArray();
        for (int i = 0; i < transactions.length; i++) {
            if (transactions[i].getIdentifier().equals(uuid)) {
                return transactions[i].getTransferAmount();
            }
        }
        throw new RuntimeException("No transaction found with this UUID");
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


package ex00;


public class User {
    private final int identifier;
    private String name;
    private double balance;


    public User(int identifier, String name, double balance) {
        this.identifier = identifier;
        this.name = name;
        if (balance < 0) {
            System.err.println("Balance cannot be negative");
            System.exit(-1);
        }
        this.balance = balance;
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

}


package ex00;

import java.util.UUID;


public class Transaction {
    public enum TransferCategory {
        debit,
        credit
    }
    private final UUID identifier;
    private final User recipient;
    private final User sender;
    private final TransferCategory transferCategory;
    private final double transferAmount;

    public Transaction(UUID identifier, User recipient, User sender, TransferCategory transferCategory, double transferAmount) {
        this.identifier = identifier;
        if (transferCategory == TransferCategory.debit && transferAmount < 0) {
            System.err.println("Cant be negative transaction");
            System.exit(-1);
        }

        if (transferCategory == TransferCategory.credit && transferAmount > 0) {
            System.err.println("Cant be positive transaction");
            System.exit(-1);
        }

        this.recipient = recipient;
        this.sender = sender;
        this.transferCategory = transferCategory;
        this.transferAmount = transferAmount;
        makeTransfer();

    }

    public UUID getIdentifier() {
        return identifier;
    }

    public User getRecipient() {
        return recipient;
    }

    public User getSender() {
        return sender;
    }

    public TransferCategory getTransferCategory() {
        return transferCategory;
    }

    public double getTransferAmount() {
        return transferAmount;
    }

    public void makeTransfer() {
        if (transferCategory == TransferCategory.debit) {
            if (sender.getBalance() < transferAmount) {
                System.err.println("The sender does not have enough money");
                System.exit(-1);
            }
            recipient.addBalance(transferAmount);
            sender.addBalance(-transferAmount);
        }
    }
}

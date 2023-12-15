package ex05;

import java.util.UUID;

public class Transaction {
    public enum TransferCategory {
        debit,
        credit
    }

    private UUID identifier;
    private final User recipient;
    private final User sender;
    private final TransferCategory transferCategory;
    private final double transferAmount;

    public Transaction(User recipient, User sender, TransferCategory transferCategory, double transferAmount) {
        this.identifier = UUID.randomUUID();
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

    public void setIdentifier(UUID id) {
        this.identifier = id;
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
                throw new IllegalTransactionException();
            }
            recipient.addBalance(transferAmount);
            sender.addBalance(-transferAmount);
        }
    }
}
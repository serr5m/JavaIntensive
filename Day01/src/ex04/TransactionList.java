package ex04;

import java.util.UUID;

public interface TransactionList {
    void addTransaction(Transaction transaction);

    void removeTransaction(UUID id);

    Transaction[] toArray();
}

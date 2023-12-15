package ex04;

import java.util.UUID;

public class TransactionsService {
    private UsersList userList;

    public TransactionsService() {
        userList = new UsersArrayList();
    }

    void addUser(User user) {
        userList.addUser(user);
    }

    User getUser(User user) {
        return userList.getUser(user);
    }

    double getBalance(int id) {
        return userList.getUserById(id).getBalance();
    }

    void performingTransferTransaction(int idRecipient, int idSender, int transferAmount) {
        User recipient = userList.getUserById(idRecipient);
        User sender = userList.getUserById(idSender);

        Transaction debit = new Transaction(recipient, sender, Transaction.TransferCategory.debit, transferAmount);
        Transaction credit = new Transaction(recipient, sender, Transaction.TransferCategory.credit, -transferAmount);

        debit.setIdentifier(credit.getIdentifier());

        recipient.getTransactionList().addTransaction(debit);
        sender.getTransactionList().addTransaction(credit);
    }

    Transaction[] specifiedUsersTransactions(User user) {
        return user.getTransactionList().toArray();
    }

    void removeTransaction(UUID uuid, int idUser) {
        userList.getUserById(idUser).getTransactionList().removeTransaction(uuid);
    }

    Transaction[] allTransactions() {
        TransactionList allTransactionsList = new TransactionsLinkedList();
        for (int i = 0; i < userList.getNumbersOfUsers(); i++) {
            Transaction[] transactionsOfSpecifiedUser = userList.getUserById(i + 1).getTransactionList().toArray();
            for (Transaction transaction : transactionsOfSpecifiedUser) {
                allTransactionsList.addTransaction(transaction);
            }
        }
        return allTransactionsList.toArray();
    }

    Transaction[] arrayOfUnpairedTransactions() {
        TransactionList unpairedTransactions = new TransactionsLinkedList();
        Transaction[] allTransactions = allTransactions();
        for (int i = 0; i < allTransactions.length; i++) {
            int countTransactions = 0;
            for (int j = 0; j < allTransactions.length; j++) {
                if (allTransactions[i].getIdentifier() == allTransactions[j].getIdentifier()) {
                    countTransactions++;
                }
            }
            if(countTransactions != 2) {
                unpairedTransactions.addTransaction(allTransactions[i]);
            }
        }
        return unpairedTransactions.toArray();
    }
}



package ex03;

import java.util.UUID;

public class Program {
    public static void main(String[] args) {
        User user1 = new User(1, "Dil", 300);
        User user2 = new User(2, "Marat", 500);
        User user3 = new User(3, "Sava", 700);


        Transaction transaction1 = new Transaction(user1, user2, Transaction.TransferCategory.credit, -200);
        Transaction transaction2 = new Transaction(user1, user2, Transaction.TransferCategory.debit, 140);
        Transaction transaction3 = new Transaction(user3, user1, Transaction.TransferCategory.credit, -100);


//        System.out.println(user1.getBalance());
//        System.out.println(user2.getBalance());
//        System.out.println(user3.getBalance());

        TransactionsLinkedList transList = new TransactionsLinkedList();


        transList.addTransaction(transaction1);
        transList.addTransaction(transaction2);
        transList.addTransaction(transaction3);


        System.out.println("size " + transList.getSize());

        System.out.println(transList.getHeadTransaction().getRecipient().getName());
        System.out.println(transList.getHeadTransaction().getRecipient().getBalance());

        System.out.println(transList.getHeadTransaction().getSender().getName());
        System.out.println(transList.getHeadTransaction().getSender().getBalance());
//
//        transList.removeTransaction(transaction1.getIdentifier());
//        transList.removeTransaction(transaction2.getIdentifier());
//        transList.removeTransaction(transaction3.getIdentifier());




        Transaction[] transactions = transList.toArray();

        for (Transaction item : transactions) {
            System.out.println(item.getSender().getName());
        }

        UUID uuid = UUID.randomUUID();
        try {
            transList.removeTransaction(uuid);
        } catch (TransactionNotFoundException ex) {
            System.out.println(ex.getMessage());
        }

    }
}


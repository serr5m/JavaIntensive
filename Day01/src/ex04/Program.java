package ex04;

public class Program {
    public static void main(String[] args) {
        TransactionsService transactionsService = new TransactionsService();

        User user1 = new User(1, "Dima", 600);
        User user2 = new User(2, "Manu", 880);
        User user3 = new User(3, "Dil", 1200);
        User user4 = new User(4, "Kek", 100);

        transactionsService.addUser(user1);
        transactionsService.addUser(user2);
        transactionsService.addUser(user3);
        transactionsService.addUser(user4);


        try {
            transactionsService.performingTransferTransaction(user1.getIdentifier(), user2.getIdentifier(), 390);
            transactionsService.performingTransferTransaction(user3.getIdentifier(), user1.getIdentifier(), 20);
        } catch (IllegalTransactionException ex) {
            System.out.println(ex.getMessage());
        }


        Transaction[] transUser1 = transactionsService.specifiedUsersTransactions(user1);
        Transaction[] transUser2 = transactionsService.specifiedUsersTransactions(user2);
        Transaction[] transUser3 = transactionsService.specifiedUsersTransactions(user3);
        Transaction[] transUser4 = transactionsService.specifiedUsersTransactions(user4);

        System.out.println("User 1: ");
        System.out.println(transUser1[0].getIdentifier());
//        System.out.println(transUser1[1].getIdentifier());

        System.out.println("User 2: ");
        System.out.println(transUser2[0].getIdentifier());

        System.out.println("User 3: ");
        System.out.println(transUser3[0].getIdentifier());
        System.out.println("ALL TRANSACTIONS: ");

//        System.out.println("User 4: ");
//        System.out.println(transUser4[0].getIdentifier());
//        System.out.println("ALL TRANSACTIONS: ");

        transactionsService.removeTransaction(transUser1[0].getIdentifier(), user1.getIdentifier());


        Transaction[] allTransaction = transactionsService.allTransactions();

        for (Transaction transaction : allTransaction) {
            System.out.println(transaction.getIdentifier());

        }

        System.out.println("UNPAIRED TRANSACTIONS: ");

        Transaction[] unpairedTransactions = transactionsService.arrayOfUnpairedTransactions();
        for (Transaction unpairedTransaction : unpairedTransactions) {
            System.out.println(unpairedTransaction.getIdentifier());
        }

    }


}

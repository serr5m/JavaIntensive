package ex05;

import java.util.Scanner;
import java.util.UUID;

public class Menu {
    private TransactionsService transactionsService;
    Scanner scanner;

    public Menu() {
        transactionsService = new TransactionsService();
        scanner = new Scanner(System.in);
    }

    void runProgram(boolean bool) {
        printHelloMessage(bool);
        System.out.println("Enter the number of the desired operation: ");
        int numberOperation;
        while (true) {
            numberOperation = scanner.nextInt();
            try {
                if (bool) {
                    executingMenuOperationDev(numberOperation);
                } else {
                    executingMenuOperationStandard(numberOperation);
                }
            } catch (NumberFormatException ex) {
                System.out.println("User ID must be an integer");
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            } catch (RuntimeException ex) {
                System.out.println(ex.getMessage());
            }

            System.out.println("Enter the number of the desired operation: ");
        }

    }

    void printHelloMessage(boolean bool) {
        String message;
        if (bool) {
            message = "1. Add a user\n2. View user balances\n3. Performe a  transfer\n" +
                    "4. View all transactions for a specific user\n5. DEV – remove a transfer by ID\n" +
                    "6. DEV - check transfer validity\n7. Finish execution";
        } else {
            message = "1. Add a user\n2. View user balances\n3. Performe a  transfer\n" +
                    "4. View all transactions for a specific user\n5. Finish execution";
        }

        System.out.println(message);
    }

    void addUserAndBalance() {
        System.out.println("Enter a user name and a balance ");
        double balance;
        String name = scanner.next();
        if (scanner.hasNextDouble()) {
            balance = scanner.nextDouble();
        } else {
            scanner.next();
            throw new RuntimeException("Balance should be a number");
        }

        User user = new User(name, balance);
        transactionsService.addUser(user);

        System.out.println("User with id = " + user.getIdentifier() + " is added");


    }

    void viewUsersBalances() {
        System.out.println("Enter a user ID");
        int id;
        if (scanner.hasNextInt()) {
            id = scanner.nextInt();
        } else {
            scanner.next();
            throw new RuntimeException("Id should be a integer");
        }
        System.out.println(transactionsService.getName(id) + " - " + transactionsService.getBalance(id));
    }

    void performTransfer() {
        System.out.println("Enter a sender ID, a recipient ID, and a transfer amount");
        int idSender, idRecipient, transferAmount = 0;
        scanner.nextLine();   //cleaning
        String input = scanner.nextLine();
        String[] splitArray = input.split(" ");

        if (splitArray.length != 3) {
            throw new IllegalArgumentException("Number of arguments is not equal to 3");
        }

        idSender = Integer.parseInt(splitArray[0]);
        idRecipient = Integer.parseInt(splitArray[1]);
        try {
            transferAmount = Integer.parseInt(splitArray[2]);
        } catch (NumberFormatException ex) {
            System.out.println("Transfer amount must be an Integer");
        }
        transactionsService.performingTransferTransaction(idRecipient, idSender, transferAmount);
        System.out.println("The transfer is completed");
    }

    void viewAllTransactionsForSpecificUser() {
        System.out.println("Enter a user ID");
        scanner.nextLine();
        String input = scanner.nextLine();
        String[] splitArray = input.split(" ");
        if (splitArray.length != 1) {
            throw new IllegalArgumentException("Number of arguments is not equal to 1");
        }

        int id = Integer.parseInt(splitArray[0]);

        Transaction[] transactions = transactionsService.specifiedUsersTransactions(transactionsService.getUserById(id));

        for (Transaction transaction : transactions) {
            System.out.println("To " + transactionsService.getName(id) + "( id  = "
                    + transactionsService.getName(id)
                    + " ) " + transaction.getTransferAmount() + " with id = " + transaction.getIdentifier());
        }

    }

    void removeTransfer() {
        int idUser;
        System.out.println("Enter a user ID and a transfer ID");

        scanner.nextLine();

        String input = scanner.nextLine();
        String[] userIdAndTransferId = input.split(" ");

        if (userIdAndTransferId.length != 2) {
            throw new RuntimeException("Wrong numbers of arguments");
        }

        idUser = Integer.parseInt(userIdAndTransferId[0]);

        UUID uuidTransfer = UUID.fromString(userIdAndTransferId[1]);

        double transferAmount = transactionsService.getUserById(idUser).getTransferAmount(uuidTransfer);
        transactionsService.removeTransaction(uuidTransfer, idUser);

        System.out.println("Transfer To " + transactionsService.getName(idUser) +
                "(id = " + transactionsService.getUserById(idUser).getIdentifier() + ") "
                + transferAmount + " removed");
    }

    void checkTransferValidity() {
        System.out.println("Check results:");
        Transaction[] unpairedTransaction = transactionsService.arrayOfUnpairedTransactions();

        if (unpairedTransaction.length != 0) {
            for (int i = 0; i < unpairedTransaction.length; i++) {
                System.out.println(unpairedTransaction[i].getRecipient().getName() + " (id = " +
                        unpairedTransaction[i].getRecipient().getIdentifier() +
                        ") has an unacknowledged transfer id = " +
                        unpairedTransaction[i].getIdentifier() + " from " +
                        unpairedTransaction[i].getSender().getName() +
                        " (id = " + unpairedTransaction[i].getSender().getIdentifier() +
                        ") for " + unpairedTransaction[i].getTransferAmount()
                );
            }
        }
    }

    void finishTheProgram() {
        scanner.close();
        System.out.print("Dosvidosiki");
        System.exit(0);
    }


    void executingMenuOperationStandard(int numberOfOperation) {
        switch (numberOfOperation) {
            case 1:
                addUserAndBalance();
                break;
            case 2:
                viewUsersBalances();
                break;
            case 3:
                performTransfer();
                break;
            case 4:
                viewAllTransactionsForSpecificUser();
                break;
            case 5:
                finishTheProgram();
                break;
        }
    }

    void executingMenuOperationDev(int numberOfOperation) {
        switch (numberOfOperation) {
            case 1:
                addUserAndBalance();
                break;
            case 2:
                viewUsersBalances();
                break;
            case 3:
                performTransfer();
                break;
            case 4:
                viewAllTransactionsForSpecificUser();
                break;
            case 5:
                removeTransfer();
                break;
            case 6:
                checkTransferValidity();
                break;
            case 7:
                finishTheProgram();
                break;
        }
    }
}

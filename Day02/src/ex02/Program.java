package ex02;

import java.util.Scanner;

public class Program {

    public static void main(String[] args) {
        try {
            if (args.length != 1) {
                throw new IllegalArgumentException("Invalid number of arguments");
            }
            FileUtility fileUtility = new FileUtility(args[0]);

            while (true) {
                Scanner scanner = new Scanner(System.in);
                String readCommand = scanner.nextLine();
                if (readCommand.equals("exit")) {
                    System.exit(0);
                }
                fileUtility.readCommand(readCommand);
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }
}

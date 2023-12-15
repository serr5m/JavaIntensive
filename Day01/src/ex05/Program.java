package ex05;

public class Program {
    public static void main(String[] args) {
        Menu menu = new Menu();
        if (args.length != 0 && args[0].equals("profile=dev")) {
            menu.runProgram(true);
        } else {
            menu.runProgram(false);
        }

        for (int i = 0; i < args.length; i++) {
            System.out.println(args[i]);
        }
    }
}

package src.java.edu.school21.printer.app;

import src.java.edu.school21.printer.logic.ImageToConsole;

import java.io.File;

public class Program {
    public static void main(String[] args) {
        try {
            checkArguments(args);
//            File file =  new File("src/resources/it.bmp");

            ImageToConsole imageToConsole = new ImageToConsole(args[0].charAt(0), args[1].charAt(0), new File("src/resources/it.bmp"));
            imageToConsole.imageToConsole();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

    }

    private static void checkArguments(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Incorrect number of arguments");
        }
        if(args[0].length() != 1 || args[1].length() != 1) {
            throw new IllegalArgumentException("Argument 1 and argument 2 must be 1 character long");
        }
    }
}
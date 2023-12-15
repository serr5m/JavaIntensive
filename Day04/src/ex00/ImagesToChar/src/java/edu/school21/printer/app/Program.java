package src.java.edu.school21.printer.app;

import src.java.edu.school21.printer.logic.ImageToConsole;

import java.nio.file.Paths;

public class Program {
    public static void main(String[] args) {
        try {
            checkArguments(args);
            ImageToConsole imageToConsole = new ImageToConsole(args[0].charAt(0), args[1].charAt(0), Paths.get((args[2])));
            imageToConsole.imageToConsole();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

    }

    private static void checkArguments(String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException("Incorrect number of arguments");
        }
        if(args[0].length() != 1 || args[1].length() != 1) {
            throw new IllegalArgumentException("Argument 1 and argument 2 must be 1 character long");
        }
    }
}
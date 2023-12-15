package src.java.edu.school21.printer.app;

import com.beust.jcommander.JCommander;
import src.java.edu.school21.printer.logic.ImageToConsole;
import src.java.edu.school21.printer.logic.Parser;

public class Program {
    public static void main(String[] args) {
        Parser arguments = new Parser();
        JCommander jCommander = new JCommander(arguments);
        try {
            jCommander.parse(args);
            ImageToConsole imageToConsole = new ImageToConsole(arguments, "/resources/it.bmp");
            imageToConsole.print();
        } catch (NullPointerException e) {
            System.out.println("Wrong link or empty file");
        } catch (Exception e) {
            System.out.println("Illegal arg");
        }
    }
}

package ex00;

public class ParserArguments {
    String argument;
    private int count;
    public ParserArguments(String input) {
        this.argument = input;
    }

    public int count() {
        String[] arrayArguments = argument.split("=");
        if (arrayArguments.length != 2) {
            throw new IllegalArgumentException("Invalid number of arguments");
        }
        if (!arrayArguments[0].equals("--count")) {
            throw new IllegalArgumentException("Invalid first argument");
        }
        try {
            count = Integer.parseInt(arrayArguments[1]);
        } catch (NumberFormatException ex) {
            System.out.println("Invalid count");
        }
        return count;
    }

}

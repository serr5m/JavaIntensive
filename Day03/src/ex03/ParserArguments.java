package ex03;

public class ParserArguments {
    String[] input;
    private int countThreads;
    public ParserArguments(String[] args) {
        input = args;
    }


    public void parsingArguments() {

        if (input.length != 1) {
            throw new IllegalArgumentException("The number of arguments must be 1");
        }
        String[] split = input[0].split("=");

        if (split.length != 2) {
            throw new IllegalArgumentException("Invalid arguments");
        }

        if (split[0].startsWith("--threadsCount")) {
            countThreads = Integer.parseInt(split[1]);
        } else {
            throw new IllegalArgumentException("Invalid arguments");
        }
    }

    public int getCountThreads() {
        return countThreads;
    }
}

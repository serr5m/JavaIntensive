package ex02;

public class ParserArguments {
    private final String[] inputArguments;
    private int arraySize;
    private int threadsCount;

    public ParserArguments(String[] input) {
        this.inputArguments = input;
    }

    void parsingArguments() {
        if (inputArguments.length != 2) {
            throw new IllegalArgumentException("Invalid number of arguments");
        }

        for (String inputArgument : inputArguments) {
            String[] tmp = inputArgument.split("=");
            if (tmp[0].startsWith("--arraySize")) {
                arraySize = Integer.parseInt(tmp[1]);
                if (arraySize > 2_000_000) {
                    throw new IllegalArgumentException("Array size cant be grearet than 2_000_000");
                }
            } else if (tmp[0].startsWith("--threadsCount")) {
                threadsCount = Integer.parseInt(tmp[1]);
            }
        }
    }

    public int getArraySize() {
        return arraySize;
    }

    public int getThreadsCount() {
        return threadsCount;
    }
}

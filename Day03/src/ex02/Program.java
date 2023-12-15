package ex02;

public class Program {

    public static void main(String[] args) {

        int arraySize, threadsCount;

        try {
            ParserArguments parserArguments = new ParserArguments(args);
            parserArguments.parsingArguments();

            arraySize = parserArguments.getArraySize();
            threadsCount = parserArguments.getThreadsCount();

            ArrayGenerator arrayGenerator = new ArrayGenerator(arraySize);
            arrayGenerator.fillArray();
            int[] array = arrayGenerator.getArray();

            int sum = 0;
            for (int item : array) {
                sum += item;
            }
            System.out.println("Sum: " + sum);

            ThreadDistributor threadDistributor = new ThreadDistributor(array, arraySize, threadsCount);
            threadDistributor.distributeArrayAcrossThreads();
            threadDistributor.startThread();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

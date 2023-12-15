package ex00;

public class Program {

    public static void main(String[] args) {
        try{
            ParserArguments parserArguments = new ParserArguments(args[0]);
            int count = parserArguments.count();

            Thread henThread = new Thread(new HenThread(count));
            Thread eggThread = new Thread(new EggThread(count));
            henThread.start();
            eggThread.start();
            henThread.join();
            eggThread.join();
            for (int i = 0; i < count; i++) {
                System.out.println("Human");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}

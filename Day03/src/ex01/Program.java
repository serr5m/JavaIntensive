package ex01;

public class Program {
    public static void main(String[] args) {
        try {
            Object lock = new Object();
            int count = new ParserArguments(args[0]).count();
            Thread eggThread = new EggThread(count, lock);
            Thread henThread = new HenThread(count, lock);
            eggThread.start();
            henThread.start();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
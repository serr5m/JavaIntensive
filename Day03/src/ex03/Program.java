package ex03;

public class Program {
    public static void main(String[] args) {
        try {
            Loader loader = new Loader(args);
            loader.fileDownloader();
        } catch (InterruptedException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
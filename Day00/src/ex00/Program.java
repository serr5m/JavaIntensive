package ex00;

public class Program {
    public static void main(String[] args) {
        int a = 479598;
        int result = 0;
        while (a != 0) {
            int remainder  = a % 10;
            result +=remainder;
            a /= 10;
        }
        System.out.println(result);
    }
}

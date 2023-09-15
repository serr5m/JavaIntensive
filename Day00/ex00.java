package Day00;

public class ex00 {
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

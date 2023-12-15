package ex01;

public class Program {
    public static void main(String[] args) {
        User user1 = new User("Sava");
        User user2 = new User("Marat");
        User user4 = new User("Aboba");
        System.out.println(user1.getId()); // 1
        System.out.println(user4.getId()); // 3
        System.out.println(user2.getId()); // 2
    }

}

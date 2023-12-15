package ex02;

public class Program {
    public static void main(String[] args) {
        UsersArrayList u = new UsersArrayList();
        User user1 = new User("Danya");
        User user2 = new User("Sasha");
        u.addUser(user1);
        u.addUser(user2);



        try{
            System.out.println(u.getUserById(1).getName());
            System.out.println(u.getUserById(2).getName());
            System.out.println(u.getUserById(3).getName());
        } catch(UserNotFoundException ex) {
            System.out.println(ex.getMessage());
        }

        try {
            System.out.println(u.getUserByIndex(3).getName());
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println(ex.getMessage());
        }
    }
}

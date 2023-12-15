package ex00;
import java.util.UUID;
public class Program {
    public static void main(String[] args) {
        User user1 = new User(1, "Dil", 300);
        User user2 = new User(2, "Manu", 250);
        UUID uuid = UUID.randomUUID();
        Transaction trans = new Transaction(uuid, user1, user2, Transaction.TransferCategory.debit, 200);



        System.out.println(trans.getIdentifier());
        System.out.println(user1.getBalance());
        System.out.println(user2.getBalance());

    }
}
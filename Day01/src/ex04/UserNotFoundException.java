package ex04;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("There is no user with this ID");
    }
}
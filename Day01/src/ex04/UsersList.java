package ex04;

public interface UsersList {
    void addUser(User user);
    User getUserById(int id);
    User getUserByIndex(int index);
    int getNumbersOfUsers();

    User getUser(User user);

}

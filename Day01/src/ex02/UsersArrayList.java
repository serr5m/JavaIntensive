package ex02;

public class UsersArrayList implements UsersList {

    private int size = 10;
    private User[] users = new User[size];
    private int index = 0;
    private int numbersOfUsers = 0;

    @Override
    public void addUser(User user) {
        if (numbersOfUsers == size) {
           coppyArray();
        }
        users[index++] = user;
        numbersOfUsers++;
    }

    @Override
    public User getUserById(int id) {
        for(int i = 0; i < numbersOfUsers; ++i) {
            if (users[i].getId() == id) {
                return users[i];
            }
        }
        
        throw new UserNotFoundException();
    }

    @Override
    public User getUserByIndex(int index) {
        if(index >= numbersOfUsers || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Array out of bounds");
        }
        return users[index];
    }

    @Override
    public int getNumbersOfUsers() {
        return numbersOfUsers;
    }

    private void coppyArray() {

        User[] tmp = new User[size * 2];
        if (size >= 0) System.arraycopy(users, 0, tmp, 0, size);
        size *= 2;
        users = tmp;
    }
}

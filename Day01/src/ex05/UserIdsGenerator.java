package ex05;

public class UserIdsGenerator {
    private int currentId;
    private static UserIdsGenerator instance;

    private UserIdsGenerator() {
    }

    public static UserIdsGenerator getInstance() {
        if (instance == null) {
            instance = new UserIdsGenerator();
        }
        return instance;
    }

    public int getCurrentId() {
        return currentId;
    }

    public int generateId() {
        currentId += 1;
        return currentId;
    }


}

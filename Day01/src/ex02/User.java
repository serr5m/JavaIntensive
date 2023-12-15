package ex02;

import ex01.UserIdsGenerator;

public class User {
    private String name;
    private final int id;
    public User(String name) {
        this.name = name;
        this.id = UserIdsGenerator.getInstance().generateId();
    }
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

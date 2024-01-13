package edu.school21.chat.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {
    private final int userId;
    private String login;
    private String password;
    private List<Chatroom> listCreatedRooms;
    private List<Chatroom> listChatroomsSocializes;

    public User(int userId, String login, String password, ArrayList<Chatroom> listCreatedRooms, ArrayList<Chatroom> listChatroomsSocializes) {
        this.userId = userId;
        this.login = login;
        this.password = password;
        this.listCreatedRooms = listCreatedRooms;
        this.listChatroomsSocializes = listChatroomsSocializes;
    }


    public int getUserId() {
        return userId;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public List<Chatroom> getListCreatedRooms() {
        return listCreatedRooms;
    }

    public List<Chatroom> getListChatroomsSocializes() {
        return listChatroomsSocializes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId == user.userId && Objects.equals(login, user.login)
                && Objects.equals(password, user.password)
                && Objects.equals(listCreatedRooms, user.listCreatedRooms)
                && Objects.equals(listChatroomsSocializes, user.listChatroomsSocializes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, login, password, listCreatedRooms, listChatroomsSocializes);
    }

    @Override
    public String toString() {
        return "id=" + userId +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", createdRooms=" + listCreatedRooms +
                ", rooms=" + listChatroomsSocializes +
                "},";
    }
}

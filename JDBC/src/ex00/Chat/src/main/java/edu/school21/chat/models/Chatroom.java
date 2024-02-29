package edu.school21.chat.models;

import java.util.List;
import java.util.Objects;

public class Chatroom {
    private int chatroomId;
    private String name;
    private User owner;
    private List<String> messages;

    public Chatroom(int chatroomId, String name, User owner, List<String>messages) {
        this.chatroomId = chatroomId;
        this.name = name;
        this.owner = owner;
        this.messages = messages;
    }

    public int getChatroomId() {
        return chatroomId;
    }

    public String getName() {
        return name;
    }

    public User getOwner() {
        return owner;
    }

    public List<String> getMessages() {
        return messages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chatroom chatroom = (Chatroom) o;
        return chatroomId == chatroom.chatroomId && Objects.equals(name, chatroom.name) && Objects.equals(owner, chatroom.owner) && Objects.equals(messages, chatroom.messages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(chatroomId, name, owner, messages);
    }

    @Override
    public String toString() {
        return "Chatroom{" +
                "chatroomId=" + chatroomId +
                ", name='" + name + '\'' +
                ", owner=" + owner +
                ", messages=" + messages +
                '}';
    }
}

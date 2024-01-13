package edu.school21.chat.models;

import java.time.LocalDateTime;
import java.util.Objects;

public class Message {
   private int messageId;
    private User author;
    private Chatroom chatroom;
    private String text;
    private LocalDateTime date = LocalDateTime.now();


    public Message(int messageId, User author, Chatroom chatroom, String text) {
        this.messageId = messageId;
        this.author = author;
        this.chatroom = chatroom;
        this.text = text;
    }

    public int getMessageId() {
        return messageId;
    }

    public User getAuthor() {
        return author;
    }

    public Chatroom getChatroom() {
        return chatroom;
    }
    public String getText() {
        return text;
    }

    public LocalDateTime getDate() {
        return date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return messageId == message.messageId && Objects.equals(author, message.author)
                && Objects.equals(chatroom, message.chatroom)
                && Objects.equals(text, message.text)
                && Objects.equals(date, message.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(messageId, author, chatroom, text, date);
    }

    @Override
    public String toString() {
        return "Message{" +
                "messageId=" + messageId +
                ", author=" + author +
                ", chatroom=" + chatroom +
                ", text='" + text + '\'' +
                ", date=" + date +
                '}';
    }
}

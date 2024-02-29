package edu.school21.chat.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Message {
    private Long messageId;
    private User author;
    private Chatroom chatroom;
    private String text;
    private LocalDateTime date;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy HH:mm");

    public Message(Long messageId, User author, Chatroom chatroom, String text, LocalDateTime date) {
        this.messageId = messageId;
        this.author = author;
        this.chatroom = chatroom;
        this.text = text;
        this.date = date;
    }

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long id) {
        this.messageId = id;
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

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDateTime(LocalDateTime localDateTime) {
        date = localDateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Objects.equals(messageId, message.messageId) && Objects.equals(author, message.author)
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
        return "Message : {\n" +
                "id=" + messageId +
                "\nauthor={" + author +
                "\nroom={" + chatroom +
                "\ntext=\"" + text + '\"' +
                "\ndateTime=" + date.format(formatter) +
                "\n}";
    }
}

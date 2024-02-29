package edu.school21.chat.app;

import edu.school21.chat.exeptions.NotSavedSubEntityException;
import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;
import edu.school21.chat.repositories.DatabaseManager;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Program {
    public static void main(String[] args) {
        try {
            DatabaseManager databaseManager = new DatabaseManager();
            User creator = new User(4, "Olya", "maksavchik", new ArrayList<>(), new ArrayList<>());
//            User creator = new User(3, "Tema", "SHlumberge SHlumberge", new ArrayList<>(), new ArrayList<>());
            User author = creator;
            Chatroom chatroom = new Chatroom(6, "find_job", creator, new ArrayList<>());
            Message message = new Message(null, author, chatroom, "petushok ", LocalDateTime.now());
            MessagesRepository messagesRepository = new MessagesRepositoryJdbcImpl(databaseManager);
            messagesRepository.save(message);
            System.out.println("message ID " + message.getMessageId());
        } catch (NotSavedSubEntityException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

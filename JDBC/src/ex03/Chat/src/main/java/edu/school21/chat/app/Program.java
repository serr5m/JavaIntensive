package edu.school21.chat.app;

import edu.school21.chat.exeptions.NotSavedSubEntityException;
import edu.school21.chat.models.Message;
import edu.school21.chat.repositories.DatabaseManager;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;

import java.sql.SQLException;
import java.util.Optional;

public class Program {
    public static void main(String[] args) {
        try {
            DatabaseManager databaseManager = new DatabaseManager();
            MessagesRepository messagesRepository = new MessagesRepositoryJdbcImpl(databaseManager);
            Optional<Message> messageOptional = messagesRepository.findById(4L);
            if(messageOptional.isPresent()) {
                Message message = messageOptional.get();
                message.setText("derevyaga");
//                message.setDateTime(LocalDateTime.parse("2020-12-22 19:30:44", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                message.setDateTime(null);
                messagesRepository.update(message);
            }
        } catch (NotSavedSubEntityException | SQLException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

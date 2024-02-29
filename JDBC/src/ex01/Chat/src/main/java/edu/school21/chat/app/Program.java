package edu.school21.chat.app;

import com.zaxxer.hikari.pool.HikariPool;
import edu.school21.chat.models.Message;
import edu.school21.chat.repositories.DatabaseManager;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;

import java.sql.SQLException;
import java.util.Optional;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        System.out.println("Enter a message ID");
        try (Scanner scanner = new Scanner(System.in)) {
            Long number = scanner.nextLong();
            DatabaseManager databaseManager = new DatabaseManager();
            MessagesRepository messagesRepository = new MessagesRepositoryJdbcImpl(databaseManager);
            Optional<Message> message = messagesRepository.findById(number);

            if (message.isPresent()) {
                System.out.println(message.get().toString());
            } else {
                System.out.println("Message not found");
            }
        } catch (HikariPool.PoolInitializationException | SQLException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

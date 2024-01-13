package edu.school21.chat.repositories;

import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;

import java.sql.*;
import java.util.Optional;


public class MessagesRepositoryJdbcImpl implements MessagesRepository {
    private final DatabaseManager databaseManager;

    public MessagesRepositoryJdbcImpl(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }


    public Optional<Message> findById(Long id) throws SQLException {
        try (Connection connection = databaseManager.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM chat_schema.message WHERE id = ?")) {
                preparedStatement.setLong(1, id);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        Chatroom chatroom = findChatroomById(resultSet.getLong("room"), connection);
                        User user = findUserById(resultSet.getLong("author"), connection);
                        Message message = new Message(id, user, chatroom, resultSet.getString("content"),
                                resultSet.getTimestamp("message_date_time").toLocalDateTime());
                        return Optional.of(message);
                    }
                }
            }
        }
        return Optional.empty();
    }

    private User findUserById(Long id, Connection connection) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM chat_schema.chat_user WHERE id = ?")) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new User(resultSet.getInt("id"), resultSet.getString("login"),
                            resultSet.getString("password"), null, null);
                }
            }
        }
        return null;
    }

    private Chatroom findChatroomById(Long id, Connection connection) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM chat_schema.chat_room WHERE id = ?")) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new Chatroom(resultSet.getInt("id"), resultSet.getString("name"),
                            null, null);
                }
            }
        }
        return null;
    }
}

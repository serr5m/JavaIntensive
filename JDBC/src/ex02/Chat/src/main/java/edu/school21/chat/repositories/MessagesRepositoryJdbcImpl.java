package edu.school21.chat.repositories;

import edu.school21.chat.exeptions.NotSavedSubEntityException;
import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    @Override
    public void save(Message message) throws SQLException {
        try (Connection connection = databaseManager.getConnection()) {
            int id_author = message.getAuthor().getUserId();
            int id_room = message.getChatroom().getChatroomId();
            String content = message.getText();
            LocalDateTime messageDateTime = message.getDate();
            try (PreparedStatement preparedStatement = connection.prepareStatement("insert into chat_schema.message(author, room, content, message_date_time) values(?, ?, ?, ?)"
                    , Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setLong(1, id_author);
                preparedStatement.setLong(2, id_room);
                preparedStatement.setString(3, content);
                preparedStatement.setTimestamp(4, Timestamp.valueOf(messageDateTime
                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));
                int affectedRows = preparedStatement.executeUpdate();
                if (affectedRows > 0) {
                    ResultSet resultSet = preparedStatement.getGeneratedKeys();
                    if (resultSet.next()) {
                        long messageId = resultSet.getLong(1);
                        message.setMessageId(messageId);
                    } else {
                        throw new NotSavedSubEntityException("User or chatroom not found");
                    }
                }
            } catch (SQLException e) {
                throw new NotSavedSubEntityException("User or chatroom not found");
            }
        }
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

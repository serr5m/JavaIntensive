package edu.school21.chat.repositories;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseManager {
    private final String url = "jdbc:postgresql://localhost:5432/postgres";
    private final String user = "postgres";
    private final String password = "postgres";
    private HikariDataSource dataSource;
    private Connection connection;

    public DatabaseManager() throws SQLException {
            HikariConfig hikariConfig = new HikariConfig();
            hikariConfig.setJdbcUrl(url);
            hikariConfig.setUsername(user);
            hikariConfig.setPassword(password);
            dataSource = new HikariDataSource(hikariConfig);
            connection = dataSource.getConnection();
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeDataSource() {
        if (dataSource != null) {
            dataSource.close();
        }
    }
}

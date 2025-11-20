package com.miun.martinclass.db;

import java.sql.*;

public class DB {

    // https://www.baeldung.com/java-ssh-remote-mysql-db-connection
    private final Connection connection;

    public DB() {
        try {
            this.connection = DriverManager.getConnection(
                    "jdbc:h2:tcp://localhost:9092/~/martinclass",
                    "root", ""
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createDummyTable() {
        try {
            String createTableSQL = "CREATE TABLE test_table (id INT, data VARCHAR(255))";
            try (Statement statement = connection.createStatement()) {
                statement.execute(createTableSQL);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //https://www.geeksforgeeks.org/java/how-to-use-preparedstatement-in-java/
    public void addStuff(int id, String name) {
        try {
            try (PreparedStatement statement = connection.prepareStatement("insert into test_table (id, data) values (?, ?)")) {
                statement.setInt(1, id);
                statement.setString(2, name);
                statement.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String getName(int id) {
        try (PreparedStatement statement = connection.prepareStatement("select data from test_table where id = ?")) {
            statement.setInt(1, id);
            var result = statement.executeQuery();
            result.next();
            return result.getString(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}

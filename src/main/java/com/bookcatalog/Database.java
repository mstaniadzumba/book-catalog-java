package com.bookcatalog;

import com.bookcatalog.util.ConfigLoader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import com.bookcatalog.util.ConfigLoader;

public class Database {

    private static final String DB_URL = ConfigLoader.get("db.url");

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    public static void createBooksTable(){
        String sql = """
                CREATE TABLE IF NOT EXISTS books(
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            title TEXT NOT NULL,
            author TEXT NOT Null
            );
            """;

        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Table created successfully");
        } catch (SQLException e) {
            System.out.println( "Error creating books table: " + e.getMessage());
        }
    }

}

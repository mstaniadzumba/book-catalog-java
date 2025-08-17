package com.bookcatalog;

import com.bookcatalog.util.ConfigLoader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
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
             // Creates a Statement object which lets you run SQL
             Statement stmt = conn.createStatement()) {
            //runs the sql command stored in sql var
            stmt.execute(sql);
            System.out.println("Table created successfully");
        } catch (SQLException e) {
            System.out.println( "Error creating books table: " + e.getMessage());
        }
    }

    // Create (Insert) a book
        public static void addBook(Book book) {
            String sql = "INSERT INTO books(title, author) VALUES(?, ?)";

            try (Connection conn = connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setString(1, book.getTitle());
                pstmt.setString(2, book.getAuthor());

                pstmt.executeUpdate();
                System.out.println("Book added: " + book);
            } catch (SQLException e) {
                System.out.println("Error adding book: " + e.getMessage());
            }
        }

        // Read (Select) all books
        public static List<Book> getAllBooks() {
            List<Book> books = new ArrayList<>();
            String sql = "SELECT id, title, author FROM books";

            try (Connection conn = connect();
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {

                while (rs.next()) {
                    Book book = new Book(rs.getInt("id"),
                            rs.getString("title"),
                            rs.getString("author"));
                    books.add(book);
                }
            } catch (SQLException e) {
                System.out.println("Error retrieving books: " + e.getMessage());
            }
            return books;
        }

        // Update a book by id
        public static void updateBook(Book book) {
            String sql = "UPDATE books SET title = ?, author = ? WHERE id = ?";

            try (Connection conn = connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setString(1, book.getTitle());
                pstmt.setString(2, book.getAuthor());
                pstmt.setInt(3, book.getId());

                int affected = pstmt.executeUpdate();
                if (affected > 0) {
                    System.out.println("Book updated: " + book);
                } else {
                    System.out.println("No book found with id " + book.getId());
                }
            } catch (SQLException e) {
                System.out.println("Error updating book: " + e.getMessage());
            }
        }

        // Delete a book by id
        public static void deleteBook(int id) {
            String sql = "DELETE FROM books WHERE id = ?";

            try (Connection conn = connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setInt(1, id);

                int affected = pstmt.executeUpdate();
                if (affected > 0) {
                    System.out.println("Book deleted with id " + id);
                } else {
                    System.out.println("No book found with id " + id);
                }
            } catch (SQLException e) {
                System.out.println("Error deleting book: " + e.getMessage());
            }
        }

        // Clear all books from the table (for testing purposes)
        public static void clearBooksTable() {
            String sql = "DELETE FROM books";

            try (Connection conn = connect();
                 Statement stmt = conn.createStatement()) {
                stmt.executeUpdate(sql);
                System.out.println("All books cleared from table");
            } catch (SQLException e) {
                System.out.println("Error clearing books table: " + e.getMessage());
            }
        }
    }


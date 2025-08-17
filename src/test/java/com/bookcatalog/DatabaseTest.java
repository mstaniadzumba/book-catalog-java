package com.bookcatalog;

import org.junit.jupiter.api.*;
import java.sql.*;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DatabaseTest {

    @BeforeAll
    public static void setup() {
        Database.createBooksTable();
    }

    @BeforeEach
    public void cleanup() {
        //Clear the table before each test instead of recreating it
        Database.clearBooksTable();
    }


   @Test
   @Order(1)
   public void testAddBook(){
       Book book = new Book("Test Driven Development", "Tania");
       Database.addBook(book);

       List<Book> books = Database.getAllBooks();
       assertFalse(books.isEmpty(), "Books list should not be empty");

       Book first = books.getFirst();
       assertEquals("Test Driven Development", first.getTitle());
       assertEquals("Tania", first.getAuthor());
   }

   @Test
   @Order(2)
   public void testUpdateBook(){
       //First add a book
       Book book = new Book("Original Title", "Original Author");
       Database.addBook(book);
       
       //Get the book to get its ID
       List<Book> books = Database.getAllBooks();
       Book addedBook = books.getFirst();
       int bookId = addedBook.getId();
       
       //Now update it
       Book updatedBook = new Book(bookId, "TDD By example", "Tania");
       Database.updateBook(updatedBook);

       //Verify the update
       List<Book> updatedBooks = Database.getAllBooks();
       Book first = updatedBooks.getFirst();
       assertEquals("TDD By example", first.getTitle());
   }

   @Test
   @Order(3)
   public void testDeleteBook(){
       //First add a book
       Book book = new Book("Book to Delete", "Author");
       Database.addBook(book);
       
       //Get the book to get its ID
       List<Book> books = Database.getAllBooks();
       Book addedBook = books.getFirst();
       int bookId = addedBook.getId();
       
       //Now delete it
       Database.deleteBook(bookId);

       //Verify it's deleted
       List<Book> remainingBooks = Database.getAllBooks();
       assertTrue(remainingBooks.isEmpty(), "Books list should be empty");
   }


    @Test
    public void testConnect() throws SQLException{
        Database db = new Database();
        Connection conn = db.connect();
        assertNotNull(conn);
        conn.close();
    }


}

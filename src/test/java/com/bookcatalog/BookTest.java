package com.bookcatalog;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BookTest{
    @Test
    public void testBookToString(){
        Book book = new Book("Java for dummies", "Tania");
        assertEquals("Java for dummies by Tania", book.toString());
    }
}
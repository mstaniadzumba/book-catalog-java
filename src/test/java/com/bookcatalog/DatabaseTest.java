package com.bookcatalog;

import org.junit.jupiter.api.*;
import java.sql.*;
import static org.junit.jupiter.api.Assertions.*;


public class DatabaseTest {
    @Test
    public void testConnect() throws SQLException{
        Database db = new Database();
        Connection conn = db.connect();
        assertNotNull(conn);
        conn.close();
    }
}

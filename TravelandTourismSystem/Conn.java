package TravelandTourismSystem;

import java.sql.*;
public class Conn {
    Connection c;
    Statement s;

    public Conn() {
        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the connection (Update database name, username, and password)
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/travel", "root", "2025");

            // Create a statement object to execute queries
            s = c.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

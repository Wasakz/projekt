package library;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;

public class Main {

    public static void main(String[] args) throws SQLException {

        System.out.println();
       Connection connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/workdb", "SA", "");
    }
}

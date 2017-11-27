package library;

import library.dao.uow.IUnitOfWork;
import library.dao.uow.UnitOfWork;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;

public class Main {

    public static void main(String[] args) throws SQLException {


       Connection connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/workdb", "SA", "");
        IUnitOfWork uow = new UnitOfWork(connection);
    }
}

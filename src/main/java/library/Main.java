package library;

import library.dao.repositories.IDatabaseCatalog;
import library.dao.uow.IUnitOfWork;
import library.dao.uow.UnitOfWork;
import library.dao.repositories.impl.DatabaseCatalog;
import library.examples.*;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;

public class Main{

    public static void main(String[] args) throws SQLException {


       Connection connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/workdb", "SA", "");
        IUnitOfWork uow = new UnitOfWork(connection);
        IDatabaseCatalog catalog = new DatabaseCatalog(connection, uow);

        AdressRepositoryExample.execute(connection, catalog);
        uow.saveChanges();
        connection.close();

        System.out.println("End");
        connection.close();

    }
}

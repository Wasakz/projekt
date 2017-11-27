package library.dao.repositories.impl;

import library.dao.mappers.AdressMapper;
import library.dao.repositories.IAdressRepository;
import library.dao.repositories.IDatabaseCatalog;
import library.dao.repositories.IStudentRepository;
import library.dao.uow.IUnitOfWork;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseCatalog implements IDatabaseCatalog {
    private Connection connection;
    IUnitOfWork uow;
    public DatabaseCatalog(java.sql.Connection connection, IUnitOfWork uow){
        this.connection = connection;
        this.uow = uow;
    }

    public IAdressRepository adresses() {
        try {
            return new AdressRepository(connection, new AdressMapper(), uow);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public IStudentRepository students(){
        // TODO Auto-generated method stub
        return null;
    }

    public void saveChanges() {
        uow.saveChanges();

    }
}

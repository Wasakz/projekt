package library.dao.repositories.impl;

import library.dao.mappers.IMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import library.dao.mappers.IMapper;
import library.dao.mappers.AdressMapper;
import library.domain.Adress;



public class AdressRepository {
    protected PreparedStatement selectByCity;
    protected PreparedStatement selectByPostal;
    protected PreparedStatement selectByStreet;

    private void selectStatements(Connection connection) throws SQLException {
        selectByCity = connection.prepareStatement( "SELECT * FROM adress WHERE city=?");
        selectByPostal = connection.prepareStatement( "SELECT * FROM adress WHERE postalCode=?");
        selectByStreet = connection.prepareStatement( "SELECT * FROM adress WHERE street=?");
    }
}

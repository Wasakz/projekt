package library.repositories;

import java.sql.SQLException;

public interface IDatabaseCatalog {
    public IStudentRepository students();
    public IAdressRepository adresses();

    public void saveChanges();

}

package library.dao.repositories;

public interface IDatabaseCatalog {
    public IStudentRepository students();
    public IAdressRepository adresses();

    public void saveChanges();

}

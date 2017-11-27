package library.dao.repositories;

import java.util.List;
import library.domain.Adress;

public interface IAdressRepository extends IRepository<Adress> {

    public List<Adress> withCity(String city);
    public List<Adress> withPostal(String postal);
    public List<Adress> withStreet(String street);
}

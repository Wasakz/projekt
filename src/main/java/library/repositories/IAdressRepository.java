package library.repositories;

import java.util.List;
import library.domain.Adress;

public interface IAdressRepository {

    public List<Adress> withCity(String city);
    public List<Adress> withPostal(String postal);
    public List<Adress> withStreet(String street);
}

package library.examples;

import library.dao.repositories.IDatabaseCatalog;

import java.sql.Connection;
import java.util.List;
import library.domain.Adress;
import library.dao.repositories.IDatabaseCatalog;

public class AdressRepositoryExample {
    public static void execute(Connection connection, IDatabaseCatalog catalog){
        catalog.adresses().createTable();
        Adress adress = new Adress("Gdansk", "34-123", "Brzegi", "55");
        catalog.adresses().add(adress);
        catalog.adresses().add(adress);
        catalog.adresses().add(adress);

        System.out.println("Count: "+ catalog.adresses().count());
        System.out.println("last id: "+ catalog.adresses().lastId());

        List<Adress> adressWithCity = catalog.adresses().withCity("Gdansk");
        List<Adress> adressWithPostal = catalog.adresses().withPostal("80-041");

        List<Adress> adresses = catalog.adresses().getPage(1, 2);

        for(Adress a: adresses){
            System.out.println(a.getId());
        }

        Adress toDelete = adresses.get(0);
        catalog.adresses().delete(toDelete);

        Adress updateName = adresses.get(1);
        updateName.setCity("Radom");

        catalog.adresses().update(updateName);
    }
}

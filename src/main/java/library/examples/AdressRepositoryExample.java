package library.examples;

import library.dao.repositories.IDatabaseCatalog;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import library.domain.Adress;
import library.dao.repositories.IDatabaseCatalog;

public class AdressRepositoryExample {
    public static void execute(Connection connection, IDatabaseCatalog catalog) throws SQLException {
        catalog.adresses().createTable();
        Adress adress = new Adress("Gdansk", "34-123", "Brzegi", "55");
        Adress address2 = new Adress("Gdynia", "81-352", "Swietojanska", "25");
        Adress adress3 = new Adress("Gdynia", "81-532", "Wladyslawa", "16");
        catalog.adresses().add(adress);
        catalog.adresses().add(address2);
        catalog.adresses().add(adress3);
        catalog.saveChanges();

        System.out.println("Count: " + catalog.adresses().count());
        System.out.println("Last id: " + catalog.adresses().lastId());
        catalog.adresses().withCity("Gdynia");
    }



/*    void sort() {
            Collections.sort(catalog.adresses().withCity("Gdynia"), new Comparator<Adress>() {
                @Override
                public int compare(Adress o1, Adress o2) {
                    return o1.getApNumber().compareTo(o2.getApNumber());
                }
            });
        }*/


}

package library.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import library.domain.Adress;

public class AdressMapper implements IMapper<Adress>  {
    public Adress map(ResultSet rs) throws SQLException {
        Adress adress = new Adress();
        adress.setId(rs.getInt("id"));
        adress.setCity(rs.getString("city"));
        adress.setPostalCode(rs.getString("postalCode"));
        adress.setStreet(rs.getString("street"));
        adress.setApNumber(rs.getString("apNumber"));
        return adress;
    }
}

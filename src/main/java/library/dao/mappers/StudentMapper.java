package library.dao.mappers;
import library.domain.Student;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentMapper implements IMapper<Student> {
    public Student map(ResultSet rs) throws SQLException {
        Student student = new Student();
        student.setId(rs.getInt("id"));
        student.setFirstName(rs.getString("firstName"));
        student.setLastName(rs.getString("lastName"));
        student.setPesel(rs.getString("pesel"));
        student.setPhoneNumber(rs.getString("phoneNumber"));
        student.setGender(rs.getString("gender"));
        return student;
    }
}

package library.repositories;

import java.util.List;
import library.domain.Student;

public interface IStudentRepository {

    public List<Student> withFirstName(String name);
    public List<Student> withLastName(String surname);
    public List<Student> withPesel(String pesel);
    public List<Student> withPhoneNumber(String phoneNumber);

}

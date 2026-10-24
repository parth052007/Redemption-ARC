
package studentmanagement;

import java.util.ArrayList;
import java.util.List;

public class StudentManager {
    private final List<Student> list = new ArrayList<>();

    public void addStudent(Student student) throws ValidationException {
        if (student.getRollno() <= 0) {
            throw new ValidationException("Roll number must be positive");
        }

        if (student.getName() == null || student.getName().isBlank()) {
            throw new ValidationException("Name cannot be empty");
        }

        if (!Double.isFinite(student.getMarks())
                || student.getMarks() < 0
                || student.getMarks() > 100) {
            throw new ValidationException("Marks must be between 0 and 100");
        }

        for (Student existing : list) {
            if (existing.getRollno() == student.getRollno()) {
                throw new ValidationException("Roll number already exists");
            }
        }

        list.add(student);
    }

    public List<Student> getAllStudents() {
        return new ArrayList<>(list);
    }
}

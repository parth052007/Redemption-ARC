
package studentmanagement;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FileService {
    private final Path path = Path.of("students.txt");

    public void saveStudents(List<Student> students) throws IOException {
        List<String> lines = new ArrayList<>();

        for (Student student : students) {
            String line = student.getRollno() + ","
                    + student.getName() + ","
                    + student.getMarks();
            lines.add(line);
        }

        Files.write(path, lines, StandardCharsets.UTF_8);
    }

    public List<Student> loadStudents() throws IOException {
        List<Student> students = new ArrayList<>();

        if (Files.notExists(path)) {
            return students;
        }

        List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);

        for (String line : lines) {
            if (line.isBlank()) {
                continue;
            }

            String[] data = line.split(",", -1);

            if (data.length != 3) {
                continue;
            }

            try {
                int rollno = Integer.parseInt(data[0].trim());
                String name = data[1].trim();
                double marks = Double.parseDouble(data[2].trim());

                if (rollno <= 0 || name.isBlank()
                        || !Double.isFinite(marks)
                        || marks < 0 || marks > 100) {
                    continue;
                }

                students.add(new Student(rollno, name, marks));
            } catch (NumberFormatException e) {
                System.out.println("Skipping invalid student record");
            }
        }

        return students;
    }
}

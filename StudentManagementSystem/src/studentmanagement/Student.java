
package studentmanagement;

public class Student {
    private int rollno;
    private String name;
    private double marks;

    public Student(int rollno, String name, double marks) {
        this.rollno = rollno;
        this.name = name;
        this.marks = marks;
    }

    public String getName() {
        return name;
    }

    public int getRollno() {
        return rollno;
    }

    public double getMarks() {
        return marks;
    }
}

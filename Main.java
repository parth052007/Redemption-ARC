class constructor {

    int age;
    String name;

    constructor(int age, String name) {
        this.age = age;
        this.name = name;
    }

    void display() {
        System.out.println("Age: " + age);
        System.out.println("Name: " + name);
    }
}

class Student extends constructor {

    int rollno;
    String coursename;

    Student(int rollno, String coursename, int age, String name) {

        super(age, name);

        this.rollno = rollno;
        this.coursename = coursename;
    }

    void finalDisplay() {

        display();

        System.out.println("Roll No: " + rollno);
        System.out.println("Course Name: " + coursename);
    }
}

public class Main {

    public static void main(String[] args) {

        Student s1 = new Student(101, "Java", 30, "Parth");

        s1.finalDisplay();
    }
}
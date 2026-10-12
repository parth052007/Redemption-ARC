#include <iostream>
using namespace std;

class Student {
public:
    string name;
    int age;
    float marks;

    void input() {
        cout << "Enter student name: ";
        cin >> name;

        cout << "Enter age: ";
        cin >> age;

        cout << "Enter marks: ";
        cin >> marks;
    }

    void display() {
        cout << "\n--- Student Details ---" << endl;
        cout << "Name: " << name << endl;
        cout << "Age: " << age << endl;
        cout << "Marks: " << marks << endl;

        if (marks >= 40) {
            cout << "Result: PASS" << endl;
        } else {
            cout << "Result: FAIL" << endl;
        }
    }
};

int main() {
    Student s;

    cout << "===== STUDENT MANAGEMENT SYSTEM =====" << endl;

    s.input();
    s.display();

    return 0;
}
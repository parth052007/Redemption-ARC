
package studentmanagement;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class StudentFrame extends JFrame {

    private final Color background = new Color(243, 249, 253);
    private final Color lightBlue = new Color(218, 239, 250);
    private final Color primaryBlue = new Color(66, 153, 205);
    private final Color darkBlue = new Color(35, 75, 105);
    private final Color white = Color.WHITE;

    private final JTextField rollField = new JTextField();
    private final JTextField nameField = new JTextField();
    private final JTextField marksField = new JTextField();
    private final JTextField searchField = new JTextField();

    private final DefaultTableModel tableModel =
            new DefaultTableModel(
                    new String[]{"Roll No", "Student Name", "Marks"}, 0) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

    private final JTable studentTable = new JTable(tableModel);
    private final StudentManager studentManager = new StudentManager();
    private final FileService fileService = new FileService();

    private JButton addButton;
    private JButton clearButton;
    private int editingRollNo = -1;

    public StudentFrame() {
        setTitle("Student Management System");
        setSize(900, 650);
        setMinimumSize(new Dimension(750, 550));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(background);
        setLayout(new BorderLayout(0, 15));

        add(createHeader(), BorderLayout.NORTH);

        JPanel mainPanel = new JPanel(new BorderLayout(0, 15));
        mainPanel.setBackground(background);
        mainPanel.setBorder(new EmptyBorder(0, 20, 10, 20));

        mainPanel.add(createFormPanel(), BorderLayout.NORTH);
        mainPanel.add(createTablePanel(), BorderLayout.CENTER);

        add(mainPanel, BorderLayout.CENTER);
        add(createFooter(), BorderLayout.SOUTH);

        loadStudents();
        setVisible(true);
    }

    private JPanel createHeader() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(lightBlue);
        panel.setBorder(new EmptyBorder(22, 25, 22, 25));

        JLabel title = new JLabel("Student Management System");
        title.setFont(new Font("Segoe UI", Font.BOLD, 26));
        title.setForeground(darkBlue);

        JLabel subtitle = new JLabel(
                "Manage student records in one place");
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subtitle.setForeground(darkBlue);

        JPanel textPanel = new JPanel(new GridLayout(2, 1, 0, 6));
        textPanel.setOpaque(false);
        textPanel.add(title);
        textPanel.add(subtitle);

        panel.add(textPanel, BorderLayout.WEST);
        return panel;
    }

    private JPanel createFormPanel() {
        JPanel card = createCard();
        card.setLayout(new BorderLayout(0, 15));

        JLabel heading = new JLabel("Add Student");
        heading.setFont(new Font("Segoe UI", Font.BOLD, 19));
        heading.setForeground(darkBlue);
        card.add(heading, BorderLayout.NORTH);

        JPanel fields = new JPanel(new GridLayout(2, 3, 15, 8));
        fields.setOpaque(false);

        fields.add(createLabel("Roll Number"));
        fields.add(createLabel("Student Name"));
        fields.add(createLabel("Marks (0-100)"));

        styleField(rollField, "Enter roll number");
        styleField(nameField, "Enter student name");
        styleField(marksField, "Enter marks");

        fields.add(rollField);
        fields.add(nameField);
        fields.add(marksField);

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        buttons.setOpaque(false);

        addButton = createButton("Add Student", primaryBlue, white);
        clearButton = createButton("Clear", lightBlue, darkBlue);

        addButton.addActionListener(e -> addStudent());
        clearButton.addActionListener(e -> clearFields());

        buttons.add(addButton);
        buttons.add(clearButton);

        JPanel content = new JPanel(new BorderLayout(0, 15));
        content.setOpaque(false);
        content.add(fields, BorderLayout.CENTER);
        content.add(buttons, BorderLayout.SOUTH);

        card.add(content, BorderLayout.CENTER);
        return card;
    }

    private JPanel createTablePanel() {
        JPanel card = createCard();
        card.setLayout(new BorderLayout(0, 15));

        JPanel top = new JPanel(new BorderLayout(10, 0));
        top.setOpaque(false);

        JLabel heading = new JLabel("Student Records");
        heading.setFont(new Font("Segoe UI", Font.BOLD, 19));
        heading.setForeground(darkBlue);

        styleField(searchField, "Search by name or roll number");
        searchField.setPreferredSize(new Dimension(250, 36));

        searchField.getDocument().addDocumentListener(
                new javax.swing.event.DocumentListener() {
                    public void insertUpdate(
                            javax.swing.event.DocumentEvent e) {
                        filterStudents();
                    }

                    public void removeUpdate(
                            javax.swing.event.DocumentEvent e) {
                        filterStudents();
                    }

                    public void changedUpdate(
                            javax.swing.event.DocumentEvent e) {
                        filterStudents();
                    }
                });

        top.add(heading, BorderLayout.WEST);
        top.add(searchField, BorderLayout.EAST);

        studentTable.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        studentTable.setRowHeight(34);
        studentTable.setForeground(darkBlue);
        studentTable.setBackground(white);
        studentTable.setSelectionBackground(lightBlue);
        studentTable.setSelectionForeground(darkBlue);
        studentTable.setGridColor(new Color(225, 235, 242));
        studentTable.setShowVerticalLines(false);
        studentTable.setFillsViewportHeight(true);

        studentTable.getTableHeader().setFont(
                new Font("Segoe UI", Font.BOLD, 14));
        studentTable.getTableHeader().setBackground(lightBlue);
        studentTable.getTableHeader().setForeground(darkBlue);
        studentTable.getTableHeader().setPreferredSize(
                new Dimension(0, 38));

        JScrollPane scrollPane = new JScrollPane(studentTable);
        scrollPane.setBorder(BorderFactory.createLineBorder(
                new Color(220, 233, 242)));

        JButton editButton = createButton("Edit Selected", primaryBlue, white);
        JButton deleteButton = createButton(
                "Delete Selected", new Color(220, 105, 105), white);

        editButton.addActionListener(e -> editSelectedStudent());
        deleteButton.addActionListener(e -> deleteSelectedStudent());

        JPanel actions = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        actions.setOpaque(false);
        actions.add(editButton);
        actions.add(deleteButton);

        card.add(top, BorderLayout.NORTH);
        card.add(scrollPane, BorderLayout.CENTER);
        card.add(actions, BorderLayout.SOUTH);

        return card;
    }

    private JPanel createFooter() {
        JPanel footer = new JPanel(new BorderLayout());
        footer.setBackground(background);
        footer.setBorder(new EmptyBorder(0, 22, 12, 22));

        JLabel status = new JLabel("Ready");
        status.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        status.setForeground(darkBlue);

        JLabel storage = new JLabel("Local storage: students.txt");
        storage.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        storage.setForeground(darkBlue);

        footer.add(status, BorderLayout.WEST);
        footer.add(storage, BorderLayout.EAST);
        return footer;
    }

    private JPanel createCard() {
        JPanel panel = new JPanel();
        panel.setBackground(white);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(
                        new Color(220, 233, 242)),
                new EmptyBorder(18, 18, 18, 18)));
        return panel;
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        label.setForeground(darkBlue);
        return label;
    }

    private void styleField(JTextField field, String placeholder) {
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setForeground(darkBlue);
        field.setBackground(white);
        field.setCaretColor(primaryBlue);
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(
                        new Color(205, 222, 235)),
                new EmptyBorder(8, 10, 8, 10)));
        field.setToolTipText(placeholder);
    }

    private JButton createButton(
            String text, Color bg, Color fg) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 13));
        button.setForeground(fg);
        button.setBackground(bg);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(bg),
                new EmptyBorder(9, 16, 9, 16)));
        button.setOpaque(true);
        return button;
    }

    private void addStudent() {
        try {
            int rollno = Integer.parseInt(rollField.getText().trim());
            String name = nameField.getText().trim();
            double marks = Double.parseDouble(marksField.getText().trim());

            Student student = new Student(rollno, name, marks);

            if (editingRollNo != -1) {
                updateStudent(student);
                return;
            }

            studentManager.addStudent(student);

            try {
                fileService.saveStudents(studentManager.getAllStudents());
            } catch (IOException e) {
                studentManager.getAllStudents().removeIf(
                        s -> s.getRollno() == rollno);
                throw e;
            }

            refreshTable();
            clearFields();

            JOptionPane.showMessageDialog(
                    this, "Student added successfully!",
                    "Success", JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException e) {
            showError("Enter a valid roll number and marks.");
        } catch (ValidationException | IOException e) {
            showError(e.getMessage());
        }
    }

    private void updateStudent(Student updated) {
        List<Student> students = studentManager.getAllStudents();
        Student old = null;

        for (Student student : students) {
            if (student.getRollno() == editingRollNo) {
                old = student;
                break;
            }
        }

        if (old == null) {
            showError("Student not found.");
            return;
        }

        if (updated.getRollno() != editingRollNo) {
            for (Student student : students) {
                if (student.getRollno() == updated.getRollno()) {
                    showError("Roll number already exists.");
                    return;
                }
            }
        }

        try {
            if (updated.getRollno() <= 0
                    || updated.getName().isBlank()
                    || !Double.isFinite(updated.getMarks())
                    || updated.getMarks() < 0
                    || updated.getMarks() > 100) {
                showError("Enter valid student details.");
                return;
            }

            students.remove(old);
            students.add(updated);

            try {
                fileService.saveStudents(students);
            } catch (IOException e) {
                students.remove(updated);
                students.add(old);
                showError("Unable to save changes: " + e.getMessage());
                return;
            }

            refreshTable();
            clearFields();

            JOptionPane.showMessageDialog(
                    this, "Student updated successfully!");

        } catch (Exception e) {
            showError(e.getMessage());
        }
    }

    private void editSelectedStudent() {
        int row = studentTable.getSelectedRow();

        if (row == -1) {
            showError("Select a student to edit.");
            return;
        }

        int modelRow = studentTable.convertRowIndexToModel(row);
        int rollno = (int) tableModel.getValueAt(modelRow, 0);
        String name = tableModel.getValueAt(modelRow, 1).toString();
        double marks = Double.parseDouble(
                tableModel.getValueAt(modelRow, 2).toString());

        editingRollNo = rollno;
        rollField.setText(String.valueOf(rollno));
        nameField.setText(name);
        marksField.setText(String.valueOf(marks));
        addButton.setText("Update Student");
    }

    private void deleteSelectedStudent() {
        int row = studentTable.getSelectedRow();

        if (row == -1) {
            showError("Select a student to delete.");
            return;
        }

        int answer = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to delete this student?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION);

        if (answer != JOptionPane.YES_OPTION) {
            return;
        }

        int modelRow = studentTable.convertRowIndexToModel(row);
        int rollno = (int) tableModel.getValueAt(modelRow, 0);

        List<Student> students = studentManager.getAllStudents();
        Student removed = null;

        for (Student student : students) {
            if (student.getRollno() == rollno) {
                removed = student;
                break;
            }
        }

        if (removed != null) {
            students.remove(removed);

            try {
                fileService.saveStudents(students);
                refreshTable();
                clearFields();
            } catch (IOException e) {
                students.add(removed);
                showError("Unable to save deletion: " + e.getMessage());
            }
        }
    }

    private void loadStudents() {
        try {
            List<Student> loaded = fileService.loadStudents();

            for (Student student : loaded) {
                try {
                    studentManager.addStudent(student);
                } catch (ValidationException e) {
                    System.out.println(e.getMessage());
                }
            }

            refreshTable();

        } catch (IOException e) {
            showError("Unable to load students: " + e.getMessage());
        }
    }

    private void refreshTable() {
        tableModel.setRowCount(0);

        for (Student student : studentManager.getAllStudents()) {
            tableModel.addRow(new Object[]{
                    student.getRollno(),
                    student.getName(),
                    student.getMarks()
            });
        }

        filterStudents();
    }

    private void filterStudents() {
        String query = searchField.getText().trim().toLowerCase();
        tableModel.setRowCount(0);

        for (Student student : studentManager.getAllStudents()) {
            if (String.valueOf(student.getRollno()).contains(query)
                    || student.getName().toLowerCase().contains(query)) {
                tableModel.addRow(new Object[]{
                        student.getRollno(),
                        student.getName(),
                        student.getMarks()
                });
            }
        }
    }

    private void clearFields() {
        rollField.setText("");
        nameField.setText("");
        marksField.setText("");
        editingRollNo = -1;
        addButton.setText("Add Student");
        studentTable.clearSelection();
        rollField.requestFocusInWindow();
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(
                this, message, "Notice", JOptionPane.ERROR_MESSAGE);
    }
}

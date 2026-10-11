import javax.swing.*;
import java.awt.*;

public class PaymentGUI extends JFrame {

    private JTextField nameField;
    private JTextField amountField;
    private JComboBox<String> paymentBox;
    private JTextArea resultArea;

    public PaymentGUI() {

        setTitle("Payment Management System");
        setSize(450, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 15));
        panel.setBorder(BorderFactory.createEmptyBorder(25, 25, 20, 25));

        JLabel nameLabel = new JLabel("Customer Name:");
        nameField = new JTextField();

        JLabel amountLabel = new JLabel("Amount (Rs.):");
        amountField = new JTextField();

        JLabel methodLabel = new JLabel("Payment Method:");
        paymentBox = new JComboBox<>(
            new String[]{"UPI", "Card"}
        );

        JButton payButton = new JButton("Process Payment");
        JButton clearButton = new JButton("Clear");

        panel.add(nameLabel);
        panel.add(nameField);

        panel.add(amountLabel);
        panel.add(amountField);

        panel.add(methodLabel);
        panel.add(paymentBox);

        panel.add(payButton);
        panel.add(clearButton);

        resultArea = new JTextArea();
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Arial", Font.PLAIN, 14));

        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setBorder(
            BorderFactory.createTitledBorder("Payment Details")
        );

        add(panel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        payButton.addActionListener(e -> processPayment());

        clearButton.addActionListener(e -> {
            nameField.setText("");
            amountField.setText("");
            resultArea.setText("");
        });
    }

    private void processPayment() {

        String name = nameField.getText().trim();
        String amountText = amountField.getText().trim();
        String method = (String) paymentBox.getSelectedItem();

        if (name.isEmpty() || amountText.isEmpty()) {
            JOptionPane.showMessageDialog(
                this,
                "Please enter customer name and amount."
            );
            return;
        }

        try {

            double amount = Double.parseDouble(amountText);

            if (!Double.isFinite(amount) || amount <= 0) {
                JOptionPane.showMessageDialog(
                    this,
                    "Enter a valid amount greater than zero."
                );
                return;
            }

            payment p;

            if ("UPI".equals(method)) {
                p = new upi(name);
            } else {
                p = new CardPayment(name);
            }

            PaymentMethod selectedMethod = (PaymentMethod) p;

            selectedMethod.processpayment(amount);

            resultArea.setText(
                "Customer Name: " + name +
                "\nPayment Method: " + method +
                "\nAmount: Rs. " + amount +
                "\nStatus: Demo payment processed"
            );

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                this,
                "Please enter a valid numeric amount."
            );
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new PaymentGUI().setVisible(true);
        });
    }
}